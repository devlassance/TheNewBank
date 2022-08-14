<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The New Bank - Saque</title>
</head>
<body>

<h1>The New Bank</h1>
<h3>Informe os detalhes</h3>

<p>Seu saldo atual é <strong>R$ ${saldo}</strong> </p>

<form action="Saque" method="post">

	Valor a ser sacado: <br>
	<input type="text" name="valor"><br>
	<input type="hidden" name="conta" value="${conta}">
	<br>
	<input type="submit" value="Sacar">
	
</form>

</body>
</html>