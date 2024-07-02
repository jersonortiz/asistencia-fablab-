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
    let loadurl = url + 'socializacioncolegio/reporte';
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
    let colegios = new Set();
    let programas = new Set();
    let poblaciones = new Set();
    let semilleros = new Set();
    let siglas = new Set();

    data.forEach(item => {
        if (item.idSemillero) semilleros.add(item.idSemillero.nombre);
        if (item.idSemillero) siglas.add(item.idSemillero.sigla);
        if (item.idColegio) colegios.add(item.idColegio.nombre);
    
        if (item.idPersona.idPoblacionEspecial) {
            poblaciones.add(item.idPersona.idPoblacionEspecial.nombre);
        }
    });

 
    addOptionsToSelect('#filter-colegio', colegios);
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

        console.log(data);
  
        let InstitucionColegio = '';
        let poblacion = 'Ninguna';

        if (item.idPersona.idPoblacionEspecial != null) {
            poblacion = item.idPersona.idPoblacionEspecial.nombre;
        }

        InstitucionColegio = item.idColegio ? item.idColegio.nombre : '';
   
        fill += '<tr>' +
            '<td>' + item.fecha + '</td>' +
            '<td>' + item.idPersona.nombre + '</td>' +
            '<td>' + item.idPersona.sexo + '</td>' +
            '<td>' + InstitucionColegio + '</td>' +
            '<td>' + poblacion + '</td>' +
            '</tr>';
    });
    $('#colegiotable').append(fill);
}

function attachFilterEvents(data) {
    $('#filter-fecha').on('input', function() { applyFilters(data); });
    $('#filter-nombre').on('input', function() { applyFilters(data); });
    $('#filter-genero').on('change', function() { applyFilters(data); });
    $('#filter-colegio').on('change', function() { applyFilters(data); });
    $('#filter-poblacion').on('change', function() { applyFilters(data); });
}

function applyFilters(data) {
    const filters = {
        fecha: $('#filter-fecha').val().toLowerCase(),
        nombre: $('#filter-nombre').val().toLowerCase(),
        genero: $('#filter-genero').val().toLowerCase(),
        colegio: $('#filter-colegio').val().toLowerCase(),
        poblacion: $('#filter-poblacion').val().toLowerCase()
    };

    const filteredData = data.filter(item => {
        const fechaStr = item.fecha ? String(item.fecha).toLowerCase() : '';
        const nombreStr = item.idPersona.nombre ? item.idPersona.nombre.toLowerCase() : '';
        const generoStr = item.idPersona.sexo ? item.idPersona.sexo.toLowerCase() : '';
        const colegioStr = item.idColegio && item.idColegio.nombre ? item.idColegio.nombre.toLowerCase() : '';
       
        const poblacionStr = item.idPersona.idPoblacionEspecial && item.idPersona.idPoblacionEspecial.nombre ? item.idPersona.idPoblacionEspecial.nombre.toLowerCase() : '';

        return (!filters.fecha || fechaStr.includes(filters.fecha)) &&
               (!filters.nombre || nombreStr.includes(filters.nombre)) &&
               (!filters.genero || generoStr.includes(filters.genero)) &&
              (!filters.colegio || colegioStr.includes(filters.colegio)) &&
               (!filters.programa || programaStr.includes(filters.programa)) &&
               (!filters.poblacion || poblacionStr.includes(filters.poblacion));
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