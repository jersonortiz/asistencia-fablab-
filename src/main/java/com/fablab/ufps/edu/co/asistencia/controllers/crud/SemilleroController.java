/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.crud;

import com.fablab.ufps.edu.co.asistencia.common.CommonDTO;
import com.fablab.ufps.edu.co.asistencia.common.CommonEntity;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.SemilleroDTO;
import com.fablab.ufps.edu.co.asistencia.entity.Semillero;
import com.fablab.ufps.edu.co.asistencia.repository.SemilleroRepository;
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
@RequestMapping("/semillero")
public class SemilleroController {

    @Autowired
    private SemilleroRepository semilleroRepository;

    @GetMapping()
    public List<Object> list() {
        return semilleroRepository
                .findAll()
                .stream()
                .map(CommonDTO::semilleroToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        Optional<Semillero> ou = semilleroRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return ResponseEntity.ok(msg);
        }

        SemilleroDTO u = CommonDTO.semilleroToDTO(ou.get());

        return ResponseEntity.ok(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable String id, @RequestBody SemilleroDTO input) {
        Optional<Semillero> ou = semilleroRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Semillero u = ou.get();
        u.setNombre(input.getNombre());
        u.setSigla(input.getSigla());

        u = semilleroRepository.save(u);

        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody SemilleroDTO input) {

        Semillero u = CommonEntity.SemilleroDTOToEntity(input);

        System.out.println(input);
        System.out.println(u);
        u = semilleroRepository.save(u);
        System.out.println(u);
        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Optional<Semillero> ou = semilleroRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {
            msg.setMsg("no existe");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }

        Semillero u = ou.get();

        try {
            semilleroRepository.delete(u);

            msg.setMsg("ok");
            return new ResponseEntity(msg, HttpStatus.OK);
        } catch (Exception e) {

            msg.setMsg("no");
            return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
        }
    }

}
