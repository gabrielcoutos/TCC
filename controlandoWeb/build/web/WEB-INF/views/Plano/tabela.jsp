<%-- 
    Document   : tabela
    Created on : 19/09/2017, 21:42:44
    Author     : Gabriel Couto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${empty planoList}">
    <strong>Nenhum plano encontrado</strong>
</c:if>
<c:if test="${not empty planoList}">
    <table class="table table-striped">
        <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Restricao</th>
            <th>Ação</th>
        </tr>
        <c:forEach var="plano" items="${planoList}">
            <tr>
                <td> ${plano.id}</td>
                <td>${plano.nome}</td>
                <td>${plano.restricao.nome}</td>
                <td><a class="btn btn-default" href="<c:url value="/plano/${plano.id}"/>"><i class="glyphicon glyphicon-pencil"></i>Editar</a>  <a class="btn btn-danger btnModal" data-toggle="modal" data-target="#modalExluir"><i class="glyphicon glyphicon-remove"></i>Excluir</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>          
