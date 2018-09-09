<!-- $Id: accessDenied.jsp,v 1.2 2015/12/15 17:25:05 a0199948 Exp $ -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1><img src="images/red_access_denied_64.png" alt="Alert"/>Access Denied</h1>
<h3>Sorry, you are not authorized to perform this activity.</h3>

<p>${requestScope['message.accessDenied']}</p>

<%--
<p>
        NOTE: This is a good place to let the user know on how he/she can request access.
        <br/><br/>
        --- OR ---
        <br/><br/>
        You can check your properties file, and edit the property 'message.accessDenied'
</p>
--%>
