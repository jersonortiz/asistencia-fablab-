/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling the index page.
 *
 * <p>
 * This controller provides a single endpoint for serving the index page.</p>
 *
 * @author authorjerson
 */
@Controller
public class indexController {

    /**
     * Handles requests to the root URL ("/").
     *
     * <p>
     * Returns the name of the view to be rendered, which corresponds to
     * "index".</p>
     *
     * @return the name of the index view
     */
    @GetMapping("/")
    public String index() {
        //has to be without blank spaces
        return "index";
    }

}
