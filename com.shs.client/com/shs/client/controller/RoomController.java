package com.shs.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.shs.client.model.ServerHandler;
import com.shs.client.view.SHSView;
import com.shs.commons.model.Room;

public class RoomController implements ActionListener{
	 private SHSView view;
	 private ServerHandler servH;
	 
	 public RoomController(SHSView v) throws IOException {
		 this.view = v;
		 servH  = new ServerHandler();
		 view.addRoomMenuListner(this);
		 view.addJBInsertListner(jbInsert);
		 view.addJBSearchListner(jbSearch);
		 view.addJBUpdateListner(jbUpdate);
		 view.addJBDeleteListner(jbDelete);
		
		 setDisplayView();//get all rooms in display view
		 
	 }
	 
	 ActionListener jbInsert = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] form = new String[3];
				form[0] = view.getJtfCreate(0).getText();
				form[1] = view.getJtfCreate(1).getText();
				form[2] = view.getJtfCreate(2).getText();
				
				try {
					String message =null;
					message=insert(form);
					String[] m = message.split("-");
					if(m[2].equals("succusful"))
						JOptionPane.showMessageDialog(null, message, "Inserted", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, message, "Insertion Error", JOptionPane.ERROR_MESSAGE);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Communication Error ", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		};
		
		ActionListener jbSearch = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] form = new String[4];
				form[0] = view.getJtfRudSearch(0).getText();
				form[1] = view.getJtfRudSearch(1).getText();
				form[2] = view.getJtfRudSearch(2).getText();
				form[3] = view.getJtfRudSearch(3).getText();
				
				
				String choix = e.getActionCommand();
				String message =null;
				List<Room> rooms=new ArrayList<>();
				try {
					if(choix.equals("RESEARCH")) {
						rooms=(List<Room>)search(form);
					}
					else {
						rooms=(List<Room>)searchAll("Room");
					}
					
					if(rooms.isEmpty()) {
						message= "Sorry no room(s) have been found";
						JOptionPane.showMessageDialog(null, message, "Research Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						message="room(s) found succesfully";
						view.getpApp().getSupRoomView().getRudView().getReadView().setView(rooms);
						JOptionPane.showMessageDialog(null, message, "Research succesful", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Communication Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}

		};
		
		ActionListener jbUpdate = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] form = new String[4];
				form[0] = view.getJtfRudUpdate(0).getText();
				form[1] = view.getJtfRudUpdate(1).getText();
				form[2] = view.getJtfRudUpdate(2).getText();
				form[3] = view.getJtfRudUpdate(3).getText();
				try {
					String message =null;
					message=update(form);
					String[] m = message.split("-");
					if(m[2].equals("succusful"))
						JOptionPane.showMessageDialog(null, message, "Updated", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, message, "Update Error", JOptionPane.ERROR_MESSAGE);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Communication Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}

		};
		
		ActionListener jbDelete = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					String id = view.getJtfRudDelete(0).getText();
					String choix = e.getActionCommand();
					String message =null;
				try {
					if(choix.equals("DELETE")) 
						message=delete(id);	
					else {
						int rep = JOptionPane.showConfirmDialog(null, "Are you sure you want to purge Room table", "Puge Room table?", JOptionPane.OK_CANCEL_OPTION);
						if(rep == JOptionPane.OK_OPTION){
							message=deleteAll("Room");
						}
						
					}
					
					if(message!=null) {
						String[] m = message.split("-");
						if(m[2].equals("succusful"))					
							JOptionPane.showMessageDialog(null, message, "Deleted", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, message, "Delete Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Communication Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
			}

		};
		
		
		private List<Room> search(String[] form) throws Exception {
			if(form[0].isEmpty() && form[1].isEmpty() && form[2].isEmpty()&&form[3].isEmpty())
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
			if(!form[3].isEmpty()) {
				if(!isInteger(form[3]))
					throw new Exception("Room number must be a number");
			}
			
			
			
			Room room = new Room();
			if(!form[0].isEmpty())
				room.setId(Integer.valueOf(form[0]));
			if(!form[1].isEmpty())
				room.setType_room(form[1]);
			if(!form[2].isEmpty())
				room.setFloor(Integer.valueOf(form[2]));
			if(!form[3].isEmpty())
				room.setRoom_number(Integer.valueOf(form[3]));
			
			return (List<Room>) (Object) servH.searchObjectToServer(room);
			
		}
		
	 protected List<Room> searchAll(String type) throws IOException {
		 return (List<Room>) (Object) servH.SearchAll(type);
			
		}

	protected String deleteAll(String type) throws IOException {
			return servH.deleteAll(type);
			
		}

	public String delete(String id) throws Exception {
		if(id.isEmpty())
			throw new Exception("Empty");
	 	if(!isInteger(id))
			throw new Exception("ID number must be a number");
			
	 	Room room = new Room();
	 	room.setId(Integer.valueOf(id));
	 	return servH.delete(room);
		}

	public String update(String[] form) throws Exception {
		 	if(form[0].isEmpty() || form[1].isEmpty() || form[2].isEmpty()|| form[3].isEmpty())
				throw new Exception("Empty");
		 	if(!isInteger(form[0]))
				throw new Exception("ID number must be a number");
			if (isInteger(Character.toString(form[1].charAt(0))))
				throw new Exception("Room type can't start by a number");
			if(!isInteger(form[2]))
				throw new Exception("Floor number must be a number");
			if(!isInteger(form[3]))
				throw new Exception("Room number must be a number");
			
			Room room = new Room();
			room.setId(Integer.parseInt(form[0]));
			room.setType_room(form[1]);
			room.setFloor(Integer.parseInt(form[2]));
			room.setRoom_number(Integer.parseInt(form[3]));
			//System.out.println(room);
			//send to server
			return servH.updateObjectToServer(room);
			
		}

	public String insert(String[] form) throws Exception {
			if(form[0].isEmpty() || form[1].isEmpty()|| form[2].isEmpty())
				throw new Exception("Empty");
			if (isInteger(Character.toString(form[0].charAt(0))))
				throw new Exception("Room type can't start by a number");
			if(!isInteger(form[1]))
				throw new Exception("Floor number must be a number");
			if(!isInteger(form[2]))
				throw new Exception("Room number must be a number");
			
			Room room = new Room();
			room.setType_room(form[0]);
			room.setFloor(Integer.parseInt(form[1]));
			room.setRoom_number(Integer.parseInt(form[2]));
			
			return servH.insertObjectToServer(room);
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
		
		public void setDisplayView() throws IOException {
			try {
			view.getpApp().getSupRoomView().getRudView().getReadView().setView((List<Room>)searchAll("Room"));
			}catch(IOException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Communication Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		

	}
 
