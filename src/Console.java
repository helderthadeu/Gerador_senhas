import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Console {
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();

	public Console() {
	}

	public void loop_principal() {
		System.out.println(
				"Seja Bem vindo(a) ao gerador de senha!. \nEste gerador é utilizado para gerar uma senha segura, que dificulte qualquer processo de quebra da mesma.");
		int escolha_usuario = 0;
		while (escolha_usuario != 6) {
			try {
				System.out.println(Gerador.printmenu());
				escolha_usuario = scan.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("Digite apenas números");
				scan.next();
			}
			switch (escolha_usuario) {
			case 1: {
				try {
					String resposta;
					int tamanhoSenha;
					boolean letraMaiuscula = false;
					boolean letraMinuscula = false;
					boolean numeros = false;
					boolean caracterEspecial = false;
					do {
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

						}
					} while (!letraMaiuscula && !letraMinuscula && !numeros && !caracterEspecial);
					do {
						System.out.println("Digite o tamanho desejado. (min 8)");
						tamanhoSenha = scan.nextInt();
						if (tamanhoSenha < 8) {
							System.out.println("Tamanho inválido.");
						}
					} while (tamanhoSenha < 8);
					String senhaGerada = Gerador.geradorSenha(letraMaiuscula, letraMinuscula, numeros,
							caracterEspecial, tamanhoSenha), opcao="n";
					System.out.println("Senha gerada:" + senhaGerada);
					System.out.println("Deseja salvar senha? s/n");
					opcao = scan.next();
					if(opcao.contentEquals("s")) {
						System.out.println("Digite o id da senha: ");
						opcao = scan.nextLine();
						Gerador.salvarSenhas(opcao, senhaGerada);
					}
					
					
				} catch (Exception e) {
					System.out.println("Erro na operação. " + e.getMessage());
				}
				break;
			}
			case 2: {
				System.out.println("Digite sua senha para ser verificada.");
				try {
					String temp_senha = scan.next();
					System.out.println(Gerador.verificaSenha(temp_senha));
				} catch (Exception e) {
					System.out.println("Erro na operação. " + e.getMessage());

				}
				break;
			}
			case 3: {
				try {
					String senha, idSenha, continuar = "n";
					do {
						System.out.println("Digite a identificação da senha a ser salva");
						idSenha = scan.next();

						System.out.println("Digite a senha: ");
						senha = scan.nextLine();
						Gerador.salvarSenhas(idSenha, senha);
						System.out.println("Deseja continuar?s/n");
						continuar = scan.nextLine();
					} while (continuar.contentEquals("s"));
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
				System.out.println("Aviso! Isso irá apagar o arquivo com senha não criptografado, caso exista.");
				System.out.println("Digite a senha do arquivo:");
				String senha = scan.next();
				System.out.println("Digite o código de segurança: ");
				int codigo = scan.nextInt();
				Alfabeto novoAlfabeto = new Alfabeto();
				novoAlfabeto.criaAlfabeto(senha, codigo);

				Criptografar criptografarArquivo = new Criptografar();
				try {
					criptografarArquivo.criptografarSenhas(novoAlfabeto);
					Files.deleteIfExists(Paths.get("senhas.txt"));

				} catch (Exception e) {
					System.out.println("Erro na operação. " + e.getMessage());
				}

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

}
