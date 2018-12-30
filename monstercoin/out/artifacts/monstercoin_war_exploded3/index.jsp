<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page import="monstercoin.config.DemoAppConfig" %>
<html>
<body>

<h3>Spring CRM REST Demo</h3>

<hr>

<a href="${pageContext.request.contextPath}/api/customers">Get All Customers</a>

<br><br>

<a href="${pageContext.request.contextPath}/userapi/users">Get All Users</a>

<br><br>

<%
    out.println(DemoAppConfig.scheduleFixedDelayTask());
%>

</body>
</html>