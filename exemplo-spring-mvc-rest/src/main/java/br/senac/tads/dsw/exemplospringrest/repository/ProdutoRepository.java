/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplospringrest.repository;

import br.senac.tads.dsw.exemplospringrest.entidade.Produto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fernando.tsuda
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Optional<Produto> findByNome(String nome);

    public List<Produto> findByPrecoVendaGreaterThanAndPrecoVendaLessThan(BigDecimal min, BigDecimal max);

    public List<Produto> findDistinctByCategorias_IdIn(List<Integer> idsCategorias);
}
