package br.edu.cs.poo.ac.seguro.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SeguradoPessoa extends Segurado{
    private String cpf;
    private double renda;

    public SeguradoPessoa(BigDecimal bonus, LocalDate dataNascimento, Endereco endereco, String nome, String cpf, double renda) {
        super(bonus, dataNascimento, endereco, nome);
        this.cpf = cpf;
        this.renda = renda;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public LocalDate getDataNascimento(){
        return super.getDataCriacao();
    }

    public void setDataNascimento(LocalDate dataNascimento){
        super.setDataCriacao(dataNascimento);
    }
}
