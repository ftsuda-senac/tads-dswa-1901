/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspringjpa.controller;

import br.senac.tads.dsw.exemplosspringjpa.entidade.Categoria;
import br.senac.tads.dsw.exemplosspringjpa.entidade.Produto;
import br.senac.tads.dsw.exemplosspringjpa.repository.CategoriaRepository;
import br.senac.tads.dsw.exemplosspringjpa.repository.ProdutoRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ModelAndView listar(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "qtd", defaultValue = "100") int qtd,
            @RequestParam(name = "idsCat", required = false) List<Integer> idsCat) {
        List<Produto> resultados;
        if (idsCat != null && !idsCat.isEmpty()) {
            // Busca pelos IDs das categorias informadas
            resultados = produtoRepository.findByCategoria(idsCat);
        } else {
            // Lista todos os produtos usando paginacao
            resultados = produtoRepository.findAll(offset, qtd);
        }
        return new ModelAndView("produto/lista").addObject("produtos", resultados);
    }

    @GetMapping("/novo")
    public ModelAndView adicionarNovo() {
        return new ModelAndView("produto/formulario")
                .addObject("produto", new Produto());
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Produto prod = produtoRepository.findById(id);
        
        if (prod.getCategorias() != null && !prod.getCategorias().isEmpty()) {
            Set<Integer> idsCategorias = new HashSet<>();
            for (Categoria cat : prod.getCategorias()) {
                idsCategorias.add(cat.getId());
            }
            prod.setIdsCategorias(idsCategorias);
        }
        return new ModelAndView("produto/formulario")
                .addObject("produto", prod);
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(
            @ModelAttribute("produto") Produto produto, 
            /*BindingResult bindingResult,*/ RedirectAttributes redirectAttributes) {
        produto.setDtCadastro(LocalDateTime.now());
        if (produto.getIdsCategorias() != null && 
                !produto.getIdsCategorias().isEmpty()) {
            Set<Categoria> categoriasSelecionadas = new HashSet<>();
            for (Integer idCat : produto.getIdsCategorias()) {
                Categoria cat = categoriaRepository.findById(idCat);
                categoriasSelecionadas.add(cat);
                cat.setProdutos(new HashSet<>(Arrays.asList(produto)));
            }
            produto.setCategorias(categoriasSelecionadas);
        }
        produtoRepository.save(produto);
        redirectAttributes.addFlashAttribute("mensagemSucesso", 
                "Produto " + produto.getNome() + " salvo com sucesso");
        return new ModelAndView("redirect:/produto");
    }

    @PostMapping("/{id}/remover")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        produtoRepository.delete(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", 
                "Produto ID " + id + " removido com sucesso");
        return new ModelAndView("redirect:/produto");
    }

    @ModelAttribute("categorias")
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

}
