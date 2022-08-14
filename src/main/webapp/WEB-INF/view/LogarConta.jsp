<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The New Bank - Logar</title>
</head>
<body>
<h1>The New Bank</h1>
<h3>Logue na sua conta</h3>

<form action="/TheNewBank/Conta/DetalhesConta" method="get">
	Número da conta: <br>
	
	<input type="text" name="conta" placeholder="Informe o número da sua conta"><br>
	<br>
	<input type="submit" value="Logar">
	
</form>


</body>
</html>