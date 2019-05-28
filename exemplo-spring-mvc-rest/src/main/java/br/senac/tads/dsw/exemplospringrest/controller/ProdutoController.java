/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplospringrest.controller;


import br.senac.tads.dsw.exemplospringrest.entidade.Categoria;
import br.senac.tads.dsw.exemplospringrest.entidade.Produto;
import br.senac.tads.dsw.exemplospringrest.repository.CategoriaRepository;
import br.senac.tads.dsw.exemplospringrest.repository.ProdutoRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
        List<Produto> produtos;
        if (idsCat != null && !idsCat.isEmpty()) {
            // Busca filtrando pelos IDs das categorias informados
            produtos = produtoRepository.findDistinctByCategorias_IdIn(idsCat);
        } else {
            // Busca normal
            produtos = produtoRepository.findAll();
        }
        return new ModelAndView("produto/lista").addObject("produtos", produtos);
    }

    @GetMapping("/novo")
    public ModelAndView adicionarNovo() {
        return new ModelAndView("produto/formulario").addObject("produto", new Produto());
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(
            @PathVariable(name = "id") Long id) {
        Optional<Produto> optProduto = produtoRepository.findById(id);
        Produto p = optProduto.get();

        // Mapeando os IDs das categorias para gerar o formulário com as opções marcadas
        Set<Integer> idsCategorias = new HashSet<>();
        for (Categoria cat : p.getCategorias()) {
            idsCategorias.add(cat.getId());
        }
        p.setIdsCategorias(idsCategorias);
        return new ModelAndView("produto/formulario").addObject("produto", p);
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("produto") Produto produto,
            /*BindingResult bindingResult,*/ RedirectAttributes redirectAttributes) {
        if (produto.getId() == null) {
            produto.setDtCadastro(LocalDateTime.now());
        }
        if (produto.getIdsCategorias() != null && !produto.getIdsCategorias().isEmpty()) {
            Set<Categoria> categoriasSelecionadas = new HashSet<>();
            for (Integer idCat : produto.getIdsCategorias()) {
                Optional<Categoria> optCat  = categoriaRepository.findById(idCat);
                Categoria cat = optCat.get();
                // Criar a relacao bidirecional entre os objetos produto e categoria.
                cat.setProdutos(new HashSet<>(Arrays.asList(produto)));
                categoriasSelecionadas.add(cat);
            }
            produto.setCategorias(categoriasSelecionadas);
        }
        produtoRepository.save(produto);
        redirectAttributes.addFlashAttribute("mensagemSucesso",
                "Produto " + produto.getNome() + " salvo com sucesso");
        return new ModelAndView("redirect:/produto");
    }

    @PostMapping("/{id}/remover")
    public ModelAndView remover(
            @PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Produto> optProduto = produtoRepository.findById(id);
        produtoRepository.delete(optProduto.get());
        redirectAttributes.addFlashAttribute("mensagemSucesso",
                "Produto ID " + id + " removido com sucesso");
        return new ModelAndView("redirect:/produto");
    }

    @ModelAttribute("categorias")
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }
    
    @GetMapping("/nome/{nome}")
    public ModelAndView editar2(
            @PathVariable(name = "nome") String nome) {
        Optional<Produto> optProduto = produtoRepository.findByNome(nome);
        Produto p = optProduto.get();

        // Mapeando os IDs das categorias para gerar o formulário com as opções marcadas
        Set<Integer> idsCategorias = new HashSet<>();
        for (Categoria cat : p.getCategorias()) {
            idsCategorias.add(cat.getId());
        }
        p.setIdsCategorias(idsCategorias);
        return new ModelAndView("produto/formulario").addObject("produto", p);
    }

}