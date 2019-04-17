/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ftsuda
 */
@Controller
@RequestMapping("/exemplo-layout")
public class ExemploLayoutController {

    @GetMapping("/pagina1")
    public ModelAndView abrirPagina1() {
        return new ModelAndView("pagina1");
    }

    @GetMapping("/pagina2")
    public ModelAndView abrirPagina2() {
        return new ModelAndView("pagina2").addObject("texto", "Texto gerado no Controller");
    }

}
