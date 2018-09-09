<%-- $Id: lotlist.jsp,v 1.8 2016/06/28 02:29:38 a0284538 Exp $ --%>
<%-- TODO: remove (demonstration only) --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Today's Lots:</h1>
<table class="table">
    <thead>
        <tr>
            <th>Lot number</th>
            <th>Logpoint</th>
            <th>Current quantity</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${lots}" var="lot" varStatus="status">
            <tr class="${status.count%2==0 ? 'tbl5' : 'tbl6'}">
                <td><a class="edit_lot" href="<c:url value='lotDetailsDialog'><c:param name='facility' value='DP1DM5'/><c:param name='lot' value='${lot.lot}'/></c:url>">${lot.lot}</a></td>
                <td>${lot.lpt}</td>
                <td>${lot.curQty}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<script type="text/javascript">

  $('.edit_lot').click(function(event) {
    $(this).attr("data-source", $(this).attr('href'));
    $(this).attr("data-target", '#lotForm');
    $(this).springFormModal({
              title : 'Lot details',
         });
    event.preventDefault();
  });
</script>
