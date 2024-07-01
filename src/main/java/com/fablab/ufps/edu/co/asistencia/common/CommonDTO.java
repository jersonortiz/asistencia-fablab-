/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.common;

import com.fablab.ufps.edu.co.asistencia.dto.CRUD.AulaDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.ColegioDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.CursoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PersonaDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PoblacionEspecialDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PracticantepasanteBecaDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.ProgramaAcademicoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.SemilleroDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.SteamStudentDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.TipoUsuarioDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.UniversidadDTO;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.PersonaReporteJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteIngresoSalidaJson;
import com.fablab.ufps.edu.co.asistencia.dto.visita.VisitaClaseDTO;
import com.fablab.ufps.edu.co.asistencia.dto.visita.VisitaCursoColegioDTO;
import com.fablab.ufps.edu.co.asistencia.dto.visita.VisitaCursoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.visita.VisitaGrabacionDTO;
import com.fablab.ufps.edu.co.asistencia.dto.visita.VisitaSemilleroDTO;
import com.fablab.ufps.edu.co.asistencia.dto.visita.VisitaSocializacionColegioDTO;
import com.fablab.ufps.edu.co.asistencia.dto.visita.VisitaSocializacionDTO;
import com.fablab.ufps.edu.co.asistencia.dto.visita.VisitaSteamSchoolDTO;
import com.fablab.ufps.edu.co.asistencia.dto.visita.VisitaSteamYoungDTO;
import com.fablab.ufps.edu.co.asistencia.dto.visita.VisitaSustentacionProyectoGradoDTO;
import com.fablab.ufps.edu.co.asistencia.entity.Aula;
import com.fablab.ufps.edu.co.asistencia.entity.Colegio;
import com.fablab.ufps.edu.co.asistencia.entity.Cursos;
import com.fablab.ufps.edu.co.asistencia.entity.IngresoSalidaPersonal;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.entity.PoblacionEspecial;
import com.fablab.ufps.edu.co.asistencia.entity.PracticantepasanteBeca;
import com.fablab.ufps.edu.co.asistencia.entity.ProgramaAcademico;
import com.fablab.ufps.edu.co.asistencia.entity.Semillero;
import com.fablab.ufps.edu.co.asistencia.entity.SteamStudent;
import com.fablab.ufps.edu.co.asistencia.entity.TipoUsuario;
import com.fablab.ufps.edu.co.asistencia.entity.Universidad;
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
 * Clase CommonDTO que contiene métodos utilitarios para convertir diferentes
 * tipos de visitas a sus correspondientes objetos DTO.
 *
 * @autor jerson
 */
public class CommonDTO {

    /**
     * Convierte un objeto VisitaSocializacion en un objeto
     * VisitaSocializacionDTO.
     *
     * @param x el objeto VisitaSocializacion a convertir.
     * @return el objeto VisitaSocializacionDTO resultante de la conversión.
     */
    public static VisitaSocializacionDTO visitaSocializacionToDTO(VisitaSocializacion x) {
        VisitaSocializacionDTO a = new VisitaSocializacionDTO();

        a.setId(x.getId());
        a.setFecha(x.getFecha());

        if (x.getIdProgramaAcademico() != null) {
            a.setIdProgramaAcademico(x.getIdProgramaAcademico().getId());

        }

        a.setOtroPrograma(x.getOtroPrograma());

        a.setIdPersona(x.getIdPersona().getId());
        a.setIdUniversidad(x.getIdUniversidad().getId());

        return a;

    }

    /**
     * Convierte un objeto VisitaGrabacion en un objeto VisitaGrabacionDTO.
     *
     * @param x el objeto VisitaGrabacion a convertir.
     * @return el objeto VisitaGrabacionDTO resultante de la conversión.
     */
    public static VisitaGrabacionDTO visitaGrabacionToDTO(VisitaGrabacion x) {
        VisitaGrabacionDTO a = new VisitaGrabacionDTO();
        System.out.println(x.toString());

        a.setId(x.getId());
        a.setFecha(x.getFecha());
        a.setIdPersona(x.getIdPersona().getId());

        if (x.getIdSemillero() != null) {

            a.setIdSemillero(x.getIdSemillero().getId());
        }

        a.setExterno(x.getExterno());
        a.setOtroPrograma(x.getOtroPrograma());
        a.setRazonGrabacion(x.getRazonGrabacion());

        if (x.getIdProgramaAcademico() != null) {
            a.setIdProgramaAcademico(x.getIdProgramaAcademico().getId());

        }

        a.setIdUniversidad(x.getIdUniversidad().getId());

        return a;

    }

