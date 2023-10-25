import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		Console senha = new Console();
		
		try {
			Files.deleteIfExists(Paths.get("C:\\Users\\marta\\OneDrive\\Área de Trabalho\\Helder\\3 - Programação\\Eclipse_workspace\\Gerador_Senhas\\senhas.txt"));
//			System.out.println("Arquivo deletado com suceso");
		} catch (Exception e) {
			System.out.println("Erro ao deltar o arquivo. "+ e.getMessage());

		}
		senha.loop_principal();
		
	}

}
