/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.dto.json;

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
public class AsistenciaClaseJson {

    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd", timezone = "UTC")
    private Date fecha;

    private String otroPrograma;

    private String otroUniversidad;

    private String codigoCarrera;

    private String nombreMateria;

    private String nombreDocente;

    private int idAula;

    private int idPersona;

    private int idProgramaAcademico;

    private int idUniversidad;
    
       //persona datos
    private String nombre;

    private String apellido;

    private String documento;

    private String telefono;

    private String codigo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd", timezone = "UTC")
    private Date fechaNacimiento;

    private String sexo;

    private int idPoblacionEspecial;

    private int idTipoUsuario;
}
