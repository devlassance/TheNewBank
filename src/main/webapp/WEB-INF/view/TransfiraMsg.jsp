<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The New Bank - Info Transferência</title>
</head>
<body>
<h1>The New Bank</h1>
<h3>Transferência realizada com sucesso!</h3>
<ul>
	<li><strong>Valor Transferido: </strong> R$ ${valor}</li>
	<li><strong>Conta Beneficiada: </strong> ${contaBeneficiario}</li>
	<li><strong>Agencia Beneficiada: </strong> ${agenciaBeneficiario}</li>
</ul>
<p>Seu saldo atual é R$ ${saldoAtual}</p>
<a href="/TheNewBank/Conta/DetalhesConta">Voltar para sua conta</a>
</body>
</html>