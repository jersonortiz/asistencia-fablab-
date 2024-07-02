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



    let loadurl = url + 'grabacion/reporte';
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


                    Programaacademico = item.otroPrograma;
                    if (item.idProgramaAcademico) {
                        Programaacademico = item.idProgramaAcademico.nombre;
                    }

                    semillero = "";
                    if (item.idSemillero) {
                        semillero = item.idSemillero.nombre;

                    }

                    externo = '';
                    if (item.externo) {
                        externo = item.externo;
                    }


                    fill += '<tr>' +
                            '<td>' + item.idPersona.nombre + '</td>' +
                            '<td>' + item.idPersona.codigo + '</td>' +
                            '<td>' + item.fecha + '</td>' +
                            '<td>' + semillero + '</td>' +
                            '<td>' + externo + '</td>' +
                            '<td>' + Programaacademico + '</td>' +
                            '<td>' + item.idUniversidad.nombre + '</td>' +
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