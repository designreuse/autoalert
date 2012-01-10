<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	final Logger logger = Logger.getLogger(this.getClass());
	ResourceBundle msgs = null;
	try {
		msgs = ResourceBundle.getBundle("AutoAlertMessages", Locale.getDefault());
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/main.css">
<link type="text/css" rel="stylesheet" href="css/datatable.css">
<title><%= msgs.getString("applicationName") %></title>
<script type="text/javascript" language="javascript" src="<%= msgs.getString("applicationModule") %>/<%= msgs.getString("applicationModule") %>.nocache.js"></script>
</head>
<body>
	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
		style="position: absolute; width: 0; height: 0; border: 0"></iframe>
	<div id="CONTAINER">
		<div id="HEADER"></div>
		<div id="MAIN" align="center"></div>
		<div id="FOOTER"><%= msgs.getString("applicationFooter") %></div>
	</div>
</body>
</html>