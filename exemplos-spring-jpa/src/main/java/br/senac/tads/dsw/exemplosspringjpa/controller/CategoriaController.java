/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspringjpa.controller;

import br.senac.tads.dsw.exemplosspringjpa.entidade.Categoria;
import br.senac.tads.dsw.exemplosspringjpa.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public ModelAndView listar() {
        List<Categoria> resultados = repository.findAll();
        return new ModelAndView("categoria/lista")
                .addObject("categorias", resultados);
    }

    @GetMapping("/novo")
    public ModelAndView adicionarNovo() {
        return new ModelAndView("categoria/formulario")
                .addObject("categoria", new Categoria());
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(Categoria cat, 
            RedirectAttributes redirectAttributes) {
        repository.save(cat);
        return new ModelAndView("redirect:/categoria");
    }
    
}
