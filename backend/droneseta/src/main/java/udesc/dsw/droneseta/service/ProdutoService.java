package udesc.dsw.droneseta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import udesc.dsw.droneseta.model.entity.Produto;
import udesc.dsw.droneseta.model.entity.enums.ProdutoCategoria;
import udesc.dsw.droneseta.repository.ProdutoRepository;
import udesc.dsw.droneseta.utils.FileSystemUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto save(Produto Produto){
        return produtoRepository.save(Produto);
    }

    public Produto get(long idProduto){
        Optional<Produto> oProduto = produtoRepository.findById(idProduto);
        return oProduto.orElse(null);
    }

    public boolean existsById(long idProduto){
        return produtoRepository.existsById(idProduto);
    }

    public Produto getById(long idProduto){
        return produtoRepository.findById(idProduto).get();
    }

    public List<Produto> getAll(){
        List<Produto> response = new ArrayList<>();
        produtoRepository.findAll().forEach(p -> response.add(p));;
        return response;
    }

    public List<Produto> getAllInEstoque(){
        List<Produto> response = new ArrayList<>();
        produtoRepository.findByQuantidadeGreaterThan(0).forEach(p -> response.add(p));;
        return response;
    }

    public List<Produto> getAllByCategoria(ProdutoCategoria categoria){
        List<Produto> response = new ArrayList<>();
        produtoRepository.findByCategoriaAndQuantidadeGreaterThan(categoria, 0).forEach(p -> response.add(p));;
        return response;
    }

    public List<Produto> getProdutosPopulares(){
        List<Produto> response = new ArrayList<>();
        produtoRepository.findProdutosMaisVendidos().forEach(p -> response.add(p));;
        return response;
    }

    public List<Object[]> getDadosProdutosPopulares(){
        return produtoRepository.findDadosProdutosMaisVendidos();
    }
    public void delete(long idProduto){
        produtoRepository.deleteById(idProduto);
    }

    public String addImage(MultipartFile file, Long idProduto) throws Exception {
        if(file == null){
            throw new IllegalArgumentException("É necessário que um arquivo de imagem seja enviado para prosseguir com a requisição");
        }

        String imageName = file.getOriginalFilename();
        String fileExtension = imageName.substring(imageName.lastIndexOf(".") + 1);

        if(!allowedExtensions.contains(fileExtension))
            throw new DataIntegrityViolationException("Somentes imagens são aceitas e estas devem estar nos seguintes formatos: jpg, png e jpeg.");

        Produto produto = getById(idProduto);
        imageName = produto.getId() + "." + fileExtension;

        String location = FileSystemUtils.saveInGallery(file, imageName);

        produto.setImagem(location);
        save(produto);
        return location;
    }
}
