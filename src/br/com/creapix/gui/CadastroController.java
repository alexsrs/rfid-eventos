package br.com.creapix.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.EntityManager;

import br.com.creapix.modelo.Cadastro;
import br.com.creapix.util.JPAUtil;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class CadastroController extends MenuController implements Initializable, ControlledScreen {

	@FXML
	private TextField tfId;

	@FXML
	private TextField tfQrcode;

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfTelefone;

	@FXML
	private TextField tfEmail;

	@FXML
	private Button btnFoto;

	@FXML
	public static ImageView imgFoto;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}

	Cadastro cadastro = new Cadastro();

	// @FXML
	@FXML
	public void eventGravarButtonClick(ActionEvent event) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		try {
			// tfId.setId(c.getId());
			cadastro.setNome(tfNome.getText());
			cadastro.setTelefone(tfTelefone.getText());
			cadastro.setEmail(tfEmail.getText());
			cadastro.setQrcode(tfQrcode.getText());
			// cadastro.setImagem();

			manager.persist(cadastro);
			manager.getTransaction().commit();
			manager.close();

			System.out.println("Usuario cadastrado com sucesso");

			limpaForm(10);

		} catch (Exception ex) {
			alert(ex.getMessage());
		}
	}

	/*
	 * public static void setTfFoto(Image image) { // ImageView imageview = new
	 * ImageView(foto); tfFoto.setImage(image); // = imageview; }
	 */

	public static void retorna(BufferedImage bufImage) throws IOException {
		// String file = new String("test.png");
		// setTfFoto(bufImage);
		// SwingFXUtils.toFXImage(bufImage, image);

		WritableImage wimage = SwingFXUtils.toFXImage(bufImage, null);
		System.out.println("Writable Image" + wimage);
		System.out.println("bUfered Image" + bufImage);

		// SwingFXUtils.toFXImage(bufImage, image);
		// ImageView tfFoto = new ImageView(image);
		imgFoto.setImage(wimage);
		Cadastro.setImagem(bufImage);
	}

	public static void setImgFoto(Image image) {
		// TODO Auto-generated method stub
		imgFoto.setImage(image);
	}

	public TextField getTfQrcode() {
		return tfQrcode;
	}

	public TextField getTfNome() {
		return tfNome;
	}

	public TextField getTfTelefone() {
		return tfTelefone;
	}

	public TextField getTfEmail() {
		return tfEmail;
	}

	public void setTfId(TextField tfId) {
		this.tfId = tfId;
	}

	public void limpaForm(int seconds) {
		Timer timer = new Timer();
		timer.schedule(new apagaTask(), seconds * 1000);
	}

	public void alert(String msg) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Mensagem");
		alert.setContentText(msg);
		alert.show();
	}

	public ImageView getImgViewFoto() {

		return imgFoto;
	}

	public static void setImgFoto(ImageView imgFoto) {
		CadastroController.imgFoto = imgFoto;
	}

	public Cadastro getCadastro() {
		return cadastro;
	}

	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
	}

	public TextField getTfId() {
		return tfId;
	}

	public void setTfQrcode(TextField tfQrcode) {
		this.tfQrcode = tfQrcode;
	}

	public void setTfNome(TextField tfNome) {
		this.tfNome = tfNome;
	}

	public void setTfTelefone(TextField tfTelefone) {
		this.tfTelefone = tfTelefone;
	}

	public void setTfEmail(TextField tfEmail) {
		this.tfEmail = tfEmail;
	}

}

class apagaTask extends TimerTask {
	@Override
	public void run() {
		System.out.println("Tarefa encerrada");

	}

}