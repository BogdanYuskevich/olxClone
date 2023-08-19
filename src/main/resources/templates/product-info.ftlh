<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Product Info</title>
</head>
<body>
<h1>BuySell</h1> <hr>
<h4>Подробна інформація про товар</h4>
<b>Назва товару ${product.title}</b> <br>
<b>Опис товару ${product.description}</b> <br>
<b>Ціна товару ${product.price}</b> <br>
<b>Місто товару ${product.town}</b> <br>
<b>Автор ${product.author}</b> <br>
<hr>
<h3>Видалити товар</h3>
<form action="/product/delete/${product.id}" method="post">
    <input type="submit" value="Видалити товар">
</form>
</body>
</html>