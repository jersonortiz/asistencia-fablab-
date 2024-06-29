/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.asistencia;

import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PoblacionEspecialDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.TipoUsuarioDTO;
import com.fablab.ufps.edu.co.asistencia.dto.IngresoSalidaDTO;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.PersonaReporteJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteIngresoSalidaJson;
import com.fablab.ufps.edu.co.asistencia.entity.Aula;
import com.fablab.ufps.edu.co.asistencia.entity.IngresoSalidaPersonal;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.entity.PoblacionEspecial;
import com.fablab.ufps.edu.co.asistencia.entity.PracticantepasanteBeca;
import com.fablab.ufps.edu.co.asistencia.entity.TipoUsuario;
import com.fablab.ufps.edu.co.asistencia.repository.IngresoSalidaPersonalRepository;
import java.util.List;
import java.util.Optional;
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

/**
 *
 * @author jerson
 */
@RestController
@RequestMapping("/ingreso")
public class IngresoSalidaController {

    @Autowired
    private IngresoSalidaPersonalRepository ingresoSalidaPersonalRepository;

    @GetMapping()
    public List<Object> list() {
        return ingresoSalidaPersonalRepository
                .findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("entrada")
    public ResponseEntity postEntrada(@RequestBody IngresoSalidaDTO input) {
        System.out.println(input.toString());

        IngresoSalidaPersonal i = new IngresoSalidaPersonal();

        i.setId(input.getId());
        i.setEntradaSalida(Boolean.TRUE);
        i.setFechaIngreso(input.getFechaIngreso());
        i.setFechaRegistro(input.getFechaIngreso());
        i.setHora(input.getHora());

        PracticantepasanteBeca p = new PracticantepasanteBeca();

        p.setId(input.getIdPracticante());

        i.setIdPracticante(p);

        i = ingresoSalidaPersonalRepository.save(i);

        input.setId(i.getId());

        return ResponseEntity.ok(input);

    }

    @PostMapping("salida")
    public ResponseEntity postSalida(@RequestBody IngresoSalidaDTO input) {
        System.out.println(input.toString());

        IngresoSalidaPersonal i = new IngresoSalidaPersonal();

        i.setId(input.getId());
        i.setEntradaSalida(Boolean.TRUE);
        i.setFechaIngreso(input.getFechaIngreso());
        i.setFechaRegistro(input.getFechaIngreso());
        i.setHora(input.getHora());

        PracticantepasanteBeca p = new PracticantepasanteBeca();

        p.setId(input.getIdPracticante());

        i.setIdPracticante(p);

        i = ingresoSalidaPersonalRepository.save(i);

        input.setId(i.getId());

        return ResponseEntity.ok(input);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {

        Optional<IngresoSalidaPersonal> ou = ingresoSalidaPersonalRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {
            msg.setMsg("no existe");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }

        IngresoSalidaPersonal u = ou.get();

        try {
            ingresoSalidaPersonalRepository.delete(u);

            msg.setMsg("ok");
            return new ResponseEntity(msg, HttpStatus.OK);
        } catch (Exception e) {

            msg.setMsg("no");
            return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
        }
    }

    private ReporteIngresoSalidaJson toDTO(IngresoSalidaPersonal input) {

        ReporteIngresoSalidaJson i = new ReporteIngresoSalidaJson();

        i.setId(input.getId());
        i.setEntradaSalida(Boolean.TRUE);
        i.setFechaIngreso(input.getFechaIngreso());
        i.setFechaRegistro(input.getFechaIngreso());
        i.setHora(input.getHora());

        i.setIdPracticante(personaToPersonaReporteDTO(input.getIdPracticante().getIdPersona()));

        return i;

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