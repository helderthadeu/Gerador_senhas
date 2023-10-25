
public class Alfabeto {

	private StringBuilder letras = new StringBuilder();

	private String Maiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String Minusculas = "abcdefghijklmnopqrstuvwxyz";
	private String Numeros = "0123456789";
	private String Especiais = "!@#$%^&*()-_=+\\\\/~?";

	public Alfabeto() {
	}

	public Alfabeto(boolean letraMaiuscula, boolean letraMinuscula, boolean numeros, boolean caracterEspecial) {
		if (letraMaiuscula) {
			letras.append(Maiusculas);
		}
		if (letraMinuscula) {
			letras.append(Minusculas);
		}
		if (numeros) {
			letras.append(Numeros);
		}
		if (caracterEspecial) {
			letras.append(Especiais);
		}
	}
	
	
	
	public void criaAlfabeto(String senha, int incremento) {
		int tempnum = 0;
		String chave = eleminaRepeticoes(senha);
		System.out.println("Chave sem repetições: " + chave);
		while (letras.length() < 127) {
			for (int i = 0; i < chave.length(); i++) {
				int tempval = chave.charAt(i) + incremento + tempnum;
				if (tempval > 126) {
					tempval-=93;
				}
				if(tempnum>254) {tempnum=0;}
				
				
				letras.append((char) tempval);
			}
			tempnum += chave.length();
			String temp = eleminaRepeticoes(letras.toString());
			letras.delete(0, letras.length());
			letras.append(temp);
		}
		
	}

	public String eleminaRepeticoes(String senha) {
		StringBuilder tempchave = new StringBuilder();
		for (int i = 0; i < senha.length(); i++) {
			int cont = 0;
			for (int j = 0; j < i+1; j++) {
				if (senha.charAt(i) == senha.charAt(j)) {
					cont++;
				}
			}
			if(cont<=1) {
				tempchave.append(senha.charAt(i));
			}
		}
		
		return tempchave.toString();
	}
	
	public String getLetras() {

		return letras.toString();
	}
	
	
	
}
