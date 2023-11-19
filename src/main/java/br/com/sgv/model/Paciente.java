/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sgv.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author angelo.acz
 */

@Entity
@Getter
@Setter
public class Paciente extends Pessoa{
    private String nomeSocial;
    private String cpf;
    private LocalDate dataNascimento; 
    private String genero;
    private String estadoCivil;
    private int filhos;
    private String diagnostico;
    private String situacao;
    private float valor;
    @OneToMany (mappedBy = "paciente",  cascade = CascadeType.ALL)
    private List<Consulta> listaConsultas = new ArrayList();
    
    public void adicionarConsulta(Consulta consulta){
        listaConsultas.add(consulta);
    }
    
    public String getDataNascimentoFormatada() {
        if (dataNascimento == null){
            return "";
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataNascimentoFormatada = dataNascimento.format(formato);
        return dataNascimentoFormatada;
    }  
    
    public String idade() {
        LocalDate dataAtual = LocalDate.now();
        if (dataNascimento != null) {
            Period periodo = Period.between(dataNascimento, dataAtual);
            int idade = periodo.getYears();
            return Integer.toString(idade);
        }else{
            return "";
        }
    }
    
    public String getDataInicio() {
        if (listaConsultas.isEmpty()){
            return "";
        } else {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataInicio = listaConsultas.get(0).getDataHoraConsulta().format(formato);
            return dataInicio;
        }
    }

}
