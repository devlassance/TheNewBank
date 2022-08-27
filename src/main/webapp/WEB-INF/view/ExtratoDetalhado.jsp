<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Extrato" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The New Bank - Extrato Detalhado</title>
</head>
<body>
<h1>The new Bank</h1>
<h3>Extrato Detalhado</h3>
<hr>
		
<c:forEach items="${extratos}" var="extrato">
	<ul>
		<li><strong>Tipo: </strong>${extrato.tipoExtrato}</li>
		
		<c:if test="${extrato.valor > 0}">
			<li><strong style="color:#000">Valor: </strong><span>${extrato.valor}</span></li>
		</c:if>
		
		<c:if test="${extrato.valor < 0}">
			<li ><strong style="color:#000">Valor: </strong><span style="color:#ff0000">${extrato.valor}</span></li>
		</c:if>
		
		<li><strong>Saldo no momento: </strong>${extrato.saldoMomento}</li>
		
		<li><strong>Data transação: </strong><fmt:formatDate value="${extrato.dataCadastro}" pattern="dd/MM/yyyy HH:mm:ss"/> </li>
	</ul>
	<hr>
</c:forEach>
	
<a href="/TheNewBank/Conta/DetalhesConta">Voltar para sua conta</a>
</body>
</html>