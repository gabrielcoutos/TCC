$(document).ready(function () {
    $("#tabelaSecundaria").hide();
    $("#tabelaPrincipal").find("td:nth-child(1)").hide();
    $(document).on('click', ".btnAdicionar", function (e) {
        e.preventDefault;
        $("#tabelaSecundaria").show();
        var teste = $(this).parent();
        $(this).parent().remove();
        teste.each(function () {
            $("#tabelaSelecionados").append(
                    "<tr><td>" + $(this).find("td:nth-child(1)").text() + "</td>" +
                    "<td>" + $(this).find("td:nth-child(2)").text() + "</td>" +
                    "<td>" + $(this).find("td:nth-child(3)").text() + "</td>" +
                    "<td>" + $(this).find("td:nth-child(4)").text() + "</td>" +
                    "<td class=btnRemover><p>" + $(this).find("td:nth-child(5)").children().text("-").html() + "</p></td></tr>"
                    // "<td class=btnRemover>" + "<p>-</p>" + "</td></tr>"
                    );
            $(".btnRemover").children().addClass("btn btn-danger");
            $("#tabelaSelecionados").find("td:nth-child(1)").hide();

        });
    });
    $(document).on('click', ".btnRemover", function (e) {
        e.preventDefault;
        var teste = $(this).parent();
        $(this).parent().remove();
        $("#tabelaPrincipal").css("display", "");
        teste.each(function () {
            $("#tabelaPrincipal").append(
                    "<tr><td>" + $(this).find("td:nth-child(1)").text() + "</td>" +
                    "<td>" + $(this).find("td:nth-child(2)").text() + "</td>" +
                    "<td>" + $(this).find("td:nth-child(3)").text() + "</td>" +
                    "<td>" + $(this).find("td:nth-child(4)").text() + "</td>" +
                    "<td class=btnAdicionar><p>" + $(this).find("td:nth-child(5)").children().text("+").html() + "</p></td></tr>"
                    // "<td class=btnRemover>" + "<p>-</p>" + "</td></tr>"
                    );
            $(".btnAdicionar").children().addClass("btn btn-default");
            $("#tabelaPrincipal").find("td:nth-child(1)").hide();
        });

    });
    $("#btnSubmeter").click(function () {
        if ($("#nomeRestricao").val() === null || $("#nomeRestricao").val() === "") {
            $("#nomeRestricao").parent().append("<label>Insira um nome</label>");
        } else {
            var jsonSite = [];
            $("#tabelaSelecionados").children().each(function () {
                var site = {
                    id: $(this).find("td:nth-child(1)").text(),
                    nome: $(this).find("td:nth-child(2)").text(),
                    descricao: $(this).find("td:nth-child(3)").text(),
                    url: $(this).find("td:nth-child(4)").text(),
                    restricao: {
                        nome: $("#nomeRestricao").val(),
                        tempoAcesso: $("#tempoAcesso").val()
                    }
                };
                jsonSite.push(site);

            });

            $.ajax({
                type: 'POST',
                url: 'novo',
                contentType: 'application/json',
                data: JSON.stringify(jsonSite),
                sucess: function (data) {
                }
            }).done(function () {
                var novaURL = "/controlando/restricoes";
                $(window.document.location).attr('href', novaURL);
            }).fail(function () {
                alert("Selecione ao menos um site");
            });
        }


    });

    $("#searchRestricao").keyup(function () {
        var letras = $(this).val();
        if (letras.trim().length >= 0) {
            var url = "novo/site/" + $(this).val();
//          if (letras.trim().length === 0) {
//               url = "procurarPlano";
//          }

            $.get(url, function (data) {
                $("#tabelaSites").html(data);
                $("#tabelaPrincipal").find("td:nth-child(1)").hide();
            });
        }

    });

});

