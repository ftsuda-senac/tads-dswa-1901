/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author fernando.tsuda
 */
public class Dados implements Serializable {

    private Long id;

    @NotBlank(message = "Preencha o nome seu animal")
    @Size(max = 100)
    private String nome;

    private String descricao;

    @Email
    private String email;

    private String senha;

    @Min(1)
    @Max(99)
    private int numeroSorte;

    private int sexo;

    @Size(min = 1, message = "Selecione pelo menos 1 interesse")
    private String[] interesses;

    @Digits(integer = 1, fraction = 2)
    private BigDecimal altura;

    @Digits(integer = 3, fraction = 1)
    private BigDecimal peso;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate dtNascimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNumeroSorte() {
        return numeroSorte;
    }

    public void setNumeroSorte(int numeroSorte) {
        this.numeroSorte = numeroSorte;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String[] getInteresses() {
        return interesses;
    }

    public void setInteresses(String[] interesses) {
        this.interesses = interesses;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

}