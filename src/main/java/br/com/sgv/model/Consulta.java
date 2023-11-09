/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sgv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.text.SimpleDateFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author angelo.acz
 */
@Entity
@Getter
@Setter
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Paciente paciente;
    private LocalDateTime dataHoraConsulta;
    private String anotacoes;

    public String getDataHoraConsultaFormatada() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataHoraConsulta.format(formato);
    }

//    public LocalDateTime getDataHoraConsulta() {
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String dataHora = dataHoraConsulta.format(formato);
//        return LocalDateTime.parse(dataHora);
//    }

    public String getDataConsultaFormatada() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataHoraConsulta.format(formato);        
    }
    
}
