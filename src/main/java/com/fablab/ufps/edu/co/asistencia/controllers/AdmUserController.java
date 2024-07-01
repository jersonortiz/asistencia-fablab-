/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers;

import com.fablab.ufps.edu.co.asistencia.dto.AdmUserDTO;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.loginJson;
import com.fablab.ufps.edu.co.asistencia.entity.AdmUser;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.repository.AdmUserRepository;
import com.fablab.ufps.edu.co.asistencia.repository.PersonaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for managing AdmUser entities.
 *
 * <p>
 * This controller provides endpoints for listing, retrieving, creating,
 * updating, and deleting AdmUser entities. It also includes a login endpoint.
 * </p>
 *
 * <p>
 * All endpoints are prefixed with "/adm".
 * </p>
 *
 * <p>
 * Note: The login endpoint expects a JSON payload with "codigo" and
 * "contraseña".
 * </p>
 *
 * <p>
 * Exception handling is provided for all endpoints, returning a 500 status with
 * a generic error message in case of unhandled exceptions.
 * </p>
 *
 * @author jerson
 */
@RestController
@RequestMapping("/adm")
public class AdmUserController {

    @Autowired
    private AdmUserRepository admUserRepository;

    @Autowired
    private PersonaRepository personaRepository;

    /**
     * Lists all AdmUser entities.
     *
     * @return a list of AdmUserDTO objects
     */
    @GetMapping()
    public ArrayList<AdmUserDTO> list() {
        ArrayList<AdmUser> aulas = (ArrayList<AdmUser>) admUserRepository.findAll();
        ArrayList<AdmUserDTO> lista = new ArrayList<>();
        for (AdmUser x : aulas) {

            AdmUserDTO a = new AdmUserDTO();
            a.setId(x.getId());
            a.setIdPersona(x.getIdPersona().getId());

            lista.add(a);
        }
        return lista;
    }

    /**
     * Handles login requests.
     *
     * @param user the login JSON payload
     * @return a ResponseEntity containing a message or the user object
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody loginJson user) {

        Optional<Persona> op = personaRepository.findByCodigo(user.getCodigo());

        MensajeJson m = new MensajeJson();

        if (op.isEmpty()) {

            m.setMsg("No existe el usuario");
            return ResponseEntity.ok(m);
        }

        Persona p = op.get();

        List<AdmUser> ad = new ArrayList<>(p.getAdmUserList());

        AdmUser firstUser = null;
        if (!ad.isEmpty()) {
            // Obtener el primer elemento de la lista
            firstUser = ad.get(0);
            System.out.println(firstUser.getId());

        }

        if (!firstUser.getPassword().equals(user.getContraseña())) {

            m.setMsg("Contraseña incorrecta");
            return ResponseEntity.ok(m);
        }

        return new ResponseEntity(user, HttpStatus.ACCEPTED);

    }

    /**
     * Retrieves an AdmUser entity by its ID.
     *
     * @param id the ID of the AdmUser entity
     * @return a ResponseEntity containing the AdmUserDTO or a message
     */
    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        Optional<AdmUser> ou = admUserRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return ResponseEntity.ok(msg);
        }

        AdmUser a = ou.get();

        AdmUserDTO u = new AdmUserDTO();

        u.setId(a.getId());
        u.setIdPersona(a.getIdPersona().getId());

        return ResponseEntity.ok(u);
    }

    /**
     * Updates an AdmUser entity by its ID.
     *
     * @param id the ID of the AdmUser entity
     * @param input the updated AdmUserDTO
     * @return a ResponseEntity containing the updated AdmUserDTO or a message
     */
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable String id, @RequestBody AdmUserDTO input) {
        Optional<AdmUser> ou = admUserRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {

            msg.setMsg("no existe");
            return ResponseEntity.ok(msg);
        }

        AdmUser u = ou.get();

        u.setPassword(input.getPassword());

        u = admUserRepository.save(u);

        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    /**
     * Creates a new AdmUser entity.
     *
     * @param input the AdmUserDTO to be created
     * @return a ResponseEntity containing the created AdmUserDTO
     */
    @PostMapping
    public ResponseEntity post(@RequestBody AdmUserDTO input) {

        AdmUser u = new AdmUser();

        Optional<Persona> ou = personaRepository.findById(input.getIdPersona());

        u.setIdPersona(ou.get());
        u.setPassword(input.getPassword());

        u = admUserRepository.save(u);

        input.setId(u.getId());

        return new ResponseEntity(input, HttpStatus.ACCEPTED);
    }

    /**
     * Deletes an AdmUser entity by its ID.
     *
     * @param id the ID of the AdmUser entity
     * @return a ResponseEntity containing a message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {

        Optional<AdmUser> ou = admUserRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {
            msg.setMsg("no existe");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }

        AdmUser u = ou.get();

        try {
            admUserRepository.delete(u);

            msg.setMsg("ok");
            return new ResponseEntity(msg, HttpStatus.OK);
        } catch (Exception e) {

            msg.setMsg("no");
            return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handles exceptions and returns a generic error message.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}
