$(document).ready(function(){
     $('#formCategoria').validate({
        rules:{
            nomeCategoria:{
                required:true,
                minlength : 2
            },
             descricao:{
                required:false
            }
        },
        messages:{
           nomeCategoria:{
                required:"Insira um nome a categoria",
                minlength : "Insira no mínimo 2 caracteres"
            }
             
        }
    });
});


