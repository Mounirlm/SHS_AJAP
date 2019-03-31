package com.shs.client.view;

import java.awt.Color;

public class ColorsDimApp {
	private int WIDTH =1600 ;
	private int HEIGHT =850 ;
	private int MENU_WIDTH =200 ;
	private Color bgThem = Color.decode("#5b9bd5");
	private Color bgTitle = Color.decode("#1f4e79");
	private Color bgApp = Color.WHITE;
	
	
	public ColorsDimApp() {
		// TODO Auto-generated constructor stub
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}

	public Color getBgThem() {
		return bgThem;
	}

	public void setBgThem(Color bgThem) {
		this.bgThem = bgThem;
	}

	public Color getBgTitle() {
		return bgTitle;
	}

	public void setBgTitle(Color bgTitle) {
		this.bgTitle = bgTitle;
	}

	public Color getBgApp() {
		return bgApp;
	}

	public void setBgApp(Color bgApp) {
		this.bgApp = bgApp;
	}

	public int getMENU_WIDTH() {
		return MENU_WIDTH;
	}

	public void setMENU_WIDTH(int mENU_WIDTH) {
		MENU_WIDTH = mENU_WIDTH;
	}
	
	
}
