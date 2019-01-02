package com.blueone.controller;

import com.blueone.model.SHSModel;
import com.blueone.view.SHSView;
 
public class SHSController {
	 private SHSModel shsModel;
	 private SHSView shsView;
	 
	 public SHSController(SHSModel m, SHSView v) {
	  shsModel = m;
	  shsView = v;
	 
	 }
	 
	 public void start() {
		 shsView.printScreen("Hello World");
	 }
 
}