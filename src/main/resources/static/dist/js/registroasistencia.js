let url = serverurl;
//let usuario = user;
let profesional = "";

$(document).ready(function () {
    $("#registroForm").submit(function (e) {
        e.preventDefault();

        registrar();
    });
cargarpracticantes();
});


function cargarpracticantes(){

    $('#idPracticante').empty();

       let loadurl = url + 'practicante';

                let init = makeinitnodat();

                fetch(loadurl, init)
                        .then((resp) => resp.json())
                        .then(function (data) {
                            //curso = data;

                           console.log(data);

                            let fill = ''

                            $.each(data, function (i, item) {
                                fill += '<option value="'+ item.id +'">'+
                                item.idPersona.nombre + ' ' + item.idPersona.codigo+'</option>';
                            });

                             $('#idPracticante').append(fill);

                        });         
 }

             function obtenerFechaHoraActual() {
                const now = new Date();
                const fechaActual = now.toISOString().split("T")[0];
                const horaActual = now.toTimeString().split(" ")[0];
                return { fechaActual, horaActual };
            }


$(function() {
            $("#fechaIngreso").datepicker({
                dateFormat: "yy-mm-dd"
            });

            // Manejar el cambio de opción en los radio buttons de fecha
            $("input[name='fechaIngresoOption']").on("change", function() {
                if ($("#fechaHoy").is(":checked")) {
                    $("#fechaIngreso").hide();
                    $("#fechaIngreso").val(""); // Limpiar el campo de fecha personalizada
                } else if ($("#fechaPersonalizada").is(":checked")) {
                    $("#fechaIngreso").show();
                }
            });

            // Manejar el cambio de opción en los radio buttons de hora
            $("input[name='horaOption']").on("change", function() {
                if ($("#horaManana").is(":checked")) {
                    $("#hora").hide();
                    $("#hora").val("08:00"); // Establecer la hora de mañana
                } else if ($("#horaTarde").is(":checked")) {
                    $("#hora").hide();
                    $("#hora").val("14:00"); // Establecer la hora de tarde
                } else if ($("#horaPersonalizada").is(":checked")) {
                    $("#hora").show();
                    $("#hora").val(""); // Limpiar el campo de hora personalizada
                }
            });

            // Función para obtener la fecha y hora actual en formato adecuado


            // Función para manejar el envío del formulario
 
        });

function registrar() {
    
    const { fechaActual, horaActual } = obtenerFechaHoraActual();

    // Establecer la fecha de ingreso según la selección del usuario
    if ($("#fechaHoy").is(":checked")) {
        $("#fechaIngreso").val(fechaActual);
    }

    // Establecer la fecha de registro
    $("#fechaRegistro").val(fechaActual);

   
    let hor = $("#hora").val();
    let sem = $("#fechaIngreso").val();
    let est = $("input[name='entradaSalida']:checked").val();
    let per = $("#idPracticante").val();
    let reg = $("#fechaRegistro").val();

    data = {
        id:0,
        hora: hor,
        fechaIngreso: sem,
        fechaRegistro: reg,
        entradaSalida: est,
        idPracticante: per
    }

    let init = makeinit(data)

    if(est=='true'){
        entrada(init);
    } else {
        salida(init);
    }
}


function entrada(init){
    let loadurl = url + 'ingreso/entrada';
    fetch(loadurl, init)
            .then((resp) => resp.json())
            .then(function (data) {
                if (data.msg) {
                    console.log(data);
                    return;
                }
                console.log(data);

                //alert("se ha registrado el practicante");
                //location.href = "./RegistroAsistencia.html";

            });

}


function salida(init){
    let loadurl = url + 'ingreso/salida';
    fetch(loadurl, init)
            .then((resp) => resp.json())
            .then(function (data) {
                if (data.msg) {
                    console.log(data);
                    return;
                }
                console.log(data);

                //lert("se ha registrado el practicante");
                //location.href = "./RegistroAsistencia.html";

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