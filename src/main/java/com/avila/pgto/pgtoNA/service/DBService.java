package com.avila.pgto.pgtoNA.service;

import com.avila.pgto.pgtoNA.domain.*;
import com.avila.pgto.pgtoNA.domain.enums.EstadoPagamento;
import com.avila.pgto.pgtoNA.domain.enums.TipoCliente;
import com.avila.pgto.pgtoNA.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    Categoria cat1 = new Categoria(null, "Tecnologia");
    Categoria cat2 = new Categoria(null, "Escritorio");
    Categoria cat3 = new Categoria(null, "Informática");
    Categoria cat4 = new Categoria(null, "Livros");
    Categoria cat5 = new Categoria(null, "Esportes");
    Categoria cat6 = new Categoria(null, "Música");
    Categoria cat7 = new Categoria(null, "Lazer");

    Produto p1 = new Produto(null, "computador", 2000.00);
    Produto p2 = new Produto(null, "impressora", 800.00);
    Produto p3 = new Produto(null, "mouse", 80.00);
    Produto p4 = new Produto(null, "teclado", 100.00);
    Produto p5 = new Produto(null, "monitor", 500.00);
    Produto p6 = new Produto(null, "ferramentas", 300.00);
    Produto p7 = new Produto(null, "caixa de som", 150.00);
    Produto p8 = new Produto(null, "webcam", 120.00);
    Produto p9 = new Produto(null, "fone de ouvido", 80.00);
    Produto p10 = new Produto(null, "mousepad", 20.00);
    Produto p11 = new Produto(null, "pendrive", 50.00);

    public void InstateateTestDataBase() throws ParseException {
        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));


        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));

        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));

        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));

        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));


        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        Estado e = new Estado(null, "Piaui");
        Estado e1 = new Estado(null, "Distrito Federal");
        Estado e2 = new Estado(null, "Goiais");


        Cidade c1 = new Cidade(null, "Samambaia", e1);
        Cidade c2 = new Cidade(null, "Gama", e1);
        Cidade c3 = new Cidade(null, "Monte Alegre", e);
        Cidade c4 = new Cidade(null, "Cristalina", e2);


        //	e.getCidades().addAll(Arrays.asList(c3));
        //	e1.getCidades().addAll(Arrays.asList(c1,c2));
        //	e2.getCidades().addAll(Arrays.asList(c4));

        estadoRepository.saveAll(Arrays.asList(e, e1, e2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));

        Cliente cli1 = new Cliente(null, "Maria", "maria@gamil.com", "3637892377", TipoCliente.PESSOAFISICA, bCryptPasswordEncoder.encode("123"));
        cli1.getTelefones().addAll(Arrays.asList("213655680", "124546547"));


        Endereco ed1 = new Endereco(null, "QR 405", "28", "Casa", "Samambaia", "72319207", cli1, c1);
        Endereco ed2 = new Endereco(null, "QR 208", "10", "Casa", "Samambaia", "72319207", cli1, c2);
        clienteRepository.save(cli1);
        enderecoRepository.saveAll(Arrays.asList(ed1, ed2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/03/2023 10:32"), cli1, ed1);
        Pedido ped2 = new Pedido(null, sdf.parse("20/03/2023 19:35"), cli1, ed2);

        Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pgto1);
        Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/03/2023 00:10"), null);
        ped2.setPagamento(pgto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
        clienteRepository.save(cli1);


        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().add(ip3);

        p1.getItens().add(ip1);
        p2.getItens().add(ip3);
        p3.getItens().add(ip2);


        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));


    }
}