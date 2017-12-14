<%-- 
    Document   : sites
    Created on : 30/09/2017, 12:06:38
    Author     : Gabriel Couto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="pt-br">

    <head>
        <title>ControLANdo</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <!-- VENDOR CSS -->
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">      
        <!-- MAIN CSS -->
        <link rel="stylesheet" href="<c:url value="/resources/css/estilo.css"/>">
        <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
        <link rel="stylesheet" href="<c:url value="/resources/css/demo.css"/>">       
        <!-- GOOGLE FONTS -->            
    </head>

    <body>
        <!-- WRAPPER -->
        <div id="wrapper">
            <!-- NAVBAR -->
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="brand">
                    <a href="<c:url value="/adminLogin"/>"><img src="<c:url value="/resources/img/logo.png"/>" alt="ControLANdo" class="img-responsive logo"></a>
                </div>
                <div class="container-fluid">
                    <div class="navbar-btn">
                        <button type="button" class="btn-toggle-fullwidth"><i class="glyphicon glyphicon-circle-arrow-left"></i></button>
                    </div>
                    <div id="navbar-menu">
                        <ul class="nav navbar-nav navbar-right">
                            <!--
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle icon-menu" data-toggle="dropdown">
                                    <i class="glyphicon glyphicon-bell"></i>
                                    <span class="badge bg-danger">5</span>
                                </a>
                                <ul class="dropdown-menu notifications">
                                    <li><a href="#" class="notification-item"><span class="dot bg-warning"></span>Notificação de exemplo</a></li>
                                    <li><a href="#" class="more">Ver todas as notificações</a></li>
                                </ul>
                            </li>
                            
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-question-sign"></i> <span>Ajuda</span> <i class="icon-submenu glyphicon glyphicon-menu-down"></i></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Tópico 01</a></li>
                                    <li><a href="#">Tópico 02</a></li>
                                </ul>
                            </li>
                            -->
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="<c:url value="/resources/img/images.jpg"/>" class="img-circle" alt="Avatar"> <span>${usuarioLogado.nome}</span> <i class="icon-submenu glyphicon glyphicon-menu-down"></i></a>
                                <ul class="dropdown-menu">
                                    <li><a href="<c:url value="/user/${usuarioLogado.id}"/>"><i class="glyphicon glyphicon-user"></i> <span>Meu Perfil</span></a></li>
                                    <li><a href="#"><i class="glyphicon glyphicon-cog"></i> <span>Configurações</span></a></li>
                                    <li><a href="<c:url value="/sair"/>"><i class="glyphicon glyphicon-log-out"></i> <span>Sair</span></a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- END NAVBAR -->
            <!-- LEFT SIDEBAR -->
            <div id="sidebar-nav" class="sidebar">
                <div class="sidebar-scroll">
                    <nav>
                        <ul class="nav">
                            <li><a href="<c:url value="/dashboards"/>" class=""><i class="glyphicon glyphicon-th-large"></i> <span>Gráficos</span></a></li>
                            <li><a href="<c:url value="/adminLogin"/>" class="active"><i class="glyphicon glyphicon-home"></i> <span>Início</span></a></li>
                            <li><a href="<c:url value="/usuarios"/>" class=""><i class="glyphicon glyphicon-user"></i> <span>Usuários</span></a></li>
                            <li><a href="<c:url value="/planos"/>" class=""><i class="glyphicon glyphicon-list-alt"></i> <span>Planos</span></a></li>                           
                            <li><a href="#collapseBloqueio" class="" data-toggle="collapse"><i class="glyphicon glyphicon-ban-circle"></i> <span>Bloqueios</span> <i class="glyphicon glyphicon-menu-down right"></i></a></li> 
                            <div class="collapse in" id="collapseBloqueio">
                                <ul class="nav"> 
                                    <li><a href="<c:url value="/restricoes"/>" class="active"><i class="glyphicon glyphicon-warning-sign"></i> <span>Restrições</span></a></li> 
                                    <li><a href="<c:url value="/sites"/>" class=""><i class="glyphicon glyphicon-globe"></i> <span>Sites</span></a></li>
                                    <li><a href="<c:url value="/categorias"/>" class=""><i class="glyphicon glyphicon-align-justify"></i> <span>Categorias</span></a></li>
                                </ul>
                            </div>
                        </ul>
                    </nav>
                </div>
            </div>
            <!-- END LEFT SIDEBAR -->
            <!-- MAIN -->
            <div class="main">
                <!-- MAIN CONTENT -->
                <div class="main-content">
                    <div class="container-fluid">
                        <div >
                            <h3>Restrição > Novo</h3>
                        </div>
                        <div class="row">

                            <div class="col-md-12">
                                <!-- Começo -->
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Cadastro de restrições</h3>
                                        <div class="right">
                                            <button type="button" class="btn-toggle-collapse"><i class="glyphicon glyphicon-menu-up"></i></button>
                                            <button type="button" class="btn-remove"><i class="glyphicon glyphicon-remove"></i></button>
                                        </div>
                                    </div>

                                    <div class="panel-body no-padding">
                                        <div class="row">
                                            <div  class="col-md-6">
                                                <input type="text" id="searchRestricao" name="searchNomeP" class="form-control" placeholder="Procure por um site"/>
                                                <div style="overflow-y: auto; height: 300px;">
                                                    <table class="table table-bordered" id="tabelaSites">
                                                        <thead>
                                                            <tr>    
                                                                <th>Nome</th>                            
                                                                <th>URL</th>
                                                                <th>Categoria</th>
                                                                <th>Adicionar</th>
                                                            </tr>
                                                        </thead>
                                                        <c:if test="${not empty restricao.siteList}">
                                                            <tbody class="disabled">
                                                                <c:forEach var="siteR" items="${restricao.siteList}">
                                                                    <tr>
                                                                        <td scope="row">${siteR.nome}</td>                               
                                                                        <td>${siteR.url}</td>
                                                                        <td>${siteR.categoria.nome}</td>
                                                                        <td >Não pode ser editado</td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </c:if>
                                                        <tbody id="tabelaPrincipal">
                                                            <c:forEach items="${siteList}" var="site">
                                                                <tr id="linhaDaTabela">
                                                                    <td >${site.id}</td>
                                                                    <td scope="row">${site.nome}</td>                               
                                                                    <td>${site.url}</td>
                                                                    <td>${site.categoria.nome}</td>
                                                                    <td class="btnAdicionar"><p class="btn btn-default">+</p></td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div style="overflow-y: auto; height: 300px;" >
                                                    <table class="table table-bordered" id="tabelaSecundaria" >
                                                        <thead>
                                                            <tr>
                                                                <th>Nome</th>                                    
                                                                <th>URL</th>
                                                                <th>Categoria</th>
                                                                <th>Remover</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id ="tabelaSelecionados">              
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                        <form id="formRestricao">
                                            <div class="form-group">
                                                <label>Nome da restrição</label>
                                                <input type="text" class="form-group" name="nomeRestricao" id="nomeRestricao" value="${restricao.nome}"/>
                                            </div>
                                            <div class="form-group">
                                                <label>Tempo de Acesso</label>
                                                <input type="number" class="form-group" name="tempoAcesso" id="tempoAcesso" <c:if test="${not empty restricao.tempoAcesso}">value="${restricao.tempoAcesso}"</c:if> <c:if test="${empty restricao.tempoAcesso}">value ="0"</c:if>placeholder="Zero = sem limite"/>
                                            </div>
                                            <div class="form-group">
                                                <button type="button" class="btn btn-primary" id="btnSubmeter">Criar</button>
                                            </div>

                                        </form>


                                    </div>
                                </div>

                                <div class="panel-footer">

                                </div>
                            </div>
                            <!-- Fim -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- END MAIN CONTENT -->
        </div>
        <!-- END MAIN -->
        <div class="clearfix"></div>
        <footer>
            
        </footer>

        <!-- END WRAPPER -->
        <!-- Javascript -->
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.slimscroll.min.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.easypiechart.min.js"/>"></script>
        <script src="<c:url value="/resources/js/chartist.min.js"/>"></script>
        <script src="<c:url value="/resources/js/klorofil-common.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
        <script src="<c:url value="/resources/js/restricao/app.js"/>"></script>
        <script src="<c:url value="/resources/js/restricao/validacao.js"/>"></script>

    </body>
    <script>

    </script>
</html>


