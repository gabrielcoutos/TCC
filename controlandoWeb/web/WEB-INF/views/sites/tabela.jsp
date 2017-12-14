<%-- 
    Document   : tabela
    Created on : 30/09/2017, 12:07:09
    Author     : Gabriel Couto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="table" id="tableSites">
    <thead>
        <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Categoria</th>
            <th>Ação</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="site" items="${siteList}">
        <tr>
            <td>${site.id}</td>
            <td>${site.nome}</td>
            <td>${site.descricao}</td>
            <td>${site.categoria.nome}</td>
            <td><a class="btn btn-default" href="<c:url value="/site/${site.id}"/>"><i class="glyphicon glyphicon-pencil"></i>Editar</a> 
                <a class="btn btn-danger" href="<c:url value="/site/${site.id}/deletar"/>"><i class="glyphicon glyphicon-remove"></i>Excluir</a></td>
        </tr>
    </c:forEach>
</tbody>
</table>