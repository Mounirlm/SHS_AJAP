package com.shs.client.view.sensor;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.shs.client.view.ColorsApp;

public class SmokeSensorView extends SensorTypeGenericView {

	private int sizeLB=15;
	@Override
	public void display() {

		JTextField textField = new JTextField();
        textField.setBounds(128, 28, 86, 20);
        textField.setColumns(10);
         
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(65, 31, 46, 14);
		this.add(textField);
		this.add(lblName);
	}

}
