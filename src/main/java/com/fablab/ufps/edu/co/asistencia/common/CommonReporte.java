/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.common;

import static com.fablab.ufps.edu.co.asistencia.common.CommonDTO.poblacionEspecialToDTO;
import static com.fablab.ufps.edu.co.asistencia.common.CommonDTO.tipoUsuarioToDTO;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.PersonaReporteJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteClaseJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteGeneralCurso;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteGrabacionJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteGradoJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteSemilleroJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteSocializacionColegioJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteSocializacionJson;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaClase;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaCurso;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaCursoColegio;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaGrabacion;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaSemillero;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaSocializacion;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaSocializacionColegio;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaSteamSchool;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaSteamYoung;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaSustentacionProyectoGrado;

/**
 * Clase CommonReporte que contiene métodos utilitarios para convertir
 * diferentes tipos de visitas a sus correspondientes objetos de reporte JSON.
 *
 * @autor jerson
 */
public class CommonReporte {

    /**
     * Convierte un objeto VisitaGrabacion en un objeto ReporteGrabacionJson.
     *
     * @param x el objeto VisitaGrabacion a convertir.
     * @return el objeto ReporteGrabacionJson resultante de la conversión.
     */
    public static ReporteGrabacionJson visitaGrabacionToJson(VisitaGrabacion x) {
        ReporteGrabacionJson a = new ReporteGrabacionJson();

        a.setId(x.getId());
        a.setFecha(x.getFecha());

        if (x.getIdSemillero() != null) {

            a.setIdSemillero(CommonDTO.semilleroToDTO(x.getIdSemillero()));

        }

        a.setExterno(x.getExterno());
        a.setOtroPrograma(x.getOtroPrograma());
        a.setRazonGrabacion(x.getRazonGrabacion());

        a.setIdProgramaAcademico(CommonDTO.programaToDTO(x.getIdProgramaAcademico()));
        a.setIdPersona(personaToPersonaReporteJson(x.getIdPersona()));
        a.setIdUniversidad(CommonDTO.universidadToDTO(x.getIdUniversidad()));

        return a;

    }

    /**
     * Convierte un objeto VisitaSustentacionProyectoGrado en un objeto
     * ReporteGradoJson.
     *
     * @param x el objeto VisitaSustentacionProyectoGrado a convertir.
     * @return el objeto ReporteGradoJson resultante de la conversión.
     */
    public static ReporteGradoJson visitaSustentacionProyectoGradoToJson(VisitaSustentacionProyectoGrado x) {
        ReporteGradoJson a = new ReporteGradoJson();

        a.setId(x.getId());
        a.setFecha(x.getFecha());
        a.setOtroPrograma(x.getOtroPrograma());

        a.setIdProgramaAcademico(CommonDTO.programaToDTO(x.getIdProgramaAcademico()));
        a.setIdPersona(personaToPersonaReporteJson(x.getIdPersona()));
        a.setIdUniversidad(CommonDTO.universidadToDTO(x.getIdUniversidad()));

        return a;

    }

    /**
     * Convierte un objeto VisitaSocializacion en un objeto
     * ReporteSocializacionJson.
     *
     * @param x el objeto VisitaSocializacion a convertir.
     * @return el objeto ReporteSocializacionJson resultante de la conversión.
     */
    public static ReporteSocializacionJson visitaSocializacionToJson(VisitaSocializacion x) {
        ReporteSocializacionJson a = new ReporteSocializacionJson();

        a.setId(x.getId());
        a.setFecha(x.getFecha());
        a.setOtroPrograma(x.getOtroPrograma());
        a.setIdProgramaAcademico(CommonDTO.programaToDTO(x.getIdProgramaAcademico()));
        a.setIdPersona(personaToPersonaReporteJson(x.getIdPersona()));
        a.setIdUniversidad(CommonDTO.universidadToDTO(x.getIdUniversidad()));

        return a;

    }

    /**
     * Convierte un objeto VisitaSocializacionColegio en un objeto
     * ReporteSocializacionColegioJson.
     *
     * @param x el objeto VisitaSocializacionColegio a convertir.
     * @return el objeto ReporteSocializacionColegioJson resultante de la
     * conversión.
     */
    public static ReporteSocializacionColegioJson visitaSocializacionColegioToJson(VisitaSocializacionColegio x) {
        ReporteSocializacionColegioJson a = new ReporteSocializacionColegioJson();

        a.setId(x.getId());
        a.setFecha(x.getFecha());

        a.setIdColegio(CommonDTO.colegioToDTO(x.getIdColegio()));
        a.setIdPersona(personaToPersonaReporteJson(x.getIdPersona()));

        return a;

    }

    /**
     * Convierte un objeto VisitaSemillero en un objeto ReporteSemilleroJson.
     *
     * @param v el objeto VisitaSemillero a convertir.
     * @return el objeto ReporteSemilleroJson resultante de la conversión.
     */
    public static ReporteSemilleroJson visitaSemilleroToJson(VisitaSemillero v) {
        ReporteSemilleroJson s = new ReporteSemilleroJson();

        s.setId(v.getId());
        s.setFecha(v.getFecha());
        s.setOtroPrograma(v.getOtroPrograma());

        s.setIdPersona(personaToPersonaReporteJson(v.getIdPersona()));
        s.setIdSemillero(CommonDTO.semilleroToDTO(v.getIdSemillero()));
        s.setIdUniversidad(CommonDTO.universidadToDTO(v.getIdUniversidad()));
        s.setIdProgramaAcademico(CommonDTO.programaToDTO(v.getIdProgramaAcademico()));

        return s;

    }

