package com.shs.client.view.sensor;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.shs.commons.model.Type_Sensor;

public class SensorTypeView extends JFrame {

	public SensorTypeView(Type_Sensor typeSensor) {
		super();
		this.setTitle("Sensor Configuration");
		this.setBounds(100, 100, 730, 489);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SensorTypeGenericView content = SensorTypeViewFactory.getInstance(typeSensor);
		this.add(content);
	}
}
