/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elis.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Atleta implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String primeiroNome = "";

    private String sobrenome = "";

    private LocalDate dataNascimento;

    private AtletaStatus status;
    private AtletaBase base;
    private AtletaPrancha prancha;

    private String email = "";

    public Atleta(){
    }
    
    public Atleta(String nome, String sobrenome, LocalDate dtNasc, String email) {
        this.primeiroNome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dtNasc;
        this.status = AtletaStatus.Profissional;
        this.base = AtletaBase.Goofy;
        this.prancha = AtletaPrancha.Fun;
        this.email = email;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the primeiroNome
     */
    public String getPrimeiroNome() {
        return primeiroNome;
    }

    /**
     * @param primeiroNome the primeiroNome to set
     */
    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    /**
     * @return the sobrenome
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * @param sobrenome the sobrenome to set
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    /**
     * @return the dataNascimento
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the status
     */
    public AtletaStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(AtletaStatus status) {
        this.status = status;
    }

    public AtletaBase getBase() {
        return base;
    }

    public void setBase(AtletaBase base) {
        this.base = base;
    }

    public AtletaPrancha getPrancha() {
        return prancha;
    }

    public void setPrancha(AtletaPrancha prancha) {
        this.prancha = prancha;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isPersisted() {
        return id != null;
    }
    
    @Override
    public String toString() {
        return primeiroNome + " " + sobrenome;
    }
}
