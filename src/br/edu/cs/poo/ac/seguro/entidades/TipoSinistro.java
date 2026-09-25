package br.edu.cs.poo.ac.seguro.entidades;
public enum TipoSinistro {
    COLISAO(1, "Colisão"),
    INCENDIO(2, "Incêndio"),
    FURTO(3, "Furto"),
    ENCHENTE(4, "Enchente"),
    DEPREDACAO(5, "Depredação");

    private int codigo;
    private String nome;

    private TipoSinistro(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoSinistro getTipoSinistro(int codigo){
        for(TipoSinistro c : TipoSinistro.values())
            if (c.getCodigo() == codigo) {
                return c;
            }
        return null;
    }
}
