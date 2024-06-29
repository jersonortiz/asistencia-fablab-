/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.dto.reporte;

import com.fablab.ufps.edu.co.asistencia.dto.CRUD.ColegioDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.CursoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PoblacionEspecialDTO;
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
public class ReporteSteamJson {

    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd", timezone = "UTC")
    private Date fecha;

    private ColegioDTO idColegio;

    private CursoDTO idCursos;

    private String nombre;

    private String sexo;

    private String semestre;

    private PoblacionEspecialDTO idPoblacionEspecial;
}