    /**
     * Convierte un objeto VisitaSustentacionProyectoGrado en un objeto
     * VisitaSustentacionProyectoGradoDTO.
     *
     * @param x el objeto VisitaSustentacionProyectoGrado a convertir.
     * @return el objeto VisitaSustentacionProyectoGradoDTO resultante de la
     * conversión.
     */
    public static VisitaSustentacionProyectoGradoDTO visitaSustentacionProyectoGradoToDTO(VisitaSustentacionProyectoGrado x) {
        VisitaSustentacionProyectoGradoDTO a = new VisitaSustentacionProyectoGradoDTO();

        a.setId(x.getId());
        a.setFecha(x.getFecha());

        if (x.getIdProgramaAcademico() != null) {
            a.setIdProgramaAcademico(x.getIdProgramaAcademico().getId());

        }

        a.setOtroPrograma(x.getOtroPrograma());

        a.setIdPersona(x.getIdPersona().getId());
        a.setIdUniversidad(x.getIdUniversidad().getId());

        return a;

    }

    /**
     * Convierte un objeto VisitaSocializacionColegio en un objeto
     * VisitaSocializacionColegioDTO.
     *
     * @param x el objeto VisitaSocializacionColegio a convertir.
     * @return el objeto VisitaSocializacionColegioDTO resultante de la
     * conversión.
     */
    public static VisitaSocializacionColegioDTO visitaSocializacionColegioToDTO(VisitaSocializacionColegio x) {
        VisitaSocializacionColegioDTO a = new VisitaSocializacionColegioDTO();

        a.setId(x.getId());
        a.setFecha(x.getFecha());

        a.setIdColegio(x.getIdColegio().getId());

        a.setIdPersona(x.getIdPersona().getId());

        return a;

    }

    /**
     * Convierte un objeto VisitaSemillero en un objeto VisitaSemilleroDTO.
     *
     * @param x el objeto VisitaSemillero a convertir.
     * @return el objeto VisitaSemilleroDTO resultante de la conversión.
     */
    public static VisitaSemilleroDTO visitaSemilleroToDTO(VisitaSemillero x) {
        VisitaSemilleroDTO a = new VisitaSemilleroDTO();

        a.setId(x.getId());
        a.setFecha(x.getFecha());

        if (x.getIdProgramaAcademico() != null) {
            a.setIdProgramaAcademico(x.getIdProgramaAcademico().getId());

        }

        a.setOtroPrograma(x.getOtroPrograma());

        a.setIdSemillero(x.getIdSemillero().getId());
        a.setIdPersona(x.getIdPersona().getId());
        a.setIdUniversidad(x.getIdUniversidad().getId());

        return a;

    }

    /**
     * Convierte un objeto VisitaClase en un objeto VisitaClaseDTO.
     *
     * @param x el objeto VisitaClase a convertir.
     * @return el objeto VisitaClaseDTO resultante de la conversión.
     */
    public static VisitaClaseDTO visitaClaseToDTO(VisitaClase x) {
        VisitaClaseDTO a = new VisitaClaseDTO();

        a.setId(x.getId());
        a.setIdPersona(x.getIdPersona().getId());
        a.setIdAula(x.getIdAula().getId());
        a.setIdUniversidad(x.getIdUniversidad().getId());
        if (x.getIdProgramaAcademico() != null) {
            a.setIdProgramaAcademico(x.getIdProgramaAcademico().getId());

        }
        a.setFecha(x.getFecha());
        a.setOtroPrograma(x.getOtroPrograma());
        a.setOtroUniversidad(x.getOtroUniversidad());
        a.setCodigoCarrera(x.getCodigoCarrera());
        a.setNombreMateria(x.getNombreMateria());
        a.setNombreDocente(x.getNombreDocente());

        return a;

    }

    /**
     * Convierte un objeto Aula en un objeto AulaDTO.
     *
     * @param x el objeto Aula a convertir.
     * @return el objeto AulaDTO resultante de la conversión.
     */
    public static AulaDTO aulaToDTO(Aula x) {

        AulaDTO a = new AulaDTO();
        a.setId(x.getId());
        a.setNombre(x.getNombre());

        return a;
    }

    /**
     * Convierte un objeto Semillero en un objeto SemilleroDTO.
     *
     * @param u el objeto Semillero a convertir.
     * @return el objeto SemilleroDTO resultante de la conversión.
     */
    public static SemilleroDTO semilleroToDTO(Semillero u) {
        SemilleroDTO ud = new SemilleroDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setSigla(u.getSigla());

        return ud;

    }

