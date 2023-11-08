/*
 * @author Helder Santos
 */


import java.awt.EventQueue;
import com.interfaces.Console;
import com.interfaces.InterfaceDesktop;

public class Main {

	public static void main(String[] args) {
		/*
		 * Plataformas disponíveis: 
		 * 0 - Console
		 * 1 - Interface Gráfica Desktop
		 */
		int plataforma = 1;

		switch (plataforma) {
		case 0: {
			Console senha = new Console();
			senha.loop_principal();
			break;
		}
		case 1: {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						InterfaceDesktop frame = new InterfaceDesktop();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		}
	}

}
