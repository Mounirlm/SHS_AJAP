package com.shs.mockserver.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.shs.client.view.ColorsApp;
import com.shs.commons.model.LBTitle;
import com.shs.mockserver.model.ServerScenarioAccess;

public class MockSHS extends JFrame {
	private static final int WIDTH = 1520;
	private static final int HIGH = 720;
	private JButton bStart= new JButton("START");
	private JButton bStop= new JButton("STOP");
	private LBTitle screenServer = new LBTitle("Waiting start ...");
	private JPanel pLeft;
	private JPanel pScens;
	private LBTitle lbSignals;
	private LBTitle lbScenarios;
	private JPanel pSignals;
	private JPanel pScenarios;
	private JPanel ptypesSensors;

	public MockSHS() {
		super("Mock Sever SHS");
		this.setSize(new Dimension(WIDTH, HIGH));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		//North
		bStart.setBackground(Color.GREEN);
		bStop.setBackground(Color.RED);
		JPanel pNorth = new JPanel();
		pNorth.setLayout(new BorderLayout());
		JPanel pStartAndStop = new JPanel();
		pStartAndStop.setLayout(new GridLayout(1, 2));
		pStartAndStop.add(bStart);
		pStartAndStop.add(bStop);
		pNorth.add(screenServer, BorderLayout.NORTH);
		pNorth.add(pStartAndStop,  BorderLayout.CENTER);
		
		//Center
		JPanel pCenter = new JPanel(new GridLayout(1, 2));
		pLeft = new JPanel(new BorderLayout());
		pScens = new JPanel(new BorderLayout());
		//pLeft
		lbSignals = new LBTitle("Signals");
		pSignals = new JPanel(new GridLayout(ServerScenarioAccess.getROWS_VIEWS(), 1));
		JScrollPane scroller = new JScrollPane(pSignals, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pLeft.add(lbSignals, BorderLayout.NORTH);
		pLeft.add(scroller, BorderLayout.CENTER);

		//pR
		JPanel pR = new JPanel(new GridLayout(2, 1));
		//pType 
		LBTitle lbTypes= new LBTitle("Type Sensors");
		JPanel pTypes = new JPanel(new BorderLayout()); 
		ptypesSensors = new JPanel(new GridLayout(ServerScenarioAccess.getROWS_VIEWS(), 1));
		JScrollPane scrollerType = new JScrollPane(ptypesSensors, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pTypes.add(lbTypes, BorderLayout.NORTH);
		pTypes.add(scrollerType, BorderLayout.CENTER);
		//pScenarios
		lbScenarios = new LBTitle("Scenarios");
		pScenarios = new JPanel(new GridLayout(ServerScenarioAccess.getROWS_VIEWS(), 1));
		JScrollPane scrollerSc = new JScrollPane(pScenarios, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pScens.add(lbScenarios, BorderLayout.NORTH);
		pScens.add(scrollerSc, BorderLayout.CENTER);
		
		pR.add(pScens, BorderLayout.CENTER);
		pR.add(pTypes, BorderLayout.NORTH);
		
		
		
		pCenter.add(pR, BorderLayout.EAST);
		pCenter.add(pLeft, BorderLayout.WEST);
		
		this.add(pNorth, BorderLayout.NORTH);
		this.add(pCenter, BorderLayout.CENTER);
		this.setVisible(true);

	}

	public JButton getbStart() {
		return bStart;
	}

	public JButton getbStop() {
		return bStop;
	}

	public JLabel getScreenServer() {
		return screenServer;
	}

	public JPanel getpLeft() {
		return pLeft;
	}

	public JPanel getpRight() {
		return pScens;
	}

	public LBTitle getLbSignals() {
		return lbSignals;
	}

	public LBTitle getLbScenarios() {
		return lbScenarios;
	}

	public JPanel getpSignals() {
		return pSignals;
	}

	public JPanel getpScenarios() {
		return pScenarios;
	}
	
	public void addSignals(String s, Color c) {
		JLabel lb = new JLabel(s);
		lb.setForeground(c);
		lb.setFont(new Font("Arial", Font.BOLD, 16));
		pSignals.add(lb);
		pSignals.revalidate();
	}
	public void removeSignals() {
		pSignals.removeAll();
		pSignals.revalidate();
	}
	
	public void addScenarios(String s) {
		JLabel lb = new JLabel(s);
		lb.setForeground(Color.BLACK);
		lb.setFont(new Font("Arial", Font.BOLD, 16));

		pScenarios.add(lb);
		pScenarios.revalidate();
	}
	public void removeScenarios() {
		pScenarios.removeAll();
		pScenarios.revalidate();
	}

	public void setScreenServer(String mess, Color color) {
		this.screenServer.setText(mess);
		this.screenServer.setBackground(color);
		
	}
	
	public void cleanViews() {
		removeScenarios();
		removeSignals();
		removePtypesSensors();
	}

	

	public static int getHigh() {
		return HIGH;
	}

	public JPanel getPtypesSensors() {
		return ptypesSensors;
	}
	
	public void addPtypesSensors(String s) {
		JLabel lb = new JLabel(s);
		lb.setForeground(Color.BLACK);
		lb.setFont(new Font("Arial", Font.BOLD, 16));

		ptypesSensors.add(lb);
		ptypesSensors.revalidate();
	}
	public void removePtypesSensors() {
		ptypesSensors.removeAll();
		ptypesSensors.revalidate();
	}
}
