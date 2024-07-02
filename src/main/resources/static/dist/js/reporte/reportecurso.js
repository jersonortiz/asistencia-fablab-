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
    let loadurl = url + 'reporte';
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
    let cursos = new Set();
    let sesiones = new Set();
    let colegios = new Set();
    let universidades = new Set();
    let programas = new Set();
    let poblaciones = new Set();

    data.forEach(item => {
        cursos.add(item.idCurso.nombre);
        sesiones.add(item.sesion);
        if (item.idColegio) colegios.add(item.idColegio.nombre);
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

    addOptionsToSelect('#filter-curso', cursos);
    addOptionsToSelect('#filter-sesion', sesiones);
    addOptionsToSelect('#filter-colegio', colegios);
    addOptionsToSelect('#filter-universidad', universidades);
    addOptionsToSelect('#filter-programa', programas);
    addOptionsToSelect('#filter-poblacion', poblaciones);
}

function addOptionsToSelect(selectId, optionsSet) {
    const select = $(selectId);
    optionsSet.forEach(option => {
        select.append(`<option value="${option}">${option}</option>`);
    });
}

function renderTable(data) {
    let fill = '';
    $.each(data, function (i, item) {
        let estudiauniversidad = 'No';
        let InstitucionColegio = '';
        let InstitucionUniversidad = '';
        let Programaacademico = '';
        let sesion = '';
        let poblacion = 'Ninguna';

        if (item.idPersona.idPoblacionEspecial != null) {
            poblacion = item.idPersona.idPoblacionEspecial.nombre;
        }

        switch (item.tipoConsulta) {
            case 'curso':
                estudiauniversidad = 'si';
                InstitucionUniversidad = item.idUniversidad.nombre;
                Programaacademico = item.otroPrograma;
                if (item.idProgramaAcademico) {
                    Programaacademico = item.idProgramaAcademico.nombre;
                }
                sesion = item.sesion;
                break;
            case 'colegio':
                estudiauniversidad = 'no';
                InstitucionColegio = item.idColegio.nombre;
                sesion = item.sesion;
                break;
            case 'school':
                estudiauniversidad = 'no';
                InstitucionColegio = item.idColegio.nombre;
                sesion = 'STEAM SCHOOL';
                break;
            case 'young':
                estudiauniversidad = 'no';
                InstitucionColegio = item.idColegio.nombre;
                sesion = 'STEAM YOUNG';
                break;
        }

        fill += '<tr>' +
            '<td>' + item.fechaVisita + '</td>' +
            '<td>' + item.idCurso.nombre + '</td>' +
            '<td>' + sesion + '</td>' +
            '<td>' + item.idPersona.nombre + '</td>' +
            '<td>' + item.idPersona.sexo + '</td>' +
            '<td>' + estudiauniversidad + '</td>' +
            '<td>' + InstitucionColegio + '</td>' +
            '<td>' + InstitucionUniversidad + '</td>' +
            '<td>' + Programaacademico + '</td>' +
            '<td>' + poblacion + '</td>' +
            '</tr>';
    });
    $('#colegiotable').append(fill);

    if ($.fn.DataTable.isDataTable("#example2")) {
        $('#example2').DataTable().destroy();
    }
    $("#example2").DataTable({
        "searching": false,
        "ordering": true,
        "buttons": [
            {
                extend: 'copy',
                exportOptions: {
                    columns: ':visible',
                    orthogonal: 'export',
                    modifier: {
                        order: 'index',
                        page: 'all',
                        search: 'none'
                    },
                    rows: ':not(:first)'
                }
            },
            {
                extend: 'csv',
                exportOptions: {
                    columns: ':visible',
                    orthogonal: 'export',
                    modifier: {
                        order: 'index',
                        page: 'all',
                        search: 'none'
                    },
                    rows: ':not(:first)'
                }
            },
            {
                extend: 'excel',
                exportOptions: {
                    columns: ':visible',
                    orthogonal: 'export',
                    modifier: {
                        order: 'index',
                        page: 'all',
                        search: 'none'
                    },
                    rows: ':not(:first)'
                }
            },
            {
                extend: 'pdf',
                exportOptions: {
                    columns: ':visible',
                    orthogonal: 'export',
                    modifier: {
                        order: 'index',
                        page: 'all',
                        search: 'none'
                    },
                    rows: ':not(:first)'
                }
            },
            {
                extend: 'print',
                exportOptions: {
                    columns: ':visible',
                    orthogonal: 'export',
                    modifier: {
                        order: 'index',
                        page: 'all',
                        search: 'none'
                    },
                    rows: ':not(:first)'
                }
            }
        ],
        "initComplete": function () {
                // Move the tfoot to be just after the thead
                $('#example2 tfoot').insertAfter('#example2 thead');
            }
    }).buttons().container().appendTo('#example2_wrapper .col-md-6:eq(0)');
    }

function attachFilterEvents(data) {
    $('#filter-fecha').on('input', function() { applyFilters(data); });
    $('#filter-curso').on('change', function() { applyFilters(data); });
    $('#filter-sesion').on('change', function() { applyFilters(data); });
    $('#filter-nombre').on('input', function() { applyFilters(data); });
    $('#filter-genero').on('change', function() { applyFilters(data); });
    $('#filter-estudia').on('change', function() { applyFilters(data); });
    $('#filter-colegio').on('change', function() { applyFilters(data); });
    $('#filter-universidad').on('change', function() { applyFilters(data); });
    $('#filter-programa').on('change', function() { applyFilters(data); });
    $('#filter-poblacion').on('change', function() { applyFilters(data); });
}

function applyFilters(data) {
    const filters = {
        fecha: $('#filter-fecha').val().toLowerCase(),
        curso: $('#filter-curso').val().toLowerCase(),
        sesion: $('#filter-sesion').val().toLowerCase(),
        nombre: $('#filter-nombre').val().toLowerCase(),
        genero: $('#filter-genero').val().toLowerCase(),
        estudia: $('#filter-estudia').val().toLowerCase(),
        colegio: $('#filter-colegio').val().toLowerCase(),
        universidad: $('#filter-universidad').val().toLowerCase(),
        programa: $('#filter-programa').val().toLowerCase(),
        poblacion: $('#filter-poblacion').val().toLowerCase()
    };

    const filteredData = data.filter(item => {
        const sesionStr = item.sesion ? String(item.sesion).toLowerCase() : '';
        return (!filters.fecha || (item.fechaVisita && item.fechaVisita.toLowerCase().includes(filters.fecha))) &&
               (!filters.curso || (item.idCurso.nombre && item.idCurso.nombre.toLowerCase().includes(filters.curso))) &&
               (!filters.sesion || sesionStr.includes(filters.sesion)) &&
               (!filters.nombre || (item.idPersona.nombre && item.idPersona.nombre.toLowerCase().includes(filters.nombre))) &&
               (!filters.genero || (item.idPersona.sexo && item.idPersona.sexo.toLowerCase().includes(filters.genero))) &&
               (!filters.estudia || (item.tipoConsulta === 'curso' ? 'si' : 'no').includes(filters.estudia)) &&
               (!filters.colegio || (item.idColegio && item.idColegio.nombre && item.idColegio.nombre.toLowerCase().includes(filters.colegio))) &&
               (!filters.universidad || (item.idUniversidad && item.idUniversidad.nombre && item.idUniversidad.nombre.toLowerCase().includes(filters.universidad))) &&
               (!filters.programa || (item.idProgramaAcademico && item.idProgramaAcademico.nombre && item.idProgramaAcademico.nombre.toLowerCase().includes(filters.programa)) || (item.otroPrograma && item.otroPrograma.toLowerCase().includes(filters.programa))) &&
               (!filters.poblacion || (item.idPersona.idPoblacionEspecial && item.idPersona.idPoblacionEspecial.nombre && item.idPersona.idPoblacionEspecial.nombre.toLowerCase().includes(filters.poblacion)));
    });

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