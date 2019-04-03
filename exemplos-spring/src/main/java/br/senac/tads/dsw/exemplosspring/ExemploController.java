/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/mv")
public class ExemploController {

    @GetMapping("/ex2")
    public ModelAndView ex2() {
        ModelAndView mv = new ModelAndView("view-ex2");
        mv.addObject("nome", "Ciclano de Souza");
        mv.addObject("numero", 9876);
        mv.addObject("dataHora", LocalDateTime.now());
        mv.addObject("lista",
                Arrays.asList("item 9", "item 8", "item 7", "item 6"));
        return mv;
    }

    //public ModelAndView ex3(String nome, int numero, String dtNascimento) {
    @GetMapping("/ex3")
    public ModelAndView ex3(
            @RequestParam("nome") String nome,
            @RequestParam(name = "numero", required = false, defaultValue = "99") int numero,
            @RequestParam(name = "dtnasc", required = false) String dtNascimento) {
        ModelAndView mv = new ModelAndView("view-ex3");
        mv.addObject("nome", nome);
        mv.addObject("numero", numero);
        if (dtNascimento != null) {
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dtNascimento, formatter);
            LocalDate dif = LocalDate.now().minusYears(date.getYear());
            mv.addObject("idade", dif.getYear());
        }
        return mv;
    }

    @GetMapping("/ex4/{xpto}")
    public ModelAndView ex4(
            @PathVariable("xpto") String username) {
        ModelAndView mv = new ModelAndView("view-ex4");
        mv.addObject("username", username);
        return mv;
    }

    @GetMapping("/ex5/{xpto}")
    public ModelAndView ex5(
            @PathVariable("xpto") String username,
            @RequestHeader("User-Agent") String userAgent) {
        ModelAndView mv = new ModelAndView("view-ex5");
        mv.addObject("username", username);
        mv.addObject("userAgent", userAgent);
        return mv;
    }

    /**
     * Obter todos cabeçalhos HTTP
     *
     * @param username
     * @param headers
     * @return
     */
    @GetMapping("/ex6/{xpto}")
    public ModelAndView ex6(
            @PathVariable("xpto") String username,
            @RequestHeader Map<String, String> headers) {
        ModelAndView mv = new ModelAndView("view-ex6");
        mv.addObject("username", username);
        mv.addObject("headers", headers);
        return mv;
    }

}
