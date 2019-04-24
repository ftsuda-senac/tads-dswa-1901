/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspringjpa.repository;

import br.senac.tads.dsw.exemplosspringjpa.entidade.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fernando.tsuda
 */
@Repository
public class CategoriaRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<Categoria> findAll() {
        Query jpqlQuery = entityManager.createQuery("SELECT c FROM Categoria c");
        return jpqlQuery.getResultList();
    }
    
    public Categoria findById(Integer id) {
        Query jpqlQuery = 
                entityManager.createQuery(
                        "SELECT c FROM Categoria c WHERE c.id = :idCat");
        jpqlQuery.setParameter("idCat", id);
        Categoria cat = (Categoria) jpqlQuery.getSingleResult();
        return cat;
    }

    @Transactional
    public void save(Categoria cat) {
        if (cat.getId() == null) {
            // Incluindo nova categoria
            entityManager.persist(cat);
        } else {
            // Atualiza categoria existente
            entityManager.merge(cat);
        }
    }
    
}
