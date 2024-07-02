/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.services.impl;

import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaClaseJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaCursoColegioJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaCursoJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaSemilleroJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaSocializacionColegioJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaSocializacionJson;
import com.fablab.ufps.edu.co.asistencia.dto.json.AsistenciaSustentacionProyectoGradoJson;
import com.fablab.ufps.edu.co.asistencia.entity.Persona;
import com.fablab.ufps.edu.co.asistencia.entity.PoblacionEspecial;
import com.fablab.ufps.edu.co.asistencia.entity.TipoUsuario;
import com.fablab.ufps.edu.co.asistencia.repository.PersonaRepository;
import com.fablab.ufps.edu.co.asistencia.services.PersonaService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author jerson
 */
@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    /*
    @Override
    public Persona createPersona(AsistenciaCursoJson input) {
        try {
            Persona ud = new Persona();

            ud.setNombre(input.getNombre());
            ud.setApellido(input.getApellido());
            ud.setDocumento(input.getDocumento());
            ud.setTelefono(input.getTelefono());
            ud.setCodigo(input.getCodigo());
            ud.setFechaNacimiento(input.getFechaNacimiento());
            ud.setSexo(input.getSexo());

            ud.setIdPoblacionEspecial(new PoblacionEspecial(input.getIdPoblacionEspecial()));
            ud.setIdTipoUsuario(new TipoUsuario(input.getIdTipoUsuario()));

            return personaRepository.save(ud);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear el estudiante", e);
        }
    }
    
    
    
     */
    /**
     * Método privado para crear una entidad `Persona` a partir de los datos
     * proporcionados.
     *
     * @param input el objeto con los datos de la persona.
     * @return la entidad `Persona` creada y guardada en el repositorio.
     */
    @Override
    public Persona createPersona(AsistenciaCursoJson input) {
        try {
            // Busca si una persona con el mismo documento ya existe
            Optional<Persona> existingPersona = personaRepository.findByDocumento(input.getDocumento());

            if (existingPersona.isPresent()) {
                // Si existe, retorna esa persona
                return existingPersona.get();
            } else {
                // Si no existe, crea una nueva persona
                Persona nuevaPersona = new Persona();
                nuevaPersona.setNombre(input.getNombre());
                nuevaPersona.setApellido(input.getApellido());
                nuevaPersona.setDocumento(input.getDocumento());
                nuevaPersona.setTelefono(input.getTelefono());
                nuevaPersona.setCodigo(input.getCodigo());
                nuevaPersona.setFechaNacimiento(input.getFechaNacimiento());
                nuevaPersona.setSexo(input.getSexo());
                nuevaPersona.setIdPoblacionEspecial(new PoblacionEspecial(input.getIdPoblacionEspecial()));
                nuevaPersona.setIdTipoUsuario(new TipoUsuario(input.getIdTipoUsuario()));

                return personaRepository.save(nuevaPersona);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear o buscar el estudiante", e);
        }
    }

    @Override
    public Persona createPersona(AsistenciaClaseJson input) {

        try {
            // Busca si una persona con el mismo documento ya existe
            Optional<Persona> existingPersona = personaRepository.findByDocumento(input.getDocumento());

            if (existingPersona.isPresent()) {
                // Si existe, retorna esa persona
                return existingPersona.get();
            } else {
                // Si no existe, crea una nueva persona
                Persona nuevaPersona = new Persona();
                nuevaPersona.setNombre(input.getNombre());
                nuevaPersona.setApellido(input.getApellido());
                nuevaPersona.setDocumento(input.getDocumento());
                nuevaPersona.setTelefono(input.getTelefono());
                nuevaPersona.setCodigo(input.getCodigo());
                nuevaPersona.setFechaNacimiento(input.getFechaNacimiento());
                nuevaPersona.setSexo(input.getSexo());
                nuevaPersona.setIdPoblacionEspecial(new PoblacionEspecial(input.getIdPoblacionEspecial()));
                nuevaPersona.setIdTipoUsuario(new TipoUsuario(input.getIdTipoUsuario()));

                return personaRepository.save(nuevaPersona);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear o buscar el estudiante", e);
        }
    }

    /**
     * Crear una nueva persona asociada a la visita de curso en colegio.
     *
     * @param input los datos de la persona a crear.
     * @return la persona creada.
     */
    @Override
    public Persona createPersona(AsistenciaCursoColegioJson input) {

        try {
            // Busca si una persona con el mismo documento ya existe
            Optional<Persona> existingPersona = personaRepository.findByDocumento(input.getDocumento());

            if (existingPersona.isPresent()) {
                // Si existe, retorna esa persona
                return existingPersona.get();
            } else {
                // Si no existe, crea una nueva persona
                Persona nuevaPersona = new Persona();
                nuevaPersona.setNombre(input.getNombre());
                nuevaPersona.setApellido(input.getApellido());
                nuevaPersona.setDocumento(input.getDocumento());
                nuevaPersona.setTelefono(input.getTelefono());
                nuevaPersona.setCodigo(input.getCodigo());
                nuevaPersona.setFechaNacimiento(input.getFechaNacimiento());
                nuevaPersona.setSexo(input.getSexo());
                nuevaPersona.setIdPoblacionEspecial(new PoblacionEspecial(input.getIdPoblacionEspecial()));
                nuevaPersona.setIdTipoUsuario(new TipoUsuario(input.getIdTipoUsuario()));

                return personaRepository.save(nuevaPersona);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear o buscar el estudiante", e);
        }
    }

    @Override
    public Persona createPersona(AsistenciaSemilleroJson input) {

        try {
            // Busca si una persona con el mismo documento ya existe
            Optional<Persona> existingPersona = personaRepository.findByDocumento(input.getDocumento());

            if (existingPersona.isPresent()) {
                // Si existe, retorna esa persona
                return existingPersona.get();
            } else {
                // Si no existe, crea una nueva persona
                Persona nuevaPersona = new Persona();
                nuevaPersona.setNombre(input.getNombre());
                nuevaPersona.setApellido(input.getApellido());
                nuevaPersona.setDocumento(input.getDocumento());
                nuevaPersona.setTelefono(input.getTelefono());
                nuevaPersona.setCodigo(input.getCodigo());
                nuevaPersona.setFechaNacimiento(input.getFechaNacimiento());
                nuevaPersona.setSexo(input.getSexo());
                nuevaPersona.setIdPoblacionEspecial(new PoblacionEspecial(input.getIdPoblacionEspecial()));
                nuevaPersona.setIdTipoUsuario(new TipoUsuario(input.getIdTipoUsuario()));

                return personaRepository.save(nuevaPersona);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear o buscar el estudiante", e);
        }
    }

    @Override
    public Persona createPersona(AsistenciaSocializacionColegioJson input) {

        try {
            // Busca si una persona con el mismo documento ya existe
            Optional<Persona> existingPersona = personaRepository.findByDocumento(input.getDocumento());

            if (existingPersona.isPresent()) {
                // Si existe, retorna esa persona
                return existingPersona.get();
            } else {
                // Si no existe, crea una nueva persona
                Persona nuevaPersona = new Persona();
                nuevaPersona.setNombre(input.getNombre());
                nuevaPersona.setApellido(input.getApellido());
                nuevaPersona.setDocumento(input.getDocumento());
                nuevaPersona.setTelefono(input.getTelefono());
                nuevaPersona.setCodigo(input.getCodigo());
                nuevaPersona.setFechaNacimiento(input.getFechaNacimiento());
                nuevaPersona.setSexo(input.getSexo());
                nuevaPersona.setIdPoblacionEspecial(new PoblacionEspecial(input.getIdPoblacionEspecial()));
                nuevaPersona.setIdTipoUsuario(new TipoUsuario(input.getIdTipoUsuario()));

                return personaRepository.save(nuevaPersona);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear o buscar el estudiante", e);
        }
    }

    @Override
    public Persona createPersona(AsistenciaSocializacionJson input) {

        try {
            // Busca si una persona con el mismo documento ya existe
            Optional<Persona> existingPersona = personaRepository.findByDocumento(input.getDocumento());

            if (existingPersona.isPresent()) {
                // Si existe, retorna esa persona
                return existingPersona.get();
            } else {
                // Si no existe, crea una nueva persona
                Persona nuevaPersona = new Persona();
                nuevaPersona.setNombre(input.getNombre());
                nuevaPersona.setApellido(input.getApellido());
                nuevaPersona.setDocumento(input.getDocumento());
                nuevaPersona.setTelefono(input.getTelefono());
                nuevaPersona.setCodigo(input.getCodigo());
                nuevaPersona.setFechaNacimiento(input.getFechaNacimiento());
                nuevaPersona.setSexo(input.getSexo());
                nuevaPersona.setIdPoblacionEspecial(new PoblacionEspecial(input.getIdPoblacionEspecial()));
                nuevaPersona.setIdTipoUsuario(new TipoUsuario(input.getIdTipoUsuario()));

                return personaRepository.save(nuevaPersona);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear o buscar el estudiante", e);
        }
    }

    @Override
    public Persona createPersona(AsistenciaSustentacionProyectoGradoJson input) {

        try {
            // Busca si una persona con el mismo documento ya existe
            Optional<Persona> existingPersona = personaRepository.findByDocumento(input.getDocumento());

            if (existingPersona.isPresent()) {
                // Si existe, retorna esa persona
                return existingPersona.get();
            } else {
                // Si no existe, crea una nueva persona
                Persona nuevaPersona = new Persona();
                nuevaPersona.setNombre(input.getNombre());
                nuevaPersona.setApellido(input.getApellido());
                nuevaPersona.setDocumento(input.getDocumento());
                nuevaPersona.setTelefono(input.getTelefono());
                nuevaPersona.setCodigo(input.getCodigo());
                nuevaPersona.setFechaNacimiento(input.getFechaNacimiento());
                nuevaPersona.setSexo(input.getSexo());
                nuevaPersona.setIdPoblacionEspecial(new PoblacionEspecial(input.getIdPoblacionEspecial()));
                nuevaPersona.setIdTipoUsuario(new TipoUsuario(input.getIdTipoUsuario()));

                return personaRepository.save(nuevaPersona);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear o buscar el estudiante", e);
        }
    }

}
