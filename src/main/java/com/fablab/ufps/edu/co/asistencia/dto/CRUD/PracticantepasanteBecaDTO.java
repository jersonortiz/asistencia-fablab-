/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.dto.CRUD;

import com.fablab.ufps.edu.co.asistencia.dto.reporte.PersonaReporteJson;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jerson
 */
@Getter
@Setter
public class PracticantepasanteBecaDTO {

    private Integer id;

    private int idCarnet;

    private String semestre;

    private boolean estado;

    private PersonaReporteJson idPersona;
}
