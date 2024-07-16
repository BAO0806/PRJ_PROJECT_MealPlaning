<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List Menus</title>
</head>
<body>
    <h1>List of Menus</h1>
    <ul>
        <c:forEach var="menu" items="${menus}">
            <li><a href="menu?action=view&menuId=${menu.menuId}">${menu.name}</a></li>
        </c:forEach>
    </ul>
</body>
</html>
