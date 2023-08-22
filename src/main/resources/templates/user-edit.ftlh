<!DOCTYPE html>
<html>
<head>
    <title>Buy It</title>
</head>
<body>
<h1>Buy It</h1><hr>
<h3>Редагування користувача ${user.name}</h3>
<form action="/admin/user/edit" method="post">
    <#list availableRoles as role>
        <div>
            <label><input type="checkbox" name="roles" value="${role}" <#if user.roleSet?seq_contains(role)>checked</#if>>${role}</label>
        </div>
    </#list>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Зберегти</button>
</form>
</body>
</html>
