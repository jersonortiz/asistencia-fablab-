/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.common;

import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PracticantepasanteBecaDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.SemilleroDTO;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.entity.PracticantepasanteBeca;
import com.fablab.ufps.edu.co.asistencia.entity.Semillero;

/**
 * Utility class for converting DTOs to entities.
 *
 * <p>
 * This class contains static methods for converting various DTO objects to
 * their corresponding entity objects. These methods can be used without
 * instantiating the class.
 * </p>
 *
 * @author jerson
 */
public class CommonEntity {

    /**
     * Converts a PracticantepasanteBecaDTO to a PracticantepasanteBeca entity.
     *
     * @param u the PracticantepasanteBecaDTO object to be converted
     * @return the converted PracticantepasanteBeca entity
     */
    public static PracticantepasanteBeca practicantepasanteBecaDTOToEntity(PracticantepasanteBecaDTO u) {
        PracticantepasanteBeca ud = new PracticantepasanteBeca();

        ud.setId(u.getId());
        ud.setIdCarnet(u.getIdCarnet());
        ud.setEstado(u.isEstado());
        ud.setSemestre(u.getSemestre());

        Persona p = new Persona();
        p.setId(u.getIdPersona().getId());
        ud.setIdPersona(p);

        return ud;

    }

    /**
     * Converts a SemilleroDTO to a Semillero entity.
     *
     * @param u the SemilleroDTO object to be converted
     * @return the converted Semillero entity
     */
    public static Semillero SemilleroDTOToEntity(SemilleroDTO u) {
        Semillero ud = new Semillero();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setSigla(u.getSigla());

        return ud;

    }

}
