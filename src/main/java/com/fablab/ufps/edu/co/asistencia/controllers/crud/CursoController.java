/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.crud;

import com.fablab.ufps.edu.co.asistencia.common.CommonDTO;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.CursoDTO;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.entity.Cursos;
import com.fablab.ufps.edu.co.asistencia.repository.CursosRepository;
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
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursosRepository cursoRepository;

    @GetMapping()
    public List<Object> list() {

        return cursoRepository
                .findAll()
                .stream()
                .map(CommonDTO::cursoToDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        Optional<Cursos> ou = cursoRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return ResponseEntity.ok(msg);
        }

        CursoDTO u = CommonDTO.cursoToDTO(ou.get());

        return ResponseEntity.ok(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable String id, @RequestBody CursoDTO input) {
        Optional<Cursos> ou = cursoRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return ResponseEntity.ok(msg);
        }

        Cursos u = ou.get();

        u.setNombre(input.getNombre());

        u = cursoRepository.save(u);

        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody CursoDTO input) {

        Cursos u = new Cursos();

        u.setNombre(input.getNombre());

        u = cursoRepository.save(u);

        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Cursos> ou = cursoRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {
            msg.setMsg("no existe");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }

        Cursos u = ou.get();

        try {
            cursoRepository.delete(u);

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
