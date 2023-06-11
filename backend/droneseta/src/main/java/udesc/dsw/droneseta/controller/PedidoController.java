package udesc.dsw.droneseta.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udesc.dsw.droneseta.exception.ValidatorException;
import udesc.dsw.droneseta.model.dto.*;
import udesc.dsw.droneseta.model.entity.Pedido;
import udesc.dsw.droneseta.model.entity.Produto;
import udesc.dsw.droneseta.service.ClienteService;
import udesc.dsw.droneseta.service.PedidoService;
import udesc.dsw.droneseta.service.ViagemService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ViagemService viagemService;

    @Autowired
    Validator validator;

    @PostMapping
    public ResponseEntity<?> createPedido(@RequestBody  PedidoDTO pedidoDTO) throws ValidatorException{
        Set<ConstraintViolation<PedidoDTO>> violations = validator.validate(pedidoDTO);
        if (!violations.isEmpty()) {
            throw new ValidatorException(violations);
        }

        if(pedidoDTO.getProdutos() != null) {
            Set<ConstraintViolation<ProdutoDTO>> violationsProduto;
            for (ProdutoDTO produtoDTO : pedidoDTO.getProdutos()) {
                violationsProduto = validator.validate(produtoDTO);
                if (!violations.isEmpty()) {
                    throw new ValidatorException(violationsProduto);
                }
            }
        }

        List<Produto> produtos = pedidoDTO.getProdutos().stream().map(pDTO -> new Produto(pDTO)).toList();

        Pedido response = pedidoService.createPedido(
                new Pedido(pedidoDTO),
                clienteService.getClienteById(pedidoDTO.getClienteId()),
                produtos);

        if(response == null){
            throw new DataIntegrityViolationException("Não foi possível registrar o novo pedido no banco de dados");
        }

        return new ResponseEntity<>(new PedidoDTO(response), HttpStatus.CREATED);
    }

    @GetMapping("cliente/{idCliente}")
    public ResponseEntity<?> getPedidosByCliente(@PathVariable Long idCliente) {
        List<Pedido> pedidos = pedidoService.getPedidosByCliente(idCliente);

        if(pedidos.isEmpty()){
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } else {
            List<PedidoDTO> dtos = pedidos.stream().map(p -> new PedidoDTO(p)).toList();
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @GetMapping("pendentes")
    public ResponseEntity<?> getPedidosPendentes() {
        // pedidoService.getPedidosPendentes(idUsuario);
        List<Pedido> pedidos = pedidoService.getPedidosPendentes();
        List<PedidoDTO> dtos = pedidos.stream().map(p -> new PedidoDTO(p)).toList();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("relatorio")
    public ResponseEntity<ResumoMensalDTO> getResumoDoMes() {

        int qtdViagem = viagemService.getNumeroViagemNoMes();
        Map<String, Integer> dadosPedido = pedidoService.getResumoPedidoMes();

        ResumoMensalDTO resumoMensalDTO = new ResumoMensalDTO(
                qtdViagem,
                ((Number) dadosPedido.get("totalQuantidadePedido")).intValue(),
                ((Number) dadosPedido.get("totalPreco")).floatValue());

        return new ResponseEntity<>(resumoMensalDTO, HttpStatus.OK);
    }

}