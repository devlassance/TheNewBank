<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The New Bank - Transfirir</title>
</head>
<body>

<h1>The New Bank</h1>
<h3>Informe os detalhes</h3>

<p>Seu saldo atual é <strong>R$ ${saldo} </strong> </p>

<form action="Transfira" method="post">

	Conta Beneficiário: <br>
	<input type="text" name="contaBeneficiario"><br>
	
	Agência Beneficário: <br>
	<input type="text" name="agenciaBeneficiario"><br>
	
	Valor a ser Transferido: <br>
	<input type="text" name="valor"><br>
		
	<br>
	<input type="submit" value="Transferir">
	
</form>


</body>
</html>