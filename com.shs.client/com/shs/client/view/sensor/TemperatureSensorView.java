package com.shs.client.view.sensor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.shs.commons.model.Type_Sensor;

public class TemperatureSensorView extends SensorTypeGenericView {

	public TemperatureSensorView(Type_Sensor typeSensor) throws IOException, SQLException, Exception {
		super(typeSensor);
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		
		JTextField textField = new JTextField();
        textField.setBounds(128, 28, 86, 20);
        textField.setColumns(10);
         
        JLabel lblTempMax = new JLabel("Temperature maximale");
        lblTempMax.setBounds(65, 31, 46, 14);
        this.add(textField);
		this.add(lblTempMax);

		JTextField textField_1 = new JTextField();
        textField_1.setBounds(128, 65, 86, 20);
        textField_1.setColumns(10);
		
        JLabel lblTempMin = new JLabel("Temperature minimal");
        lblTempMin.setBounds(65, 68, 46, 14);
        this.add(textField_1);
		this.add(lblTempMin);
		
		JLabel lblAlerte = new JLabel("Nombre d'alertes");
        lblAlerte.setBounds(65, 68, 46, 14);
        this.add(lblAlerte);
         
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("1");
        comboBox.addItem("2");
        comboBox.addItem("3");
        comboBox.addItem("4");
        comboBox.addItem("5");
       
        
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        comboBox.setBounds(128, 65, 86, 20);
        this.add(comboBox);
         
         
        JButton btnConf = new JButton("Configurer");
         
        btnConf.setBackground(Color.BLUE);
        btnConf.setForeground(Color.MAGENTA);
        btnConf.setBounds(65, 387, 89, 23);
        this.add(btnConf);
		   
        
	}

}
