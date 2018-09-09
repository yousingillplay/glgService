// $Id: utilities.js,v 1.1 2014/02/05 00:17:05 a0199948 Exp $

// Fix for DataTables column filter width issue (on narrow windows the column
// filter width adjusts to the window but the table remains wide)
function setHeaderMinWidth() {
  $(".dataTables_wrapper").each(function() {
    var wrapperWidth = $(this).width();
    var tableWidth = $(this).find("table.dataTable").outerWidth();
    if (tableWidth > wrapperWidth) {
      $(this).css("min-width", tableWidth+"px");
    }
  });
}
