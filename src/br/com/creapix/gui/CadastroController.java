package br.com.creapix.gui;

import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
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
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import serial.SerialComLeitura;

public class CadastroController extends MenuController implements Initializable, ControlledScreen {

	@FXML
	public BorderPane borderPane;

	@FXML
	public AnchorPane anchorPane;

	@FXML
	private Label lblTagRfid;

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
	static ImageView imgFoto;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setLblTagRfid("Clique no botão e aproxime o card");
		if (imgFoto == null) {
			System.out.println(imgFoto);
			imgFoto = new ImageView("/br/com/creapix/gui/cam.png");
			anchorPane.getChildren().add(imgFoto);
			imgFoto.setX(710);
			imgFoto.setY(30);
			System.out.println(imgFoto.getImage());
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
			cadastro.setQrcode(lblTagRfid.getText());
			Cadastro.setImagem(getByteArray(imgFoto));

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
		lblTagRfid.setText("Aproxime o card da leitora...");
		while (SerialComLeitura.getUUID() == null) {
			verificaSerial();
		}
		// tag = false;
	}

	public byte[] getByteArray(ImageView imgFoto2) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write((RenderedImage) imgFoto2, "jpg", baos);
		baos.flush();
		byte[] bytearray = baos.toByteArray();
		baos.close();
		return bytearray;
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
				setLblTagRfid(SerialComLeitura.getUUID());
			}
		});

		leitura.FecharCom();
	}

	public String getLblTagRfid() {
		return lblTagRfid.getText();
	}

	public void setLblTagRfid(String string) {
		lblTagRfid.setText(string);
	}

	public static void setImgFoto(WritableImage image) {
		imgFoto.setImage(image);

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