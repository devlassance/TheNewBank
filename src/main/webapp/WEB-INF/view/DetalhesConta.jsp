<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The New Bank - Detalhes da conta</title>
</head>
<body>

<h1>The New Bank</h1>
<h3>Detalhes da Conta</h3>

<p>Olá <strong>${nome}</strong>, seja bem vindo! Abaixo os dados de sua conta</p>
<hr>
<ul>
	<li><strong>Sua agência:</strong> ${agencia}</li>
	<li><strong>Sua conta:</strong> ${conta}</li>
	<li><strong>Seu saldo:</strong> R$ ${saldo}</li>
</ul>

<hr>

<a href="/TheNewBank/Conta/Deposite?conta=${conta}">Deposite um valor</a> -
<a href="/TheNewBank/Conta/Saque?conta=${conta}">Saque um valor</a> -
<a href="/TheNewBank/Conta/Transfira?conta=${conta}">Transfira um valor</a> -
<a href="/TheNewBank/Conta/ExtratoDetalhado?conta=${conta}">Extrato Detalhado</a>

<hr>

</body>
</html>