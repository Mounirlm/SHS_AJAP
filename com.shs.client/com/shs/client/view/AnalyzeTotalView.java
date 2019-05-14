package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JPanel;

import com.shs.commons.model.LBTitle;

public class AnalyzeTotalView extends JPanel implements AnalyzePanel{
	public AnalyzeTotalView() {
		super();
		this.setLayout(new BorderLayout());
		LBTitle lbTitle = new LBTitle("Analyze Wing");
		this.add(lbTitle, BorderLayout.NORTH);
	}

	@Override
	public void updateInfoGUI(Map<String, Integer> indicators) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSelectComboBoxListener(ActionListener actionComboBox) {
		// TODO Auto-generated method stub
		
	}
}
