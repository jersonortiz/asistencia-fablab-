/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.crud;

import com.fablab.ufps.edu.co.asistencia.common.CommonDTO;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.ProgramaAcademicoDTO;
import com.fablab.ufps.edu.co.asistencia.entity.ProgramaAcademico;
import com.fablab.ufps.edu.co.asistencia.repository.ProgramaAcademicoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author jerson
 */
@RestController
@RequestMapping("/programa")
public class ProgramaAcademicoController {

    @Autowired
    private ProgramaAcademicoRepository programaAcademicoRepository;

    @GetMapping()
    public List<Object> list() {
        return programaAcademicoRepository
                .findAll()
                .stream()
                .map(CommonDTO::programaToDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {

        Optional<ProgramaAcademico> ou = programaAcademicoRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return ResponseEntity.ok(msg);
        }

        ProgramaAcademicoDTO u = CommonDTO.programaToDTO(ou.get());

        return ResponseEntity.ok(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable String id, @RequestBody ProgramaAcademicoDTO input) {

        Optional<ProgramaAcademico> ou = programaAcademicoRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        ProgramaAcademico u = ou.get();
        u.setNombre(input.getNombre());
        u.setDescripcion(input.getDescripcion());

        u = programaAcademicoRepository.save(u);

        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody ProgramaAcademicoDTO input) {

        ProgramaAcademico u = new ProgramaAcademico();
        u.setNombre(input.getNombre());
        u.setDescripcion(input.getDescripcion());

        u = programaAcademicoRepository.save(u);
        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {

        Optional<ProgramaAcademico> ou = programaAcademicoRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {
            msg.setMsg("no existe");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }

        ProgramaAcademico u = ou.get();

        try {
            programaAcademicoRepository.delete(u);

            msg.setMsg("ok");
            return new ResponseEntity(msg, HttpStatus.OK);
        } catch (Exception e) {

            msg.setMsg("no");
            return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
        }

    }

}
