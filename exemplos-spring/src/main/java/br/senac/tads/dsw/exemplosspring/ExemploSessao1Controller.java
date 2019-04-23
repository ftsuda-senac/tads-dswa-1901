/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@SessionAttributes("dtRegistro")
public class ExemploSessao1Controller {

    @GetMapping("/exemplo-sessao1")
    public ModelAndView mostrarTela(
            @ModelAttribute("dtRegistro") List<LocalDateTime> registrosAcesso) {
        registrosAcesso.add(LocalDateTime.now());
        return new ModelAndView("exemplo-sessao1");
    }

    @ModelAttribute("dtRegistro")
    public List<LocalDateTime> getListaRegistros() {
        return new ArrayList<LocalDateTime>();
    }
}
