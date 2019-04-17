/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author fernando.tsuda
 */
@Controller
public class PrimeiroExemploController {

    @GetMapping("/ex1")
    public String ex1() {
        return "view-ex1";
    }

    @GetMapping("/ex2")
    public String ex2(Model model) {
        model.addAttribute("nome", "Fulano da Silva");
        model.addAttribute("numero", 100);
        model.addAttribute("dataHora", LocalDateTime.now());
        model.addAttribute("lista", Arrays.asList("item 1", "item 2", "item 3"));
        return "view-ex2";
    }

}
