$(document).ready(function () {
    $("#searchNome").keyup(function () {
        var letras = $(this).val();

        if (letras.trim().length >= 0) {
            var url = "procurarPlano/" + $(this).val();
//          if (letras.trim().length === 0) {
//               url = "procurarPlano";
//          }

            $.get(url, function (data) {               
                $("#tablePlanos").html(data);
            });
        }

    });
    
    $(document).on("click",".btnModal",function(){          
        $("#nomePlan").text("Tem certeza que deseja excluir o plano "+$(this).parent().parent().find("td:nth-child(2)").text()+"?");
        var id =$(this).parent().parent().find("td:nth-child(1)").text();
        $("#btnExcluirPlano").show();
        $("#btnExcluirPlano").attr("href","plano/"+id+"/deletar");
    });
    var url =window.location.search;
    if(url !== ""){
        console.log("oi");
        $("#nomePlan").text("Plano sendo utilizado, impossível excluir");
         $("#btnExcluirPlano").hide();
        $("#modalExluir").modal("show");
    }    
});

