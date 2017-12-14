$(document).ready(function () {
    $("#searchNome").keyup(function () {
        var letras = $(this).val();

        if (letras.trim().length >= 0) {
            var url = "procurarUsuario/" + $(this).val();
//            if (letras.trim().length === 0) {
//                url = "procurarUsuario";
//            }

            $.get(url, function (data) {
                $("#tableUsuarios").html(data);
            });
        }

    });


});




