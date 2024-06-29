/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.dto.reporte;

import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PersonaDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.ProgramaAcademicoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.UniversidadDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 *
 * @author jerson
 */
public class ReporteGradoJson {

    private Integer id;

    private String otroPrograma;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd", timezone = "UTC")
    private Date fecha;

    private PersonaDTO idPersona;

    private ProgramaAcademicoDTO idProgramaAcademico;

    private UniversidadDTO idUniversidad;
}
