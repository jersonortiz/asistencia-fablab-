/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.dto.reporte;

import com.fablab.ufps.edu.co.asistencia.dto.CRUD.AulaDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PersonaDTO;
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
public class ReporteClaseJson {

    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd", timezone = "UTC")
    private Date fecha;

    private String otroPrograma;

    private String otroUniversidad;

    private String codigoCarrera;

    private String nombreMateria;

    private String nombreDocente;
    
    private AulaDTO idAula;

    private PersonaReporteJson idPersona;

    private ProgramaAcademicoDTO idProgramaAcademico;

    private UniversidadDTO idUniversidad;

}
