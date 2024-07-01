/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.crud;

import com.fablab.ufps.edu.co.asistencia.common.CommonDTO;
import com.fablab.ufps.edu.co.asistencia.common.CommonEntity;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.dto.CRUD.PracticantepasanteBecaDTO;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.entity.PracticantepasanteBeca;
import com.fablab.ufps.edu.co.asistencia.repository.PracticantepasanteBecaRepository;
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
@RequestMapping("/practicante")
public class PracticantepasanteBecaController {

    @Autowired
    private PracticantepasanteBecaRepository practicantepasanteBecaRepository;

    @GetMapping()
    public List<Object> list() {

        return practicantepasanteBecaRepository
                .findAll()
                .stream()
                .map(CommonDTO::practicantepasanteBecaToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        Optional<PracticantepasanteBeca> ou = practicantepasanteBecaRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return ResponseEntity.ok(msg);
        }

        PracticantepasanteBecaDTO u = CommonDTO.practicantepasanteBecaToDTO(ou.get());

        return ResponseEntity.ok(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable String id, @RequestBody PracticantepasanteBecaDTO input) {
        Optional<PracticantepasanteBeca> ou = practicantepasanteBecaRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        PracticantepasanteBeca ud = ou.get();

        ud.setId(input.getId());
        ud.setIdCarnet(input.getIdCarnet());
        ud.setEstado(input.isEstado());
        ud.setSemestre(input.getSemestre());

        Persona p = new Persona();
        p.setId(input.getIdPersona().getId());
        ud.setIdPersona(p);

        ud = practicantepasanteBecaRepository.save(ud);

        input.setId(ud.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody PracticantepasanteBecaDTO input) {

        PracticantepasanteBeca u = CommonEntity.practicantepasanteBecaDTOToEntity(input);

        System.out.println(input);
        System.out.println(u);

        u = practicantepasanteBecaRepository.save(u);
        System.out.println(u);
        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {

        Optional<PracticantepasanteBeca> ou = practicantepasanteBecaRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {
            msg.setMsg("no existe");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }

        PracticantepasanteBeca u = ou.get();

        try {
            practicantepasanteBecaRepository.delete(u);

            msg.setMsg("ok");
            return new ResponseEntity(msg, HttpStatus.OK);
        } catch (Exception e) {

            msg.setMsg("no");
            return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
        }

    }


}
