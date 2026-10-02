package br.edu.cs.poo.ac.seguro.telas;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoEmpresaMediator;

/**
 * Tela única de CRUD para SeguradoEmpresa.
 * Busca, Inclui, Altera e Exclui usando o CNPJ como chave.
 */
public class TelaSeguradoEmpresa extends JFrame {

    private final SeguradoEmpresaMediator mediator = SeguradoEmpresaMediator.getInstancia();
    private final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // segurado atualmente carregado na tela (resultado da última busca), usado para alterar
    private SeguradoEmpresa seguradoCarregado;

    private JTextField txtCnpj;
    private JTextField txtNome;
    private JTextField txtDataAbertura;
    private JTextField txtFaturamento;
    private JCheckBox chkLocadora;

    private JTextField txtLogradouro;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JTextField txtCep;
    private JTextField txtCidade;
    private JTextField txtEstado;
    private JTextField txtPais;

    private JButton btnBuscar;
    private JButton btnIncluir;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnLimpar;

    public TelaSeguradoEmpresa() {
        super("Cadastro de Segurado Empresa");
        montarTela();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480, 600);
        setLocationRelativeTo(null);
    }

    private void montarTela() {
        JPanel painelCampos = new JPanel(new GridLayout(0, 2, 6, 6));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtCnpj = new JTextField();
        txtNome = new JTextField();
        txtDataAbertura = new JTextField();
        txtFaturamento = new JTextField();
        chkLocadora = new JCheckBox("É locadora de veículos");

        txtLogradouro = new JTextField();
        txtNumero = new JTextField();
        txtComplemento = new JTextField();
        txtCep = new JTextField();
        txtCidade = new JTextField();
        txtEstado = new JTextField();
        txtPais = new JTextField();

        painelCampos.add(new JLabel("CNPJ:"));
        painelCampos.add(txtCnpj);
        painelCampos.add(new JLabel("Nome:"));
        painelCampos.add(txtNome);
        painelCampos.add(new JLabel("Data de abertura (dd/MM/yyyy):"));
        painelCampos.add(txtDataAbertura);
        painelCampos.add(new JLabel("Faturamento:"));
        painelCampos.add(txtFaturamento);
        painelCampos.add(new JLabel(""));
        painelCampos.add(chkLocadora);

        painelCampos.add(new JLabel("Logradouro:"));
        painelCampos.add(txtLogradouro);
        painelCampos.add(new JLabel("Número:"));
        painelCampos.add(txtNumero);
        painelCampos.add(new JLabel("Complemento:"));
        painelCampos.add(txtComplemento);
        painelCampos.add(new JLabel("CEP:"));
        painelCampos.add(txtCep);
        painelCampos.add(new JLabel("Cidade:"));
        painelCampos.add(txtCidade);
        painelCampos.add(new JLabel("Estado (UF):"));
        painelCampos.add(txtEstado);
        painelCampos.add(new JLabel("País:"));
        painelCampos.add(txtPais);

        btnBuscar = new JButton("Buscar");
        btnIncluir = new JButton("Incluir");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnBuscar);
        painelBotoes.add(btnIncluir);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);

        btnBuscar.addActionListener(e -> buscar());
        btnIncluir.addActionListener(e -> incluir());
        btnAlterar.addActionListener(e -> alterar());
        btnExcluir.addActionListener(e -> excluir());
        btnLimpar.addActionListener(e -> limparCampos());

        setLayout(new BorderLayout());
        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void buscar() {
        String cnpj = txtCnpj.getText().trim();
        SeguradoEmpresa seg = mediator.buscarSeguradoEmpresa(cnpj);
        if (seg == null) {
            seguradoCarregado = null;
            JOptionPane.showMessageDialog(this, "Segurado empresa não encontrado para o CNPJ informado.");
            return;
        }
        seguradoCarregado = seg;
        preencherCamposComSegurado(seg);
        JOptionPane.showMessageDialog(this, "Segurado encontrado.");
    }

    private void incluir() {
        LocalDate dataAbertura = parseData(txtDataAbertura.getText().trim());
        if (dataAbertura == null && !txtDataAbertura.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data de abertura inválida. Use o formato dd/MM/yyyy.");
            return;
        }

        Endereco endereco = montarEndereco();
        double faturamento = parseDouble(txtFaturamento.getText().trim());

        SeguradoEmpresa novo = new SeguradoEmpresa(
                txtNome.getText().trim(),
                endereco,
                dataAbertura,
                BigDecimal.ZERO,
                txtCnpj.getText().trim(),
                faturamento,
                chkLocadora.isSelected()
        );

        String erro = mediator.incluirSeguradoEmpresa(novo);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro, "Erro ao incluir", JOptionPane.ERROR_MESSAGE);
            return;
        }
        seguradoCarregado = novo;
        JOptionPane.showMessageDialog(this, "Segurado empresa incluído com sucesso.");
    }

    private void alterar() {
        if (seguradoCarregado == null) {
            JOptionPane.showMessageDialog(this, "Busque um segurado pelo CNPJ antes de alterar.");
            return;
        }

        LocalDate dataAbertura = parseData(txtDataAbertura.getText().trim());
        if (dataAbertura == null && !txtDataAbertura.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data de abertura inválida. Use o formato dd/MM/yyyy.");
            return;
        }

        seguradoCarregado.setNome(txtNome.getText().trim());
        seguradoCarregado.setEndereco(montarEndereco());
        seguradoCarregado.setDataAbertura(dataAbertura);
        seguradoCarregado.setFaturamento(parseDouble(txtFaturamento.getText().trim()));
        seguradoCarregado.setEhLocadoraDeVeiculos(chkLocadora.isSelected());

        String erro = mediator.alterarSeguradoEmpresa(seguradoCarregado);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro, "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Segurado empresa alterado com sucesso.");
    }

    private void excluir() {
        String cnpj = txtCnpj.getText().trim();
        String erro = mediator.excluirSeguradoEmpresa(cnpj);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro, "Erro ao excluir", JOptionPane.ERROR_MESSAGE);
            return;
        }
        seguradoCarregado = null;
        limparCampos();
        JOptionPane.showMessageDialog(this, "Segurado empresa excluído com sucesso.");
    }

    private void limparCampos() {
        seguradoCarregado = null;
        txtCnpj.setText("");
        txtNome.setText("");
        txtDataAbertura.setText("");
        txtFaturamento.setText("");
        chkLocadora.setSelected(false);
        txtLogradouro.setText("");
        txtNumero.setText("");
        txtComplemento.setText("");
        txtCep.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
        txtPais.setText("");
    }

    private void preencherCamposComSegurado(SeguradoEmpresa seg) {
        txtNome.setText(seg.getNome());
        txtDataAbertura.setText(seg.getDataAbertura() == null ? "" : seg.getDataAbertura().format(formatoData));
        txtFaturamento.setText(String.valueOf(seg.getFaturamento()));
        chkLocadora.setSelected(seg.isEhLocadoraDeVeiculos());

        Endereco end = seg.getEndereco();
        if (end != null) {
            txtLogradouro.setText(valorOuVazio(end.getLogradouro()));
            txtNumero.setText(valorOuVazio(end.getNumero()));
            txtComplemento.setText(valorOuVazio(end.getComplemento()));
            txtCep.setText(valorOuVazio(end.getCep()));
            txtCidade.setText(valorOuVazio(end.getCidade()));
            txtEstado.setText(valorOuVazio(end.getEstado()));
            txtPais.setText(valorOuVazio(end.getPais()));
        }
    }

    private Endereco montarEndereco() {
        return new Endereco(
                txtLogradouro.getText().trim(),
                txtCep.getText().trim(),
                txtNumero.getText().trim(),
                txtComplemento.getText().trim(),
                txtPais.getText().trim(),
                txtEstado.getText().trim(),
                txtCidade.getText().trim()
        );
    }

    private LocalDate parseData(String texto) {
        if (texto.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(texto, formatoData);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private double parseDouble(String texto) {
        if (texto.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(texto.replace(",", "."));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private String valorOuVazio(String texto) {
        return texto == null ? "" : texto;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaSeguradoEmpresa().setVisible(true));
    }
}