package br.com.creapix.util;

import java.io.IOException;

import javax.persistence.EntityManager;

import br.com.creapix.modelo.Cadastro;

public class PopulaCadastro {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		Cadastro cadastro1 = new Cadastro();
		Cadastro cadastro2 = new Cadastro();

		cadastro1.setNome("Alex Sandro");
		cadastro1.setTelefone("98487-0182");
		cadastro1.setEmail("alex@creapix.com.br");
		cadastro1.setQrcode("1234567898765432");
		cadastro1.setImagem("c:\\image\\eu.jpg");

		cadastro2.setNome("Ana laura");
		cadastro2.setTelefone("97323-2799");
		cadastro2.setEmail("analaura@creapix.com.br");
		cadastro2.setQrcode("98765432123456789");
		cadastro2.setImagem("c:\\image\\ana.jpg");

		// persistindo os cadastros
		manager.persist(cadastro1);
		manager.persist(cadastro2);

		manager.getTransaction().commit();

		manager.close();
	}

}
