package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.Apolice;

public class ApoliceDAO extends DAOGenerico {

    @Override
    public Class getClasseEntidade() {
        return Apolice.class;
    }

    @Override
    public Apolice buscar(String numero) {
        return (Apolice) super.buscar(numero);
    }
}