package com.shs.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;

import com.shs.client.model.RoomServerHandler;
import com.shs.client.model.UserServerHandler;
import com.shs.client.view.AnalyzeView;
import com.shs.client.view.SHSView;

public class AnalyzeController{

	private SHSView view;
	private RoomServerHandler RoomServH;
	private UserServerHandler UserServH;
	private AlertServHandler AlertServH;
	private ResidentServHandler ResidentServH;
	private SensorServHandler SensorServH;
	private AnalyzeView analyzeView;

	public AnalyzeController(SHSView v) throws UnknownHostException, IOException {
		this.view = v;
		RoomServH  = new RoomServerHandler();
		UserServH = new UserServerHandler();
		AlertServH = new AlertServHandler();
		ResidentServH = new ResidentServHandler();
		SensorServH = new SensorServHandler();
		
		analyzeView = view.getpApp().getAnalyzeView();
		analyzeView.addTopBarButtonListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof JButton ){
					String choice = e.getActionCommand();
					switch (choice) {
					case "Floor":
						analyzeView.setCard("floor");
						break;
						
					case "Wing":
						analyzeView.setCard("wing");
						break;
					case "Total":
						analyzeView.setCard("total");
					default:
						break;
					}
				}
				
			}
		});
	}

}
