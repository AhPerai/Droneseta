package udesc.dsw.droneseta.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import udesc.dsw.droneseta.exception.ValidatorException;
import udesc.dsw.droneseta.model.dto.DadosProdutosVendidosDTO;
import udesc.dsw.droneseta.model.dto.ProdutoDTO;
import udesc.dsw.droneseta.model.entity.Produto;
import udesc.dsw.droneseta.model.entity.enums.ProdutoCategoria;
import udesc.dsw.droneseta.service.ProdutoService;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    Validator validator;

    @PostMapping
    public ResponseEntity<?> createProduto(@RequestBody ProdutoDTO produtoDTO) throws ValidatorException {
        Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produtoDTO);

        if(violations.isEmpty()){
            Produto produto = produtoService.save(new Produto(produtoDTO));
            return new ResponseEntity<>(new ProdutoDTO(produto), HttpStatus.CREATED);
        } else {
            throw new ValidatorException(violations);
        }
    }

    @PostMapping("{idProduto}/image")
    public ResponseEntity<?> uploadImage(
            @PathVariable Long idProduto,
            @RequestParam MultipartFile image) throws Exception {
        String response = produtoService.addImage(image, idProduto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{idProduto}")
    public ResponseEntity<?> getById(@PathVariable long idProduto) throws Exception {
        Produto produto = produtoService.get(idProduto);
        if(produto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ProdutoDTO(produto), HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<List<?>> getAll(){
        List<ProdutoDTO> result = produtoService.getAllInEstoque().stream().map(p -> new ProdutoDTO(p)).toList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("categoria/{categoria}")
    public ResponseEntity<?> getAllByCategoria(@PathVariable String categoria){
        List<Produto> produtos = produtoService.getAllByCategoria(ProdutoCategoria.valueOf(categoria));
        List<ProdutoDTO> result = produtos.stream().map(p -> new ProdutoDTO(p)).toList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/relatorio/mais-vendidos")
    public ResponseEntity<List<DadosProdutosVendidosDTO>> getDadosProdutosMaisVendidos() {
        List<Object[]> rawData = produtoService.getDadosProdutosPopulares();

        List<DadosProdutosVendidosDTO> response = rawData.stream()
                .map(data -> new DadosProdutosVendidosDTO(
                        (String) data[0],
                        (String) data[1],
                        ((Number) data[2]).intValue(),
                        ((Number) data[3]).floatValue()
                ))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("populares")
    public ResponseEntity<List<?>> getProdutosMaisVendidos(){
        List<ProdutoDTO> result = produtoService.getProdutosPopulares().stream().map(p -> new ProdutoDTO(p)).toList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{idProduto}")
    public ResponseEntity<?> updateById(@PathVariable("idProduto") long id, @RequestBody ProdutoDTO produtoDTO) throws ValidatorException, IOException {
        Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produtoDTO);

        if(!violations.isEmpty()){
            throw new ValidatorException(violations);
        }

        Produto produto = produtoService.get(id);
        if(produto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidade(produtoDTO.getQuantidade());
        produto.setTamanho(produtoDTO.getTamanho());

        produtoService.save(produto);

        return new ResponseEntity<>(new ProdutoDTO(produto), HttpStatus.OK);
    }

    @DeleteMapping("{idProduto}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long idProduto) {
        produtoService.delete(idProduto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
