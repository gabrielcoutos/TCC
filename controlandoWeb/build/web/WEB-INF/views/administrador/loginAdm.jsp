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
        <link rel="stylesheet" href="<c:url value="/resources/css/chartist-custom.css"/>">
        <!-- MAIN CSS -->
        <link rel="stylesheet" href="<c:url value="/resources/css/estilo.css"/>">
        <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
        <link rel="stylesheet" href="<c:url value="/resources/css/demo.css"/>">       
        <!-- GOOGLE FONTS -->       
        <script src="<c:url value="/resources/js/Chart.min.js"/>"></script>
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
                        <div class="col-md-12">
                            <div class="row"> 
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Ações servidor SQUID</h3>
                                        <div class="right">
                                            <button type="button" class="btn-toggle-collapse"><i class="glyphicon glyphicon-menu-down"></i></button>
                                            <button type="button" class="btn-remove"><i class="glyphicon glyphicon-remove"></i></button>
                                        </div>
                                    </div>

                                    <div class="panel-body no-padding" style="display: none;">
                                        <div class = "col-md-2">
                                            <button type="button" class="btn btn-primary center-block modalOpen" data-toggle="modal" data-target="#modalIPs">Gerar Ips</button>
                                        </div>
                                        <div class = "col-md-2">
                                            <button type="button" class="btn btn-primary center-block modalOpen" data-toggle="modal" data-target="#modalStarSquid">Iniciar Servidor</button>
                                        </div>
                                        <div class = "col-md-2">
                                            <button type="button" class="btn btn-primary center-block modalOpen" data-toggle="modal" data-target="#modalRecarregar">Recarregar</button>
                                        </div>
                                        <div class = "col-md-2">
                                            <button type="button" class="btn btn-primary center-block modalOpen" data-toggle="modal" data-target="#modalReiniciar">Reiniciar</button>
                                        </div>      
                                        <div class = "col-md-2">
                                            <button type="button" class="btn btn-primary center-block modalOpen" data-toggle="modal" data-target="#modalParar">Parar</button>
                                        </div>        
                                    </div>
                                    <div class="panel-footer">

                                    </div>

                                </div>
                            </div>
                        </div>
                        </br>
                        <div class="row">
                            <div class="col-md-12">
                                <!-- Começo -->
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Aprovações pendentes</h3>
                                        <div class="right">
                                            <button type="button" class="btn-toggle-collapse"><i class="glyphicon glyphicon-menu-up"></i></button>
                                            <button type="button" class="btn-remove"><i class="glyphicon glyphicon-remove"></i></button>
                                        </div>
                                    </div>
                                    <div class="panel-body no-padding">

                                        <form class="form-group" method="GET">
                                            <div class="input-group">                               
                                                <input type="text" id="searchNome" name="searchNome" class="form-control" placeholder="Procurar usuário"/>
                                                <div class="input-group-btn"> 
                                                    <input type="submit" class="btn btn-default" value="Buscar"/>
                                                </div>
                                            </div>
                                        </form>
                                        <div style="overflow-y: auto;height: 200px;">
                                            <c:if test="${empty usuarioListNCadastrado}">
                                                <strong>Nenhum usuário encontrado</strong>
                                            </c:if>
                                            <c:if test="${not empty usuarioListNCadastrado}">
                                                <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th>Nome</th>
                                                            <th>E-mail</th>
                                                            <th>Status</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="usuario" items="${usuarioListNCadastrado}">
                                                            <tr>
                                                                <td> <a href="<c:url value="/user/${usuario.id}"/>">${usuario.nome}</a></td>
                                                                <td>${usuario.email}</td>
                                                                <td><span class="label label-warning">Pendente</span></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </c:if> 
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
                <!-- modals -->
                <!-- modal Gerar IP -->
                <div id="modalIPs" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Gerar IPs da rede</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label>Insira o endereço Ip da rede: </label>
                                    <input type="text" class="form-control" name="ips" id="ips" placeholder="ex: 192.168.5.0 /24"/>
                                </div>
                                <div >
                                    <p id ="erroIp"></p>
                                    <img src="<c:url value="/resources/img/131.gif"/>" class="imgLoader center-block" id="">
                                    <img src="<c:url value="/resources/img/complete.png"/>" class="imgComplete center-block" id="">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="btnGerarIp">Gerar </button>
                                <button type="button" class="btn btn-default fechar" data-dismiss="modal">Fechar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- fim -->
                <!-- modal Iniciar servidor -->
                <div id="modalStarSquid" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Iniciar Servidor ? </h4>
                            </div>
                            <div class="modal-body">                                
                                <div >
                                    
                                    <img src="<c:url value="/resources/img/131.gif"/>" class="center-block imgLoader" id="imgLoader">
                                    <img src="<c:url value="/resources/img/complete.png"/>" class="center-block imgComplete" id="imgComplete">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <a type="button" class="btn btn-primary" id="btnIniciar">Iniciar</a>
                                <button type="button" class="btn btn-default fechar" data-dismiss="modal">Fechar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- fim -->
                <!-- modal Recarregar -->
                <div id="modalRecarregar" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Recarregar servidor ? </h4>
                            </div>
                            <div class="modal-body">                                
                                <div >
                                    <label>As novas configurações do servidor serão recarregadas instantaneamente.Isso não afeta os usários que estão utilizando a rede.</label>
                                    <img src="<c:url value="/resources/img/131.gif"/>" class="center-block imgLoader" id="imgLoader">
                                    <img src="<c:url value="/resources/img/complete.png"/>" class="center-block imgComplete" id="imgComplete">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <a type="button" class="btn btn-primary" id="btnRecarregar"  >Recarregar</a>
                                <button type="button" class="btn btn-default fechar" data-dismiss="modal">Fechar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- fim -->
                <!-- modal Reiniciar -->
                <div id="modalReiniciar" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Reiniciar servidor ? </h4>
                            </div>
                            <div class="modal-body">                                
                                <div >
                                    <label>O servidor irá reinicar, processo mais demorado e todos que estão utilizando a rede irão perder a conexão.</label>
                                    <img src="<c:url value="/resources/img/131.gif"/>" class="center-block imgLoader" id="imgLoader">
                                    <img src="<c:url value="/resources/img/complete.png"/>" class="center-block imgComplete" id="imgComplete">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <a type="button" class="btn btn-primary" id="btnReiniciar">Reiniciar</a>
                                <button type="button" class="btn btn-default fechar" data-dismiss="modal">Fechar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- fim -->
                <!-- modal Reiniciar -->
                <div id="modalParar" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Desligar servidor ? </h4>
                            </div>
                            <div class="modal-body">                                
                                <div >
                                    <label>Desliga o servidor proxy</label>
                                    <img src="<c:url value="/resources/img/131.gif"/>" class="center-block imgLoader" id="imgLoader">
                                    <img src="<c:url value="/resources/img/complete.png"/>" class="center-block imgComplete" id="imgComplete">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <a type="button" class="btn btn-primary" id="btnParar" >Desligar</a>
                                <button type="button" class="btn btn-default fechar" data-dismiss="modal">Fechar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- fim -->

                <!-- END MAIN CONTENT -->               
            </div>
            <!-- END MAIN -->
            <div class="clearfix"></div>
            <footer>
               
            </footer>
        </div>
        <!-- END WRAPPER -->
        <!-- Javascript -->
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.slimscroll.min.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.easypiechart.min.js"/>"></script>
        <script src="<c:url value="/resources/js/chartist.min.js"/>"></script>
        <script src="<c:url value="/resources/js/klorofil-common.js"/>"></script>
        <script src="<c:url value="/resources/js/usuario/controleModals.js"/>"></script>

    </body>

</html>
