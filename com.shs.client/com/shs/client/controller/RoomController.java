package com.shs.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;

import com.shs.client.model.ServerHandler;
import com.shs.client.view.SHSView;
import com.shs.commons.model.Room;

public class RoomController implements ActionListener{
	 private SHSView view;
	 private ServerHandler servH;
	 
	 public RoomController(SHSView v) throws IOException {
		 this.view = v;
		 view.addRoomMenuListner(this);
		 view.addJBInsertListner(jbInsert);
		 view.addJBSearchListner(jbSearch);
		 view.addJBUpdateListner(jbUpdate);
		 view.addJBDeleteListner(jbDelete);
		 servH  = new ServerHandler();
	 }
	 
	 ActionListener jbInsert = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] form = new String[2];
				form[0] = view.getJtfCreate(0).getText();
				form[1] = view.getJtfCreate(1).getText();
				try {
					insert(form);
					view.getpApp().getSupRoomView().setCreateTitle("Inserted new secured room with success");
					
				} catch (Exception e1) {
					view.getpApp().getSupRoomView().setCreateTitle(e1.getMessage());
				}
				
			}
		};
		
		ActionListener jbSearch = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] form = new String[3];
				form[0] = view.getJtfRudSearch(0).getText();
				form[1] = view.getJtfRudSearch(1).getText();
				form[2] = view.getJtfRudSearch(2).getText();
				try {
					search(form);
					view.getpApp().getSupRoomView().getRudView().getSearchView().getFormView().setTitle("Search room with success");
					
				} catch (Exception e1) {
					view.getpApp().getSupRoomView().getRudView().getSearchView().getFormView().setTitle(e1.getMessage());
				}
				
			}

		};
		
		ActionListener jbUpdate = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] form = new String[3];
				form[0] = view.getJtfRudUpdate(0).getText();
				form[1] = view.getJtfRudUpdate(1).getText();
				form[2] = view.getJtfRudUpdate(2).getText();
				try {
					update(form);
					view.getpApp().getSupRoomView().getRudView().getUpdateView().getFormView().setTitle("Updated room with success");
					
				} catch (Exception e1) {
					view.getpApp().getSupRoomView().getRudView().getUpdateView().getFormView().setTitle(e1.getMessage());
				}
				
			}

		};
		
		ActionListener jbDelete = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 0;
				if(e.getSource() instanceof JButton) {
					id = view.getpApp().getSupRoomView().getRudView().getReadView().getIdTupleByButton(e.getSource());
				}
				
				try {
					System.out.println(id);
					delete(id);
					view.getpApp().getSupRoomView().getRudView().getReadView().getTitle().setText("Deleted room with success");
					
				} catch (Exception e1) {
					view.getpApp().getSupRoomView().getRudView().getReadView().getTitle().setText(e1.getMessage());
				}
				
			}

		};
		
		
		private void search(String[] form) throws Exception {
			if(form[0].isEmpty() && form[1].isEmpty() && form[2].isEmpty())
				throw new Exception("Empty");
			if(!form[0].isEmpty()) {
				if(!isInteger(form[0]))
				throw new Exception("ID number must be a number");
			}
			
			if(!form[1].isEmpty()){
				if (isInteger(Character.toString(form[1].charAt(0))))
				throw new Exception("Room type can't start by a number");
			}
			if(!form[2].isEmpty()) {
				if(!isInteger(form[2]))
					throw new Exception("Floor number must be a number");
			}
			
			
			
			Room room = new Room();
			if(!form[0].isEmpty())
				room.setId(Integer.valueOf(form[0]));
			if(!form[1].isEmpty())
				room.setType_room(form[1]);
			if(!form[2].isEmpty())
				room.setFloor(Integer.valueOf(form[2]));
			
			//System.out.println(servH.searchObjectToServer(room));
			
		}
		
	 public void delete(int id) {
			// TODO Auto-generated method stub
			
		}

	public void update(String[] form) throws Exception {
		 	if(form[0].isEmpty() && form[1].isEmpty() && form[2].isEmpty())
				throw new Exception("Empty");
		 	if(!isInteger(form[0]))
				throw new Exception("ID number must be a number");
			if (isInteger(Character.toString(form[1].charAt(0))))
				throw new Exception("Room type can't start by a number");
			if(!isInteger(form[2]))
				throw new Exception("Floor number must be a number");
			
			Room room = new Room();
			room.setId(Integer.parseInt(form[0]));
			room.setType_room(form[1]);
			room.setFloor(Integer.parseInt(form[2]));
			//System.out.println(room);
			//send to server
			//System.out.println(servH.insertObjectToServer(room));
			
		}

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
 
