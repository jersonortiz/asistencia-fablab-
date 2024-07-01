/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.crud;

import com.fablab.ufps.edu.co.asistencia.common.CommonDTO;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PersonaDTO;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.entity.PoblacionEspecial;
import com.fablab.ufps.edu.co.asistencia.entity.TipoUsuario;
import com.fablab.ufps.edu.co.asistencia.repository.PersonaRepository;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
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
 *
 * @author jerson
 */
@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping()
    public List<Object> list() {
        return personaRepository
                .findAll()
                .stream()
                .map(CommonDTO::personaToDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        Optional<Persona> ou = personaRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return ResponseEntity.ok(msg);
        }

        PersonaDTO u = CommonDTO.personaToDTO(ou.get());

        return ResponseEntity.ok(u);
    }

    @GetMapping("/doc/{doc}")
    public ResponseEntity getDocumento(@PathVariable String doc) {
        Optional<Persona> ou = personaRepository.findByDocumento(doc);

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }

        PersonaDTO u = CommonDTO.personaToDTO(ou.get());

        return ResponseEntity.ok(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable String id, @RequestBody PersonaDTO input) {
        Optional<Persona> ou = personaRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Persona ud = ou.get();

        ud.setNombre(input.getNombre());
        ud.setApellido(input.getApellido());
        ud.setDocumento(input.getDocumento());
        ud.setTelefono(input.getTelefono());
        ud.setFechaNacimiento(input.getFechaNacimiento());
        ud.setSexo(input.getSexo());
        ud.setCodigo(input.getCodigo());
        PoblacionEspecial pe = new PoblacionEspecial();

        pe.setId(input.getIdPoblacionEspecial());

        ud.setIdPoblacionEspecial(pe);

        TipoUsuario tu = new TipoUsuario();

        tu.setId(input.getIdTipoUsuario());
        ud.setIdTipoUsuario(tu);

        ud = personaRepository.save(ud);

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody PersonaDTO input) {
        Persona ud = new Persona();

        ud.setNombre(input.getNombre());
        ud.setApellido(input.getApellido());
        ud.setDocumento(input.getDocumento());
        ud.setTelefono(input.getTelefono());
        ud.setFechaNacimiento(input.getFechaNacimiento());
        ud.setSexo(input.getSexo());
        ud.setCodigo(input.getCodigo());

        PoblacionEspecial pe = new PoblacionEspecial();

        pe.setId(input.getIdPoblacionEspecial());

        ud.setIdPoblacionEspecial(pe);

        TipoUsuario tu = new TipoUsuario();

        tu.setId(input.getIdTipoUsuario());
        ud.setIdTipoUsuario(tu);

     
        ud = personaRepository.save(ud);
    
        input.setId(ud.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Optional<Persona> ou = personaRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {
            msg.setMsg("no existe");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }

        Persona u = ou.get();

        try {
            personaRepository.delete(u);

            msg.setMsg("ok");
            return new ResponseEntity(msg, HttpStatus.OK);
        } catch (Exception e) {

            msg.setMsg("no");
            return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
        }
    }

}
