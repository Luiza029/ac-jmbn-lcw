package br.edu.cs.poo.ac.seguro.entidades;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.Period;

public class Segurado {
    private String nome;
    private Endereco endereco;
    private LocalDate dataCriacao;
    private BigDecimal bonus;

    public Segurado(BigDecimal bonus, LocalDate dataCriacao, Endereco endereco, String nome) {
        this.bonus = bonus;
        this.dataCriacao = dataCriacao;
        this.endereco = endereco;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    protected LocalDate getDataCriacao() {
        return dataCriacao;
    }

    protected void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public int getIdade(){
        Period periodo = Period.between(this.dataCriacao, LocalDate.now());
        return periodo.getYears();
    }

    public void creditarBonus(BigDecimal valor){
        this.bonus = this.bonus.add(valor);
    }

    public void debitarBonus(BigDecimal valor){
        this.bonus = this.bonus.subtract(valor);
    }

}
