$(document).ready(function () {
    $('#formUsuario').validate({
        rules: {
            nomeUsuario: {
                required: true,
                minlength: 5
            },
            emailUsuario: {
                required: true,
                email: true
            },
            dataUsuario: {
                required: true,
                date: true
            },
            cpfUsuario: {
                required: true,
                minlength: 11,
                maxlength: 11
            },
            senhaUsuario: {
                required: true,
                minlength: 6,
                maxlength: 25
            },
            senhaUsuario2: {
                required: true,
                equalTo: "#senhaUsuario"
            },
            tipoUsuario: {
                required: true
            },
            planoUsuario: {
                required: true
            }

        },
        messages: {
            nomeUsuario: {
                required: "Insira um nome",
                minlength: "Nome de pelo menos 5 caracteres"
            },
            emailUsuario: {
                required: "Insira um email",
                email: "Insira um email válido"
            },
            senhaUsuario: {
                required: "Insira uma senha",
                minlength: "Pelo menos 6 dígitos",
                maxlength: "No máximo de 25"
            },
            senhaUsuario2: {
                required: "Confirme sua senha ",
                equalTo: "Senhas diferentes"

            },
            dataUsuario: {
                required: "Insira a data de nascimento",
                date: "Insira uma data válida"
            },
            cpfUsuario: {
                required: "Insira o CPF",
                minlength: "Insira os 11 digitos do CPF",
                maxlength: "Remova caracteres como ponto ou barra"
            },
            tipoUsuario: {
                required: "Selecione o tipo de usuário"
            },
            planoUsuario: {
                required: "Selecione um plano"
            }
        }
    });
    $("#formEntrar").validate({
        rules: {
            nomeUsuarioL: {
                required: true,
                email: true
            },
            senhaUsuario1: {
                required: true,
                minlength: 6,
                maxlength: 25
            }
        },
        messages: {
            nomeUsuarioL: {
                required: "Insira o seu email",
                email: "Email inválido"
            },
            senhaUsuario1: {
                required: "Insira sua senha",
                minlength: "Senha muito curta",
                maxlength: "Senha muito longa"
            }
        }




    });
});


