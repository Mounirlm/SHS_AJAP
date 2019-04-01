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
		 servH  = new ServerHandler();
	 }
	
	 public static void insert(String[] form) throws Exception {
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

		private static boolean isInteger(String s) {
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
 
