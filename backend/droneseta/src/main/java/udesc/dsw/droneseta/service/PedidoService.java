package udesc.dsw.droneseta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import udesc.dsw.droneseta.model.entity.*;
import udesc.dsw.droneseta.repository.EnderecoRepository;
import udesc.dsw.droneseta.repository.PedidoRepository;
import udesc.dsw.droneseta.repository.ProdutoPedidoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoPedidoRepository produtoPedidoRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ProdutoService produtoService;

    public Pedido createPedido(Pedido pedido, Cliente cliente, List<Produto> produtos) {
        if(cliente == null){
            throw new DataIntegrityViolationException("O cliente informado não existe no sistema");
        }

        Optional<Endereco> oEndereco = enderecoRepository.findById(cliente.getEndereco().getId());

        pedido.setCliente(cliente);
        pedido.setEndereco(oEndereco.get());

        produtos.forEach(produtoDesejado -> {
            Produto produtoEmEstoque = produtoService.getById(produtoDesejado.getId());

            if(produtoEmEstoque == null){
                throw new DataIntegrityViolationException("O produto informado não foi encontrado no sistema");
            }

            if(produtoDesejado.getQuantidade() > produtoEmEstoque.getQuantidade()){
                throw new DataIntegrityViolationException("Desculpe, a quantidade desejada é superior ao que temos em estoque");
            }
        });

        Pedido newPedido = pedidoRepository.save(pedido);

        List<ProdutoPedido> produtosSolicitados = new ArrayList<>();
        produtos.forEach(produto -> {
            Produto produtoEmEstoque = produtoService.getById(produto.getId());
            produtoEmEstoque.setQuantidade(produtoEmEstoque.getQuantidade() - produto.getQuantidade());
            produtoService.save(produtoEmEstoque);

            ProdutoPedido produtoPedido = produtoPedidoRepository.save(new ProdutoPedido(newPedido, produtoEmEstoque, produto.getQuantidade()));
            produtosSolicitados.add(produtoPedido);
        });

        newPedido.setProdutos(produtosSolicitados);
        newPedido.setQuantidadeAEntregar(produtosSolicitados.stream().mapToInt(p -> p.getQuantidade()).sum());
        newPedido.setQuantidadePedido(newPedido.getQuantidadeAEntregar());
        return pedidoRepository.save(newPedido);
    }

    public List<Pedido> getPedidosByCliente(Long idCliente) {
        return pedidoRepository.findByCliente(clienteService.getClienteById(idCliente));
    }

    public List<Pedido> getPedidosPendentes() {
        return pedidoRepository.findByEntregueOrderByHorario(false);
    }

    public Map<String, Integer> getResumoPedidoMes(){ return pedidoRepository.resumoPedidosDoMes();}

    public Pedido savePedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }
}
