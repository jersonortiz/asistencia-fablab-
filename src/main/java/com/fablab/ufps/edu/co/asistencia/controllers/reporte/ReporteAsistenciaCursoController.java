/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.reporte;

import com.fablab.ufps.edu.co.asistencia.dto.CRUD.ColegioDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.CursoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PoblacionEspecialDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.ProgramaAcademicoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.TipoUsuarioDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.UniversidadDTO;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.PersonaReporteJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteCursoJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteGeneralCurso;
import com.fablab.ufps.edu.co.asistencia.entity.Colegio;
import com.fablab.ufps.edu.co.asistencia.entity.Cursos;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.entity.PoblacionEspecial;
import com.fablab.ufps.edu.co.asistencia.entity.ProgramaAcademico;
import com.fablab.ufps.edu.co.asistencia.entity.SteamStudent;
import com.fablab.ufps.edu.co.asistencia.entity.TipoUsuario;
import com.fablab.ufps.edu.co.asistencia.entity.Universidad;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaCurso;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaCursoColegio;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaSteamSchool;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaSteamYoung;
import com.fablab.ufps.edu.co.asistencia.repository.PersonaRepository;
import com.fablab.ufps.edu.co.asistencia.repository.PoblacionEspecialRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaCursoColegioRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaCursoRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaSteamSchoolRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaSteamYoungRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jerson
 */
@RestController
@RequestMapping("/reporte")
public class ReporteAsistenciaCursoController {

    @Autowired
    private VisitaCursoRepository visitaCursoRepository;

    @Autowired
    private VisitaCursoColegioRepository visitaCursoColegioRepository;

      @Autowired
    private VisitaSteamSchoolRepository visitaSteamSchoolRepository;

    @Autowired
    private VisitaSteamYoungRepository visitaSteamYoungRepository;
    
        @Autowired
    private PersonaRepository personaRepository;

        
            @GetMapping("/persona")
    public List<Object> listP() {
              return personaRepository
                .findAll()
                .stream()
                .map(this::personaToPersonaReporteDTO)
                .collect(Collectors.toList());
    }
        
