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

public class AnalyzeController{

	private SHSView view;
	private AnalyzeView analyzeView;
	private AnalyzeFloorView analyzeFloorView;
	private int floor;
	private String month;
	private int year;
	
	public AnalyzeController(SHSView v) throws UnknownHostException, IOException {
		this.view = v;
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
	        Map<String,Integer> indicators=searchIndicators(floor,month,year);
			
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
	        year= (int)cb.getSelectedItem();
			
		}};

	protected Map<String, Integer> searchIndicators(int floor2, String month2, int year2) {
		Map<String, Integer> indicators = new HashMap<String,Integer>();
		
		return null;
	}

}
