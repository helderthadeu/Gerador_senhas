package com.geradores;

public class FuncoesAuxiliares {
	static public Boolean jahPossui(String str) {
		for (int i = str.length() - 2; i > 0; i--) {
			if (str.charAt(i) == str.charAt(str.length() - 1)) {
				return false;
			}
		}
		return false;
	}

}
