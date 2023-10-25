import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Criptografar {

	public Criptografar() {

	}

	public void criptografarSenhas(Alfabeto novoAlfabeto) throws IOException {

		File file = new File("senhas.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		File fileCr;
		if (Files.exists(Paths.get("senhasEncriptadas_0.txt"))) {
			int cont = 1;
			String temp = "senhasEncriptadas_" + cont + ".txt";
			while (Files.exists(Paths.get(temp))) {
				cont++;
				temp = "senhasEncriptadas_" + cont + ".txt";
			}

			fileCr = new File(temp);
		} else {
			fileCr = new File("senhasEncriptadas_0.txt");
		}

		BufferedWriter writer = new BufferedWriter(new FileWriter(fileCr));

		String tempLinha;
		String alfabeto = novoAlfabeto.getLetras();

		while ((tempLinha = reader.readLine()) != null) {
			int tempVal;
			StringBuilder tmp = new StringBuilder();
			for (int i = 0; i < tempLinha.length(); i++) {
				tempVal = tempLinha.charAt(i);
				if (tempVal > 127 || tempVal < 33) {

					tempVal = 32;
				}
				tempVal -= 32;
				tmp.append(alfabeto.charAt(tempVal));
			}
			writer.append(tmp);
		}
		
		writer.close();
		reader.close();
	}

	public void descriptografarSenhas(Alfabeto novoAlfabeto) throws IOException {
		File file;
		Scanner scan = new Scanner(System.in);
		if (Files.exists(Paths.get("senhasEncriptadas_1.txt"))) {
			System.out.println("senhasEncriptadas_0.txt");
			int cont = 1;
			String temp = "senhasEncriptadas_" + cont + ".txt";
			while (Files.exists(Paths.get(temp))) {
				System.out.println(temp);
				cont++;
				temp = "senhasEncriptadas_" + cont + ".txt";

			}
			System.out.println("Qual arquivo deseja verificar as senhas: ");
			String arquivo = scan.next();

			arquivo = "senhasEncriptadas_" + arquivo + ".txt";
			file = new File(arquivo);

		} else {
			file = new File("senhasEncriptadas_0.txt");
		}

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String tempLinha;
		String alfabeto = novoAlfabeto.getLetras();

		while ((tempLinha = reader.readLine()) != null) {
			int tempVal = 0;

			for (int i = 0; i < tempLinha.length(); i++) {
				tempVal = tempLinha.charAt(i);
				for (int j = 0; j < alfabeto.length(); j += 1) {
					int tempnum = alfabeto.charAt(j);

					if (tempVal == tempnum) {
						System.out.print((char) (j + 32));
						break;
					}
				}

			}

		}
		
		reader.close();
		System.out.println("\nArquivo desencriptado com sucesso.");
	}

}
