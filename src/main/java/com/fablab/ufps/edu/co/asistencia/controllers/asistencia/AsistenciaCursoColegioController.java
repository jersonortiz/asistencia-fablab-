/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.asistencia;

import com.fablab.ufps.edu.co.asistencia.common.CommonDTO;
import com.fablab.ufps.edu.co.asistencia.common.CommonReporte;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaCursoColegioJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.entity.Colegio;
import com.fablab.ufps.edu.co.asistencia.entity.Cursos;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.entity.PoblacionEspecial;
import com.fablab.ufps.edu.co.asistencia.entity.TipoUsuario;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaCursoColegio;
import com.fablab.ufps.edu.co.asistencia.repository.PersonaRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaCursoColegioRepository;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controlador para la gestión de la asistencia a cursos en colegios.
 * Proporciona endpoints para listar, crear, obtener, actualizar y eliminar
 * visitas de curso en colegios.
 *
 * @author jerson
 */
@RestController
@RequestMapping("/cursocolegio")
public class AsistenciaCursoColegioController {

    @Autowired
    private VisitaCursoColegioRepository visitaCursoColegioRepository;

    
        @Autowired
    private PersonaService personaService;

    /**
     * Obtener la lista de todas las visitas de curso en colegios.
     *
     * @return una lista de objetos representando las visitas de curso en
     * colegios.
     */
    @GetMapping()
    public List<Object> list() {
        return visitaCursoColegioRepository
                .findAll()
                .stream()
                .map(CommonDTO::visitaCursoColegioToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Este método maneja las solicitudes HTTP GET al endpoint "/reporte".
     * Recupera todos los registros del `visitaCursoColegioRepository`,
     * convierte cada registro utilizando el método
     * `CommonReporte.visitaCursoColegioToReporteGeneralCurso`, y devuelve los
     * registros convertidos como una lista.
     *
     * @return una lista de objetos, donde cada objeto representa un registro
     * convertido del repositorio.
     */
    @GetMapping("/reporte")
    public List<Object> reporte() {
        return visitaCursoColegioRepository
                .findAll()
                .stream()
                .map(CommonReporte::visitaCursoColegioToReporteGeneralCurso)
                .collect(Collectors.toList());
    }

    /**
     * Obtener una visita de curso en colegio por su ID.
     *
     * @param id el ID de la visita de curso en colegio a obtener.
     * @return el objeto representando la visita de curso en colegio, o un
     * mensaje indicando que no existe.
     */
    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        return null;
    }

    /**
     * Actualizar una visita de curso en colegio por su ID.
     *
     * @param id el ID de la visita de curso en colegio a actualizar.
     * @param input los nuevos datos de la visita de curso en colegio.
     * @return una respuesta HTTP indicando el resultado de la operación.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }

    /**
     * Crear una nueva visita de curso en colegio.
     *
     * @param input los datos de la visita de curso en colegio a crear.
     * @return una respuesta HTTP con el objeto creado o un mensaje de error.
     */
    @PostMapping
    public ResponseEntity post(@RequestBody AsistenciaCursoColegioJson input) {
        try {
            // Creación de objetos necesarios
            VisitaCursoColegio vst = new VisitaCursoColegio();

            vst.setFechaVisita(input.getFechaVisita());
            vst.setSesion(input.getSesion());
            vst.setIdColegio(new Colegio(input.getIdColegio()));

            vst.setIdCurso(new Cursos(input.getIdCurso()));
            vst.setIdPersona(personaService.createPersona(input));

            // Guardar en la base de datos
            vst = visitaCursoColegioRepository.save(vst);
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
     * Eliminar una visita de curso en colegio por su ID.
     *
     * @param id el ID de la visita de curso en colegio a eliminar.
     * @return una respuesta HTTP indicando el resultado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

  

}
