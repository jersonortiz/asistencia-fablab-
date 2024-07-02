
$(document).ready(function () {
    $("#registroForm").submit(function (e) {
        e.preventDefault();

        registrar();
    });

    cargarpoblacion();
    cargartipo();
    cargaruniversidad();
    cargarprograma();

});

function mostrarCampoOtroPrograma(opcionSeleccionada) {
    const campoOtroPrograma = document.getElementById('campoOtroPrograma');
    if (opcionSeleccionada === 'otro') {
        campoOtroPrograma.style.display = 'block';
    } else {
        campoOtroPrograma.style.display = 'none';
        document.getElementById('otroPrograma').value = '';
    }
}

function registrar() {
    let loadurl = url + 'socializacion';

    // Obtener los valores de los campos del formulario

    //let fecha = $("#fechaVisita").val();
    let otroPrograma = $("#otroPrograma").val();
    //let idPersona = $("#idPersona").val();
    let idProgramaAcademico = $("#idProgramaAcademico").val();
    let idUniversidad = $("#idUniversidad").val();

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


    if (idProgramaAcademico === 'otro') {
        idProgramaAcademico = '';
    }
    // Crear objeto con los datos del formulario
    let data = {

        fecha: fecha,
        otroPrograma: otroPrograma,
        idProgramaAcademico: idProgramaAcademico,
        idUniversidad: idUniversidad,
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


