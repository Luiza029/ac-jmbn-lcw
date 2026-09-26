package br.edu.cs.poo.ac.seguro.testes;
import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;

public class TesteApoliceDAO extends TesteDAO {
    private ApoliceDAO dao = new ApoliceDAO();
    protected Class getClasse() {
        return Apolice.class;
    }

    @Test
    public void teste01() {
        String numero = "00000000";
        cadastro.incluir(new Apolice(null, null, null, null ,numero), numero);
        Apolice ve = dao.buscar(numero);
        Assertions.assertNotNull(ve);
    }
    @Test
    public void teste02() {
        String numero = "10000000";
        cadastro.incluir(new Apolice(null, null, null, null ,numero), numero);
        Apolice ve = dao.buscar("11000000");
        Assertions.assertNull(ve);
    }
    @Test
    public void teste03() {
        String numero = "20000000";
        cadastro.incluir(new Apolice(null, null, null, null ,numero), numero);
        boolean ret = dao.excluir(numero);
        Assertions.assertTrue(ret);
    }
    @Test
    public void teste04() {
        String numero = "30000000";
        cadastro.incluir(new Apolice(null, null, null, null ,numero), numero);
        boolean ret = dao.excluir("31000000");
        Assertions.assertFalse(ret);
    }
    @Test
    public void teste05() {
        String numero = "40000000";
        boolean ret = dao.incluir(new Apolice(null, null, null, null ,numero));
        Assertions.assertTrue(ret);
        Apolice ve = dao.buscar(numero);
        Assertions.assertNotNull(ve);
    }

    @Test
    public void teste06() {
        String numero = "50000000";
        Apolice ve = new Apolice(null, null, null, null ,numero);
        cadastro.incluir(ve, numero);
        boolean ret = dao.incluir(ve);
        Assertions.assertFalse(ret);
    }
    @Test
    public void teste07() {
        String numero = "60000000";
        boolean ret = dao.alterar(new Apolice(null, null, null, null ,numero));
        Assertions.assertFalse(ret);
        Apolice ve = dao.buscar(numero);
        Assertions.assertNull(ve);
    }

    @Test
    public void teste08() {
        String numero = "70000000";
        Apolice ve = new Apolice(null, null, null, null ,numero);
        cadastro.incluir(ve, numero);
        ve = new Apolice(null, null, null, null ,numero);
        boolean ret = dao.alterar(ve);
        Assertions.assertTrue(ret);
    }
}