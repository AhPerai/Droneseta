package udesc.dsw.droneseta.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import udesc.dsw.droneseta.model.entity.Produto;
import udesc.dsw.droneseta.model.entity.enums.ProdutoCategoria;
import udesc.dsw.droneseta.utils.FileSystemUtils;

@Data
@NoArgsConstructor
public class ProdutoDTO {

    private Long id;

    private String nome;

    private String descricao;

    private ProdutoCategoria categoria;

    private String tamanho;

    private float preco;

    private byte[] imagem;

    private int quantidade;

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.categoria = produto.getCategoria();
        this.tamanho = produto.getTamanho();
        this.preco = produto.getPreco();
        this.quantidade = produto.getQuantidade();

        if(produto.getImagem() != null){
            this.imagem = FileSystemUtils.findFile(produto.getImagem());
        }
    }
}
