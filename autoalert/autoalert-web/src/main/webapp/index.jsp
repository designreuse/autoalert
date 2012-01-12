<jsp:root
	xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
    xmlns:c="http://java.sun.com/jsp/jstl/core" 
	version="2.0" >
	
	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

	<c:choose>
		<c:when test="${param['cmd'] == 'logoff'}" ><c:remove var="username" scope="session"/></c:when>
		<c:otherwise><c:set var="username" scope="session" value="${pageContext.request.remoteUser}"/></c:otherwise>
	</c:choose>
	
	<c:if test="${msgs == null}">
	    <fmt:setBundle basename="info.geekinaction.autoalert.view.AutoAlertMessages" var="msgs" scope="application"/>
	</c:if>
	
    <jsp:text><![CDATA[<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">]]></jsp:text>
    	
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<link type="text/css" rel="stylesheet" href="css/style.css" ></link>
	<link type="text/css" rel="stylesheet" href="css/datatable.css" ></link>
	<title><fmt:message key="applicationTitle" bundle="${msgs}"/></title>
	<script type="text/javascript" language="javascript" src="autoalert/autoalert.nocache.js">Error</script>
	</head>
	<body>
		<div id="header-wrapper">
			<div id="header">
				<div id="logo">
					<h1><a href="#" class="title"><fmt:message key="applicationName" bundle="${msgs}"/></a></h1>
					<p><img src="images/logo.png" /></p>
					<a href="${pageContext.request.requestURI}?cmd=logoff" class="logoff"><img src="images/logoff.png" /></a>
				</div>
			</div>
	    </div>
		<div id="page">
			<div id="page-bgtop">
				<div id="page-bgbtm">
					<c:choose>
						<c:when test="${sessionScope.username != null}" >
							<div id="content">
								<div class="post">
									<h2 class="title"><div id="gwt_title"></div></h2>
									<div style="clear: both;">&#160;</div>
									<div class="entry">
										<div id="gwt_app">&#160;</div>
									</div>
								</div>
								<div style="clear: both;">&#160;</div>					
							</div>
							<!-- end #content -->
							
							<div id="sidebar">
								<ul>
									<li>
										<div id="search" >
											<h3>
												<span>Welcome</span>
												<span>&#160;</span>
												<span><i><c:out value="${sessionScope.username}" /></i></span>
											</h3>
										</div>
										<div style="clear: both;">&#160;</div>
									</li>
									<li>
										<h2><fmt:message key="sidebarGeneral" bundle="${msgs}"/></h2>
										<ul>
											<li><a href="#" id="INSTANCE_INFO" name="a_sidebar"><fmt:message key="sidebarGeneralInfo" bundle="${msgs}"/></a></li>
										</ul>
									</li>
									<li>
										<h2><fmt:message key="sidebarInstanceResourceUsage" bundle="${msgs}"/></h2>
										<ul>
											<li><a href="#" id="INSTANCE_CPU" name="a_sidebar"><fmt:message key="sidebarInstanceCPUUsageHistory" bundle="${msgs}"/></a></li>
											<li><a href="#" id="INSTANCE_IO" name="a_sidebar"><fmt:message key="sidebarInstanceIOUsageHistory" bundle="${msgs}"/></a></li>
										</ul>
									</li>
									<li>
										<h2><fmt:message key="sidebarSessionResourceUsage" bundle="${msgs}"/></h2>
										<ul>
											<li><a href="#" id="SESSIONS_BY_CPU" name="a_sidebar"><fmt:message key="sidebarSessionTopCPU" bundle="${msgs}"/></a></li>
											<li><a href="#" id="SESSION_BY_IO" name="a_sidebar"><fmt:message key="sidebarSessionTopIO" bundle="${msgs}"/></a></li>
										</ul>
									</li>
									<li>
										<h2><fmt:message key="sidebarStorageStatus" bundle="${msgs}"/></h2>
										<ul>
											<li><a href="#" id="STORAGE_TABLESPACES" name="a_sidebar"><fmt:message key="sidebarStorageTablespaces" bundle="${msgs}"/></a></li>
											<li><a href="#" id="STORAGE_DATAFILES" name="a_sidebar"><fmt:message key="sidebarStorageDatafiles" bundle="${msgs}"/></a></li>
										</ul>
									</li>
								</ul>
							</div>
							<!-- end #sidebar -->				
						</c:when>
						<c:otherwise>
							<div id="content">
								<h2><fmt:message key="logout" bundle="${msgs}"/></h2>
							</div>
						</c:otherwise>
					</c:choose>
					<div style="clear: both;">&#160;</div>
				</div>
			</div>

		</div>
		<!-- end #page -->
		
		<div id="footer-wrapper">
			<div id="footer">
				<p><fmt:message key="applicationFooter" bundle="${msgs}"/></p>
			</div>
		</div>
		<!-- end #footer -->
		
	</body>
	</html>

</jsp:root>
