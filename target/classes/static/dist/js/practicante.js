let url = serverurl;
//let usuario = user;

let redes = [];


$(document).ready(function () {

    loadstart();

});


function editar(value) {
    location.href = "./editarpracticante.html?id=" + value;
}

function eliminar(value) {
    let loadurl = url + 'practicante/' + value;

    let data = {
        'id': value
    };

    let init = makeinitDelete(data)


    if (confirm("eliminar practicante?") == true) {
        fetch(loadurl, init)
                .then((resp) => resp.json())
                .then(function (data) {

                    loadstart();

                });
    }
}



function loadstart() {


    let loadurl = url + 'practicante';
    let init = makeinitnodat();


    $('#colegiotable').empty();

    fetch(loadurl, init)
            .then((resp) => resp.json())
            .then(function (data) {

                console.log(data);
                if (data.msg) {
                    console.log(data);
                    return;
                }
                let fill = ''
                $.each(data, function (i, item) {

                    estado = 'activo'

                    if (!item.estado) {
                        estado = 'inactivo'

                    }

                    fill += '<tr>' +
                            '<td>' + item.idPersona.nombre + '</td>' +
                            '<td>' + item.idPersona.codigo + '</td>' +
                            '<td>' + item.idPersona.telefono + '</td>' +
                            '<td>' + estado + '</td>' +
                            '<td>' +
                            '<button type="button" class="btn btn-info" value="' + item.id + '" onclick="editar(this.value)" title="editar publicacion">' +
                            '<i class="fas fa-pen"></i>' +
                            '</button>' +
                            '<button type="button" class="btn btn-danger" value="' + item.id + '" onclick="eliminar(this.value)" title="eliminar">' +
                            '<i class="fas fa-trash"></i>' +
                            '</button>' +
                            '</td>' +
                            '</tr>';




                });

                $('#colegiotable').append(fill);

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

function makeinitDelete(data) {
    let heads = new Headers();
    heads.append("Accept", "application/json");
    heads.append("Content-Type", "application/json");
    //heads.append("Authorization", authToken);
    heads.append("Access-Control-Allow-Origin", '*');
    let init = {
        method: 'DELETE',
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