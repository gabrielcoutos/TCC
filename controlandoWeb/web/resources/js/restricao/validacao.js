$(document).ready(function(){
    $('#formRestricao').validate({
        rules:{
            nomeRestricao:{
                required: true                
            },
            tempoAcesso:{
                required: true
            }
        },
        messages:{
            nomePlano:{
                required: "Insira um nome a restrição"                
            },
            selectRestricao:{
                required: "coloque um limite de tempo"
            }
        }
        
    });
});



