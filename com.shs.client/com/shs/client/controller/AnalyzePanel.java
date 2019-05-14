package com.shs.client.controller;

import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JComboBox;

public interface AnalyzePanel {

	void updateInfoGUI(Map<String, Integer> indicators);

	void addSelectComboBoxListener(ActionListener actionFloorComboBox, ActionListener actionMonthComboBox,
			ActionListener actionYearComboBox);
	
	
}
