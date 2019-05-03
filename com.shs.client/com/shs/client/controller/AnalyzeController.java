package com.shs.client.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import com.shs.client.model.RoomServerHandler;
import com.shs.client.model.UserServerHandler;
import com.shs.client.view.SHSView;

public class AnalyzeController {

	private SHSView view;
	private RoomServerHandler RoomServH;
	private UserServerHandler UserServH;
	private AlertServHandler AlertServH;
	private ResidentServHandler ResidentServH;
	private SensorServHandler SensorServH;

	public AnalyzeController(SHSView v) throws UnknownHostException, IOException {
		this.view = v;
		RoomServH  = new RoomServerHandler();
		UserServH = new UserServerHandler();
		AlertServH = new AlertServHandler();
		ResidentServH = new ResidentServHandler();
		SensorServH = new SensorServHandler();
	}

}
