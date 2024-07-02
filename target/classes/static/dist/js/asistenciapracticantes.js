let url = serverurl;
//let usuario = user;

let redes = [];


$(document).ready(function () {

    loadstart();

});


function eliminar(value) {
    let loadurl = url + 'ingreso/' + value;

    let data = {
        'id': value
    };

    let init = makeinitDelete(data)


    if (confirm("eliminar aula?") == true) {
        fetch(loadurl, init)
                .then((resp) => resp.json())
                .then(function (data) {

                    loadstart();
                });
    }
}



function loadstart() {



    let loadurl = url + 'ingreso';
    let init = makeinitnodat();


    $('#tipotabla').empty();

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

                    entradasalida = 'salida'
                    if (item.entradaSalida) {
                        entradasalida = 'entrada'
                    }

                    fill += '<tr>' +
                            '<td>' + item.idPracticante.nombre + '</td>' +
                            '<td>' + item.idPracticante.codigo + '</td>' +
                            '<td>' + item.hora + '</td>' +
                            '<td>' + item.fechaIngreso + '</td>' +
                            '<td>' + entradasalida + '</td>' +
                            '<td>' +
                            '<button type="button" class="btn btn-danger" value="' + item.id + '" onclick="eliminar(this.value)" title="eliminar">' +
                            '<i class="fas fa-trash"></i>' +
                            '</button>' +
                            '</td>' +
                            '</tr>';




                });

                $('#tipotabla').append(fill);

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