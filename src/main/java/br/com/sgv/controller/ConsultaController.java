/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sgv.controller;

import br.com.sgv.model.Consulta;
import br.com.sgv.repository.PacienteRepository;
import br.com.sgv.repository.ConsultaRepository;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author angelo.acz
 */

@Controller
public class ConsultaController {
    

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    
    @GetMapping("/consultas")
    public String listar(Model model) {
        model.addAttribute("listaConsultas", consultaRepository.findAll());
        return "gerenciar_consultas";
    }

    
     @GetMapping("/consultas/novo")
    public String novo(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("listaPacientes", pacienteRepository.findAll());
        return "editar_consulta";
    }
     @GetMapping("/consultas/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<Consulta> consulta = consultaRepository.findById(id);
        model.addAttribute("consulta", consulta.get());
        model.addAttribute("listaPacientes", pacienteRepository.findAll());
        return "editar_consulta";
    }
    
   
    @PostMapping("/consultas")
    public String salvar(@Valid Consulta consulta, BindingResult result){
        if (result.hasErrors()) {
            return "editar_consulta";
        }
        consultaRepository.save(consulta);
        consulta.getPaciente().adicionarConsulta(consulta);
        pacienteRepository.save(consulta.getPaciente());
        return "redirect:/consultas";
    }

    @DeleteMapping("/consultas/{id}")
    public String excluir(@PathVariable("id") long id) {
        consultaRepository.deleteById(id);
        return "redirect:/consultas";
    }
}

