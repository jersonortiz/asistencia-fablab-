/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.crud;

import com.fablab.ufps.edu.co.asistencia.common.CommonDTO;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.UniversidadDTO;
import com.fablab.ufps.edu.co.asistencia.entity.Universidad;
import com.fablab.ufps.edu.co.asistencia.repository.UniversidadRepository;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author jerson
 */
@RestController
@RequestMapping("/universidad")
@CrossOrigin
public class UniversidadController {

    @Autowired
    private UniversidadRepository universidadRepository;

    /*
   
    
     */
    @GetMapping()
    public List<Object> list() {
        return universidadRepository
                .findAll()
                .stream()
                .map(CommonDTO::universidadToDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {

        Optional<Universidad> ou = universidadRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return ResponseEntity.ok(msg);
        }

        UniversidadDTO u = CommonDTO.universidadToDTO(ou.get());

        return ResponseEntity.ok(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable String id, @RequestBody UniversidadDTO input) {

        Optional<Universidad> ou = universidadRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Universidad u = ou.get();
        u.setNombre(input.getNombre());
        u.setDireccion(input.getDireccion());
        u.setTelContacto(input.getTelContacto());

        u = universidadRepository.save(u);

        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody UniversidadDTO input) {

        Universidad u = new Universidad();
        u.setNombre(input.getNombre());
        u.setDireccion(input.getDireccion());
        u.setTelContacto(input.getTelContacto());
        System.out.println(input);
        System.out.println(u);
        u = universidadRepository.save(u);
        System.out.println(u);
        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {

        Optional<Universidad> ou = universidadRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {
            msg.setMsg("no existe");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }

        Universidad u = ou.get();

        try {
            universidadRepository.delete(u);

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
