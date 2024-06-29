/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.asistencia;

import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PoblacionEspecialDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.ProgramaAcademicoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.SemilleroDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.TipoUsuarioDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.UniversidadDTO;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaCursoJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaSemilleroJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.PersonaReporteJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteSemilleroJson;
import com.fablab.ufps.edu.co.asistencia.dto.visita.VisitaCursoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.visita.VisitaSemilleroDTO;
import com.fablab.ufps.edu.co.asistencia.entity.Cursos;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.entity.PoblacionEspecial;
import com.fablab.ufps.edu.co.asistencia.entity.ProgramaAcademico;
import com.fablab.ufps.edu.co.asistencia.entity.Semillero;
import com.fablab.ufps.edu.co.asistencia.entity.TipoUsuario;
import com.fablab.ufps.edu.co.asistencia.entity.Universidad;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaCurso;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaSemillero;
import com.fablab.ufps.edu.co.asistencia.repository.PersonaRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaCursoRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaSemilleroRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author jerson
 */
@RestController
@RequestMapping("/visitasemillero")
public class AsistenciaSemilleroController {

    @Autowired
    private VisitaSemilleroRepository visitaSemilleroRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping()
    public List<Object> list() {
        return visitaSemilleroRepository
                .findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/reporte")
    public List<Object> Reporte() {
        return visitaSemilleroRepository.findAll()
                .stream()
                .map(this::toJson)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody AsistenciaSemilleroJson input) {
        try {
            // Creación de objetos necesarios
            VisitaSemillero vst = new VisitaSemillero();

            if (input.getOtroPrograma() != null && !input.getOtroPrograma().isBlank()) {
                vst.setOtroPrograma(input.getOtroPrograma());
                vst.setIdProgramaAcademico(null); // Asegurar que idProgramaAcademico sea null si otroPrograma está presente
            } else if (input.getIdProgramaAcademico() > 0) {
                vst.setIdProgramaAcademico(new ProgramaAcademico(input.getIdProgramaAcademico()));
                vst.setOtroPrograma(null); // Asegurar que otroPrograma sea null si idProgramaAcademico está presente
            } else {
                return ResponseEntity.badRequest().body("Debe especificar un programa académico válido o proporcionar otro programa");
            }

            vst.setFecha(input.getFecha());

            vst.setIdSemillero(new Semillero(input.getIdSemillero()));
            vst.setIdPersona(CreatePersona(input));
            vst.setIdUniversidad(new Universidad(input.getIdUniversidad()));

            // Guardar en la base de datos
            vst = visitaSemilleroRepository.save(vst);

            input.setId(vst.getId());
            // Respuesta exitosa
            return ResponseEntity.ok(input);  // O podrías devolver un DTO específico con los datos guardados si lo deseas
        } catch (Exception e) {
            // Manejo de excepciones
            MensajeJson msg = new MensajeJson();
            e.printStackTrace();
            msg.setMsg("Error al procesar la solicitud");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

    private Persona CreatePersona(AsistenciaSemilleroJson input) {

        try {

            Persona ud = new Persona();

            ud.setNombre(input.getNombre());
            ud.setApellido(input.getApellido());
            ud.setDocumento(input.getDocumento());
            ud.setTelefono(input.getTelefono());
            ud.setCodigo(input.getCodigo());
            ud.setFechaNacimiento(input.getFechaNacimiento());
            ud.setSexo(input.getSexo());

            ud.setIdPoblacionEspecial(new PoblacionEspecial(input.getIdPoblacionEspecial()));

            ud.setIdTipoUsuario(new TipoUsuario(input.getIdTipoUsuario()));

            return personaRepository.save(ud);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear el estudiante", e);
        }
    }

    private ReporteSemilleroJson toJson(VisitaSemillero v) {
        ReporteSemilleroJson s = new ReporteSemilleroJson();

        s.setId(v.getId());
        s.setFecha(v.getFecha());
        s.setOtroPrograma(v.getOtroPrograma());

        s.setIdPersona(personaToPersonaReporteDTO(v.getIdPersona()));
        s.setIdSemillero(semilleroToDTO(v.getIdSemillero()));
        s.setIdUniversidad(universidadToDTO(v.getIdUniversidad()));
        s.setIdProgramaAcademico(programaToDTO(v.getIdProgramaAcademico()));

        return s;

    }

    private VisitaSemilleroDTO toDTO(VisitaSemillero x) {
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

    private SemilleroDTO semilleroToDTO(Semillero u) {
        SemilleroDTO ud = new SemilleroDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setSigla(u.getSigla());

        return ud;

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

    private UniversidadDTO universidadToDTO(Universidad u) {
        UniversidadDTO ud = new UniversidadDTO();

        ud.setId(u.getId());
        ud.setNombre(u.getNombre());
        ud.setDireccion(u.getDireccion());
        ud.setTelContacto(u.getTelContacto());

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