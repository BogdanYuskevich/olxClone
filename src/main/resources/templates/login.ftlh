<h1>BUYSELL</h1><hr>
<h4>Авторизація</h4>
<form action="/login" method="post">
    Email: <input type="email" name="username"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
</form>
<a href="/registration">Зареєструватись</a>
</body>
</html>