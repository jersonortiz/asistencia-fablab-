let url = serverurl;
//let usuario = user;
let profesional = "";

$(document).ready(function () {
    $("#publiform").submit(function (e) {
        e.preventDefault();

        registrar();
    });
    cargarpersonas();
});

function registrar() {
    let loadurl = url + 'practicante';

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
                alert("se ha registrado el practicante");
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
