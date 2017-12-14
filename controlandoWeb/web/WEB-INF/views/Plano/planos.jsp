<%-- 
    Document   : plano
    Created on : 16/09/2017, 16:31:24
    Author     : Gabriel Couto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="<c:url value="/resources/css/chartist-custom.css"/>">
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
                            <li><a href="<c:url value="/adminLogin"/>" class=""><i class="glyphicon glyphicon-home"></i> <span>Início</span></a></li>
                            <li><a href="<c:url value="/usuarios"/>" class=""><i class="glyphicon glyphicon-user"></i> <span>Usuários</span></a></li>
                            <li><a href="<c:url value="/planos"/>" class="active"><i class="glyphicon glyphicon-list-alt"></i> <span>Planos</span></a></li>                           
                            <li><a href="#collapseBloqueio" class="" data-toggle="collapse"><i class="glyphicon glyphicon-ban-circle"></i> <span>Bloqueios</span> <i class="glyphicon glyphicon-menu-down right"></i></a></li> 
                            <div class="collapse" id="collapseBloqueio">
                                <ul class="nav"> 
                                    <li><a href="<c:url value="/restricoes"/>" class=""><i class="glyphicon glyphicon-warning-sign"></i> <span>Restrições</span></a></li> 
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
                        <div class="row">
                            <div class="col-md-12">
                                <!-- Começo -->
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Planos disponíveis</h3>
                                        <div class="right">
                                            <button type="button" class="btn-toggle-collapse"><i class="glyphicon glyphicon-menu-up"></i></button>
                                            <button type="button" class="btn-remove"><i class="glyphicon glyphicon-remove"></i></button>
                                        </div>
                                    </div>
                                    <div class="panel-body no-padding">

                                        <strong id="negado" style="display: none;">${excluirNegado}</strong>
                                        <form class="form-group"  method="GET">
                                            <div class="input-group">                               
                                                <input type="text" id="searchNome" name="searchNomeP" class="form-control" placeholder="Procure por um plano"/>
                                                <div class="input-group-btn"> 
                                                    <a  class="btn btn-primary" href="<c:url value="/plano/novo"/>" >Novo</a>
                                                </div>
                                            </div>
                                        </form>
                                        <c:if test="${empty planoList}">
                                            <strong>Nenhum plano encontrado</strong>
                                        </c:if>
                                        <c:if test="${not empty planoList}">
                                            <div style="overflow-y: auto;max-height: 400px;">
                                                <table class="table table-striped" id="tablePlanos">
                                                    <thead>
                                                        <tr>
                                                            <th>Código</th>
                                                            <th>Nome</th>
                                                            <th>Restrição</th>
                                                            <th>Opções</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="plano" items="${planoList}">
                                                            <tr>
                                                                <td scope="row">${plano.id}</td>
                                                                <td>${plano.nome}</td>
                                                                <td>${plano.restricao.nome}</td>
                                                                <td><a class="btn btn-default" href="<c:url value="/plano/${plano.id}"/>"><i class="glyphicon glyphicon-pencil"></i>Editar</a>
                                                                    <a class="btn btn-danger btnModal" data-toggle="modal" data-target="#modalExluir"><i class="glyphicon glyphicon-remove"></i>Excluir</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </c:if> 
                                    </div>
                                    <div class="panel-footer">
                                        <div class="row">                                    
                                        </div>
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
        </div>

        <!-- END WRAPPER -->
        <!-- Modals -->
        <div id="modalExluir" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Excluir plano</h4>
                    </div>
                    <div class="modal-body">
                        <p id="nomePlan"></p>
                    </div>
                    <div class="modal-footer">
                        <a href="<c:url value="/plano/${plano.id}/deletar"/>" class="btn btn-danger" id="btnExcluirPlano">Excluir</a>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
        <!-- fim -->
        <!-- Javascript -->
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.slimscroll.min.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.easypiechart.min.js"/>"></script>
        <script src="<c:url value="/resources/js/chartist.min.js"/>"></script>
        <script src="<c:url value="/resources/js/klorofil-common.js"/>"></script>
        <script src="<c:url value="/resources/js/plano/app.js"/>"></script>
    </body>

</html>
