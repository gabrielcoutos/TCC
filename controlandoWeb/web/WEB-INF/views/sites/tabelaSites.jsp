<%-- 
    Document   : tabelaSites
    Created on : 16/09/2017, 18:52:44
    Author     : Gabriel Couto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Categoria</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="sites" items="${siteList}">
            <tr>
                <td>${site.id}</td>
                <td>${site.nome}</td>
                <td>${site.descricao}</td>
                <td>${site.categoria.nome}</td>
            </tr>
        </c:forEach>
       
    </tbody>
</table>

