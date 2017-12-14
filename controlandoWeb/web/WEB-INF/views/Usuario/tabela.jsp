<%-- 
    Document   : tabela
    Created on : 19/09/2017, 20:15:46
    Author     : Gabriel Couto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${empty usuarioList}">
    <strong>Nenhum usuario encontrado</strong>
</c:if>
<c:if test="${not empty usuarioList}">
    <table class="table">
        <tr>
            <th>Status</th>
            <th>Nome</th>
            <th>E-mail</th>
            <th>Plano</th>
            <th>Opções</th>
        </tr>
        <c:forEach var="usuarioC" items="${usuarioList}">
            <tr>
                <td><c:if test="${usuarioC.status eq 1}"><span class="label label-success">Aprovado</span></c:if> <c:if test="${(usuarioC.status eq 0) and (usuarioC.delete eq 0)}"><span class="label label-warning">Pendente</span></c:if><c:if test="${usuarioC.delete eq 1}"><span class="label label-danger">Deletado</span></c:if></td>
                <td> <a href="<c:url value="/user/${usuarioC.id}"/>">${usuarioC.nome}</a></td>
                <td>${usuarioC.email}</td>
                <td><c:if test="${empty usuarioC.plano.nome}">Usuário sem plano</c:if>${usuarioC.plano.nome}</td>
                <td><a href="<c:url value="/user/${usuarioC.id}"/>" class="btn btn-default"><i class="glyphicon glyphicon-pencil"></i> Editar</a> 
                    <a href="<c:url value="/user/${usuarioC.id}/reprovar"/>" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i> Excluir</small</a></td>

            </tr>
        </c:forEach>
    </table>
</c:if>          
