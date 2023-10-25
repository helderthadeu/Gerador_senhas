import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;


public class Gerador {

	static void salvarSenhas(String idSenha, String senha) throws Exception {

		File file = new File("senhas.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

		writer.write( idSenha + " " + senha + " ");
		
		writer.close();
		file = null;
	}

	static String geradorSenha(boolean letraMaiuscula, boolean letraMinuscula, boolean numeros, boolean caracterEspecial, int tamanhoSenha) throws Exception {
		
		String senhaGerada = "";
		senhaGerada = Gerador.gerarSenha(letraMaiuscula, letraMinuscula, numeros, caracterEspecial, tamanhoSenha);
		return senhaGerada;

	}

	static String gerarSenha(boolean letraMaiuscula, boolean letraMinuscula, boolean numeros, boolean caracterEspecial,
			int tamanho) throws Exception {
		Alfabeto letras = new Alfabeto(letraMaiuscula, letraMinuscula, numeros, caracterEspecial);
		StringBuilder senhaGerada = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < tamanho; i++) {
			do {
				int random = rand.nextInt(letras.getLetras().length());
				senhaGerada.append(letras.getLetras().charAt(random));
			} while (FuncoesAuxiliares.jahPossui(senhaGerada.toString()));
		}

		return senhaGerada.toString();
	}

	static String verificaSenha(String senha) throws Exception {
		String forca;
		double cont = 0;
		boolean possuiMaiuscula = false;
		boolean possuiMinuscula = false;
		boolean possuiEspecial = false;
		boolean possuiNumero = false;
		/*
		 * Nível de força da senha é gerado através de uma função linear, da soma do
		 * "valor" dos componentes multiplicados pela quantidade de caracteres. Letra
		 * maiuscula +=26 Letra minusciula +=26 Caracteres especiais +=21 Números +=10
		 * 
		 */
		for (int i = 0; i < senha.length(); i++) {
			if (Character.isUpperCase(senha.charAt(i))) {
				possuiMaiuscula = true;
			}
			if (Character.isLowerCase(senha.charAt(i))) {
				possuiMinuscula = true;
			}
			if (Character.isDigit(senha.charAt(i))) {
				possuiNumero = true;
			}
			if (!Character.isUpperCase(senha.charAt(i)) && !Character.isLowerCase(senha.charAt(i))
					&& Character.isDigit(senha.charAt(i))) {
				possuiEspecial = true;
			}
		}

		if (possuiMaiuscula) {
			cont += 30;
		}
		if (possuiMinuscula) {
			cont += 30;
		}
		if (possuiNumero) {
			cont += 10;
		}
		if (possuiEspecial) {
			cont += 20;
		}
		cont *= (senha.length() * 6.2);
		cont -= 310;
		System.out.println(cont);
		if (cont > 10000) {
			forca = "Muito Forte";
		} else if (cont > 7500) {
			forca = "Forte";
		} else if (cont > 5000) {
			forca = "Media";
		} else if (cont > 2500) {
			forca = "Fraca";
		} else {
			forca = "Muito Fraca";
		}

		return "Sua Senha é: " + forca;
	}

	static String printmenu() {
		return "Escolha uma opção: " + "\n1 - Gerar Senha aleatoriamente." + "\n2 - Verificar 'força' da senha."
				+ "\n3 - Salvar senhas em arquivo." + "\n4 - Verificar Senhas criptografadas salvas." + "\n5 - Criptografar arquivos (já criados) com senhas em um novo arquivo."
				+ "\n6 - Sair";
	}

}
