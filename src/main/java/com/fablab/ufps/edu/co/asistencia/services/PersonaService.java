/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.services;

import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaClaseJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaCursoColegioJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaCursoJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaSemilleroJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaSocializacionColegioJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaSocializacionJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaSustentacionProyectoGradoJson;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;

/**
 *
 * @author jerson
 */
public interface PersonaService {

    /**
     * Crear una nueva persona asociada a la visita de clase.
     *
     * @param input los datos de la persona a crear.
     * @return la persona creada.
     */
    Persona createPersona(AsistenciaCursoJson input);

    Persona createPersona(AsistenciaClaseJson input);

    Persona createPersona(AsistenciaCursoColegioJson input);

    Persona createPersona(AsistenciaSemilleroJson input);

    Persona createPersona(AsistenciaSocializacionColegioJson input);

    Persona createPersona(AsistenciaSocializacionJson input);

    Persona createPersona(AsistenciaSustentacionProyectoGradoJson input);
}
