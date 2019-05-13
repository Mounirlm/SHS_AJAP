package com.shs.client.view.sensor;

import javax.swing.JPanel;

public abstract class SensorTypeGenericView extends JPanel {
	
	public SensorTypeGenericView() {
		super();
		display();
	}
	
	public abstract void display();
}
