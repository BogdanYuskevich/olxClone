<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>BuyIt</title>
</head>
<body>
<h1>BuyIt</h1>
<hr>
<#if user.getEmail()??>
    <h3>Імя користувач ${user.name}</h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Вийти">
    </form>
<#else>
    <a href="/login">Ввійти</a><br>
</#if>
<#if user.isAdmin()>
    <a href="/admin">Панель адміністратора</a>
</#if>

<h4>Товари зі всієї України.</h4>

<form>
    Пошук за назвою : <input type="text" name="title"><br>
    <input type="submit" value="Пошук">
</form>
<#list products as product>
    <div>
        <p><b>${product.title}</b></p>
        <p>${product.price}</p>
        <a href="/product/${product.id}">More.....</a>
    </div>
<#else >
    Товарів немає
</#list>
<hr>
<#if user.getEmail()??>
    <h3>Створити новий товар</h3>
    <form action="/product/create" method="post" enctype="multipart/form-data">
        Назва об'яви : <input type="text" name="title" required> <br><br>
        Опис об'яви : <input type="text" name="description" required> <br><br>
        Ціна: <input type="number" name="price" required> <br><br>
        Місто : <input type="text" name="town" required> <br><br>
        Фото 1 : <input type="file" name="file1"> <br><br>
        Фото 2 : <input type="file" name="file2"> <br><br>
        Фото 3 : <input type="file" name="file3"> <br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавити товар">
    </form>
</#if>
</body>
</html>
