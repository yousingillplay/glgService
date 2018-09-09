<!-- $Id: footer.jsp,v 1.6 2015/09/03 01:11:58 a0199948 Exp $ -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container-fluid">

<hr style="background-color: #000; color: #000; height: 6px"/>
<c:if test="${requestScope['data.classification'] == 'max'}">
<div class="alert alert-danger" role="alert">
  <span class="glyphicon glyphicon-alert" aria-hidden="true"></span>
  <span class="sr-only">TI Confidential - Maximum Restrictions</span>
  TI Confidential - Maximum Restrictions
</div>
</c:if>

<c:if test="${requestScope['data.classification'] == 'nda'}">
<div class="alert alert-warning" role="alert">
  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
  <span class="sr-only">TI Confidential - NDA Restrictions</span>
  TI Confidential - NDA Restrictions
</div>
</c:if>

<c:if test="${requestScope['data.classification'] == 'selective'}">
<div class="alert alert-info" role="alert">
  <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
  <span class="sr-only">TI Information - Selective Disclosure</span>
  TI Information - Selective Disclosure
</div>
</c:if>

<table>
  <tr><td>
      <c:if test="${not empty requestScope['menu.team.label']
                    and not empty requestScope['menu.team.email']}"><b>
          <a href="mailto:${requestScope['menu.team.email']}">${requestScope['menu.team.label']}</a>
      </b>&nbsp;</c:if><c:if test="${not empty requestScope['menu.home.label']
                    and not empty requestScope['menu.home.url']}"><b>
          <a href="${requestScope['menu.home.url']}">${requestScope['menu.home.label']}</a></b>
      </c:if>
  </td></tr>
  <tr><td>Version ${requestScope['project.version']}
      <c:set var="bn" value="${requestScope['build.number']}"/>
      <c:choose>
      <c:when test="${not empty bn and not fn:contains(bn, 'BUILD_NUMBER')}">
      build ${bn}
      </c:when>
      <c:otherwise>
      non-production build
      </c:otherwise>
      </c:choose>
      (${empty requestScope['build.date'] ? 'no build date' : requestScope['build.date']})</td></tr>
</table>

</div>
