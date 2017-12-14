$(document).ready(function(){
    $("#searchNome").keyup(function () {
        var letras = $(this).val();
        if (letras.trim().length >= 0) {
            var url = "procurarSite/" + $(this).val();
//          if (letras.trim().length === 0) {
//               url = "procurarPlano";
//          }

            $.get(url, function (data) {               
                $("#tableSites").html(data);
            });
        }

    });
    
    
});

