/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jerson
 */
@Getter
@Setter
public class IngresoSalidaDTO {

    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "UTC")
    private LocalTime hora;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd", timezone = "UTC")
    private Date fechaIngreso;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd", timezone = "UTC")
    private Date fechaRegistro;

    private Boolean entradaSalida;

    private int idPracticante;
}
