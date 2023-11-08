package com.geradores;

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

	public void criptografarSenhas(Alfabeto novoAlfabeto, String caminhoSenha) throws IOException {

		if (caminhoSenha.endsWith(".txt")) {
			caminhoSenha = caminhoSenha.substring(0, caminhoSenha.length() - 4);
		}
		File fileCr;
		if (Files.exists(Paths.get(caminhoSenha + "_encriptado.txt"))) {
			int cont = 1;
			String temp = caminhoSenha + "_encriptado_" + cont + ".txt";
			while (Files.exists(Paths.get(temp))) {
				cont++;
				temp = caminhoSenha + "_encriptado_" + cont + ".txt";
			}

			fileCr = new File(temp);
		} else {
			fileCr = new File(caminhoSenha + "_encriptado.txt");
		}

		File file = new File(caminhoSenha + ".txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
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
		Files.deleteIfExists(Paths.get(file.toString()));
	}

	public String descriptografarSenhas(Alfabeto novoAlfabeto, String caminhoArquivo, boolean console)
			throws IOException {
		File file;
		if (caminhoArquivo.endsWith(".txt")) {
			caminhoArquivo = caminhoArquivo.substring(0, caminhoArquivo.length() - 4);
		}
		if (Files.exists(Paths.get(caminhoArquivo + "_1.txt")) && console) {
			int cont = 1;
			String temp = caminhoArquivo + "_" + cont + ".txt";
			System.out.println(caminhoArquivo + ".txt");

			while (Files.exists(Paths.get(temp))) {
				if (console) {
					System.out.println(temp);
				}
				cont++;
				temp = caminhoArquivo + "_" + cont + ".txt";
			}

			System.out.println("Selecione o arquivo pelo número(0 para o primeiro): ");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			int tmpCont;
			do {
				tmpCont = scan.nextInt();
				if (tmpCont > cont) {
					System.out.println("Opção Inválida.");
				}
			} while (tmpCont > cont);
			if (tmpCont != 0) {
				temp = caminhoArquivo + "_" + tmpCont + ".txt";
			} else {
				temp = caminhoArquivo + ".txt";
			}

			file = new File(temp);
		} else {
			file = new File(caminhoArquivo + ".txt");
		}

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String tempLinha;
		String alfabeto = novoAlfabeto.getLetras();
		StringBuilder senhas = new StringBuilder();

		while ((tempLinha = reader.readLine()) != null) {
			int tempVal = 0;

			for (int i = 0; i < tempLinha.length(); i++) {
				tempVal = tempLinha.charAt(i);
				for (int j = 0; j < alfabeto.length(); j += 1) {
					int tempnum = alfabeto.charAt(j);

					if (tempVal == tempnum) {
						senhas.append((char) (j + 32));
						break;
					}
				}

			}

		}

		reader.close();
		return senhas.toString();
	}

}
