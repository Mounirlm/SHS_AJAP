package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

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
	private List<JPanel> pArgs;
	
	
	
	public FormView(String titleView,List<String> cols, List<String> buttons, List<String> labels, String alignement, boolean title) {
		super();
		this.setLayout(new BorderLayout());
		pArgs = new ArrayList<>();
		if(title) {
			//title
			JLabel lbtitle= new JLabel(titleView);
			lbtitle.setOpaque(true);
			lbtitle.setBackground(Color.decode("#afabab"));
			lbtitle.setForeground(cdApp.getBgApp());
			lbtitle.setHorizontalAlignment(JLabel.CENTER);
			lbtitle.setFont(new Font("Arial", Font.BOLD, sizeTitle));
			lbtitle.setBorder(new LineBorder(cdApp.getBgTitle(), 2));
			this.add(lbtitle, BorderLayout.NORTH);
		}
		
		
		//Form
		JPanel pForm;
		if(alignement.equals("h")) 
			pForm = new JPanel(new GridLayout(1,cols.size()+buttons.size()));
		else 
			pForm = new JPanel(new GridLayout(cols.size()+buttons.size(),1));
		
		
		
		for (int i = 0; i < cols.size(); i++) {
			
				JLabel lb =new JLabel(cols.get(i));
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
					if(labels.get(j)==cols.get(i)) {
						labelFound=true;
					}
				}
					if(labelFound) {
						lbVal = new JLabel();
						lbVal.setFont(new Font("Arial", Font.BOLD, sizeVal));
						lbVal.setOpaque(true);
						lbVal.setBackground(cdApp.getBgApp());
						lbVal.setBorder(new LineBorder(Color.BLACK, 1));
						pCol.add(lbVal, BorderLayout.CENTER);
					}else {
						jtf = new JTextField();
						jtf.setFont(new Font("Arial", Font.BOLD, sizeVal));
						pCol.add(jtf, BorderLayout.CENTER);
					
				}
				pArgs.add(pCol);
				pForm.add(pCol);
		
			
		}
		
		for (int i = 0; i < buttons.size(); i++) {
			//Panel Valider
			JPanel pValidate = new JPanel();
			validateButton = new JButton(buttons.get(i));
			validateButton.setBackground(cdApp.getBgThem());
			validateButton.setForeground(cdApp.getBgApp());
			validateButton.setHorizontalAlignment(JLabel.CENTER);
			validateButton.setFont(new Font("Arial", Font.BOLD, sizeButton));
			pValidate.add(buttons.get(i),validateButton);
			pArgs.add(pValidate);
			pForm.add(pValidate);
		}
		
		
		this.add(pForm, BorderLayout.CENTER);
	}
}
