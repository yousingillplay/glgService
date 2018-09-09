<%-- $Id: topmenulayout.jsp,v 1.15 2018/02/05 06:22:43 a0284538 Exp $ --%>
<%@ page session="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${requestScope['menu.pageTitle']}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="webjars/datatables-bootstrap/2-20120201/DT_bootstrap.css"/>
        <!-- TI Spring Archetype style overrides -->
        <link rel="stylesheet" type="text/css" href="css/ffw-spring.css"/>
        <link rel="stylesheet" type="text/css" href="css/ffw-spring-jqueryui.css"/>
        <link rel="stylesheet" type="text/css" href="css/ffw-spring-bootstrap.css"/>

        <script src="webjars/jquery/2.1.3/jquery.min.js"></script>
        <script src="webjars/jquery-ui/1.11.4/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/jquery.event.drag-2.0.min.js"></script>
        <script type="text/javascript" src="js/jquery.cookie.js"></script>
        <script type="text/javascript" src="js/jquery.springFormModal.js"></script>
        <script type="text/javascript" src="js/spinner.js"></script>

        <script type="text/javascript" src="webjars/datatables/1.10.5/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/dataTables.bootstrap.js"></script>        
        <script type="text/javascript" src="js/jquery.dataTables.columnFilter-1.5.0.js"></script>
        <script type="text/javascript" src="js/full_numbers_no_ellipses.js"></script>
        <script type="text/javascript" src="webjars/datatables-plugins/1.10.11-1/integration/bootstrap/3/dataTables.bootstrap.js"></script>

        <script type="text/javascript" src="js/utilities.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/bootstrap-hover-dropdown.js"></script>
        <script type="text/javascript" src="webjars/jquery-hoverIntent/50f11356f8/jquery.hoverIntent.js"></script>

        <!--
            ClickJack busting script
            ref: https://www.owasp.org/index.php/Clickjacking_Defense_Cheat_Sheet
            ref: https://www.codemagi.com/blog/post/194
        -->
        <style id="antiClickjack">body{display:none !important;}</style>
        <script type="text/javascript">
            if (self === top) {
                var antiClickjack = document.getElementById("antiClickjack");
                antiClickjack.parentNode.removeChild(antiClickjack);
            } else {
                top.location = self.location;
            }
        </script>
    </head>
    <body>

        <%-- Disables caching for Ajax requests --%>
        <script type="text/javascript">
            $.ajaxSetup({cache: false});
        </script>

        <div class="container-fluid">
            <div id="menu" class="row"><tiles:insertAttribute name="menu" /></div> <!-- End of menu div -->
            <div id="body" class="row"><tiles:insertAttribute name="body" /></div> <!-- End of body div -->
            <div id="footer" class="row"><tiles:insertAttribute name="footer" /></div> <!-- End of footer div -->
        </div>

        <%-- This div is a placeholder for a popup jQuery dialog --%>
        <div id="edit_dialog" title="Add/Update Record"></div> <!-- End of popup dialog div -->

    </body>
</html>
