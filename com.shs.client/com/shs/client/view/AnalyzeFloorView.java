package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AnalyzeFloorView extends JPanel {
	
	public AnalyzeFloorView() {
		super();
		this.setLayout(new BorderLayout());
		LBTitle lbTitle = new LBTitle("Analyze Floor");
		this.add(lbTitle, BorderLayout.NORTH);
		JPanel pCenter = new JPanel(new GridLayout(6, 1));
		this.add(pCenter);
		
		//pFloor
		JPanel pSelect = new JPanel(new GridLayout(1, 3));
		Integer[] floorList = {0,1,2};
		String[] monthList = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		Integer[] yearList = {2019,2020};
		JComboBox<Integer> floorComboBox = new JComboBox<Integer>(floorList);
		JComboBox<String> monthComboBox = new JComboBox<String>(monthList);
		JComboBox<Integer> yearComboBox = new JComboBox<Integer>(yearList);
		pSelect.add(floorComboBox);
		pSelect.add(monthComboBox);
		pSelect.add(yearComboBox);
		pCenter.add(pSelect);
		
		//pDate
		JPanel pAlert = new JPanel(new GridLayout(1,2));
		JLabel labelAlert1 = new JLabel("Number of alerts",SwingConstants.CENTER);
		JLabel labelAlert2 = new JLabel("5 Alertes dont 3 fausses",SwingConstants.CENTER);
		pAlert.add(labelAlert1);
		pAlert.add(labelAlert2);
		pCenter.add(pAlert);
	}
}
