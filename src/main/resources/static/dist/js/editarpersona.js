let url = serverurl;
let usuario = user;
let aula = "";

$(document).ready(function () {
    $("#publiform").submit(function (e) {
        e.preventDefault();

        guardar();
    });


    cargarpoblacion();
    cargartipo();
    cargarpersona();

});

function getTipoId() {
    let urls = new URL(window.location);

    let searchParams = urls.searchParams;

    return searchParams.get('id'); // 'node'

}

function cargarpersona() {

    let idu = getTipoId();

    let idurl = new URLSearchParams({
        id: idu,
    });

    let loadurl = url + 'persona/' + idu;

    let init = makeinitnodat();

    fetch(loadurl, init)
            .then((resp) => resp.json())
            .then(function (data) {
                aula = data;

                console.log(aula);

                $("#nombre").val(data.nombre);
                $("#apellido").val(data.apellido);
                $("#telefono").val(data.telefono);
                $("#documento").val(data.telefono);
                $("#codigo").val(data.codigo);
                $("#fechaNacimiento").val(data.fechaNacimiento);
                $("#sexo").val(data.sexo);
                $("#idPoblacionEspecial").val(data.idPoblacionEspecial);
                $("#idTipoUsuario").val(data.idTipoUsuario);



            });

}

function guardar() {

    let idu = getTipoId();

    let loadurl = url + 'persona/' + idu;

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
                alert("se han actualizado los datos de la persona");
                location.href = "./persona.html";

            });


}

$(function () {
    $("#fechaNacimiento, #fecha, #fechaVisita").datepicker({
        dateFormat: "yy-mm-dd"
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
        method: 'PUT',
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