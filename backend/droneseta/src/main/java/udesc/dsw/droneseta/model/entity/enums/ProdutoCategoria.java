package udesc.dsw.droneseta.model.entity.enums;

public enum ProdutoCategoria {
    CAMISETA("camiseta"),
    CASACO("casaco"),
    JAQUETA("jaqueta"),
    ;

    private final String nome;

    ProdutoCategoria(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}
