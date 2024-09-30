<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt lại mật khẩu</title>
</head>
<body>
<h2>Đặt lại mật khẩu</h2>
<form action="/update-password" method="post">
    <input type="hidden" name="token" value="${token}">
    <label for="password">Nhập mật khẩu mới:</label>
    <input type="password" id="password" name="password" required>
    <button type="submit">Đặt lại mật khẩu</button>
</form>
</body>
</html>