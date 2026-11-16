package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class VeiculoDAO extends DAOGenerico {

    @Override
    public Class getClasseEntidade() {
        return Veiculo.class;
    }

    @Override
    public Veiculo buscar(String placa) {
        return (Veiculo) super.buscar(placa);
    }
}