    /**
     * Convierte un objeto ProgramaAcademico en un objeto ProgramaAcademicoDTO.
     *
     * @param u el objeto ProgramaAcademico a convertir.
     * @return el objeto ProgramaAcademicoDTO resultante de la conversión.
     */
    public static ProgramaAcademicoDTO programaToDTO(ProgramaAcademico u) {
        ProgramaAcademicoDTO ud = new ProgramaAcademicoDTO();

        if (u == null) {
            return null;
        }

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setDescripcion(u.getDescripcion());

        return ud;

    }

    /**
     * Convierte un objeto Cursos en un objeto CursoDTO.
     *
     * @param u el objeto Cursos a convertir.
     * @return el objeto CursoDTO resultante de la conversión.
     */
    public static CursoDTO cursoToDTO(Cursos u) {
        CursoDTO ud = new CursoDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());

        return ud;

    }

    /**
     * Convierte un objeto Universidad en un objeto UniversidadDTO.
     *
     * @param u el objeto Universidad a convertir.
     * @return el objeto UniversidadDTO resultante de la conversión.
     */
    public static UniversidadDTO universidadToDTO(Universidad u) {
        UniversidadDTO ud = new UniversidadDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setDireccion(u.getDireccion());
        ud.setTelContacto(u.getTelContacto());

        return ud;

    }

    /**
     * Convierte un objeto SteamStudent en un objeto PersonaReporteJson.
     *
     * @param u el objeto SteamStudent a convertir.
     * @return el objeto PersonaReporteJson resultante de la conversión.
     */
    public static PersonaReporteJson SteamToPersonaReporteDTO(SteamStudent u) {
        PersonaReporteJson ud = new PersonaReporteJson();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setSexo(u.getSexo());
        ud.setIdPoblacionEspecial(poblacionEspecialToDTO(u.getIdPoblacionEspecial()));

        return ud;

    }

    /**
     * Convierte un objeto Persona en un objeto PersonaDTO.
     *
     * @param u el objeto Persona a convertir.
     * @return el objeto PersonaDTO resultante de la conversión.
     */
    public static PersonaDTO personaToDTO(Persona u) {
        PersonaDTO ud = new PersonaDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setApellido(u.getApellido());
        ud.setDocumento(u.getDocumento());
        ud.setTelefono(u.getTelefono());
        ud.setFechaNacimiento(u.getFechaNacimiento());
        ud.setSexo(u.getSexo());
        ud.setCodigo(u.getCodigo());
        ud.setIdPoblacionEspecial(u.getIdPoblacionEspecial().getId());
        ud.setIdTipoUsuario(u.getIdTipoUsuario().getId());

        return ud;

    }

    /**
     * Convierte un objeto Colegio en un objeto ColegioDTO.
     *
     * @param u el objeto Colegio a convertir.
     * @return el objeto ColegioDTO resultante de la conversión.
     */
    public static ColegioDTO colegioToDTO(Colegio u) {
        ColegioDTO ud = new ColegioDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setDireccion(u.getDireccion());
        ud.setTelContacto(u.getTelContacto());

        return ud;

    }

    /**
     * Convierte un objeto PoblacionEspecial en un objeto PoblacionEspecialDTO.
     *
     * @param u el objeto PoblacionEspecial a convertir.
     * @return el objeto PoblacionEspecialDTO resultante de la conversión.
     */
    public static PoblacionEspecialDTO poblacionEspecialToDTO(PoblacionEspecial u) {
        PoblacionEspecialDTO ud = new PoblacionEspecialDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        return ud;

    }

    /**
     * Convierte un objeto TipoUsuario en un objeto TipoUsuarioDTO.
     *
     * @param u el objeto TipoUsuario a convertir.
     * @return el objeto TipoUsuarioDTO resultante de la conversión.
     */
    public static TipoUsuarioDTO tipoUsuarioToDTO(TipoUsuario u) {
        TipoUsuarioDTO ud = new TipoUsuarioDTO();

        ud.setId(u.getId());
        ud.setTipo(u.getTipo());
        return ud;

    }

    /**
     * Convierte un objeto SteamStudent en un objeto SteamStudentDTO.
     *
     * @param u el objeto SteamStudent a convertir.
     * @return el objeto SteamStudentDTO resultante de la conversión.
     */
    public static SteamStudentDTO SteamStudentToDTO(SteamStudent u) {
        SteamStudentDTO ud = new SteamStudentDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setSexo(u.getSexo());
        ud.setSemestre(u.getSemestre());
        ud.setIdPoblacionEspecial(u.getIdPoblacionEspecial().getId());
        ud.setIdColegio(u.getIdColegio().getId());

        return ud;

    }

    /**
     * Convierte un objeto PracticantepasanteBeca en un objeto
     * PracticantepasanteBecaDTO.
     *
     * @param u el objeto PracticantepasanteBeca a convertir.
     * @return el objeto PracticantepasanteBecaDTO resultante de la conversión.
     */
    public static PracticantepasanteBecaDTO practicantepasanteBecaToDTO(PracticantepasanteBeca u) {
        PracticantepasanteBecaDTO ud = new PracticantepasanteBecaDTO();

        ud.setId(u.getId());
        ud.setIdCarnet(u.getIdCarnet());
        ud.setEstado(u.getEstado());
        ud.setIdPersona(CommonReporte.personaToPersonaReporteJson(u.getIdPersona()));
        ud.setSemestre(u.getSemestre());

        return ud;

    }

    /**
     * Convierte un objeto IngresoSalidaPersonal en un objeto
     * ReporteIngresoSalidaJson.
     *
     * @param input el objeto IngresoSalidaPersonal a convertir.
     * @return el objeto ReporteIngresoSalidaJson resultante de la conversión.
     */
    public static ReporteIngresoSalidaJson ingresoSalidaPersonalToDTO(IngresoSalidaPersonal input) {

        ReporteIngresoSalidaJson i = new ReporteIngresoSalidaJson();

        i.setId(input.getId());
        i.setEntradaSalida(Boolean.TRUE);
        i.setFechaIngreso(input.getFechaIngreso());
        i.setFechaRegistro(input.getFechaIngreso());
        i.setHora(input.getHora());

        i.setIdPracticante(CommonReporte.personaToPersonaReporteJson(input.getIdPracticante().getIdPersona()));

        return i;

    }

    /**
     * Convierte un objeto VisitaCursoColegio en un objeto
     * VisitaCursoColegioDTO.
     *
     * @param x el objeto VisitaCursoColegio a convertir.
     * @return el objeto VisitaCursoColegioDTO resultante de la conversión.
     */
    public static VisitaCursoColegioDTO visitaCursoColegioToDTO(VisitaCursoColegio x) {
        VisitaCursoColegioDTO a = new VisitaCursoColegioDTO();

        a.setId(x.getId());
        a.setFechaVisita(x.getFechaVisita());
        a.setSesion(x.getSesion());
        a.setIdColegio(x.getIdColegio().getId());
        a.setIdCurso(x.getIdCurso().getId());
        a.setIdPersona(x.getIdPersona().getId());

        return a;

    }

    /**
     * Convierte un objeto VisitaCurso en un objeto VisitaCursoDTO.
     *
     * @param x el objeto VisitaCurso a convertir.
     * @return el objeto VisitaCursoDTO resultante de la conversión.
     */
    public static VisitaCursoDTO visitaCursotoDTO(VisitaCurso x) {
        VisitaCursoDTO a = new VisitaCursoDTO();

        a.setId(x.getId());
        a.setFechaVisita(x.getFechaVisita());
        a.setSesion(x.getSesion());

        if (x.getIdProgramaAcademico() != null) {
            a.setIdProgramaAcademico(x.getIdProgramaAcademico().getId());

        }

        a.setOtroPrograma(x.getOtroPrograma());

        a.setIdCurso(x.getIdCurso().getId());
        a.setIdPersona(x.getIdPersona().getId());
        a.setIdUniversidad(x.getIdUniversidad().getId());

        return a;

    }

    /**
     * Convierte un objeto VisitaSteamSchool en un objeto VisitaSteamSchoolDTO.
     *
     * @param x el objeto VisitaSteamSchool a convertir.
     * @return el objeto VisitaSteamSchoolDTO resultante de la conversión.
     */
    public static VisitaSteamSchoolDTO visitaSteamSchoolToDTO(VisitaSteamSchool x) {
        VisitaSteamSchoolDTO a = new VisitaSteamSchoolDTO();

        a.setIdColegio(x.getIdColegio().getId());
        a.setIdCurso(x.getIdCurso().getId());
        a.setIdSteamStudent(x.getIdSteamStudent().getId());
        a.setFecha(x.getFecha());
        a.setId(x.getId());
        return a;

    }

    /**
     * Convierte un objeto VisitaSteamYoung en un objeto VisitaSteamYoungDTO.
     *
     * @param x el objeto VisitaSteamYoung a convertir.
     * @return el objeto VisitaSteamYoungDTO resultante de la conversión.
     */
    public static VisitaSteamYoungDTO visitaSteamYoungToDTO(VisitaSteamYoung x) {
        VisitaSteamYoungDTO a = new VisitaSteamYoungDTO();

        a.setIdColegio(x.getIdColegio().getId());
        a.setIdCurso(x.getIdCurso().getId());
        a.setIdSteamStudent(x.getIdSteamStudent().getId());
        a.setFecha(x.getFecha());
        a.setId(x.getId());
        return a;

    }

}
