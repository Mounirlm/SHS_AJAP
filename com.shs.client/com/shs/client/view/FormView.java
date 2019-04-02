package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class FormView extends JPanel {
	private ColorsDimApp cdApp = new ColorsDimApp();//TODO METTRE EN STATIC
	private JTextField jtf;
	private JLabel lbVal;
	private JButton validateButton;
	private int sizeTitle=30, sizeLB=15, sizeVal=11, sizeButton=20;
	private Map<String, String> pArgs;
	private List<JButton> buttons;
	private LBTitle lbTitle;
	
	
	
	public FormView(String titleView,Map<String, String> tuples, List<String> but, List<String> labels, String alignement, boolean title) {
		super();
		this.setLayout(new BorderLayout());
		pArgs = tuples;
		buttons = new ArrayList<>();
		if(title) {
			//title
			lbTitle =new LBTitle(titleView);
			this.add(lbTitle, BorderLayout.NORTH);
		}
		
		
		//Form
		JPanel pForm;
		if(alignement.equals("h")) 
			pForm = new JPanel(new GridLayout(1,tuples.size()+but.size()));
		else 
			pForm = new JPanel(new GridLayout(tuples.size()+but.size(),1));
		
		
		
		for (Map.Entry<String, String> entry : tuples.entrySet()) {
			
				JLabel lb =new JLabel(entry.getKey());
				lb.setOpaque(true);
				lb.setBackground(cdApp.getBgThem());
				lb.setForeground(cdApp.getBgApp());
				lb.setHorizontalAlignment(JLabel.CENTER);
				lb.setFont(new Font("Arial", Font.BOLD, sizeLB));
				lb.setBorder(new LineBorder(Color.BLACK, 1));
				JPanel pCol = new JPanel(new BorderLayout());
				pCol.add(lb, BorderLayout.NORTH);
				boolean labelFound = false;
				for (int j = 0; j < labels.size(); j++) {
					if(labels.get(j)==entry.getKey()) {
						labelFound=true;
					}
				}
					if(labelFound) {
						lbVal = new JLabel(entry.getValue());
						lbVal.setFont(new Font("Arial", Font.BOLD, sizeVal));
						lbVal.setOpaque(true);
						lbVal.setBackground(cdApp.getBgApp());
						lbVal.setBorder(new LineBorder(Color.BLACK, 1));
						pCol.add(lbVal, BorderLayout.CENTER);
					}else {
						jtf = new JTextField(entry.getValue());
						jtf.setFont(new Font("Arial", Font.BOLD, sizeVal));
						pCol.add(jtf, BorderLayout.CENTER);
					
				}
				pForm.add(pCol);
		
			
		}
		
		
		for (int i = 0; i < but.size(); i++) {
			//Panel Valider
			JPanel pValidate = new JPanel();
			validateButton = new JButton(but.get(i));
			buttons.add(validateButton);
			validateButton.setBackground(cdApp.getBgThem());
			validateButton.setForeground(cdApp.getBgApp());
			validateButton.setHorizontalAlignment(JLabel.CENTER);
			validateButton.setFont(new Font("Arial", Font.BOLD, sizeButton));
			pValidate.add(but.get(i),validateButton);
			pForm.add(pValidate);
		}
		
		
		this.add(pForm, BorderLayout.CENTER);
	}
	
	
	
}
