<%-- 
    Document   : tabela
    Created on : 30/09/2017, 16:56:39
    Author     : Gabriel Couto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="table" id="tableCategorias">
    <thead>
        <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Descrição</th>                                                           
            <th>Ação</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="categoria" items="${categoriaList}">
        <tr>
            <td>${categoria.id}</td>
            <td>${categoria.nome}</td>
            <td>${categoria.descricao}</td>                                                                
            <td><a class="btn btn-default" href="<c:url value="/categoria/${categoria.id}"/>"><i class="glyphicon glyphicon-pencil"></i>Editar</a> 
                <a class="btn btn-danger" href="<c:url value="/categoria/${categoria.id}/deletar"/>"><i class="glyphicon glyphicon-remove"></i>Excluir</a></td>
        </tr>
    </c:forEach>
</tbody>
</table>
