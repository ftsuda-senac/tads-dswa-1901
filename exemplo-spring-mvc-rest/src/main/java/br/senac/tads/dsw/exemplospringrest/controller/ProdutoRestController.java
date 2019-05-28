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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fernando.tsuda
 */
@RestController
@RequestMapping("/api/produto")
public class ProdutoRestController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    @CrossOrigin(origins = "*")
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Produto obterPorId(@PathVariable("id") Long id) {
        Optional<Produto> optProduto = produtoRepository.findById(id);
        if (optProduto.isPresent()) {
            return optProduto.get();
        }
        return null;
    }

    @PostMapping(consumes = {"application/json"})
    @CrossOrigin(origins = "*")
    public Produto incluir(@RequestBody Produto produto) {
        if (produto.getId() == null) {
            produto.setDtCadastro(LocalDateTime.now());
        }
        if (produto.getIdsCategorias() != null && !produto.getIdsCategorias().isEmpty()) {
            Set<Categoria> categoriasSelecionadas = new HashSet<>();
            for (Integer idCat : produto.getIdsCategorias()) {
                Optional<Categoria> optCat = categoriaRepository.findById(idCat);
                Categoria cat = optCat.get();
                // Criar a relacao bidirecional entre os objetos produto e categoria.
                cat.setProdutos(new HashSet<>(Arrays.asList(produto)));
                categoriasSelecionadas.add(cat);
            }
            produto.setCategorias(categoriasSelecionadas);
        }
        produtoRepository.save(produto);
        return produto;
    }

}
