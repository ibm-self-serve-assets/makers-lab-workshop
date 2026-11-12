<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.bank.dao.User" %>
<%@ page import="com.bank.dao.UserDAO" %>
<%@ page import="java.util.List" %>
<%
    String ctx = request.getContextPath();
%>
<html>
<head>
    <title>Admin - Create Account - ABC Bank</title>
    <link rel="stylesheet" type="text/css" href="<%= ctx %>/css/style.css"/>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<div class="content">
    <h2>Create New Account</h2>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <p class="error-msg"><%= error %></p>
    <%
        }
        List users = null;
        try {
            UserDAO userDAO = new UserDAO();
            users = userDAO.findAll();
        } catch (Exception e) {
            // ignore
        }
    %>
    <form action="<%= ctx %>/adminCreateAccount.do" method="post">
        <table class="form-table" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td>User ID:</td>
                <td>
                    <select name="userId" required>
                        <option value="">-- Select --</option>
                        <%
                            if (users != null) {
                                for (Object o : users) {
                                    User u = (User) o;
                        %>
                        <option value="<%= u.getId() %>"><%= u.getUsername() %> (ID: <%= u.getId() %>)</option>
                        <%
                                }
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Account Number:</td>
                <td><input type="text" name="accountNumber" size="20" required/></td>
            </tr>
            <tr>
                <td>Initial Balance:</td>
                <td><input type="text" name="initialBalance" size="15" value="0"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create Account" class="btn"/></td>
            </tr>
        </table>
    </form>
    <p class="back-links"><a href="<%= ctx %>/adminAccounts.do">Back to Accounts</a></p>
</div>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
