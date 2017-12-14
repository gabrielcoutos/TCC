<%-- 
    Document   : tabelaSites
    Created on : 10/10/2017, 09:24:09
    Author     : gabriel.couto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered" >
    <thead>
        <tr>    
            <th>Nome</th>                            
            <th>URL</th>
            <th>Categoria</th>
            <th>Adicionar</th>
        </tr>
    </thead>
    <tbody id="tabelaPrincipal">
    <c:forEach var="site" items="${siteList}" >
        <tr id="linhaDaTabela">
            <td >${site.id}</td>
            <td >${site.nome}</td>                               
            <td>${site.url}</td>
            <td>${site.categoria.nome}</td>
            <td class="btnAdicionar"><p class="btn btn-default">+</p></td>
        </tr>
    </c:forEach>
</tbody>
</table>
