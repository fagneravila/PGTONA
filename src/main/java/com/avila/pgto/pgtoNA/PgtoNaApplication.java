package com.avila.pgto.pgtoNA;

import com.avila.pgto.pgtoNA.domain.Categoria;
import com.avila.pgto.pgtoNA.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PgtoNaApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;


	public static void main(String[] args) {
		SpringApplication.run(PgtoNaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Tecnologia");
		Categoria cat2 = new Categoria(null, "Escritorio");

	    categoriaRepository.saveAll(Arrays.asList(cat1,cat2));


	}
}
