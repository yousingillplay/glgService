<!-- $Id: menu.jsp,v 1.8 2015/07/31 09:25:41 a0284538 Exp $ -->
<%@ page import="java.util.*" %>
<%@ page import="com.ti.util.directoryservices.EmployeeInfo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href='#'><img src="resources/css/ti-logo-small.png" alt="Texas Instruments"></a>
            <a class="navbar-brand" href='#'>My Spring App</a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#side-navbar-collapse">
                <span class="sr-only">Top Links</span>
                <span class="glyphicon glyphicon-globe"></span>
            </button>
        </div>

        <ul class="nav navbar-nav navbar-left collapse navbar-collapse" id="main-navbar-collapse">
            <li class="menu-item"><a href='<c:url value="/"/>'>Home</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-hover="dropdown">Hello <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href='<c:url value="/hello?choice=World"/>'>World</a></li>
                    <li><a href='<c:url value="/hello?choice=Kitty"/>'>Kitty</a></li>
                    <li class="dropdown-submenu">
                        <a href="#">Languages</a>
                        <ul class="dropdown-menu">
                            <!-- Third level list items on submenu -->
                            <li><a href='#'>Bonjour</li>
                            <!-- Example of conditionally enabling an item based on user role -->
                            <sec:authorize access="hasRole('ROLE_USER')">
                                <li><a href='<c:url value="/hello?choice=Guten%20Tag"/>'>Guten Tag</a></li>
                            </sec:authorize>
                            <sec:authorize access="!hasRole('ROLE_USER')">
                                <li class="disabled"><a href="#">Guten Tag</a></li>
                            </sec:authorize>
                            <li class="divider"></li>
                            <li><a href='<c:url value="/hello?choice=Hola"/>'>&iexcl;Hola!</a></li>
                            <li><a href='<c:url value="/hello?choice=Konnichiwa"/>'>Konnichiwa</a></li>
                            <li><a href='<c:url value="/hello?choice=Mabuhay"/>'>Mabuhay!</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-hover="dropdown">Lots <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href='<c:url value="/listlots?facility=DP1DM5"/>'>List today's lots</a></li>
                    <li><a href='<c:url value="/lottable?facility=DP1DM5"/>'>Data table example</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-hover="dropdown">Goodbye <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href='<c:url value="/goodbye/Cruel%20World"/>'>Cruel world</a></li>
                </ul>
            </li>

        </ul>

        <ul class="nav navbar-nav navbar-right collapse navbar-collapse" id="side-navbar-collapse">
            <sec:authentication property="principal.username" var="userid" />
            <c:choose>
                <c:when test="${not empty userid}">
                    <jsp:useBean id="employeeDirectory" class="com.ti.util.directoryservices.EmployeeDirectory" />
                    <% String user = (String) pageContext.getAttribute("userid");%>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-hover="dropdown"><span class="glyphicon glyphicon-user"></span> ${userid} <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><%=employeeDirectory.getEmployeeById(user).get(EmployeeInfo.FULL_NAME)%></a></li>
                            <li class="divider"></li>
                            <li><c:url value="logout" var="logoutUrl"/><a href="${logoutUrl}" class="hdrtoplink">Logout</a></li>
                        </ul>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="https://myportal.ti.com/portal/dt?provider=TIPassLoginSingleContainer" class="hdrtoplink">Login</a></li>
                </c:otherwise>
            </c:choose>

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-hover="dropdown"><span class="glyphicon glyphicon-globe"></span> Global TI <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <%
                        // Get a sorted list of global menu keys
                        Set<String> keySet = new TreeSet<String>();
                        for (Enumeration<?> e = request.getAttributeNames(); e.hasMoreElements();) {
                            String prop = (String) e.nextElement();
                            if (prop.matches("global\\.menu\\.(\\d+\\.?)*\\.name")) {
                                keySet.add(prop.substring(0, prop.length() - 5));
                            }
                        }
                        for (String key : keySet) {
                            String name = (String) request.getAttribute(key + ".name");
                            String url = (String) request.getAttribute(key + ".url");
                            out.write("<li><a href=\"" + url + "\" target=\"_blank\" class=\"hdrtoplink\">" + name + "</a></li>");
                        }
                    %>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-hover="dropdown"><span class="glyphicon glyphicon-question-sign"></span> Help <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="about">About</a></li>
                    <li><a href="http://stars.itg.ti.com" target="_blank" class="hdrtoplink">Submit a STARS Ticket</a></li>
                    <li><a href='monitor'>Monitor status</a></li>
                </ul>
            </li>
        </ul>

        <form class="nav navbar-form navbar-right" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search" />
                <button class="btn btn-default" type="submit" onclick="alert('Search box for demo only.');"><span class="glyphicon glyphicon-search"></span></button>
            </div>
        </form>
    </div><!-- /.container-fluid -->
</nav>
