<!-- $Id: header.jsp,v 1.6 2015/03/18 14:19:51 a0199948 Exp $ -->
<%@ page import="java.util.*" %>
<style>
  .right1 {float: right;}
  .left1 {float: left;}

  a.hdrtoplink {
    color: #9b9b9b;
    text-decoration: underline;
  }
  a.hdrtoplink:hover  {
    color: #ff0000;
  }
</style>
<div class="left1">
<a href="http://www.ti.com/hdr_home"><img src="resources/css/hdr_ti_logo.gif" alt="Texas Instruments"></a>
</div> <!-- End of logo div -->
<div class="right1">
      <br/>
<%
  // Get a sorted list of global menu keys
  Set<String> keySet = new TreeSet<String>();
  for (Enumeration<?> e = request.getAttributeNames(); e.hasMoreElements(); ) {
    String prop = (String)e.nextElement();
    if (prop.matches("global\\.menu\\.(\\d+\\.?)*\\.name")) {
      keySet.add(prop.substring(0, prop.length()-5));
    }
  }
  for (String key : keySet) {
    String name = (String)request.getAttribute(key+".name");
    String url = (String)request.getAttribute(key+".url");
    out.write("      <a href=\""+url+"\" class=\"hdrtoplink\">"+name+"</a>&nbsp;|&nbsp;\n");
  }
%>
      <a href="${pageContext.request.contextPath}/logout" class="hdrtoplink">Logout</a>
</div> <!-- End of global menu div -->
