/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.sgv.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.sgv.model.Consulta;
/**
 *
 * @author angelo.acz
 */
public interface ConsultaRepository extends CrudRepository<Consulta,Long>{
    
}
