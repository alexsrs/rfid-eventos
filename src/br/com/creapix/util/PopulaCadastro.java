package br.com.creapix.util;

import java.io.IOException;

import javax.persistence.EntityManager;

import br.com.creapix.modelo.Cadastro;

public class PopulaCadastro {

	public static void main(String[] args) throws IOException {

		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		Cadastro cadastro1 = new Cadastro();
		cadastro1.setNome("Alex");
		cadastro1.setEmail("email");
		cadastro1.setTelefone("2344223");
		cadastro1.setQrcode("22 ed 12 23");
		cadastro1.setImagem("c://imagem/eu.jpg");
		// Movimentacoes da conta1
		manager.persist(cadastro1);
		manager.getTransaction().commit();
		/*
		 * Movimentacao movimentacao1 = new Movimentacao();
		 * 
		 * movimentacao1.setData(Calendar.getInstance());
		 * movimentacao1.setPontuacao(1000);
		 * movimentacao1.setTipoDeMovimentacao(TipoDeMovimentacao.PROVA1);
		 * movimentacao1.setCadastro(cadastro1);
		 * 
		 * manager.persist(movimentacao1);
		 */
		manager.close();

	}

}
