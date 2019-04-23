/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspringjpa.entidade;

import java.io.Serializable;

/**
 *
 * @author fernando.tsuda
 */
public class ImagemProduto implements Serializable {

    private Long id;

    private String nomeArquivo;

    private String legenda;

    private Produto produto;

    public ImagemProduto() {

    }

    public ImagemProduto(String nomeArquivo, String legenda) {
        this.nomeArquivo = nomeArquivo;
        this.legenda = legenda;
    }

    public ImagemProduto(Long id, String nomeArquivo, String legenda) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.legenda = legenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getUrlArquivo() {
        // MOCK
        return "http://lorempixel.com/400/200/sports/";
    }

    @Override
    public String toString() {
        return "ImagemProduto{" + "id=" + id + ", legenda=" + legenda + ", nomeArquivo=" + nomeArquivo + '}';
    }

}
