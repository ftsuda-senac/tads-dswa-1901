/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@Scope("session")
public class ExemploSessao2Controller {
    
    private List<LocalDateTime> registroAcessos = new ArrayList<>();

    @GetMapping("/exemplo-sessao2")
    public ModelAndView mostrarTela() {
        registroAcessos.add(LocalDateTime.now());
        return new ModelAndView("exemplo-sessao2");
    }

    @ModelAttribute("dtRegistro")
    public List<LocalDateTime> getRegistroAcessos() {
        return registroAcessos;
    }
}
