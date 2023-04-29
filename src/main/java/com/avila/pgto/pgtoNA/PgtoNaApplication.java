package com.avila.pgto.pgtoNA;

import com.avila.pgto.pgtoNA.domain.*;
import com.avila.pgto.pgtoNA.domain.enums.EstadoPagamento;
import com.avila.pgto.pgtoNA.domain.enums.TipoCliente;
import com.avila.pgto.pgtoNA.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class PgtoNaApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(PgtoNaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Tecnologia");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null,"computador", 2000.00);
		Produto p2 = new Produto(null,"impressora", 800.00);
		Produto p3 = new Produto(null,"mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		Estado e = new Estado(null,"Piaui");
		Estado e1 = new Estado(null,"Distrito Federal");
		Estado e2 = new Estado(null, "Goiais");


		Cidade c1 = new Cidade(null,"Samambaia",e1);
		Cidade c2 = new Cidade(null, "Gama", e1);
		Cidade c3 = new Cidade(null, "Monte Alegre", e);
		Cidade c4 = new Cidade(null,"Cristalina",e2);


		//	e.getCidades().addAll(Arrays.asList(c3));
		//	e1.getCidades().addAll(Arrays.asList(c1,c2));
		//	e2.getCidades().addAll(Arrays.asList(c4));

		estadoRepository.saveAll(Arrays.asList(e,e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3,c4));

		Cliente cli1 = new Cliente(null, "Maria", "maria@gamil.com","3637892377",TipoCliente.PESSOAFISICA );
		cli1.getTelefones().addAll(Arrays.asList("213655680","124546547"));


		Endereco ed1 = new Endereco(null,"QR 405", "28","Casa","Samambaia", "72319207",cli1,c1);
		Endereco ed2 = new Endereco(null,"QR 208", "10","Casa","Samambaia", "72319207",cli1,c2);
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(ed1,ed2));

	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/03/2023 10:32"),cli1, ed1);
		Pedido ped2 = new Pedido(null, sdf.parse("20/03/2023 19:35"),cli1, ed2);

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1,6);
        ped1.setPagamento(pgto1);
		Pagamento pgto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/03/2023 00:10"),null);
		ped2.setPagamento(pgto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1,pgto2));
		clienteRepository.save(cli1);

	}
}