    @GetMapping()
    public List<Object> list() {
        
         List<ReporteGeneralCurso> combinedList = new ArrayList<>();
      combinedList.addAll(listCurso());
        combinedList.addAll(listColegio());
        combinedList.addAll(listSchool());
        combinedList.addAll(listYoung());

        return combinedList.stream()
                .sorted(Comparator.comparing(obj -> ((ReporteGeneralCurso) obj).getFechaVisita(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}/{tipo}")
    public ResponseEntity<?> delete(@PathVariable String id,@PathVariable String tipo ) {

        Optional<VisitaCurso> ou = visitaCursoRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {
            msg.setMsg("no existe");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }

        VisitaCurso u = ou.get();

        try {
            visitaCursoRepository.delete(u);

            msg.setMsg("ok");
            return new ResponseEntity(msg, HttpStatus.OK);
        } catch (Exception e) {

            msg.setMsg("no");
            return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
        }
    }

    public List<ReporteGeneralCurso> listCurso() {
        return visitaCursoRepository
                .findAll()
                .stream()
                .map(this::toReporteGeneralCurso)
                .collect(Collectors.toList());
    }

    public List<ReporteGeneralCurso> listColegio() {
        return visitaCursoColegioRepository
                .findAll()
                .stream()
                .map(this::toReporteGeneralCurso)
                .collect(Collectors.toList());
    }

    public List<ReporteGeneralCurso> listSchool() {
        return visitaSteamSchoolRepository
                .findAll()
                .stream()
                .map(this::toReporteGeneralCurso)
                .collect(Collectors.toList());
    }

    public List<ReporteGeneralCurso> listYoung() {
        return visitaSteamYoungRepository
                .findAll()
                .stream()
                .map(this::toReporteGeneralCurso)
                .collect(Collectors.toList());
    }

    private ReporteGeneralCurso toDTO(VisitaCurso x) {
        ReporteCursoJson a = new ReporteCursoJson();

        return toReporteGeneralCurso(x);

    }

    public ReporteGeneralCurso toReporteGeneralCurso(VisitaCurso v) {

        ReporteGeneralCurso rg = new ReporteGeneralCurso();

        rg.setId(v.getId());
        rg.setFechaVisita(v.getFechaVisita());
        rg.setSesion(v.getSesion());

        rg.setOtroPrograma(v.getOtroPrograma());

        rg.setIdCurso(cursoToDTO(v.getIdCurso()));
        rg.setIdUniversidad(universidadToDTO(v.getIdUniversidad()));
        rg.setIdProgramaAcademico(programaToDTO(v.getIdProgramaAcademico()));
        rg.setIdPersona(personaToPersonaReporteDTO(v.getIdPersona()));

        rg.setTipoConsulta("curso");

        return rg;
    }

    public ReporteGeneralCurso toReporteGeneralCurso(VisitaCursoColegio v) {
        ReporteGeneralCurso rg = new ReporteGeneralCurso();
        rg.setId(v.getId());
        rg.setFechaVisita(v.getFechaVisita());
        rg.setSesion(v.getSesion());

        rg.setIdCurso(cursoToDTO(v.getIdCurso()));
        rg.setIdColegio(colegioToDTO(v.getIdColegio()));
        rg.setIdPersona(personaToPersonaReporteDTO(v.getIdPersona()));

        rg.setTipoConsulta("colegio");

        return rg;
    }

    public ReporteGeneralCurso toReporteGeneralCurso(VisitaSteamSchool v) {

        ReporteGeneralCurso rg = new ReporteGeneralCurso();

        rg.setId(v.getId());
        rg.setFechaVisita(v.getFecha());

        rg.setIdCurso(cursoToDTO(v.getIdCurso()));
        rg.setIdColegio(colegioToDTO(v.getIdColegio()));
        rg.setIdPersona(SteamToPersonaReporteDTO(v.getIdSteamStudent()));

        rg.setTipoConsulta("school");

        return rg;

    }

    public ReporteGeneralCurso toReporteGeneralCurso(VisitaSteamYoung v) {

        ReporteGeneralCurso rg = new ReporteGeneralCurso();

        rg.setId(v.getId());
        rg.setFechaVisita(v.getFecha());

        rg.setIdCurso(cursoToDTO(v.getIdCurso()));
        rg.setIdColegio(colegioToDTO(v.getIdColegio()));
        rg.setIdPersona(SteamToPersonaReporteDTO(v.getIdSteamStudent()));

        rg.setTipoConsulta("young");

        return rg;

    }

    private ProgramaAcademicoDTO programaToDTO(ProgramaAcademico u) {
        ProgramaAcademicoDTO ud = new ProgramaAcademicoDTO();

        if (u == null) {
            return null;
        }

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setDescripcion(u.getDescripcion());

        return ud;

    }

    private CursoDTO cursoToDTO(Cursos u) {
        CursoDTO ud = new CursoDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());

        return ud;

    }



    private UniversidadDTO universidadToDTO(Universidad u) {
        UniversidadDTO ud = new UniversidadDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setDireccion(u.getDireccion());
        ud.setTelContacto(u.getTelContacto());

        return ud;

    }

    private PersonaReporteJson SteamToPersonaReporteDTO(SteamStudent u) {
        PersonaReporteJson ud = new PersonaReporteJson();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setSexo(u.getSexo());
        ud.setIdPoblacionEspecial(poblacionEspecialToDTO(u.getIdPoblacionEspecial()));

        return ud;

    }

    private ColegioDTO colegioToDTO(Colegio u) {
        ColegioDTO ud = new ColegioDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setDireccion(u.getDireccion());
        ud.setTelContacto(u.getTelContacto());

        return ud;

    }

    private PersonaReporteJson personaToPersonaReporteDTO(Persona u) {
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

    private PoblacionEspecialDTO poblacionEspecialToDTO(PoblacionEspecial u) {
        PoblacionEspecialDTO ud = new PoblacionEspecialDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        return ud;

    }

    private TipoUsuarioDTO tipoUsuarioToDTO(TipoUsuario u) {
        TipoUsuarioDTO ud = new TipoUsuarioDTO();

        ud.setId(u.getId());
        ud.setTipo(u.getTipo());
        return ud;

    }

}
