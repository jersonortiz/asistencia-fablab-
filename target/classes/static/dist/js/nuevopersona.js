let url = serverurl;
//let usuario = user;
let profesional = "";

$(document).ready(function () {
    $("#publiform").submit(function (e) {
        e.preventDefault();

        registrar();
    });
    cargarpoblacion();
    cargartipo();
});

function registrar() {
    let loadurl = url + 'persona';

    let nombre = $("#nombre").val();
    let apellido = $("#apellido").val();
    let documento = $("#documento").val();
    let telefono = $("#telefono").val();
    let codigo = $("#codigo").val();
    let fechaNacimiento = $("#fechaNacimiento").val();
    let sexo = $("#sexo").val();
    let idPoblacionEspecial = $("#idPoblacionEspecial").val();
    let idTipoUsuario = $("#idTipoUsuario").val();


    let data = {
        nombre: nombre,
        apellido: apellido,
        documento: documento,
        telefono: telefono,
        codigo: codigo,
        fechaNacimiento: fechaNacimiento,
        sexo: sexo,
        idPoblacionEspecial: idPoblacionEspecial,
        idTipoUsuario: idTipoUsuario
    };

    console.log(data);
    let init = makeinit(data)

    fetch(loadurl, init)
            .then((resp) => resp.json())
            .then(function (data) {
                if (data.msg) {
                    console.log(data);
                    return;
                }
                console.log(data);

                console.log(data);
                alert("se ha registrado la persona");
                location.href = "./persona.html";

            });


}
$(function () {
    $("#fechaNacimiento, #fecha, #fechaVisita").datepicker({
        dateFormat: "yy-mm-dd"
    });

    $("#documento").on("blur", function () {
        var documento = $(this).val();
        if (documento) {
            let loadurl = url + 'persona/doc/' + documento;
            let init = makeinitnodat();

            fetch(loadurl, init)
                    .then((resp) => resp.json())
                    .then(function (data) {
                        console.log(data);

                        if (data && !data.msg) {
                            $('#documentoHelp').text('El documento ya existe').removeClass('text-muted text-success').addClass('text-danger');
                            $('#guardarBtn').prop('disabled', true); // Deshabilitar el botón
                        } else {
                            $('#documentoHelp').text('El documento está disponible').removeClass('text-muted text-danger').addClass('text-success');
                            $('#guardarBtn').prop('disabled', false); // Habilitar el botón
                        }
                    })
                    .catch(function (error) {
                        console.error('Error:', error);
                        $('#documentoHelp').text('Error al verificar el documento').removeClass('text-muted text-success').addClass('text-danger');
                        $('#guardarBtn').prop('disabled', true); // Deshabilitar el botón en caso de error
                    });
        } else {
            $('#documentoHelp').text('Verificando...').removeClass('text-danger text-success').addClass('text-muted');
            $('#guardarBtn').prop('disabled', true); // Deshabilitar el botón si el campo está vacío
        }
    });
});


function cargarpoblacion() {

    $('#idPoblacionEspecial').empty();

    let loadurl = url + 'poblacion';

    let init = makeinitnodat();

    fetch(loadurl, init)
            .then((resp) => resp.json())
            .then(function (data) {
                //curso = data;

                console.log(data);

                let fill = ''

                $.each(data, function (i, item) {
                    fill += '<option value="' + item.id + '">' + item.nombre + '</option>';

                });

                $('#idPoblacionEspecial').append(fill);





            });
}

function cargartipo() {

    $('#idTipoUsuario').empty();

    let loadurl = url + 'tipo';

    let init = makeinitnodat();

    fetch(loadurl, init)
            .then((resp) => resp.json())
            .then(function (data) {
                //curso = data;

                console.log(data);

                let fill = ''

                $.each(data, function (i, item) {
                    fill += '<option value="' + item.id + '">' + item.tipo + '</option>';

                });

                $('#idTipoUsuario').append(fill);


                $("#idTipoUsuario").val(4);

            });
}


function makeinit(data) {
    let heads = new Headers();
    heads.append("Accept", "application/json");
    heads.append("Content-Type", "application/json");
    //heads.append("Authorization", authToken);
    heads.append("Access-Control-Allow-Origin", '*');
    let init = {
        method: 'POST',
        mode: 'cors',
        body: JSON.stringify(data),
        headers: heads
    };
    return init;
}

function makeinitnodat() {
    let heads = new Headers();
    heads.append("Accept", "application/json");
    heads.append("Content-Type", "application/json");
    //heads.append("Authorization", authToken);
    heads.append("Access-Control-Allow-Origin", '*');
    let init = {
        method: 'GET',
        mode: 'cors',
        headers: heads
    };
    return init;
}