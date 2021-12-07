/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elis.model;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author elis
 */
public interface AtletaRepository extends CrudRepository<Atleta, Long> {

    @Override
    List<Atleta> findAll();
    
    Atleta findById(long id);
    
    @Query(value = "from Atleta c where lower(c.primeiroNome) like '%'||lower(?1)||'%' or lower(sobrenome) like '%'||lower(?1)||'%'")
    List<Atleta> findByNome(String nome);
}
