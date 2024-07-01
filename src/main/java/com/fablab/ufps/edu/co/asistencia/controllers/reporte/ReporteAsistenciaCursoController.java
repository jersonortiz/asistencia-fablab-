/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers.reporte;

import com.fablab.ufps.edu.co.asistencia.common.CommonDTO;
import com.fablab.ufps.edu.co.asistencia.common.CommonReporte;
import com.fablab.ufps.edu.co.asistencia.dto.json.MensajeJson;
import com.fablab.ufps.edu.co.asistencia.dto.reporte.ReporteGeneralCurso;
import com.fablab.ufps.edu.co.asistencia.entity.VisitaCurso;
import com.fablab.ufps.edu.co.asistencia.repository.PersonaRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaCursoColegioRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaCursoRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaSteamSchoolRepository;
import com.fablab.ufps.edu.co.asistencia.repository.VisitaSteamYoungRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jerson
 */
@RestController
@RequestMapping("/reporte")
public class ReporteAsistenciaCursoController {

    @Autowired
    private VisitaCursoRepository visitaCursoRepository;

    @Autowired
    private VisitaCursoColegioRepository visitaCursoColegioRepository;

    @Autowired
    private VisitaSteamSchoolRepository visitaSteamSchoolRepository;

    @Autowired
    private VisitaSteamYoungRepository visitaSteamYoungRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/persona")
    public List<Object> listP() {
        return personaRepository
                .findAll()
                .stream()
                .map(CommonReporte::personaToPersonaReporteJson)
                .collect(Collectors.toList());
    }

    @GetMapping()
    public List<Object> list() {

        List<ReporteGeneralCurso> combinedList = new ArrayList<>();
        combinedList.addAll(listCurso());
        combinedList.addAll(listColegio());
        combinedList.addAll(listSchool());
        combinedList.addAll(listYoung());

        return combinedList.stream()
                .sorted(Comparator.comparing(obj -> ((ReporteGeneralCurso) obj).getFechaVisita(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}/{tipo}")
    public ResponseEntity<?> delete(@PathVariable String id, @PathVariable String tipo) {

        Optional<VisitaCurso> ou = visitaCursoRepository.findById(Integer.valueOf(id));

        MensajeJson msg = new MensajeJson();
        if (ou.isEmpty()) {
            msg.setMsg("no existe");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }

        VisitaCurso u = ou.get();

        try {
            visitaCursoRepository.delete(u);

            msg.setMsg("ok");
            return new ResponseEntity(msg, HttpStatus.OK);
        } catch (Exception e) {

            msg.setMsg("no");
            return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
        }
    }

    public List<ReporteGeneralCurso> listCurso() {
        return visitaCursoRepository
                .findAll()
                .stream()
                .map(CommonReporte::visitaCursoToReporteGeneralCurso)
                .collect(Collectors.toList());
    }

    public List<ReporteGeneralCurso> listColegio() {
        return visitaCursoColegioRepository
                .findAll()
                .stream()
                .map(CommonReporte::visitaCursoColegioToReporteGeneralCurso)
                .collect(Collectors.toList());
    }

    public List<ReporteGeneralCurso> listSchool() {
        return visitaSteamSchoolRepository
                .findAll()
                .stream()
                .map(CommonReporte::visitaSteamSchoolToReporteGeneralCurso)
                .collect(Collectors.toList());
    }

    public List<ReporteGeneralCurso> listYoung() {
        return visitaSteamYoungRepository
                .findAll()
                .stream()
                .map((CommonReporte::visitaSteamYoungToReporteGeneralCurso))
                .collect(Collectors.toList());
    }

}
