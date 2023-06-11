package udesc.dsw.droneseta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import udesc.dsw.droneseta.model.entity.Produto;
import udesc.dsw.droneseta.model.entity.enums.ProdutoCategoria;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> getProdutosByCategoria(ProdutoCategoria categoria);

    List<Produto> findByQuantidadeGreaterThan(int quantidade);

    List<Produto> findByCategoriaAndQuantidadeGreaterThan(ProdutoCategoria categoria, int quantidade);

    @Query( "SELECT p, SUM(pp.quantidade) AS quantidade_total FROM Produto p JOIN ProdutoPedido pp ON p.id = pp.produto.id GROUP BY p.id ORDER BY quantidade_total DESC LIMIT 8")
    List<Produto> findProdutosMaisVendidos();

    @Query("SELECT pp.produto.nome, pp.produto.tamanho, " +
            "SUM(pp.quantidade), SUM(pp.quantidade * pp.produto.preco) " +
            "FROM ProdutoPedido pp " +
            "JOIN pp.pedido p " +
            "WHERE MONTH(p.horario) = MONTH(CURRENT_DATE) " +
            "GROUP BY pp.produto.nome, pp.produto.tamanho " +
            "ORDER BY SUM(pp.quantidade) DESC " +
            "LIMIT 8")
    List<Object[]> findDadosProdutosMaisVendidos();
}
