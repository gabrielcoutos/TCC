$(document).ready(function () {
    $('#loader').hide();
    $(document).ajaxStart(function () {
        $('#loader').show();
    }).ajaxStop(function () {
        $('#loader').hide();
    });

    $(document).on('click', '.btnSites', function () {

        var idRestricao = $(this).parent().find("td:nth-child(1)").text();
        var url = "listaSite/" + idRestricao;
        $.get(url, function (data) {
            $("#modalSites").html(data);
        });
    });
    $("#searchRestricao").keyup(function () {
        var letras = $(this).val();

        if (letras.trim().length >= 0) {
            var url = "procurarRestricao/" + $(this).val();
//            if (letras.trim().length === 0) {
//                url = "procurarRestricao";
//            }

            $.get(url, function (data) {

                $("#tableRestricao").html(data);
            });
        }

    });
    $(document).on("click", ".btnModalRestricao", function () {
        console.log("oi");
        $("#nomeRestr").text("Tem certeza que deseja excluir a restricao " + $(this).parent().parent().find("td:nth-child(2)").text() + "?");
        var id = $(this).parent().parent().find("td:nth-child(1)").text();
        $("#btnExcluirRestricao").show();
        $("#btnExcluirRestricao").attr("href", "restricao/" + id + "/deletar");
    });
    var url = window.location.search;   
    if (url !== "") {
        $("#nomeRestr").text("Restrição sendo utilizada, impossível excluir!");
        $("#btnExcluirRestricao").hide();
        $("#modalExcluir").modal("show");
    }

});

