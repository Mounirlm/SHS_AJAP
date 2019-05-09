package com.shs.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.shs.client.model.RoomServerHandler;
import com.shs.client.model.UserServerHandler;
import com.shs.client.view.AnalyzeFloorView;
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
	private AnalyzeFloorView analyzeFloorView;

	public AnalyzeController(SHSView v) throws UnknownHostException, IOException {
		this.view = v;
		RoomServH  = new RoomServerHandler();
		UserServH = new UserServerHandler();
		AlertServH = new AlertServHandler();
		ResidentServH = new ResidentServHandler();
		SensorServH = new SensorServHandler();
		
		analyzeView = view.getpApp().getAnalyzeView();
		analyzeView.addTopBarButtonListener(actionTopBarButton);
		
		analyzeFloorView = analyzeView.getFloorView();
		
		analyzeFloorView.addSelectComboBoxListener(actionFloorComboBox, actionMonthComboBox, actionYearComboBox);
	}
	
	
	ActionListener actionTopBarButton = new ActionListener() {
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
	};
	
	ActionListener actionFloorComboBox = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
	        int floor= (int)cb.getSelectedItem();
	        System.out.println(floor);
			
		}
	};
	
	ActionListener actionMonthComboBox = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
	        String month= (String)cb.getSelectedItem();
			
		}};
	
	ActionListener actionYearComboBox = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
	        int year= (int)cb.getSelectedItem();
			
		}};

}
