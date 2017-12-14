<%-- 
    Document   : tabela
    Created on : 11/09/2017, 20:17:09
    Author     : Gabriel Couto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="table table-bordered" id="table1" style="overflow: scroll">
    <thead>
        <tr>    
            <th>Código</th>
            <th>Nome</th>            
            <th>URL</th>
            <th>Categoria</th> 
            
        </tr>
    </thead>
    <tbody id="tabelaPrincipal">
    <c:forEach items="${restricao.siteList}" var="site">
        <tr id="linhaDaTabela">
            <td >${site.id}</td>
            <td scope="row">${site.nome}</td>            
            <td>${site.url}</td>
            <td>${site.categoria.nome}</td>           
        </tr>
    </c:forEach>
</tbody>
</table>