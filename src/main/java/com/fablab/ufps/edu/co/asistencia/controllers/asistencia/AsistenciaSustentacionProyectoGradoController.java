/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.asistencia;

import com.fablab.ufps.edu.co.asistencia.common.CommonDTO;
import com.fablab.ufps.edu.co.asistencia.common.CommonReporte;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaSustentacionProyectoGradoJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.entity.PoblacionEspecial;
import com.fablab.ufps.edu.co.asistencia.entity.ProgramaAcademico;
import com.fablab.ufps.edu.co.asistencia.entity.TipoUsuario;
import com.fablab.ufps.edu.co.asistencia.entity.Universidad;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaSustentacionProyectoGrado;
import com.fablab.ufps.edu.co.asistencia.repository.PersonaRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaSustentacionProyectoGradoRepository;
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
 *
 * @author jerson
 */
@RestController
@RequestMapping("/grado")
public class AsistenciaSustentacionProyectoGradoController {

    @Autowired
    private VisitaSustentacionProyectoGradoRepository visitaSustentacionProyectoGradoRepository;
    @Autowired
    private PersonaService personaService;

    @GetMapping()
    public List<Object> list() {
        return visitaSustentacionProyectoGradoRepository
                .findAll()
                .stream()
                .map(CommonDTO::visitaSustentacionProyectoGradoToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/reporte")
    public List<Object> reporte() {
        return visitaSustentacionProyectoGradoRepository
                .findAll()
                .stream()
                .map(CommonReporte::visitaSustentacionProyectoGradoToJson)
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
    public ResponseEntity post(@RequestBody AsistenciaSustentacionProyectoGradoJson input) {
        try {
            // Creación de objetos necesarios
            VisitaSustentacionProyectoGrado vst = new VisitaSustentacionProyectoGrado();

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
            vst.setIdPersona(personaService.createPersona(input));
            vst.setIdUniversidad(new Universidad(input.getIdUniversidad()));

            // Guardar en la base de datos
            vst = visitaSustentacionProyectoGradoRepository.save(vst);

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

}
