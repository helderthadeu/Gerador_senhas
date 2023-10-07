import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Criptografar {

	public Criptografar() {

	}

	public void criptografarSenhas(Alfabeto novoAlfabeto) throws IOException {

		File file = new File("senhas.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		File fileCr = new File("senhasEncriptadas.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileCr));

		String tempLinha;
		String alfabeto = novoAlfabeto.getLetras();

		while ((tempLinha = reader.readLine()) != null) {
			int tempVal;

			for (int i = 0; i < tempLinha.length(); i++) {
				tempVal = tempLinha.charAt(i);
				if (tempVal > 127 || tempVal < 33) {

					tempVal = 32;
				}
				tempVal -= 32;
				writer.append(alfabeto.charAt(tempVal));
			}

		}
		writer.close();
		reader.close();
	}

	public void descriptografarSenhas(Alfabeto novoAlfabeto) throws IOException {
		File file = new File("senhasEncriptadas.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		

		String tempLinha;
		String alfabeto = novoAlfabeto.getLetras();

		while ((tempLinha = reader.readLine()) != null) {
			int tempVal=0;

			for (int i = 0; i < tempLinha.length(); i++) {
				tempVal = tempLinha.charAt(i);
				for(int j = 0; j < alfabeto.length(); j+=1) {
					int tempnum=alfabeto.charAt(j);
					
					if(tempVal == tempnum) {
						System.out.print((char)(j+32));
					break;
					}
				}
				
				
				
			}

		}
		reader.close();
		System.out.println("\nArquivo desencriptado com sucesso.");
	}

}
