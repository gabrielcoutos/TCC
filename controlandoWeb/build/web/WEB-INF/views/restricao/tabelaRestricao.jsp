<%-- 
    Document   : tabelaRestricao
    Created on : 19/09/2017, 22:09:48
    Author     : Gabriel Couto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${empty restricaoList}">
    <strong>Nenhuma restrição encontrado</strong>
</c:if>
<c:if test="${not empty restricaoList}">
    <table class="table">
        <tr>            
            <th>Código</th>
            <th>Nome</th>
            <th>Tempo de acesso</th>
            <th>Sites</th>
            <th>Ação</th>

        </tr>
        <c:forEach var="restricao" items="${restricaoList}">
            <tr>
                <td> ${restricao.id} </td>
                <td>${restricao.nome}</td>
                <td>${restricao.tempoAcesso}</td>
                <td class="btnSites"><button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">+</button></td>
                <td><a class="btn btn-default" href="<c:url value="/restricao/${restricao.id}"/>"><i class="glyphicon glyphicon-pencil"></i>editar</a>  
                    <a class="btn btn-danger btnModalRestricao" data-toggle="modal" data-target="#modalExcluir" ><i class="glyphicon glyphicon-remove"></i>Excluir</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>          
