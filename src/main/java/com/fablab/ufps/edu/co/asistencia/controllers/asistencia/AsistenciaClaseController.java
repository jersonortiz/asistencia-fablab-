/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.asistencia;

import com.fablab.ufps.edu.co.asistencia.common.CommonDTO;
import com.fablab.ufps.edu.co.asistencia.common.CommonReporte;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaClaseJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.entity.Aula;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.entity.PoblacionEspecial;
import com.fablab.ufps.edu.co.asistencia.entity.ProgramaAcademico;
import com.fablab.ufps.edu.co.asistencia.entity.TipoUsuario;
import com.fablab.ufps.edu.co.asistencia.entity.Universidad;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaClase;
import com.fablab.ufps.edu.co.asistencia.repository.PersonaRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaClaseRepository;
import com.fablab.ufps.edu.co.asistencia.services.PersonaService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controlador para la gestión de la asistencia a clases. Proporciona endpoints
 * para listar, reportar, crear y eliminar visitas de clase.
 *
 * @author jerson
 */
@RestController
@RequestMapping("/clase")
public class AsistenciaClaseController {

    @Autowired
    private VisitaClaseRepository visitaClaseRepository;

    @Autowired
    private PersonaService personaService;

    /**
     * Listar todas las visitas de clase.
     *
     * @return una lista de objetos representando las visitas de clase.
     */
    @GetMapping()
    public List<Object> list() {
        return visitaClaseRepository
                .findAll()
                .stream()
                .map(CommonDTO::visitaClaseToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Generar un reporte de todas las visitas de clase.
     *
     * @return una lista de objetos representando el reporte de visitas de
     * clase.
     */
    @GetMapping("/reporte")
    public List<Object> Reporte() {
        return visitaClaseRepository
                .findAll()
                .stream()
                .map(CommonReporte::VisitaClaseToReporteClaseJson)
                .collect(Collectors.toList());
    }

    /**
     * Crear una nueva visita de clase.
     *
     * @param input los datos de la visita de clase a crear.
     * @return una respuesta HTTP con el objeto creado o un mensaje de error.
     */
    @PostMapping
    public ResponseEntity post(@RequestBody AsistenciaClaseJson input) {

        try {
            // Creación de objetos necesarios
            VisitaClase vst = new VisitaClase();

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
            vst.setCodigoCarrera(input.getCodigoCarrera());

            vst.setCodigoCarrera(input.getCodigoCarrera());
            vst.setNombreMateria(input.getNombreMateria());
            vst.setNombreDocente(input.getNombreDocente());

            vst.setIdAula(new Aula(input.getIdAula()));
            vst.setIdUniversidad(new Universidad(input.getIdUniversidad()));
            vst.setIdPersona(personaService.createPersona(input));

            // Guardar en la base de datos
            vst = visitaClaseRepository.save(vst);

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
     * Eliminar una visita de clase por su ID.
     *
     * @param id el ID de la visita de clase a eliminar.
     * @return una respuesta HTTP indicando el resultado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

}
