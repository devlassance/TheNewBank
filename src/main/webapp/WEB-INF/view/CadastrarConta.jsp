<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The new Bank</title>
</head>
<body>

<h1>The new Bank</h1>
<h3>Cadastre sua conta</h3>

	<form action="/TheNewBank/Conta/CadastrarConta" method="post">
	
		Defina um nome: <br>
		<input type="text" name="nome"> <br>
		<br>
		Defina uma senha: <br>
		<input type="password" name="senha"><br>
		<br>
		<input type="submit" value="Cadastrar">
	</form>
	<a href="/TheNewBank">Logar numa conta existência</a>

</body>
</html>