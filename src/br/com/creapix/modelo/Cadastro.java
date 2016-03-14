package br.com.creapix.modelo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Cadastro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String telefone;
	private String email;

	private String qrcode;

	@Lob
	@Column(name = "BOOK_IMAGE", nullable = false, columnDefinition = "mediumblob")
	private byte[] imagem;

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String i) {
		this.qrcode = i;
	}

	@OneToMany(mappedBy = "cadastro")
	private List<Movimentacao> movimentacoes;

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(String caminho) throws IOException {

		BufferedImage originalImage = ImageIO.read(new File(caminho));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos);
		baos.flush();
		this.imagem = baos.toByteArray();

	}

}
