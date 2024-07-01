/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.crud;

import com.fablab.ufps.edu.co.asistencia.common.CommonDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.AulaDTO;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.entity.Aula;
import com.fablab.ufps.edu.co.asistencia.repository.AulaRepository;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author jerson
 */
@RestController
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    private AulaRepository aulaRepository;

    @GetMapping()
    public List<Object> list() {

        return aulaRepository
                .findAll()
                .stream()
                .map(CommonDTO::aulaToDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        Optional<Aula> ou = aulaRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return ResponseEntity.ok(msg);
        }

        AulaDTO u = CommonDTO.aulaToDTO(ou.get());

        return ResponseEntity.ok(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable String id, @RequestBody AulaDTO input) {
        Optional<Aula> ou = aulaRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return ResponseEntity.ok(msg);
        }

        Aula u = ou.get();

        u.setNombre(input.getNombre());

        u = aulaRepository.save(u);

        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody AulaDTO input) {
        Aula u = new Aula();

        u.setNombre(input.getNombre());

        u = aulaRepository.save(u);

        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {

        Optional<Aula> ou = aulaRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {
            msg.setMsg("no existe");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }

        Aula u = ou.get();

        try {
            aulaRepository.delete(u);

            msg.setMsg("ok");
            return new ResponseEntity(msg, HttpStatus.OK);
        } catch (Exception e) {

            msg.setMsg("no");
            return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}
