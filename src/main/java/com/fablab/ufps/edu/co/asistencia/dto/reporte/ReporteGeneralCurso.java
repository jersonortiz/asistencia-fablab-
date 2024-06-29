/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.dto.reporte;

import com.fablab.ufps.edu.co.asistencia.dto.CRUD.ColegioDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.CursoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.ProgramaAcademicoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.UniversidadDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jerson
 */
@Getter
@Setter
public class ReporteGeneralCurso {

    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd", timezone = "UTC")
    private Date fechaVisita;

    private int sesion;

    private String otroPrograma;

    private CursoDTO idCurso;

    private PersonaReporteJson idPersona;

    private ProgramaAcademicoDTO idProgramaAcademico;

    private UniversidadDTO idUniversidad;

    private ColegioDTO idColegio;
    
    private String  tipoConsulta;
    
    

}
