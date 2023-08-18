<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <h1>BuySell</h1> <hr>
    <h4>Товари зі всієї України.</h4>
    <#list products as product>
        <div>
            <p><b>${product.title}</b></p>
            <p>${product.price}</p>
            <a href="/product/${product.id}">More.....</a>
        </div>
    </#list>
    <hr>
    <h3>Створити новий товар</h3>
    <form action="/product/create" method="post">
        Назва об'яви : <input type="text" name="title"> <br><br>
        Опис об'яви : <input type="text" name="desc"> <br><br>
        Ціна: <input type="number" name="price"> <br><br>
        Місто : <input type="text" name="town"> <br><br>
        Ім'я автора : <input type="text" name="author"> <br><br>
        <input type="submit" value="Добавити товар">

    </form>
</head>
<body>

</body>
</html>