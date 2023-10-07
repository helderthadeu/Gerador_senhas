import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
public class Gerador {
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();

	public Gerador() {
	}

	public void loop_principal() {
		System.out.println(
				"Seja Bem vindo(a) ao gerador de senha!. \nEste gerador é utilizado para gerar uma senha segura, que dificulte qualquer processo de quebra da mesma.");
		int escolha_usuario = 0;
		while (escolha_usuario != 6) {
			this.printmenu();
			try {
				escolha_usuario = scan.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("Digite apenas números");
				scan.next();
			}
			switch (escolha_usuario) {
			case 1: {
				try {
					this.geradorSenha();
				} catch (Exception e) {
					System.out.println("Erro na operação. " + e.getMessage());
				}
				break;
			}
			case 2: {
				System.out.println("Digite sua senha para ser verificada.");
				try {
					String temp_senha = scan.next();
					this.verificaSenha(temp_senha);
				} catch (Exception e) {
					System.out.println("Erro na operação. " + e.getMessage());

				}
				break;
			}
			case 3: {
				try {
					this.salvarSenhas();

				} catch (Exception e) {
					System.out.println("Erro na operação. " + e.getMessage());
				}

				break;
			}

			case 4: {
				System.out.println("Digite a senha do arquivo:");
				String senha = scan.next();
				System.out.println("Digite o código de segurança: ");
				int codigo = scan.nextInt();
				Alfabeto novoAlfabeto = new Alfabeto();
				novoAlfabeto.criaAlfabeto(senha, codigo);

				Criptografar descriptografar = new Criptografar();
				try {
					descriptografar.descriptografarSenhas(novoAlfabeto);
				} catch (Exception e) {
					System.out.println("Erro na operação. " + e.getMessage());
				}
				break;
			}
			case 5: {

				System.out.println("Digite a senha do arquivo:");
				String senha = scan.next();
				System.out.println("Digite o código de segurança: ");
				int codigo = scan.nextInt();
				Alfabeto novoAlfabeto = new Alfabeto();
				novoAlfabeto.criaAlfabeto(senha, codigo);

				Criptografar criptografarArquivo = new Criptografar();
				try {
					criptografarArquivo.criptografarSenhas(novoAlfabeto);
				} catch (Exception e) {
					System.out.println("Erro na operação. " + e.getMessage());
				}
				System.out.println("Arquivo encriptado com sucesso!");

				break;
			}

			case 6: {
				System.out.println("Obrigado por utilizar nossos serviçoes.");
				break;
			}
			default: {
				System.out.println("Opção inválida. ");
				break;
			}

			}
		}

	}

	private void salvarSenhas() {
		String senha, idSenha;

		try {
			File file = new File("senhas.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

			System.out.println("Digite a identificação da senha a ser salva");
			idSenha = scan.next();

			System.out.println("Digite a senha: ");
			senha = scan.next();

			writer.write(senha + " - " + idSenha);
			writer.newLine();

			writer.close();
		} catch (Exception e) {
			System.out.println("Erro na operação." + e.getMessage());
		}

	}

	private void geradorSenha() throws Exception {
		String senhaGerada = "", resposta;
		int tamanhoSenha;
		boolean letraMaiuscula = false;
		boolean letraMinuscula = false;
		boolean numeros = false;
		boolean caracterEspecial = false;

		System.out.println("Deseja utilizar letra maiuscula? s/n");
		resposta = scan.next();
		if (resposta.equals("s")) {
			letraMaiuscula = true;
			resposta = null;
		}

		System.out.println("Deseja utilizar letra minúscula? s/n");
		resposta = scan.next();
		if (resposta.equals("s")) {
			letraMinuscula = true;
			resposta = null;
		}

		System.out.println("Deseja utilizar números? s/n");
		resposta = scan.next();
		if (resposta.equals("s")) {
			numeros = true;
			resposta = null;
		}

		System.out.println("Deseja utilizar caracteres especiais? s/n");
		resposta = scan.next();
		if (resposta.equals("s")) {
			caracterEspecial = true;
			resposta = null;
		}

		if (!letraMaiuscula && !letraMinuscula && !numeros && !caracterEspecial) {
			System.out.println("Nenhuma opção selecionada, reiniciando o questionamento. ");
			this.geradorSenha();
		}
		do {
			System.out.println("Digite o tamanho desejado. (min 8)");
			tamanhoSenha = scan.nextInt();
			if (tamanhoSenha < 8) {
				System.out.println("Tamanho inválido.");
			}
		} while (tamanhoSenha < 8);
		senhaGerada = this.gerarSenha(letraMaiuscula, letraMinuscula, numeros, caracterEspecial, tamanhoSenha);
		System.out.println("A senha gerada foi: " + senhaGerada);
	}

	private String gerarSenha(boolean letraMaiuscula, boolean letraMinuscula, boolean numeros, boolean caracterEspecial,
			int tamanho) throws Exception {
		Alfabeto letras = new Alfabeto(letraMaiuscula, letraMinuscula, numeros, caracterEspecial);
		StringBuilder senhaGerada = new StringBuilder();

		for (int i = 0; i < tamanho; i++) {
			int random = rand.nextInt(letras.getLetras().length());
			senhaGerada.append(letras.getLetras().charAt(random));
		}

		return senhaGerada.toString();
	}

	private void verificaSenha(String senha) throws Exception {
		String forca;
		double cont = 0;
		boolean possuiMaiuscula = false;
		boolean possuiMinuscula = false;
		boolean possuiEspecial = false;
		boolean possuiNumero = false;
		/*
		 * Niveis de Força para a Senha, baseado na quantidade de possibilidades daquele
		 * caracter: Caso possua letra maíuscula: cont + 30 Caso possua letra minúscula:
		 * cont + 30 Caso possua letra caracter especial: cont + 25 Caso possua letra
		 * numero: cont + 15
		 * 
		 * O tamanho da senha influenciará, onde ele é multiplicado por 12,5 , gerando 8
		 * como "valor máximo".
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
			cont = +30;
		}
		if (possuiMinuscula) {
			cont = +30;
		}
		if (possuiNumero) {
			cont = +25;
		}
		if (possuiEspecial) {
			cont = +15;
		}
		cont = senha.length() * 12.5;
		if (cont > 160) {
			forca = "Muito Forte";
		} else if (cont > 120) {
			forca = "Forte";
		} else if (cont > 80) {
			forca = "Media";
		} else if (cont > 40) {
			forca = "Fraca";
		} else {
			forca = "Muito Fraca";
		}

		System.out.println("Sua Senha é: " + forca);
	}

	private void printmenu() {
		System.out.println("Escolha uma opção: ");
		System.out.println("1 - Gerar Senha aleatoriamente.");
		System.out.println("2 - Verificar 'força' da senha.");
		System.out.println("3 - Salvar senhas.");
		System.out.println("4 - Verificar Senhas salvas.");
		System.out.println("5 - Criptografar arquivos com senhas.");
		System.out.println("6 - Sair");

	}

}
