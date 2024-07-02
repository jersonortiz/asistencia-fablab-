/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.asistencia;

import com.fablab.ufps.edu.co.asistencia.common.CommonDTO;
import com.fablab.ufps.edu.co.asistencia.common.CommonReporte;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaCursoJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.entity.Cursos;
import com.fablab.ufps.edu.co.asistencia.entity.ProgramaAcademico;
import com.fablab.ufps.edu.co.asistencia.entity.Universidad;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaCurso;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaCursoRepository;
import com.fablab.ufps.edu.co.asistencia.services.PersonaService;
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

/**
 * Controlador para la gestión de la asistencia a cursos. Proporciona endpoints
 * para listar, crear, obtener, actualizar y eliminar visitas de curso.
 *
 * @author jerson
 */
@RestController
@RequestMapping("/cursoclase")
public class AsistenciaCursoController {

    @Autowired
    private VisitaCursoRepository visitaCursoRepository;

    @Autowired
    private PersonaService personaService;

    /**
     * Maneja las solicitudes HTTP GET para listar todos los registros de
     * visitas a cursos.
     *
     * @return una lista de objetos convertidos a DTO.
     */
    @GetMapping()
    public List<Object> list() {
        return visitaCursoRepository
                .findAll()
                .stream()
                .map(CommonDTO::visitaCursotoDTO)
                .collect(Collectors.toList());
    }

    /**
     * Maneja las solicitudes HTTP GET al endpoint "/reporte". Recupera todos
     * los registros del `visitaCursoRepository`, los convierte usando
     * `CommonReporte.visitaCursoToReporteGeneralCurso`, y devuelve los
     * registros convertidos como una lista.
     *
     * @return una lista de objetos convertidos a reporte general.
     */
    @GetMapping("/reporte")
    public List<Object> reporte() {
        return visitaCursoRepository
                .findAll()
                .stream()
                .map(CommonReporte::visitaCursoToReporteGeneralCurso)
                .collect(Collectors.toList());
    }

    /**
     * Maneja las solicitudes HTTP GET para obtener un registro específico por
     * ID.
     *
     * @param id el ID del registro a obtener.
     * @return el objeto correspondiente al ID especificado.
     */
    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        return null;
    }

    /**
     * Maneja las solicitudes HTTP PUT para actualizar un registro específico
     * por ID.
     *
     * @param id el ID del registro a actualizar.
     * @param input el objeto con los nuevos datos.
     * @return una respuesta indicando el resultado de la operación.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }

    /**
     * Maneja las solicitudes HTTP POST para crear un nuevo registro de
     * asistencia a curso.
     *
     * @param input el objeto con los datos del nuevo registro.
     * @return una respuesta con el registro creado o un mensaje de error.
     */
    @PostMapping
    public ResponseEntity post(@RequestBody AsistenciaCursoJson input) {
        try {
            // Creación de objetos necesarios
            VisitaCurso vst = new VisitaCurso();

            if (input.getOtroPrograma() != null && !input.getOtroPrograma().isBlank()) {
                vst.setOtroPrograma(input.getOtroPrograma());
                vst.setIdProgramaAcademico(null); // Asegurar que idProgramaAcademico sea null si otroPrograma está presente
            } else if (input.getIdProgramaAcademico() > 0) {
                vst.setIdProgramaAcademico(new ProgramaAcademico(input.getIdProgramaAcademico()));
                vst.setOtroPrograma(null); // Asegurar que otroPrograma sea null si idProgramaAcademico está presente
            } else {
                return ResponseEntity.badRequest().body("Debe especificar un programa académico válido o proporcionar otro programa");
            }

            vst.setFechaVisita(input.getFechaVisita());
            vst.setSesion(input.getSesion());
            vst.setIdCurso(new Cursos(input.getIdCurso()));
            vst.setIdPersona(personaService.createPersona(input));
            vst.setIdUniversidad(new Universidad(input.getIdUniversidad()));

            // Guardar en la base de datos
            vst = visitaCursoRepository.save(vst);

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

    /**
     * Maneja las solicitudes HTTP DELETE para eliminar un registro específico
     * por ID.
     *
     * @param id el ID del registro a eliminar.
     * @return una respuesta indicando el resultado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

}
