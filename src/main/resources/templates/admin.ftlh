<!DOCTYPE html>
<html>
<head>
    <title>BuyIt</title>
</head>
<body>
<h1>BuyIt</h1>
<hr>
<h4>Панель адміністратора</h4>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Номер телефону</th>
        <th>Активність</th>
        <th>Ролі</th>
        <th>Бан</th>
        <th>Редагування</th>
        <th>Докладна інформація</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.email}</th>
            <th>${user.phone}</th>
            <th><#if user.active>true<#else>false</#if></th>
            <th><#list user.roleSet as role>${role}</#list></th>
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Бан">
                </form>
            </th>
            <!-- ... (попередній HTML-код) ... -->
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <th><a href="/editRoles/${user.id}">Редагування</a></th>
            <!-- ... (залишок HTML-коду) ... -->

            <th><a href="/user/${user.id}">Подробная информация</a></th>
        </tr>
    <#else>
        <h3>Пользователей нет</h3>
    </#list>
</table>
</body>
</html>