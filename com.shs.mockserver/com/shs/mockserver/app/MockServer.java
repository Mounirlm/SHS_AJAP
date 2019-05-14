package com.shs.mockserver.app;

import com.shs.mockserver.controller.MockController;
import com.shs.mockserver.view.MockSHS;

public class MockServer {

	public static void main(String[] args) {
		MockSHS view = new MockSHS();
		MockController	app = new MockController(view);
	}

}
