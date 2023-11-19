/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sgv.controller;

import br.com.sgv.model.Paciente;
import br.com.sgv.repository.PacienteRepository;
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
public class PacienteController {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    
    @GetMapping("/pacientes")
    public String listar(Model model) {
        model.addAttribute("listaPacientes", pacienteRepository.findAll());
        return "gerenciar_pacientes";
    }
    
    @GetMapping("/pacientes/novo")
    public String novo(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "editar_paciente";
    }
    
    @GetMapping("/pacientes/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        model.addAttribute("paciente", paciente.get());
        return "editar_paciente";
    }
    
    @PostMapping("/pacientes")
    public String salvar(@Valid Paciente paciente, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_paciente";
        }
        pacienteRepository.save(paciente);
        return "redirect:/pacientes";
    }
    
    @DeleteMapping("/pacientes/{id}")
    public String excluir(@PathVariable("id") long id) {
        pacienteRepository.deleteById(id);
        return "redirect:/pacientes";
    }
    
    @GetMapping("/prontuarios")
    public String prontuarios(Model model) {
        model.addAttribute("listaPacientes", pacienteRepository.findAll());
        return "prontuarios";
    }
    
    @GetMapping("/prontuarios/{id}")
    public String exibir(@PathVariable("id") long id, Model model) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        model.addAttribute("paciente", paciente.get());
        return "prontuario_completo";
    }
}
