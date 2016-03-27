package br.com.creapix.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.EntityManager;

import br.com.creapix.modelo.Cadastro;
import br.com.creapix.util.JPAUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import serial.SerialComLeitura;

public class CadastroController extends MenuController implements Initializable, ControlledScreen {

	@FXML
	private Label tagRfid;

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfTelefone;

	@FXML
	private TextField tfEmail;

	@FXML
	private Button btnFoto;

	@FXML
	private Button btnTagRfid;

	@FXML
	public static ImageView imgFoto;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		if (imgFoto == null) {
			Image image = new Image("/br/com/creapix/gui/cam.png");
			System.out.println(image);
			imgFoto = new ImageView(image);
		}
	}

	Cadastro cadastro = new Cadastro();

	@FXML
	public void eventGravarButtonClick(ActionEvent event) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		try {
			cadastro.setNome(tfNome.getText());
			cadastro.setTelefone(tfTelefone.getText());
			cadastro.setEmail(tfEmail.getText());
			cadastro.setQrcode(tagRfid.getText());
			// Cadastro.setImagem(imgFoto.getImage());

			manager.persist(cadastro);
			manager.getTransaction().commit();
			manager.close();

			System.out.println("Usuario cadastrado com sucesso");

			limpaForm(10);

		} catch (Exception ex) {
			alert(ex.getMessage());
		}
	}

	@FXML
	public void eventPegarRfidUUID(ActionEvent event) {
		System.out.println("sera aqui");

		btnTagRfid.isDisable();
		tagRfid.setText("Aproxime o card da leitora...");
		while (SerialComLeitura.getPeso() == null) {
			verificaSerial();
		}
		// tag = false;
	}

	private void verificaSerial() {

		System.out.println("inicio da leitura");
		SerialComLeitura leitura = new SerialComLeitura("COM5", 9600, 0);

		leitura.HabilitarLeitura();

		leitura.ObterIdDaPorta();

		leitura.AbrirPorta();

		leitura.LerDados();

		// Controle de tempo da leitura aberta na serial

		try {

			System.out.println("Pegando UUID");
			Thread.sleep(1000);

		} catch (InterruptedException ex) {

			System.out.println("Erro na Thread: " + ex);

		}
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				setTagRfid(SerialComLeitura.getPeso());
			}
		});

		leitura.FecharCom();
	}

	public String getTagRfid() {
		return tagRfid.getText();
	}

	public void setTagRfid(String string) {
		tagRfid.setText(string);
	}

	public static void setImgFoto(WritableImage image) {
		System.out.println("Image=  " + image);
		imgFoto.setImage(image);
		System.out.println("Image=  " + imgFoto.getImage());
	}

	public TextField getTfNome() {
		return tfNome;
	}

	public void setTfNome(TextField tfNome) {
		this.tfNome = tfNome;
	}

	public TextField getTfTelefone() {
		return tfTelefone;
	}

	public void setTfTelefone(TextField tfTelefone) {
		this.tfTelefone = tfTelefone;
	}

	public TextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(TextField tfEmail) {
		this.tfEmail = tfEmail;
	}

	public Cadastro getCadastro() {
		return cadastro;
	}

	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
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
}

class apagaTask extends TimerTask {
	@Override
	public void run() {
		System.out.println("Tarefa encerrada");

	}

}