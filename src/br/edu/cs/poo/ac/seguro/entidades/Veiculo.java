package br.edu.cs.poo.ac.seguro.entidades;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class Veiculo implements Registro {
    private String placa;
    private int ano;
    private Segurado proprietario;
    private CategoriaVeiculo categoria;

    @Override
    public String getIdUnico() {
        return placa;
    }
}