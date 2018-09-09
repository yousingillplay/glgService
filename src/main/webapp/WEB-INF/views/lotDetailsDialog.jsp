<%-- $Id: lotDetailsDialog.jsp,v 1.5 2015/09/03 22:27:03 a0199948 Exp $ --%>
<%-- TODO: remove (demonstration only) --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="post" commandName="lot" id="lotForm" cssClass="form-group" action="lotDetailsDialog" >
    <input type="hidden" name="facility" value="${facility}"/>

    <div class="form-group">
        <label for="lot">Lot number:</label>
        <form:input path="lot" cssClass="form-control" cssStyle="text-align:right" readonly="true"/>
    </div>

    <div class="form-group">
        <label for="lpt">Logpoint: <span style="color:red">*</span></label>
        <form:input path="lpt" cssClass="form-control" cssStyle="text-align:right"/>
    </div>

    <div class="form-group">
        <label for="curQty">Current quantity: <span style="color:red">*</span></label>
        <form:input path="curQty" cssClass="form-control" cssStyle="text-align:right"/>
    </div>

    <small><span style="color:red">*</span><i> Indicates a required field</i></small>
</form:form>
