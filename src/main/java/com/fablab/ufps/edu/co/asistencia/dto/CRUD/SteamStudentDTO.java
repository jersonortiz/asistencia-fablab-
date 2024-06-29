/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.dto.CRUD;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jerson
 */
@Getter
@Setter
public class SteamStudentDTO {

    private Integer id;

    private String nombre;

    private String sexo;

    private String semestre;

    private int idColegio;

    private int idPoblacionEspecial;
}
