$(document).ready(function () {
    $("#registroForm").submit(function (e) {
        e.preventDefault();

        registrar();
    });

    cargarpoblacion();
    cargartipo();
    cargarcolegio();

});

function registrar() {
    let loadurl = url + 'socializacioncolegio';

    // Obtener los valores de los campos del formulario

    //let fecha = $("#fechaVisita").val();
    let idColegio = $("#idColegio").val();
    let nombre = $("#nombre").val();
    let apellido = $("#apellido").val();
    let documento = $("#documento").val();
    let telefono = $("#telefono").val();
    let codigo = $("#codigo").val();
    let fechaNacimiento = $("#fechaNacimiento").val();
    let sexo = $("#sexo").val();
    let idPoblacionEspecial = $("#idPoblacionEspecial").val();
    let idTipoUsuario = $("#idTipoUsuario").val();

    let fecha = new Date().toISOString().split('T')[0];

    // Crear objeto con los datos del formulario
    let data = {

        fecha: fecha,
        idColegio: idColegio,
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
                alert("se ha registrado la asistencia");
                //location.href = "./colegio.html";

            });

}
