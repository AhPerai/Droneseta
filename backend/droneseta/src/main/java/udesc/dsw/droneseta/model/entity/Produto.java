package udesc.dsw.droneseta.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import udesc.dsw.droneseta.model.dto.ProdutoDTO;
import udesc.dsw.droneseta.model.entity.enums.ProdutoCategoria;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Produto{

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoPedido> pedidos;

    @Column
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column
    private ProdutoCategoria categoria;

    @Column
    private String tamanho;

    @Column
    private float preco;

    @Column
    private int quantidade;

    @Column
    private String imagem;

    public Produto(ProdutoDTO produtoDTO){
        this.id = produtoDTO.getId();
        this.nome = produtoDTO.getNome();
        this.descricao = produtoDTO.getDescricao();
        this.categoria = produtoDTO.getCategoria();
        this.tamanho = produtoDTO.getTamanho();
        this.preco = produtoDTO.getPreco();
        this.quantidade = produtoDTO.getQuantidade();
    }

    public Produto(String nome, String descricao, ProdutoCategoria categoria, String tamanho, float preco, int quantidade, String imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.tamanho = tamanho;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imagem = imagem;
    }
}
