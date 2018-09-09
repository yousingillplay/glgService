<!-- $Id: about.jsp,v 1.4 2015/07/21 02:19:13 a0284538 Exp $ -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<%-- Find how many "about" keys we have (props are unsorted,
   so we'll compose the key names with a counter) --%>
<c:forEach items="${requestScope}" var="prop" varStatus="i">
  <c:if test="${fn:startsWith(prop.key, 'about') and fn:endsWith(prop.key, 'name')}">
    <c:set var="ctr" value="${ctr+1}"/>
  </c:if>
</c:forEach>
<c:choose>
  <%-- Show each about property in a row --%>
  <c:when test="${not empty ctr}">
    <table>
      <tr>
        <td style="white-space: nowrap; color:#ea272a; font-size:150%" colspan="2"><b>${requestScope["about.title"]}</b></td>
      </tr>
      <%-- Loop through, composing the key names as we go --%>
      <c:forEach begin="1" end="${ctr}" var="i">
        <c:set var="aboutName" value="about.${i}.name"/>
        <c:set var="aboutValue" value="about.${i}.value"/>
        <tr>
          <td style="text-align:right; padding:10px;"><b><!--br/-->${requestScope[aboutName]}
              <c:if test="${not empty requestScope[aboutName]}">:</c:if>
            </b><!--br/-->&nbsp;</td>
          <td style="padding:10px;"><!--br/-->${requestScope[aboutValue]}<!--br/-->&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
  </c:when>
  <%-- How-to message if there are no about properties --%>
  <c:otherwise>
    <c:if test="${empty ctr}">
      <h1 class="page-header">About Page</h1><br/>
      <div>To customize this page, add the properties &quot;about.title,&quot;
        &quot;about.1.name&quot;, &quot;about.1.value&quot;, &quot;about.2.name&quot;,
        etc. to your application's external properties file.</div>
    </c:if>
  </c:otherwise>
</c:choose>