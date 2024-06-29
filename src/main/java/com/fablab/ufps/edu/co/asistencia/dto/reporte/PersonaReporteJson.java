/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.dto.reporte;

import com.fablab.ufps.edu.co.asistencia.dto.CRUD.ColegioDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.CursoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PoblacionEspecialDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.TipoUsuarioDTO;
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
public class PersonaReporteJson {

    private Integer id;

    private String nombre;

    private String documento;

    private String telefono;

    private String codigo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd", timezone = "UTC")
    private Date fechaNacimiento;

    private String sexo;

    private PoblacionEspecialDTO idPoblacionEspecial;

    private TipoUsuarioDTO idTipoUsuario;

}
