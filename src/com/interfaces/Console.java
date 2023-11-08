
package com.interfaces;

import java.io.File;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.filechooser.FileSystemView;
import com.geradores.Alfabeto;
import com.geradores.Criptografar;
import com.geradores.Gerador;

public class Console {
	private Scanner scan = new Scanner(System.in);

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
					this.geracaoAleatoriaSenha();

				} catch (Exception e) {
					System.out.println("Erro na operação. " + e.getMessage());
				}
				break;
			}
			case 2: {
				try {
					this.verificacaoForcaSenha();
				} catch (Exception e) {
					System.out.println("Erro na operação. " + e.getMessage());

				}
				break;
			}
			case 3: {
				try {
					this.salvamentoSenha();
				} catch (Exception e) {
					System.out.println("Erro na operação. " + e.getMessage());
				}

				break;
			}
			case 4: {
				try {
					this.criptografarSenha();
				} catch (Exception e) {
					System.out.println("Erro na operação. " + e.getMessage());
				}

				break;
			}

			case 5: {
				try {
					this.descriptografarSenha();
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

	private void geracaoAleatoriaSenha() throws Exception {
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
		String senhaGerada = Gerador.geradorSenha(letraMaiuscula, letraMinuscula, numeros, caracterEspecial,
				tamanhoSenha), opcao = "n";
		System.out.println("Senha gerada:" + senhaGerada);
		System.out.println("Deseja salvar senha? s/n");
		opcao = scan.next();
		if (opcao.contentEquals("s")) {
			System.out.println("Digite a identificação da senha: ");
			String tmp = scan.next();
			System.out.println("Digite o nome do arquivo: ");
			String caminhoarquivo = scan.next();
			if (!caminhoarquivo.endsWith(".txt")) {
				caminhoarquivo = caminhoarquivo + ".txt";
			}
			FileSystemView fileSystemView = FileSystemView.getFileSystemView();
			File documentsFolder = fileSystemView.getDefaultDirectory();
			caminhoarquivo = documentsFolder.getAbsolutePath() + "\\" + caminhoarquivo;

			Gerador.salvarSenhas(tmp, senhaGerada, caminhoarquivo);
			System.out.println("Local de salvamento: " + Paths.get(caminhoarquivo).getParent());
		}
	}

	private void verificacaoForcaSenha() throws Exception {
		System.out.println("Digite sua senha para ser verificada.");
		String temp_senha = scan.next();
		System.out.println(Gerador.verificaSenha(temp_senha));

	}

	private void salvamentoSenha() throws Exception {
		String senha, idSenha, continuar = "n", caminhoarquivo = null;
		do {
			System.out.println("Digite a identificação da senha a ser salva");
			idSenha = scan.next();

			System.out.println("Digite a senha: ");
			senha = scan.next();
			if (caminhoarquivo == null) {
				System.out.println("Digite o nome do arquivo: ");
				caminhoarquivo = scan.next();
				if (!caminhoarquivo.endsWith(".txt")) {
					caminhoarquivo = caminhoarquivo + ".txt";
				}
				FileSystemView fileSystemView = FileSystemView.getFileSystemView();
				File documentsFolder = fileSystemView.getDefaultDirectory();
				caminhoarquivo = documentsFolder.getAbsolutePath() + "\\" + caminhoarquivo;
			}
			Gerador.salvarSenhas(idSenha, senha, caminhoarquivo);
			System.out.println("Local de salvamento: " + Paths.get(caminhoarquivo).getParent());
			System.out.println("Deseja continuar?s/n");
			continuar = scan.next();
		} while (continuar.contentEquals("s"));

	}

	private void criptografarSenha() throws Exception {
		System.out.println("Aviso! Isso irá apagar o arquivo com senha não criptografado, caso exista.");
		System.out.println("Digite o nome do arquivo: :");
		String caminhoArquivo = scan.next();
		System.out.println("Digite a senha do arquivo:");
		String senha = scan.next();
		System.out.println("Digite o código de segurança: ");
		int codigo = scan.nextInt();
		Alfabeto novoAlfabeto = new Alfabeto();
		novoAlfabeto.criaAlfabeto(senha, codigo);
		Criptografar criptografarArquivo = new Criptografar();

		if (!caminhoArquivo.endsWith(".txt")) {
			caminhoArquivo = caminhoArquivo + ".txt";
		}
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		File documentsFolder = fileSystemView.getDefaultDirectory();
		caminhoArquivo = documentsFolder.getAbsolutePath() + "\\" + caminhoArquivo;
		criptografarArquivo.criptografarSenhas(novoAlfabeto, caminhoArquivo);

	}

	private void descriptografarSenha() throws Exception {
		System.out.println("Digite o nome original do arquivo: ");
		String caminhoArquivo = scan.next();
		System.out.println("Digite a senha do arquivo:");
		String senha = scan.next();
		System.out.println("Digite o código de segurança: ");
		int codigo = scan.nextInt();
		Alfabeto novoAlfabeto = new Alfabeto();
		novoAlfabeto.criaAlfabeto(senha, codigo);

		if (!caminhoArquivo.endsWith("_encriptado.txt")) {
			caminhoArquivo = caminhoArquivo + "_encriptado.txt";
		} else if (!caminhoArquivo.endsWith(".txt")) {
			caminhoArquivo = caminhoArquivo + ".txt";
		} else if (!caminhoArquivo.endsWith("_encriptado")) {
			caminhoArquivo = caminhoArquivo + "_encriptado.txt";
		}
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		File documentsFolder = fileSystemView.getDefaultDirectory();
		caminhoArquivo = documentsFolder.getAbsolutePath() + "\\" + caminhoArquivo;
		Criptografar descriptografar = new Criptografar();

		System.out.println(descriptografar.descriptografarSenhas(novoAlfabeto, caminhoArquivo, true));
		System.out.println("\nArquivo desencriptado com sucesso.");

	}
}
