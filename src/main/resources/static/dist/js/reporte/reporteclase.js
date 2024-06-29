let url = serverurl;
//let usuario = user;

let redes = [];


$(document).ready(function () {

    loadstart();

});


function editar(value) {
    location.href = "./editarcolegio.html?id=" + value;
}

function eliminar(value) {
    let loadurl = url + 'colegio/'+value;

    let data = {
        'id': value
    };

    let init = makeinitDelete(data)


    if (confirm("eliminar colegio") == true) {
        fetch(loadurl, init)
                .then((resp) => resp.json())
                .then(function (data) {

                    loadstart();

                });
    }
}
function loadstart() {
    let loadurl = url + 'clase/reporte';
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

            // Generar opciones de dropdowns
            populateDropdowns(data);

            // Renderizar la tabla
            renderTable(data);

            // Adjuntar eventos de filtrado
            attachFilterEvents(data);
        });
}

function populateDropdowns(data) {
    let universidades = new Set();
    let programas = new Set();
    let poblaciones = new Set();

    data.forEach(item => {
        if (item.idUniversidad) universidades.add(item.idUniversidad.nombre);
        if (item.idProgramaAcademico) {
            programas.add(item.idProgramaAcademico.nombre);
        } else if (item.otroPrograma) {
            programas.add(item.otroPrograma);
        }
        if (item.idPersona.idPoblacionEspecial) {
            poblaciones.add(item.idPersona.idPoblacionEspecial.nombre);
        }
    });

    addOptionsToSelect('#filter-universidad', universidades);
    addOptionsToSelect('#filter-programa', programas);
    addOptionsToSelect('#filter-poblacion', poblaciones);
}

function addOptionsToSelect(selectId, optionsSet) {
    const select = $(selectId);
    select.empty(); // Limpiar opciones existentes
    optionsSet.forEach(option => {
        select.append(`<option value="${option}">${option}</option>`);
    });
}

function renderTable(data) {
    let fill = '';
    $.each(data, function (i, item) {
        let poblacion = item.idPersona.idPoblacionEspecial ? item.idPersona.idPoblacionEspecial.nombre : 'Ninguna';
        let Programaacademico = item.idProgramaAcademico ? item.idProgramaAcademico.nombre : item.otroPrograma || '';

        fill += `<tr>
            <td>${item.fecha}</td>
            <td>${item.idPersona.nombre}</td>
            <td>${item.idPersona.sexo}</td>
            <td>${item.nombreDocente}</td>
            <td>${item.codigoCarrera}</td>
            <td>${item.nombreMateria}</td>
            <td>${Programaacademico}</td>
            <td>${item.idUniversidad ? item.idUniversidad.nombre : item.otroUniversidad || ''}</td>
            <td>${poblacion}</td>
        </tr>`;
    });
    $('#colegiotable').html(fill); // Usar .html() para asegurar que el contenido se actualice correctamente
}

function attachFilterEvents(data) {
    $('#filter-fecha').on('input', function() { applyFilters(data); });
    $('#filter-nombre').on('input', function() { applyFilters(data); });
    $('#filter-genero').on('change', function() { applyFilters(data); });
    $('#filter-docente').on('input', function() { applyFilters(data); }); // Cambio a 'input' en lugar de 'change'
    $('#filter-codigo').on('input', function() { applyFilters(data); }); // Cambio a 'input' en lugar de 'change'
    $('#filter-materia').on('input', function() { applyFilters(data); }); // Cambio a 'input' en lugar de 'change'
    $('#filter-universidad').on('change', function() { applyFilters(data); });
    $('#filter-programa').on('change', function() { applyFilters(data); });
    $('#filter-poblacion').on('change', function() { applyFilters(data); });
}

function applyFilters(data) {
    const filters = {
        fecha: $('#filter-fecha').val().toLowerCase(),
        nombre: $('#filter-nombre').val().toLowerCase(),
        genero: $('#filter-genero').val().toLowerCase(),
        universidad: $('#filter-universidad').val().toLowerCase(),
        docente: $('#filter-docente').val().toLowerCase(),
        codigo: $('#filter-codigo').val().toLowerCase(),
        materia: $('#filter-materia').val().toLowerCase(),
        programa: $('#filter-programa').val().toLowerCase(),
        poblacion: $('#filter-poblacion').val().toLowerCase()
    };

    const filteredData = data.filter(item => {
        const fechaStr = item.fecha ? String(item.fecha).toLowerCase() : '';
        const nombreStr = item.idPersona.nombre ? item.idPersona.nombre.toLowerCase() : '';
        const generoStr = item.idPersona.sexo ? item.idPersona.sexo.toLowerCase() : '';
        const universidadStr = item.idUniversidad && item.idUniversidad.nombre ? item.idUniversidad.nombre.toLowerCase() : '';
        const docenteStr = item.nombreDocente ? String(item.nombreDocente).toLowerCase() : '';
        const codigoStr = item.codigoCarrera ? String(item.codigoCarrera).toLowerCase() : '';
        const materiaStr = item.nombreMateria ? item.nombreMateria.toLowerCase() : '';
        const programaStr = (item.idProgramaAcademico && item.idProgramaAcademico.nombre ? item.idProgramaAcademico.nombre.toLowerCase() : '') || (item.otroPrograma ? item.otroPrograma.toLowerCase() : '');
        const poblacionStr = item.idPersona.idPoblacionEspecial && item.idPersona.idPoblacionEspecial.nombre ? item.idPersona.idPoblacionEspecial.nombre.toLowerCase() : '';

        // Check if each item passes the filter criteria
        return (filters.fecha === '' || fechaStr.includes(filters.fecha)) &&
               (filters.nombre === '' || nombreStr.includes(filters.nombre)) &&
               (filters.genero === '' || generoStr.includes(filters.genero)) &&
               (filters.universidad === '' || universidadStr.includes(filters.universidad)) &&
               (filters.docente === '' || docenteStr.includes(filters.docente)) &&
               (filters.codigo === '' || codigoStr.includes(filters.codigo)) &&
               (filters.materia === '' || materiaStr.includes(filters.materia)) &&
               (filters.programa === '' || programaStr.includes(filters.programa)) &&
               (filters.poblacion === '' || poblacionStr.includes(filters.poblacion));
    });

    // Render the filtered data
    $('#colegiotable').empty();
    renderTable(filteredData);
}


$(function() {
        $("#filter-fecha").datepicker({
            dateFormat: "yy-mm-dd"
        });

       });



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