    /**
     * Convierte un objeto VisitaClase en un objeto ReporteClaseJson.
     *
     * @param x el objeto VisitaClase a convertir.
     * @return el objeto ReporteClaseJson resultante de la conversión.
     */
    public static ReporteClaseJson VisitaClaseToReporteClaseJson(VisitaClase x) {
        ReporteClaseJson a = new ReporteClaseJson();
        a.setId(x.getId());
        a.setFecha(x.getFecha());
        a.setOtroPrograma(x.getOtroPrograma());
        a.setOtroUniversidad(x.getOtroUniversidad());
        a.setCodigoCarrera(x.getCodigoCarrera());
        a.setNombreMateria(x.getNombreMateria());
        a.setNombreDocente(x.getNombreDocente());

        a.setIdPersona(personaToPersonaReporteJson(x.getIdPersona()));
        a.setIdAula(CommonDTO.aulaToDTO(x.getIdAula()));
        a.setIdUniversidad(CommonDTO.universidadToDTO(x.getIdUniversidad()));
        a.setIdProgramaAcademico(CommonDTO.programaToDTO(x.getIdProgramaAcademico()));

        return a;
    }

    /**
     * Convierte un objeto VisitaCurso en un objeto ReporteGeneralCurso.
     *
     * @param v el objeto VisitaCurso a convertir.
     * @return el objeto ReporteGeneralCurso resultante de la conversión.
     */
    public static ReporteGeneralCurso visitaCursoToReporteGeneralCurso(VisitaCurso v) {

        ReporteGeneralCurso rg = new ReporteGeneralCurso();

        rg.setId(v.getId());
        rg.setFechaVisita(v.getFechaVisita());
        rg.setSesion(v.getSesion());

        rg.setOtroPrograma(v.getOtroPrograma());

        rg.setIdCurso(CommonDTO.cursoToDTO(v.getIdCurso()));
        rg.setIdUniversidad(CommonDTO.universidadToDTO(v.getIdUniversidad()));
        rg.setIdProgramaAcademico(CommonDTO.programaToDTO(v.getIdProgramaAcademico()));
        rg.setIdPersona(personaToPersonaReporteJson(v.getIdPersona()));

        rg.setTipoConsulta("curso");

        return rg;
    }

    /**
     * Convierte un objeto VisitaCursoColegio en un objeto ReporteGeneralCurso.
     *
     * @param v el objeto VisitaCursoColegio a convertir.
     * @return el objeto ReporteGeneralCurso resultante de la conversión.
     */
    public static ReporteGeneralCurso visitaCursoColegioToReporteGeneralCurso(VisitaCursoColegio v) {
        ReporteGeneralCurso rg = new ReporteGeneralCurso();
        rg.setId(v.getId());
        rg.setFechaVisita(v.getFechaVisita());
        rg.setSesion(v.getSesion());

        rg.setIdCurso(CommonDTO.cursoToDTO(v.getIdCurso()));
        rg.setIdColegio(CommonDTO.colegioToDTO(v.getIdColegio()));
        rg.setIdPersona(personaToPersonaReporteJson(v.getIdPersona()));

        rg.setTipoConsulta("colegio");

        return rg;
    }

    /**
     * Convierte un objeto VisitaSteamSchool en un objeto ReporteGeneralCurso.
     *
     * @param v el objeto VisitaSteamSchool a convertir.
     * @return el objeto ReporteGeneralCurso resultante de la conversión.
     */
    public static ReporteGeneralCurso visitaSteamSchoolToReporteGeneralCurso(VisitaSteamSchool v) {

        ReporteGeneralCurso rg = new ReporteGeneralCurso();

        rg.setId(v.getId());
        rg.setFechaVisita(v.getFecha());

        rg.setIdCurso(CommonDTO.cursoToDTO(v.getIdCurso()));
        rg.setIdColegio(CommonDTO.colegioToDTO(v.getIdColegio()));
        rg.setIdPersona(CommonDTO.SteamToPersonaReporteDTO(v.getIdSteamStudent()));

        rg.setTipoConsulta("school");

        return rg;

    }

    /**
     * Convierte un objeto VisitaSteamYoung en un objeto ReporteGeneralCurso.
     *
     * @param v el objeto VisitaSteamYoung a convertir.
     * @return el objeto ReporteGeneralCurso resultante de la conversión.
     */
    public static ReporteGeneralCurso visitaSteamYoungToReporteGeneralCurso(VisitaSteamYoung v) {

        ReporteGeneralCurso rg = new ReporteGeneralCurso();

        rg.setId(v.getId());
        rg.setFechaVisita(v.getFecha());

        rg.setIdCurso(CommonDTO.cursoToDTO(v.getIdCurso()));
        rg.setIdColegio(CommonDTO.colegioToDTO(v.getIdColegio()));
        rg.setIdPersona(CommonDTO.SteamToPersonaReporteDTO(v.getIdSteamStudent()));

        rg.setTipoConsulta("young");

        return rg;

    }

    /**
     * Convierte un objeto Persona en un objeto PersonaReporteJson.
     *
     * @param u el objeto Persona a convertir.
     * @return el objeto PersonaReporteJson resultante de la conversión.
     */
    public static PersonaReporteJson personaToPersonaReporteJson(Persona u) {
        PersonaReporteJson ud = new PersonaReporteJson();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre() + ' ' + u.getApellido());
        ud.setDocumento(u.getDocumento());
        ud.setTelefono(u.getTelefono());
        ud.setFechaNacimiento(u.getFechaNacimiento());
        ud.setSexo(u.getSexo());
        ud.setCodigo(u.getCodigo());
        ud.setIdPoblacionEspecial(poblacionEspecialToDTO(u.getIdPoblacionEspecial()));
        ud.setIdTipoUsuario(tipoUsuarioToDTO(u.getIdTipoUsuario()));

        return ud;

    }

}
