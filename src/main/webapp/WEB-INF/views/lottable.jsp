<%-- $Id: lottable.jsp,v 1.5 2015/09/03 22:27:03 a0199948 Exp $ --%>
<%-- TODO: remove (DataTables usage example) --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container-fluid">
    <div class="row">
        <h3 class="col-md-12 view-title">Today's Lots</h3>
    </div>
    <div class="panel panel-default datatable-panel">
        <div class="row">
            <div class="col-md-12">
                <table class="table tblstandard table-striped" id="maintable">
                    <thead>
                        <tr class="tbl2">
                            <th>Lot number</th>
                            <th>Logpoint</th>
                            <th>Current quantity</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr class="tblbottom">
                            <th>Lot number</th>
                            <th>Logpoint</th>
                            <th>Current quantity</th>
                        </tr>
                    </tfoot>
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
// Send an Ajax request for the data
$(document).ready(function() {
	var mainTable = $('#maintable').dataTable({
    "bServerSide": true,
    "sAjaxSource": "<c:url value='lottable.json'/>",
    "order": [[1,'asc']],
    "aoColumns" : [
      { "mDataProp": "lot"},
      { "mDataProp": "lpt"},
      { "mDataProp": "curQty"}
    ],
    /* The following lines demonstrate how to send extra data to the server */
    "fnServerData": function (sSource, aoData, fnCallback) {
      aoData.push({ "name": "facility", "value": "DP1DM5" });
      $.getJSON(sSource, aoData, function (json) {
        fnCallback(json)
      } );
    }
  }).columnFilter({aoColumns: [ { type:"text"},{ type:"text"},{ type:"text"} ]});
	//new FixedHeader( mainTable );
} );
</script>
