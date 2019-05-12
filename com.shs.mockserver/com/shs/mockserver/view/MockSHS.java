package com.shs.mockserver.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.shs.commons.model.LBTitle;

public class MockSHS extends JFrame {
	private static final int WIDTH = 820;
	private static final int HIGH = 820;
	private JButton bStart= new JButton("Start");
	private JButton bStop= new JButton("Stop");
	private LBTitle screenServer = new LBTitle("Wait ...");
	private JPanel pLeft;
	private JPanel pRight;
	private LBTitle lbSignals;
	private LBTitle lbScenarios;
	private JPanel pSignals;
	private JPanel pScenarios;

	public MockSHS() {
		super("Mock Sever SHS");
		this.setSize(new Dimension(HIGH, WIDTH));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		//North
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
		pRight = new JPanel(new BorderLayout());
		//pLeft
		lbSignals = new LBTitle("Signals");
		pLeft.add(lbSignals, BorderLayout.NORTH);
		pSignals = new JPanel(new BorderLayout());
		JScrollPane scroller = new JScrollPane(pSignals, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//pRigh
		lbScenarios = new LBTitle("Scenarios");
		pRight.add(lbScenarios, BorderLayout.NORTH);
		pScenarios = new JPanel();
		JScrollPane scrollerSc = new JScrollPane(pScenarios, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pCenter.add(pRight, BorderLayout.EAST);
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
		return pRight;
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
	
	public void addSignals(String s) {
		pSignals.add(new JLabel(s));
	}
	public void removeSignals() {
		pSignals.removeAll();
		pSignals.revalidate();
	}
	
	public void addScenarios(String s) {
		pScenarios.add(new JLabel(s));
	}
	public void removeScenarios() {
		pScenarios.removeAll();
		pScenarios.revalidate();
	}
	
	public static void main(String[] args) {
		MockSHS mockSHS= new MockSHS();
	}
}
