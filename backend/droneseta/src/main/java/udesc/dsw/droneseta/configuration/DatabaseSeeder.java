package udesc.dsw.droneseta.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import udesc.dsw.droneseta.model.entity.*;
import udesc.dsw.droneseta.model.entity.enums.ProdutoCategoria;
import udesc.dsw.droneseta.repository.*;
import udesc.dsw.droneseta.service.PedidoService;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DatabaseSeeder {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private CartaoInfoRepository cartaoInfoRepository;
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seedData() {
        seedUsers();
        seedProdutos();
        seedPedidos();
    }

    private void seedUsers() {
        Admin admin = new Admin("admin", "admin@admin.com", "admin12345");

        Endereco endereco = new Endereco(
                "Brasil", "SC", "Ibirama", "Bela-Vista",
                "R. Dr. Getúlio Vargas", "89140000", 2882
        );
        CartaoInfo cartao = new CartaoInfo(
                "4556566838024610", "586", LocalDate.of(2025, 9, 1));

        Cliente cliente = new Cliente(
                "Paulo Roberto Farah", "paulo.farah@udesc.br", "12345", endereco, cartao);
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        admin.setSenha(passwordEncoder.encode(admin.getSenha()));
        enderecoRepository.save(endereco);
        cartaoInfoRepository.save(cartao);
        adminRepository.save(admin);
        clienteRepository.save(cliente);
    }

    private void seedProdutos() {
        String path = Paths.get("").toAbsolutePath().resolve("uploads").toString();
        ;

        List<Produto> produtosToAdd = List.of(
                //Camisetas

                new Produto(
                        "Camisa em denim tipo bull",
                        "Esta camisa de mangas curtas com corte boxy e reto é feita em denim tipo bull com efeito degradê que lhe confere charme vintage. O pesponto contrastante destaca a silhueta valorizada por um bolso aplicado decorado com o icônico logotipo triangular em metal esmaltado.",
                        ProdutoCategoria.CAMISETA, "P", 89.90F, 3, path + "\\1.png"),

                new Produto(
                        "Camisa em denim tipo bull",
                        "Esta camisa de mangas curtas com corte boxy e reto é feita em denim tipo bull com efeito degradê que lhe confere charme vintage. O pesponto contrastante destaca a silhueta valorizada por um bolso aplicado decorado com o icônico logotipo triangular em metal esmaltado.",
                        ProdutoCategoria.CAMISETA, "M", 89.90F, 5, path + "\\1.png"),

                new Produto(
                        "Camisa em denim tipo bull",
                        "Esta camisa de mangas curtas com corte boxy e reto é feita em denim tipo bull com efeito degradê que lhe confere charme vintage. O pesponto contrastante destaca a silhueta valorizada por um bolso aplicado decorado com o icônico logotipo triangular em metal esmaltado.",
                        ProdutoCategoria.CAMISETA, "G", 89.90F, 2, path + "\\1.png"),

                new Produto(
                        "Camisa em Re-Nylon com mangas curtas",
                        "Esta camisa com corte boxy e mangas curtas tem uma personalidade moderna. Feita com tecido Re-Nylon inovador, ela tem uma gola arredondada característica e abotoamento com botão de pressão. O logotipo triangular em metal esmaltado no bolso no peito torna a peça icônica.",
                        ProdutoCategoria.CAMISETA, "M", 95.90F, 6, path + "\\2.png"),

                new Produto(
                        "Camiseta em interlock",
                        "A camiseta Prada, uma peça essencial da marca, personifica a elegância da simplicidade, tornando-se uma atitude e uma busca por reinventar o básico e propor novos significados. O modelo é recriado com um corte amplo e valorizado pelo logotipo triangular em tecido.",
                        ProdutoCategoria.CAMISETA, "P", 44.50F, 7, path + "\\3.png"),

                new Produto(
                        "Camiseta em interlock",
                        "A camiseta Prada, uma peça essencial da marca, personifica a elegância da simplicidade, tornando-se uma atitude e uma busca por reinventar o básico e propor novos significados. O modelo é recriado com um corte amplo e valorizado pelo logotipo triangular em tecido.",
                        ProdutoCategoria.CAMISETA, "M", 44.50F, 3, path + "\\3.png"),

                new Produto(
                        "Blusa em jersey de cetim",
                        "Um design inspirado nas roupas esportivas caracteriza esta blusa em jersey de cetim com acabamento sedoso e brilhante. Definida por sua modelagem slim e decote canoa, a peça é decorada com letras estampadas que adicionam um toque exclusivo delicado.",
                        ProdutoCategoria.CAMISETA, "P", 49.99F, 3, path + "\\4.png"),

                new Produto(
                        "Blusa em jersey de cetim",
                        "Um design inspirado nas roupas esportivas caracteriza esta blusa em jersey de cetim com acabamento sedoso e brilhante. Definida por sua modelagem slim e decote canoa, a peça é decorada com letras estampadas que adicionam um toque exclusivo delicado.",
                        ProdutoCategoria.CAMISETA, "M", 49.99F, 5, path + "\\4.png"),


                //Jaquetas

                new Produto(
                        "Jaqueta com abotoamento central em Re-Nylon",
                        "Charme de alfaiataria e tecido inovador encontram-se nesta jaqueta com design clássico e abotoamento central feita em Re-Nylon, um fio regenerado produzido a partir de resíduos plásticos purificados e reciclados recuperados do oceano. A peça é decorada com o logotipo triangular em metal esmaltado, um emblema da marca.",
                        ProdutoCategoria.JAQUETA, "P", 260F, 5, path + "\\5.png"),

                new Produto(
                        "Jaqueta com abotoamento central em Re-Nylon",
                        "Charme de alfaiataria e tecido inovador encontram-se nesta jaqueta com design clássico e abotoamento central feita em Re-Nylon, um fio regenerado produzido a partir de resíduos plásticos purificados e reciclados recuperados do oceano. A peça é decorada com o logotipo triangular em metal esmaltado, um emblema da marca.",
                        ProdutoCategoria.JAQUETA, "M", 260F, 5, path + "\\5.png"),

                new Produto(
                        "Jaqueta com abotoamento central em Re-Nylon",
                        "Charme de alfaiataria e tecido inovador encontram-se nesta jaqueta com design clássico e abotoamento central feita em Re-Nylon, um fio regenerado produzido a partir de resíduos plásticos purificados e reciclados recuperados do oceano. A peça é decorada com o logotipo triangular em metal esmaltado, um emblema da marca.",
                        ProdutoCategoria.JAQUETA, "G", 450F, 5, path + "\\5.png"),


                new Produto(
                        "Blazer em lã angorá com abotoamento central",
                        "Detalhes contrastantes delineiam a silhueta deste blazer feito em lã angorá fina. Um item clássico da alfaiataria masculina tradicional é recriado e decorado com elementos sofisticados.",
                        ProdutoCategoria.JAQUETA, "P", 450F, 1, path + "\\7.png"),

                new Produto(
                        "Blazer em lã angorá com abotoamento central",
                        "Detalhes contrastantes delineiam a silhueta deste blazer feito em lã angorá fina. Um item clássico da alfaiataria masculina tradicional é recriado e decorado com elementos sofisticados.",
                        ProdutoCategoria.JAQUETA, "M", 450F, 2, path + "\\7.png"),

                new Produto(
                        "Blazer em lã angorá com abotoamento central",
                        "Detalhes contrastantes delineiam a silhueta deste blazer feito em lã angorá fina. Um item clássico da alfaiataria masculina tradicional é recriado e decorado com elementos sofisticados.",
                        ProdutoCategoria.JAQUETA, "G", 450F, 1, path + "\\7.png"),

                //CASACOS

                new Produto(
                        "Sobretudo em mescla de algodão",
                        "Este sobretudo em mescla de algodão combina elegância minimalista e modernismo, exibindo detalhes refinados e masculinos que remetem à clássica jaqueta bomber. O logotipo em malha aplicado destaca-se na parte posterior, adicionando um toque icônico e contemporâneo.",
                        ProdutoCategoria.CASACO, "P", 195F, 2, path + "\\6.png"),

                new Produto(
                        "Sobretudo em mescla de algodão",
                        "Este sobretudo em mescla de algodão combina elegância minimalista e modernismo, exibindo detalhes refinados e masculinos que remetem à clássica jaqueta bomber. O logotipo em malha aplicado destaca-se na parte posterior, adicionando um toque icônico e contemporâneo.",
                        ProdutoCategoria.CASACO, "M", 195F, 3, path + "\\6.png"),

                new Produto(
                        "Sobretudo em mescla de algodão",
                        "Este sobretudo em mescla de algodão combina elegância minimalista e modernismo, exibindo detalhes refinados e masculinos que remetem à clássica jaqueta bomber. O logotipo em malha aplicado destaca-se na parte posterior, adicionando um toque icônico e contemporâneo.",
                        ProdutoCategoria.CASACO, "G", 195F, 2, path + "\\6.png"),

                new Produto(
                        "Caban em algodão Panamá",
                        "Esta caban em algodão panamá com gola alta é caracterizada por sua linha suave e mangas largas. O logotipo triangular em malha decora a parte posterior da peça.",
                        ProdutoCategoria.CASACO, "P", 225F, 2, path + "\\8.png"),

                new Produto(
                        "Caban em algodão Panamá",
                        "Esta caban em algodão panamá com gola alta é caracterizada por sua linha suave e mangas largas. O logotipo triangular em malha decora a parte posterior da peça.",
                        ProdutoCategoria.CASACO, "M", 225F, 3, path + "\\8.png"),

                new Produto(
                        "Caban em algodão Panamá",
                        "Esta caban em algodão panamá com gola alta é caracterizada por sua linha suave e mangas largas. O logotipo triangular em malha decora a parte posterior da peça.",
                        ProdutoCategoria.CASACO, "G", 225F, 2, path + "\\8.png"));

        produtoRepository.saveAll(produtosToAdd);
    }


    /*
     * Cria 4 instâncias de Pedidos diferentes, a partir de 8 produtos distintos
     */
    private void seedPedidos() {
        List<Produto> produtos = produtoRepository.findAll();
        Cliente cliente = clienteRepository.getClienteByEmail("paulo.farah@udesc.br");

        List<Produto> produtosSelecinados = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getNome))
                .values()
                .stream()
                .flatMap(group -> {
                    if (group.size() > 1) {
                        int randomIndex = (int) (Math.random() * group.size());
                        return Stream.of(group.get(randomIndex));
                    } else {
                        return group.stream();
                    }
                })
                .limit(8)
                .collect(Collectors.toList());

        for (int i = 0; i < 4; i++) {
            List<Produto> produtosAleatorios = produtosSelecinados.subList(0, Math.min(2, produtosSelecinados.size()));

            Random random = new Random();
            for (Produto produto : produtosAleatorios) {
                int quantidadePedida = random.nextInt(produto.getQuantidade()) + 1;
                produto.setQuantidade(quantidadePedida);
            }

            float preco = (float) produtosAleatorios.stream()
                    .mapToDouble(produto -> produto.getPreco() * produto.getQuantidade())
                    .sum();

            Pedido pedido = new Pedido(preco);
            pedidoService.createPedido(pedido, cliente, produtosAleatorios);
            produtosSelecinados.removeAll(produtosAleatorios);
        }

    }
}
