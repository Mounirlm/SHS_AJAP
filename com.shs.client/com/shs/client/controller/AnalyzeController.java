package com.shs.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.shs.client.view.AnalyzeFloorView;
import com.shs.client.view.AnalyzeView;
import com.shs.client.view.SHSView;
import com.shs.commons.model.AlertClientHandler;
import com.shs.commons.model.SensorClientHandler;

public class AnalyzeController{
	
	private SHSView view;
	private AnalyzeView analyzeView;
	private AnalyzeFloorView analyzeFloorView;
	private int floor;
	private String month;
	private int year;
	private AlertClientHandler alertH;
	private SensorClientHandler sensorH;
	
	public AnalyzeController(SHSView v) throws UnknownHostException, IOException {
		this.view = v;
		
		alertH = new AlertClientHandler();
		sensorH = new SensorClientHandler();
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
			updateInfoFromComboBox();
	        System.out.println(floor);
	        Map<String,Integer> indicators=searchIndicators(floor,month,year);
			
		}
	};
	
	ActionListener actionMonthComboBox = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			updateInfoFromComboBox();
			
		}};
	
	ActionListener actionYearComboBox = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			updateInfoFromComboBox();
		}};

	protected Map<String, Integer> searchIndicators(int floor2, String month2, int year2) {
		Map<String, Integer> indicators = new HashMap<String,Integer>();
		
		int nAlerts=0;
		
		try {
			nAlerts = alertH.searchAlertByFloor(floor2,month2,year2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		indicators.put("nAlerts", nAlerts);
		System.out.println(indicators.get("nAlerts"));
		return indicators;
	}

	protected void updateInfoFromComboBox() {
		floor = (int) analyzeFloorView.floorComboBox.getSelectedItem();
		month = (String) analyzeFloorView.monthComboBox.getSelectedItem();
		year = (int) analyzeFloorView.yearComboBox.getSelectedItem();
	}

}
