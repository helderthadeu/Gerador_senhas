package com.interfaces;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Fontes {
	private File fontFile;
	private Font fonttmp;
	private Font fontGrande;
	private Font fontMedia;
	private Font fontPequena;

	Fontes() {
		this.setFontGrande();
		this.setFontMedia();
		this.setFontPequena();

	}
	
	
	private void setFontGrande() {
		fontFile = new File("src\\Files\\FonteTitulos.ttf");
		try {
			fonttmp = Font.createFont(Font.TRUETYPE_FONT, fontFile);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		fontGrande = fonttmp.deriveFont(Font.PLAIN, 40);
	}

	private void setFontMedia() {
//		fontFile = new File("src\\Files\\FonteBotoes.ttf");
		try {
			fonttmp = Font.createFont(Font.TRUETYPE_FONT, fontFile);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		fontMedia = fonttmp.deriveFont(Font.PLAIN, 16);
	}
	
	
	
	private void setFontPequena() {
		fontFile = new File("src\\Files\\Inter-Light.ttf");
		try {
			fonttmp = Font.createFont(Font.TRUETYPE_FONT, fontFile);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		fontPequena = fonttmp.deriveFont(Font.BOLD, 14);
		
	}
	public Font getFontGrande() {
		return fontGrande;
	}
	public Font getFontMedia() {
		return fontMedia;
	}
	public Font getFontPequena() {
		return fontPequena;
	}
}
