package br.com.creapix.modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
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
	@Column(name = "BOOK_IMAGE", nullable = true, columnDefinition = "mediumblob")
	private static byte[] imagem;

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

	public void setNome(String tfNome) {
		this.nome = tfNome;
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

	public BufferedImage getImagem(byte[] bytes) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");
		// ImageIO is a class containing static methods for locating
		// ImageReaders
		// and ImageWriters, and performing simple encoding and decoding.
		ImageReader reader = (ImageReader) readers.next();
		Object source = bis;
		ImageInputStream iis = ImageIO.createImageInputStream(source);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		BufferedImage image = reader.read(0, param);
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, null, null);
		return bufferedImage;
	}

	public void setImagem(String caminho) throws IOException {
		BufferedImage originalImage = ImageIO.read(new File(caminho));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos);
		baos.flush();
		imagem = baos.toByteArray();
	}

	public static void setImagem(BufferedImage bufImagem) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufImagem, "jpg", baos);
		baos.flush();
		imagem = baos.toByteArray();
	}

	public void setImagem(Image img) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedImage bufImagem = (BufferedImage) img;
		ImageIO.write(bufImagem, "jpg", baos);
		baos.flush();
		imagem = baos.toByteArray();
	}

	public static void setImagem(byte[] byteArray) {
		imagem = byteArray;
	}

}
