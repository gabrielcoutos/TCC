$(document).ready(function(){
    $('#formSite').validate({
        rules:{
            nomeSite:{
                required:true               
            },
            descricao:{
                
            },
            urlSite:{
                required:true,
                url:true
            },
            selectCategoria:{
                required:true
            }
        },
        messages:{
             nomeSite:{
                required:"Insira um nome ao site"              
            },
            descricao:{
                
            },
            urlSite:{
                required:"Insira a URL do site ",
                url:"Insira uma URL válida"
            },
            selectCategoria:{
                required:"Selecione a categoria do site"
            }
        }
    });
});

