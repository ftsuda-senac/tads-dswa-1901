/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/formulario")
public class FormularioController {

    @GetMapping
    public ModelAndView mostrarFormulario() {
        ModelAndView mv = new ModelAndView("formulario");
        mv.addObject("dados", new Dados());
        return mv;
    }

    @PostMapping
    public ModelAndView receberDados(
            @ModelAttribute("dados") Dados dadosPreenchidos) {
        ModelAndView mv = new ModelAndView("resultado");
        mv.addObject("dados", dadosPreenchidos);
        return mv;
    }

}
