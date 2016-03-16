package br.com.creapix.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.management.Query;
import javax.persistence.EntityManager;

import br.com.creapix.modelo.Cadastro;
import br.com.creapix.util.JPAUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
	private Button btnGravar;

	@FXML
	private ImageView tfFoto;

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

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// TODO Auto-generated method stub
	}

	// @FXML
	@FXML
	public void eventGravarButtonClick(ActionEvent event) {
		Cadastro cadastro = new Cadastro();
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		try {
			// tfId.setId(c.getId());
			cadastro.setNome(tfNome.getText());
			cadastro.setTelefone(tfTelefone.getText());
			cadastro.setEmail(tfEmail.getText());
			cadastro.setQrcode(tfQrcode.getText());
			cadastro.setImagem("c:\\image\\eu.jpg");

			manager.persist(cadastro);
			manager.getTransaction().commit();
			manager.close();

			System.out.println("Usuario cadastrado com sucesso");
			// setTfId(this.tfId);
			// final Image image2 = new
			// Image(Main.class.getResourceAsStream("c:/image/eu.jpg"));
			// this.tfFoto.setImage(image2);
			// query.setParameter("pConta", conta);

			Query query = (Query) manager.createQuery("select m from Cadastro m where m.cadastro=:pCadastro");

			((javax.persistence.Query) query).setParameter("pCadastro", cadastro);

			tfFoto.getImage();
			apagaForm(5);

		} catch (Exception ex) {
			alert(ex.getMessage());
		}
	}

	public void apagaForm(int seconds) {
		Timer timer = new Timer();
		timer.schedule(new apagaTask(), seconds * 1000);
	}

	class apagaTask extends TimerTask {
		@Override
		public void run() {
			System.out.println("Tarefa encerrada");

		}
	}

	public void alert(String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Mensagem");
		alert.setContentText(msg);
		alert.show();
	}

}