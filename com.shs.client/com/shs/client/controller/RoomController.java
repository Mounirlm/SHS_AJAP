package com.shs.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import com.shs.client.model.ServerHandler;
import com.shs.client.view.SHSView;
import com.shs.commons.model.Room;

public class RoomController implements ActionListener{
	 private SHSView view;
	 private static ServerHandler servH;
	 
	 public RoomController(SHSView v) throws IOException {
		 this.view = v;
		 view.addRoomMenuListner(this);
		 view.addJBInsertListner(jbInsert);
		 servH  = new ServerHandler();
	 }
	 
	 ActionListener jbInsert = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] form = new String[2];
				form[0] = view.getJtf(0).getText();
				form[1] = view.getJtf(1).getText();
				try {
					insert(form);
					view.setCreateTitle("Inserted new secured room with success");
					
				} catch (Exception e1) {
					view.setCreateTitle(e1.getMessage());
				}
				
			}
		};
	
	 public void insert(String[] form) throws Exception {
			if(form[0].isEmpty() || form[1].isEmpty())
				throw new Exception("Empty");
			if (isInteger(Character.toString(form[0].charAt(0))))
				throw new Exception("Room type can't start by a number");
			if(!isInteger(form[1]))
				throw new Exception("Floor number must be a number");
			
			Room room = new Room();
			room.setType_room(form[0]);
			room.setFloor(Integer.parseInt(form[1]));
			//System.out.println(room);
			//send to server
			System.out.println(servH.insertObjectToServer(room));
		}

		private boolean isInteger(String s) {
			try {
				Integer.parseInt(s);
			} catch (Exception e) {
				return false;
			}
			return true;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JButton ){
				String choix = e.getActionCommand();
				switch (choix) {
				case "CREATE":
					view.setCardRoom("create");
					break;
					
				case "READ - UPDATE - DELETE":
					view.setCardRoom("rud");
					break;

				default:
					break;
				}
			}
			
		}
		
		
		

	}
 
