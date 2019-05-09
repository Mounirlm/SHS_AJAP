package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AnalyzeFloorView extends JPanel {
	
	public JComboBox<Integer> floorComboBox;
	public JComboBox<String> monthComboBox;
	public JComboBox<Integer> yearComboBox;

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
		floorComboBox = new JComboBox<Integer>(floorList);
		monthComboBox = new JComboBox<String>(monthList);
		yearComboBox = new JComboBox<Integer>(yearList);
		pSelect.add(floorComboBox);
		pSelect.add(monthComboBox);
		pSelect.add(yearComboBox);
		pCenter.add(pSelect);
		
		//pAlert
		JPanel pAlert = new JPanel(new GridLayout(1,2));
		JLabel labelAlert1 = new JLabel("Alerts",SwingConstants.CENTER);
		JLabel labelAlert2 = new JLabel("5 Alertes dont 3 fausses",SwingConstants.LEFT);
		pAlert.add(labelAlert1);
		pAlert.add(labelAlert2);
		pCenter.add(pAlert);
		
		//pSensor
		JPanel pSensor = new JPanel(new GridLayout(1,2));
		JLabel labelSensor1 = new JLabel("Sensors",SwingConstants.CENTER);
		JLabel labelSensor2 = new JLabel("201 sensors",SwingConstants.LEFT);
		pSensor.add(labelSensor1);
		pSensor.add(labelSensor2);
		pCenter.add(pSensor);
		
		//pErrorSensorr
		JPanel pErrorSensor = new JPanel(new GridLayout(1,2));
		JLabel labelErrorSensor1 = new JLabel("Sensors' Errors",SwingConstants.CENTER);
		JLabel labelErrorSensor2 = new JLabel("201 errors",SwingConstants.LEFT);
		pErrorSensor.add(labelErrorSensor1);
		pErrorSensor.add(labelErrorSensor2);
		pCenter.add(pErrorSensor);
		
		//pResident
		JPanel pResident = new JPanel(new GridLayout(1,2));
		JLabel labelResident1 = new JLabel("Residents",SwingConstants.CENTER);
		JLabel labelResident2 = new JLabel("13 residents",SwingConstants.LEFT);
		pResident.add(labelResident1);
		pResident.add(labelResident2);
		pCenter.add(pResident);
	}
	public void addSelectComboBoxListener(ActionListener actionFloorComboBox, ActionListener actionMonthComboBox, ActionListener actionYearComboBox) {
		floorComboBox.addActionListener(actionFloorComboBox);
		monthComboBox.addActionListener(actionMonthComboBox);
		yearComboBox.addActionListener(actionYearComboBox);
	}
}
