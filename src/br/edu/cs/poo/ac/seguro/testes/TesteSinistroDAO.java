package br.edu.cs.poo.ac.seguro.testes;


import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;

public class TesteSinistroDAO extends TesteDAO {
    private SinistroDAO dao = new SinistroDAO();
    protected Class getClasse() {
        return Sinistro.class;
    }

    @Test
    public void teste01() {
        String numero = "00000000";
        cadastro.incluir(new Sinistro(numero, null, null, null, null ,null, TipoSinistro.COLISAO), numero);
        Sinistro ve = dao.buscar(numero);
        Assertions.assertNotNull(ve);
    }
    @Test
    public void teste02() {
        String numero = "10000000";
        cadastro.incluir(new Sinistro(numero, null, null, null, null ,null, TipoSinistro.COLISAO), numero);
        Sinistro ve = dao.buscar("11000000");
        Assertions.assertNull(ve);
    }
    @Test
    public void teste03() {
        String numero = "20000000";
        cadastro.incluir(new Sinistro(numero, null, null, null, null ,null, TipoSinistro.COLISAO), numero);
        boolean ret = dao.excluir(numero);
        Assertions.assertTrue(ret);
    }
    @Test
    public void teste04() {
        String numero = "30000000";
        cadastro.incluir(new Sinistro(numero, null, null, null, null ,null, TipoSinistro.COLISAO), numero);
        boolean ret = dao.excluir("31000000");
        Assertions.assertFalse(ret);
    }
    @Test
    public void teste05() {
        String numero = "40000000";
        boolean ret = dao.incluir(new Sinistro(numero, null, null, null, null ,null, TipoSinistro.COLISAO));
        Assertions.assertTrue(ret);
        Sinistro ve = dao.buscar(numero);
        Assertions.assertNotNull(ve);
    }

    @Test
    public void teste06() {
        String numero = "50000000";
        Sinistro ve = new Sinistro(numero, null, null, null, null ,null, TipoSinistro.COLISAO);
        cadastro.incluir(ve, numero);
        boolean ret = dao.incluir(ve);
        Assertions.assertFalse(ret);
    }
    @Test
    public void teste07() {
        String numero = "60000000";
        boolean ret = dao.alterar(new Sinistro(numero, null, null, null, null ,null, TipoSinistro.COLISAO));
        Assertions.assertFalse(ret);
        Sinistro ve = dao.buscar(numero);
        Assertions.assertNull(ve);
    }

    @Test
    public void teste08() {
        String numero = "70000000";
        Sinistro ve = new Sinistro(numero, null, null, null, null ,null, TipoSinistro.COLISAO);
        cadastro.incluir(ve, numero);
        ve = new Sinistro(numero, null, null, null, null ,null, TipoSinistro.COLISAO);
        boolean ret = dao.alterar(ve);
        Assertions.assertTrue(ret);
    }
}