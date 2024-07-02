let url = serverurl;
//let usuario = user;
let colegio = "";

$(document).ready(function () {
    $("#publiform").submit(function (e) {
        e.preventDefault();

        guardar();
    });


    cargarpersonas();
    cargar();

});

function getId() {
    let url = new URL(window.location);

    let searchParams = url.searchParams;

    return searchParams.get('id'); // 'node'

}

function cargar() {

    let idu = getId();

    //let idurl = new URLSearchParams({
    //    id: idu,
    //});

    let loadurl = url + 'practicante/' + idu;

    let init = makeinitnodat();

    fetch(loadurl, init)
            .then((resp) => resp.json())
            .then(function (data) {
                colegio = data;
                console.log(data);

                $("#idCarnet").val(data.idCarnet);
                $("#semestre").val(data.semestre);
                console.log(data.estado);
                let estado = data.estado ? 'true' : 'false';
                $("#estado").val(estado);
                $("#idPersona").val(data.idPersona.id);

            });

}

function guardar() {

    let idu = getId();

    let loadurl = url + 'practicante/' + idu;


    let car = $("#idCarnet").val();
    let sem = $("#semestre").val();
    let est = $("#estado").val();
    let per = $("#idPersona").val();

    let data = {
        idCarnet: car,
        semestre: sem,
        estado: est,
        idPersona: {id: per}

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
                alert("se ha actualizado el practicante");
                location.href = "./practicante.html";

            });


}

function cargarpersonas() {

    $('#idPersona').empty();

    let loadurl = url + 'persona';

    let init = makeinitnodat();

    fetch(loadurl, init)
            .then((resp) => resp.json())
            .then(function (data) {
                //curso = data;

                console.log(data);

                let fill = ''

                $.each(data, function (i, item) {
                    fill += '<option value="' + item.id + '">' +
                            item.nombre + ' ' + item.apellido + ' ' +
                            item.codigo + '</option>';
                });

                $('#idPersona').append(fill);

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