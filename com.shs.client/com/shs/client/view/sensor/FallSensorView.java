package com.shs.client.view.sensor;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class FallSensorView extends SensorTypeGenericView {

	@Override
	public void display() {
		// TODO Auto-generated method stub
		JTextField textField = new JTextField();
        textField.setBounds(128, 28, 86, 20);
        textField.setColumns(10);
         
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(65, 31, 46, 14);
		this.add(textField);
		this.add(lblName);
	}

}
