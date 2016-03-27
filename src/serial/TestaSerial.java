package serial;

public class TestaSerial extends SerialCom {

	public static void main(String[] args) {
		// Iniciando leitura serial

		// SerialComLeitura leitura = new
		// SerialComLeitura("/dev/cu.usbmodemFD131", 9600, 0);
		SerialComLeitura leitura = new SerialComLeitura("COM5", 9600, 0);

		leitura.HabilitarLeitura();

		leitura.ObterIdDaPorta();

		leitura.AbrirPorta();

		leitura.LerDados();

		// Controle de tempo da leitura aberta na serial

		try {

			Thread.sleep(1000);

		} catch (InterruptedException ex) {

			System.out.println("Erro na Thread: " + ex);

		}

		leitura.FecharCom();

	}

}