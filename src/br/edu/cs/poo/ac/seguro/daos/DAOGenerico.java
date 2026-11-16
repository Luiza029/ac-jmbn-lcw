package br.edu.cs.poo.ac.seguro.daos;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.seguro.entidades.Registro;

public abstract class DAOGenerico {
    private CadastroObjetos cadastro;

    public DAOGenerico() {
        cadastro = new CadastroObjetos(getClasseEntidade());
    }

    public abstract Class getClasseEntidade();

    public Registro buscar(String id) {
        return (Registro) cadastro.buscar(id);
    }

    public boolean incluir(Registro registro) {
        if (buscar(registro.getIdUnico()) != null) {
            return false;
        }
        cadastro.incluir(registro, registro.getIdUnico());
        return true;
    }

    public boolean alterar(Registro registro) {
        if (buscar(registro.getIdUnico()) == null) {
            return false;
        }
        cadastro.alterar(registro, registro.getIdUnico());
        return true;
    }

    public boolean excluir(String id) {
        if (buscar(id) == null) {
            return false;
        }
        cadastro.excluir(id);
        return true;
    }

    public Registro[] buscarTodos() {
        Serializable[] objetos = cadastro.buscarTodos();
        Registro[] registros = new Registro[objetos.length];
        for (int i = 0; i < objetos.length; i++) {
            registros[i] = (Registro) objetos[i];
        }
        return registros;
    }
}