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
        <link rel="stylesheet" href="<c:url value="/resources/css/adm.css"/>">
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
                        <div class="row">
                            <div class="col-md-12">
                                <!-- Começo -->
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Restrições do sistema</h3>
                                        <div class="right">
                                            <button type="button" class="btn-toggle-collapse"><i class="glyphicon glyphicon-menu-up"></i></button>
                                            <button type="button" class="btn-remove"><i class="glyphicon glyphicon-remove"></i></button>
                                        </div>
                                    </div>
                                    <div class="panel-body no-padding">

                                        <strong>${excluirNegado}</strong>

                                        <div class="input-group">                               
                                            <input type="text" id="searchRestricao" name="searchNomeP" class="form-control" placeholder="Procure por uma restrição"/>
                                            <div class="input-group-btn"> 
                                                <a  class="btn btn-primary" href="<c:url value="/restricao/novo"/>" >Novo</a>
                                            </div>
                                        </div>
                                        <img src="<c:url value="/resources/img/ajax-loader.gif"/>" id="loader" class="center-block" style="margin-top: 10%;"/>
                                        <div style="overflow-y: auto;max-height: 400px;">
                                            <c:if test="${empty restricaoList}">
                                                <strong>Nenhuma restrição encontrado</strong>
                                            </c:if>
                                            <c:if test="${not empty restricaoList}">
                                                <table class="table " id="tableRestricao" >
                                                    <thead>
                                                        <tr>
                                                            <th>Código</th>
                                                            <th>Nome</th>
                                                            <th>Tempo de acesso</th>
                                                            <th>Sites</th>
                                                            <th>Ação</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="restricao" items="${restricaoList}">
                                                            <tr>
                                                                <td>${restricao.id}</a> </td>
                                                                <td>${restricao.nome}</td>
                                                                <td>${restricao.tempoAcesso}</td>
                                                                <td class="btnSites"><button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="glyphicon glyphicon-plus"></i></button></td>
                                                                <td><a class="btn btn-default" href="<c:url value="/restricao/${restricao.id}"/>"><i class="glyphicon glyphicon-pencil"></i>Editar</a> 
                                                                    <a class="btn btn-danger btnModalRestricao" data-toggle="modal" data-target="#modalExcluir" ><i class="glyphicon glyphicon-remove"></i>Excluir</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </c:if> 
                                        </div>
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
        <!-- Modals -->
        <div id="modalExcluir" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Excluir Restrição</h4>
                    </div>
                    <div class="modal-body">
                        <p id="nomeRestr"></p>
                    </div>
                    <div class="modal-footer">
                        <a href="" class="btn btn-danger" id="btnExcluirRestricao">Excluir</a>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
        <!-- fim -->

        <!-- Large modal -->

        <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
                        <h4 class="modal-title">Sites</h4>
                    </div>             
                    <div class="modal-body" id="modalSites">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="fecharSites">Fechar</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value ="/resources/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/js/restricao/tabela.js"/>"></script>
        <!-- END WRAPPER -->
        <!-- Javascript -->
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.slimscroll.min.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.easypiechart.min.js"/>"></script>
        <script src="<c:url value="/resources/js/chartist.min.js"/>"></script>
        <script src="<c:url value="/resources/js/klorofil-common.js"/>"></script>
        <script src="<c:url value="/resources/js/restricao/tabela.js"/>"></script>
    </body>

</html>
