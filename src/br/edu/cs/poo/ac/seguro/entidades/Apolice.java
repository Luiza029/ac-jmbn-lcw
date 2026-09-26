package br.edu.cs.poo.ac.seguro.entidades;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Apolice {
    private Veiculo veiculo;
    private BigDecimal valorFranquia;
    private BigDecimal valorPremio;
    private BigDecimal valorMaximoSegurado;
    private String numero;
}