<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <!--Import Google Icon Font-->        
        <!--Import materialize.css-->
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <!--<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/materialize.min.css"/>"  media="screen,projection"/>-->

        <!--Let browser know website is optimized for mobile-->
        <title>ControLANdo</title>

        <!-- Bootstrap -->



        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="<c:url value="/resources/js/html5shiv.min.js"/>"></script>
        <script src="<c:url value="/resources/js/respond.min.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.form.js"/>"></script>
        <![endif]-->
    </head>
    <body>
        <div class="container" style="width: 30%; ">           
            <form action="<c:url value="/user/${usuario.id}/aprovar"/>" class="form-group" method="post">  
                <div class="form-group">
                    <label for="nomeUsuarioC">Nome: </label>
                    <input type="text" name="nomeUsuario" id="nomeUsuarioC" class="form-control" value="${usuario.nome}" />
                </div>
                <div class="form-group">
                    <label for="emailUsuario">Email:</label>
                    <input type="email" name="emailUsuario" id="emailUsuario" class="form-control" value="${usuario.email}" />
                </div>
                <div class="form-group">
                    <label for="dataUsuario">Data de nascimento: </label>
                    <input type="date" name="dataUsuario" id="dataUsuario" class="form-control" value="${usuario.data_nascimento}" />
                </div>
                <div class="form-group">
                    <label for="cpfUsuario">CPF: </label>
                    <input type="number" name="cpfUsuario" id="cpfUsuario" class="form-control" value="${usuario.cpf}" />
                </div>
                <div class="form-group">
                    <label for="selectTipo">Tipo:</label>
                    <select class="form-control" id="selectTipo" name="tipoUsuario">
                        <option value="0" selected="">Usuario Comum</option>
                        <option value="1">Administrador</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectPlano" >Plano: </label>
                    <select class="form-control" id="selectPlano" name="planoUsuario"> 
                        <option  selected="">${usuario.plano.nome}</option>
                        <c:if test="${empty planoList}">
                            <option disabled="" >Nenhum Plano Cadastrado</option>
                        </c:if>
                        <c:forEach var="plano" items="${planoList}" >
                            <option value="${plano.id}"<c:if test="${usuario.plano.nome eq plano.nome}"></c:if>>${plano.nome}</option>
                        </c:forEach>

                    </select>
                    <a href="<c:url value="/plano/novo" />">Novo Plano</a> 
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Aprovar</button>
                </div>
            </form>
            <div class="form-group">
                <a href="<c:url value="/user/${usuario.id}/reprovar"/>">Reprovar</a>
            </div>
        </div>
    </body>
</html>
