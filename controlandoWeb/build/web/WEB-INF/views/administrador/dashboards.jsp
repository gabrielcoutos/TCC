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
                            <li><a href="<c:url value="/dashboards"/>" class="active"><i class="glyphicon glyphicon-th-large"></i> <span>Gráficos</span></a></li>
                            <li><a href="<c:url value="/adminLogin"/>" class=""><i class="glyphicon glyphicon-home"></i> <span>Início</span></a></li>
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
                        <div class="row">
                            <div class="col-md-4">
                                <!-- Começo -->
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Status do sistema</h3>
                                        <div class="right">
                                            <button type="button" class="btn-toggle-collapse"><i class="glyphicon glyphicon-menu-up"></i></button>
                                            <button type="button" class="btn-remove"><i class="glyphicon glyphicon-remove"></i></button>
                                        </div>
                                    </div>
                                    <div class="panel-body">
                                        <div id="system-load" class="easy-pie-chart" data-percent="70">
                                            <span class="percent">70</span>
                                        </div>
                                        <h4>CPU Load</h4>
                                        <ul class="list-unstyled list-justify">
                                            <li>High: <span>95%</span></li>
                                            <li>Average: <span>87%</span></li>
                                            <li>Low: <span>20%</span></li>
                                            <li>Threads: <span>996</span></li>                                           
                                        </ul>
                                    </div>
                                </div>   
                            </div>
                            <!-- fim -->
                            <!-- Começo -->
                            <div class="col-md-4">
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Usuarios</h3>
                                        <div class="right">
                                            <button type="button" class="btn-toggle-collapse"><i class="glyphicon glyphicon-menu-up"></i></button>
                                            <button type="button" class="btn-remove"><i class="glyphicon glyphicon-remove"></i></button>
                                        </div>
                                    </div>
                                    <div class="panel-body">
                                        <canvas id="myChart">
                                            <p id="aprovados">${aprovados}</p>
                                            <p id="deletados">${deletados}</p>
                                            <p id="naoAnalisados">${naoAnalisados}</p>
                                        </canvas>

                                    </div>

                                </div>
                            </div>
                            <!-- fim -->
                            <!-- Começo -->
                            <div class="col-md-4">
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Top 4 planos</h3>
                                        <div class="right">
                                            <button type="button" class="btn-toggle-collapse"><i class="glyphicon glyphicon-menu-up"></i></button>
                                            <button type="button" class="btn-remove"><i class="glyphicon glyphicon-remove"></i></button>
                                        </div>
                                    </div>
                                    <div class="panel-body">
                                        <canvas id="chartPlanos">
                                            <p id="planoNome1">${plano1.nome}</p>
                                            <p id="planoqtd1">${plano1.qtd}</p>
                                            <p id="planoNome2">${plano2.nome}</p>
                                            <p id="planoqtd2">${plano2.qtd}</p>
                                            <p id="planoNome3">${plano3.nome}</p>
                                            <p id="planoqtd3">${plano3.qtd}</p>
                                            <p id="planoNome4">${plano4.nome}</p>
                                            <p id="planoqtd4">${plano4.qtd}</p>
                                        </canvas>

                                    </div>
                                </div>   
                            </div>
                            <!-- fim -->                            
                        </div>
                        <div class="row">
                            <!-- Começo -->
                            <div class="col-md-4">
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Top 5 sites acessados hoje</h3>
                                        <div class="right">
                                            <button type="button" class="btn-toggle-collapse"><i class="glyphicon glyphicon-menu-up"></i></button>
                                            <button type="button" class="btn-remove"><i class="glyphicon glyphicon-remove"></i></button>
                                        </div>
                                    </div>
                                    <div class="panel-body">
                                        <canvas id="chartSites">
                                            <p id="siteNome1">${site1.nome}</p>
                                            <p id="siteqtd1">${site1.qtd}</p>
                                            <p id="siteNome2">${site2.nome}</p>
                                            <p id="siteqtd2">${site2.qtd}</p>
                                            <p id="siteNome3">${site3.nome}</p>
                                            <p id="siteqtd3">${site3.qtd}</p>
                                            <p id="siteNome4">${site4.nome}</p>
                                            <p id="siteqtd4">${site4.qtd}</p>
                                            <p id="siteNome5">${site5.nome}</p>
                                            <p id="siteqtd5">${site5.qtd}</p>
                                        </canvas>

                                    </div>
                                </div>   
                            </div>
                            <!-- fim -->
                            <!-- Começo -->
                            <div class="col-md-8">
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Quantidade de usuários por dia</h3>
                                        <div class="right">
                                            <button type="button" class="btn-toggle-collapse"><i class="glyphicon glyphicon-menu-up"></i></button>
                                            <button type="button" class="btn-remove"><i class="glyphicon glyphicon-remove"></i></button>
                                        </div>
                                    </div>
                                    <div class="panel-body">
                                        <canvas id="chartuDias">
                                            <p id="uDias1">${uDias1.dia}</p>
                                            <p id="uDiasqtd1">${uDias1.qtd}</p>
                                            <p id="uDias2">${uDias2.dia}</p>
                                            <p id="uDiasqtd2">${uDias2.qtd}</p>
                                            <p id="uDias3">${uDias3.dia}</p>
                                            <p id="uDiasqtd3">${uDias3.qtd}</p>
                                            <p id="uDias4">${uDias4.dia}</p>
                                            <p id="uDiasqtd4">${uDias4.qtd}</p>
                                            <p id="uDias5">${uDias5.dia}</p>
                                            <p id="uDiasqtd5">${uDias5.qtd}</p>
                                            <!-- usuarios -->
                                            <p id="megaqtd1">${mega1.qtd}</p>
                                            <p id="megaqtd2">${mega2.qtd}</p>
                                            <p id="megaqtd3">${mega3.qtd}</p>
                                            <p id="megaqtd4">${mega4.qtd}</p>
                                            <p id="mega5qtd5">${mega5.qtd}</p>
                                        </canvas>
                                    </div>
                                </div>   
                            </div>
                            <!-- fim -->
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
    <!-- Javascript -->
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.slimscroll.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.easypiechart.min.js"/>"></script>
    <script src="<c:url value="/resources/js/chartist.min.js"/>"></script>
    <script src="<c:url value="/resources/js/klorofil-common.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/usuario/adm.js"/>"></script>

</body>

</html>
