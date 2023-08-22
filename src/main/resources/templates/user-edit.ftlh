<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Редагування ролей користувача</title>
</head>
<body>
<h1>Редагування ролей користувача</h1>
<hr>
<form th:action="@{/admin/user/updateRoles}" method="post">
    <input type="hidden" name="userId" th:value="${user.id}">
    <h2>${user.email}</h2>
    <h3>Поточні ролі:</h3>
    <ul>
        <#list user.roleSet as role>${role}<#sep>, </#sep></#list>
    </ul>
    <h3>Виберіть нові ролі:</h3>
    <form action="/admin/changeRoles/${user.id}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <select multiple name="selectedRoles">
            <#list allRoles as role>
                <option value="${role}" <#if user.roleSet?seq_contains(role)>selected</#if>>${role}</option>
            </#list>
        </select>
        <input type="submit" value="Зберегти ролі">
    </form>



</form>
</body>
</html>
