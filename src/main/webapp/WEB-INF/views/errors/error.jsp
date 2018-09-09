<!-- $Id: error.jsp,v 1.3 2015/12/16 00:50:03 a0199948 Exp $ -->

<h1><img src="images/red_exclamation_mark_64.png" alt="Alert"/>The system has encountered an error.</h1>
<h3>
    An email has been sent to notify the support group. <br/>
    We apologize for any inconvenience.
</h3>

<%-- Update the properties file with the location of STARS ticket. --%>
<c:if test="${not empty requestScope['stars.helpdesk'] and not empty requestScope['stars.product']}">
    <p>
        If a business process is interrupted or functioning incorrectly, you can submit a
        <a href="https://stars.itg.ti.com/arsys701/shared/STARSHome.jsp" target="_blank">STARS</a> ticket
        to the <b>${requestScope['stars.helpdesk']}</b> helpdesk; select <b>${requestScope['stars.product']}</b>
        from the product list.
    </p>
</c:if>
<%-- Update the properties file with the support team email information. --%>
<c:if test="${not empty requestScope['menu.team.label'] and not empty requestScope['menu.team.email']}">
    <p>
        If the system is down or you are experiencing a production stop, please notify
        <a href="mailto:${requestScope['menu.team.email']}">${requestScope['menu.team.label']}</a>
    </p>
</c:if>

<p>${requestScope['message.errorDefault']}</p>

<%--
<p>
        NOTE: This is a good place to let the user know on how he/she can help the support group to handle their issue.
        Like giving certain information or a screenshot when the error happened.
        This is a good place to include app-specific information like contact emails, numbers, or STARS location.
        <br/><br/>
        --- OR ---
        <br/><br/>
        You can check your properties file, and edit the property 'message.errorDefault'
</p>
--%>

