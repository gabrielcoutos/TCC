$(document).ready(function () {
    $(".imgLoader").hide();
    $(".imgComplete").hide();
    $(".modalOpen").click(function () {
        $(".imgLoader").hide();
        $(".imgComplete").hide();
    });
    $("#btnGerarIp").click(function () {
        $(".imgLoader").show();
        var ip = $("#ips").val();
        var ips =ip.split(".");        
        if (ip !== null && ip!=="" && ips.length ===4) {
            $("#erroIp").hide();
            $.ajax({
                url: "gerarIp",
                type: 'GET',
                method: "get",
                data: "ip=" + $("#ips").val(),
                beforeSend: function () {
                    $(".imgLoader").show();
                },
                complete: function () {
                    $(".imgLoader").hide();
                    $(".imgComplete").show();
                }
            });

        }else{
            $(".imgLoader").hide();
            $("#erroIp").html("Por favor insira a faixa de IP com a máscara no formato /24");
        }

    });

    $("#btnIniciar").click(function () {
        $(".imgComplete").hide();
        $(".imgLoader").show();
        $.ajax({
            url: "iniciarSquid",
            type: 'GET',
            method: 'GET',
            beforeSend: function () {
                $(".imgLoader").show();
            },
            complete: function () {
                $(".imgLoader").hide();
                $(".imgComplete").show();
            }
        });
    });

    $("#btnRecarregar").click(function () {
        $(".imgComplete").hide();
        $(".imgLoader").show();
        $.ajax({
            url: "recarregarSquid",
            type: 'GET',
            method: 'GET',
            beforeSend: function () {
                $(".imgLoader").show();
            },
            complete: function () {
                $(".imgLoader").hide();
                $(".imgComplete").show();
            }
        });
    });
    $("#btnReiniciar").click(function () {
        $(".imgComplete").hide();
        $(".imgLoader").show();
        $.ajax({
            url: "reiniciarSquid",
            type: 'GET',
            method: 'GET',
            beforeSend: function () {
                $(".imgLoader").show();
            },
            complete: function () {
                $(".imgLoader").hide();
                $(".imgComplete").show();
            }
        });
    });
    $("#btnParar").click(function () {
        $(".imgComplete").hide();
        $(".imgLoader").show();
        $.ajax({
            url: "pararSquid",
            type: 'GET',
            method: 'GET',
            beforeSend: function () {
                $(".imgLoader").show();
            },
            complete: function () {
                $(".imgLoader").hide();
                $(".imgComplete").show();
            }
        });
    });

});


