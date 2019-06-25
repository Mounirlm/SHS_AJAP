package com.shs.client.view;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.shs.client.model.EquipmentQuoteModel;

public class EquipmentNeed extends JPanel {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField messageText; 
	 private JButton sendButton;
	 
	 
	 public EquipmentNeed() { 
			super();
			this.setLayout(new Miglayout());
			add(new JLabel("Message :"), "cell 0 0");
			messageText = new JTextField();
			sendButton = new JButton(" Make new quotation ");
			add(sendButton, "cell 2 0");
			
	 }
	 
	 private void quotation() {
		 sendButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 sendMessage();
			 }
		 });
		 
		 
	 }
	 
	 public void add(EquipmentQuoteModel message) { 
		  if (message != null) { 
		   message.add(message); 
		  }  
		 } 
}
