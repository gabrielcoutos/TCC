$(document).ready(function(){
    $('#formRestricao').validate({
        rules:{
            nomePlano:{
                required: true                
            },
            selectRestricao:{
                required: true
            }
        },
        messages:{
            nomePlano:{
                required: "Insira um nome ao plano"                
            },
            selectRestricao:{
                required: "selecione uma restrição"
            }
        }
        
    });
});

