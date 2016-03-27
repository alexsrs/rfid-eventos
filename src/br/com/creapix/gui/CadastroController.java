package br.com.creapix.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.EntityManager;

import br.com.creapix.modelo.Cadastro;
import br.com.creapix.util.JPAUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

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

	@FXML
	public static Pane paneFoto;

	@FXML
	public static BorderPane cadastroBorderPane;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (imgFoto == null) {
			Image image = new Image("/br/com/creapix/gui/cam.png");
			System.out.println(image);
			imgFoto = new ImageView(image);
		} else {
			System.out.println("Estou aqui");
		}
		// TODO Auto-generated method stub

	}

	Cadastro cadastro = new Cadastro();

	// @FXML
	@FXML
	public void eventGravarButtonClick(ActionEvent event) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		try {
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

	public static void setImgFoto(WritableImage image) {
		System.out.println("Image=  " + image);
		imgFoto.setImage(image);

		System.out.println("Image=  " + imgFoto.getImage());
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