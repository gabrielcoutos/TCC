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
        <link rel="stylesheet" href="<c:url value="/resources/css/estilo.css"/>">
        <!--<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/materialize.min.css"/>"  media="screen,projection"/>-->

        <!--Let browser know website is optimized for mobile-->
        <title>controLANdo</title>

        <!-- Bootstrap -->



        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="<c:url value="/resources/js/html5shiv.min.js"/>"></script>
        <script src="<c:url value="/resources/js/respond.min.js"/>"></script>
        <![endif]-->
    </head>
    <body>
        <div class="container">
            <div class="row" id="pwd-container">
                <div class="col-md-4"></div>

                <div class="col-md-4">
                    <section class="login-form" >

                        <div>

                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs" role="tablist">
                                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Entrar</a></li>
                                <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Cadastrar</a></li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="home">

                                    <form action= "<c:url value="/entrar"/>" method="post" role="login" id="formEntrar">

                                        <input type="email" name="nomeUsuarioL" id="nomeUsuarioL" class="form-control" placeholder="Seu email " required=""/>
                                        <input type="password" name="senhaUsuario1" id="senhaUsuario1" class="form-control" placeholder="Sua Senha" required=""/>  
                                        <button type="submit"  class="btn btn-primary btn-block"/>Acessar</button>
                                    </form>
                                    <Strong>${erro}</Strong>
                                </div>
                                <div role="tabpanel" class="tab-pane" id="profile">

                                    <form action="<c:url value="/cadastrar"/>" method="post" role="cadastro" id="formUsuario">

                                        <input type="text" name="nomeUsuario" id="nomeUsuarioC" class="form-control" placeholder="Insira seu nome" required=""/>
                                        <input type="email" name="emailUsuario" id="emailUsuario" class="form-control" placeholder="Insira seu email" required=""/>
                                        <input type="number" name="cpfUsuario" id="cpfUsuario" class="form-control" placeholder="Insira seu cpf" required=""/>
                                        <input type="date" name="dataUsuario" id="dataUsuario" class="form-control" placeholder="Insira sua data de nascimento" required=""/>
                                        <input type="password" name="senhaUsuario" id="senhaUsuario" class="form-control" placeholder="Insira sua senha" required=""/> 
                                        <input type="password" name="senhaUsuario2" id="senhaUsuario2" class="form-control" placeholder="Insira a senha novamente" required=""/> 
                                        <button type="submit"  class="btn btn-primary btn-block"/>Cadastrar</button>
                                    </form>
                                </div>

                            </div>

                        </div>

                        <div class="form-links">
                            <br/>
                            <a href="<c:url value="/ajuda"/>">Esqueceu a Senha</a>
                        </div>
                        <Strong>${help}</Strong>
                    </section>
                </div>

                <div class="col-md-4"></div>
            </div>

        </div>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
        <script src="<c:url value="/resources/js/usuario/validacao.js"/>"></script>
        <script src="<c:url value="/resources/js/todos.js"/>"></script>
    </body>
</html>