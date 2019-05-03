package com.shs.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.shs.client.model.RoomServerHandler;
import com.shs.client.view.SHSView;
import com.shs.commons.model.Room;
import com.shs.commons.model.Type_Room;
import com.shs.commons.model.Wing_Room;

public class RoomController implements ActionListener{
	 private SHSView view;
	 private RoomServerHandler servH;
	 
	 public RoomController(SHSView v) throws IOException {
		 this.view = v;
		 this.servH  = new RoomServerHandler();
		 //get types and wings from database
		 List<Wing_Room> wing = servH.selectAllWingRoom();
		 List<Type_Room> types = servH.selectAllTypeRoom();
		 
		 
		 //generate combobox (list) in create, resaerch and update view
		 view.getpApp().getSupRoomView().getCreateView().createCols(types, wing);
		 view.getpApp().getSupRoomView().getRudView().getSearchView().createCols(types, wing);
		 view.getpApp().getSupRoomView().getRudView().getUpdateView().createCols(types, wing);
		 		 
		 //add listners
		 view.getpApp().getSupRoomView().addRoomMenuListner(this);
		 view.getpApp().getSupRoomView().getCreateView().addJBInsertListner(jbInsert);
		 view.getpApp().getSupRoomView().getRudView().getSearchView().getFormView().addJBListner(jbSearch);//lister for select and select all
		 view.getpApp().getSupRoomView().getRudView().getUpdateView().getFormView().addJBListner(jbUpdate);
		 view.getpApp().getSupRoomView().getRudView().getDeleteView().getFormView().addJBListner(jbDelete);
		 
	 }
	 
	 ActionListener jbInsert = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] form = new String[5];
				form[0] = view.getpApp().getSupRoomView().getCreateView().getFormView().getJtf(0).getText();
				form[1] = view.getpApp().getSupRoomView().getCreateView().getFormView().getJtf(1).getText();
				form[2] = view.getpApp().getSupRoomView().getCreateView().getFormView().getJtf(2).getText();
				Type_Room type = (Type_Room) view.getpApp().getSupRoomView().getCreateView().getFormView().getCombo_type().getSelectedItem();
				Wing_Room wing = (Wing_Room) view.getpApp().getSupRoomView().getCreateView().getFormView().getCombo_wing().getSelectedItem();
				form[3] = type.toString();
				form[4] = wing.toString();
				
				try {
					String message =null;
					message=insert(form, type, wing);
					String[] m = message.split("-");
					if(m[2].equals("succusful")) {
						JOptionPane.showMessageDialog(null, message, "Inserted", JOptionPane.INFORMATION_MESSAGE);
						setDisplayView();
					}
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
				String[] form = new String[6];
				form[0] = view.getpApp().getSupRoomView().getRudView().getSearchView().getFormView().getJtf(0).getText();
				form[1] = view.getpApp().getSupRoomView().getRudView().getSearchView().getFormView().getJtf(1).getText();
				form[2] = view.getpApp().getSupRoomView().getRudView().getSearchView().getFormView().getJtf(2).getText();
				form[3] = view.getpApp().getSupRoomView().getRudView().getSearchView().getFormView().getJtf(3).getText();
				Type_Room type = (Type_Room) view.getpApp().getSupRoomView().getCreateView().getFormView().getCombo_type().getSelectedItem();
				Wing_Room wing = (Wing_Room) view.getpApp().getSupRoomView().getCreateView().getFormView().getCombo_wing().getSelectedItem();
				form[4] = type.toString();
				form[5] = wing.toString();
				
				String choix = e.getActionCommand();
				String message =null;
				List<Room> rooms=new ArrayList<>();
				try {
					if(choix.equals("RESEARCH")) {
						rooms= search(form, type, wing);
					}
					else {
						rooms=searchAll();
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
				String[] form = new String[6];
				form[0] = view.getpApp().getSupRoomView().getRudView().getUpdateView().getFormView().getJtf(0).getText();
				form[1] = view.getpApp().getSupRoomView().getRudView().getUpdateView().getFormView().getJtf(1).getText();
				form[2] = view.getpApp().getSupRoomView().getRudView().getUpdateView().getFormView().getJtf(2).getText();
				form[3] = view.getpApp().getSupRoomView().getRudView().getUpdateView().getFormView().getJtf(3).getText();
				
				Type_Room type = (Type_Room) view.getpApp().getSupRoomView().getCreateView().getFormView().getCombo_type().getSelectedItem();
				Wing_Room wing = (Wing_Room) view.getpApp().getSupRoomView().getCreateView().getFormView().getCombo_wing().getSelectedItem();
				form[4] = type.toString();
				form[5] = wing.toString();
				
				try {
					String message =null;
					message=update(form, type, wing);
					String[] m = message.split("-");
					if(m[2].equals("succusful")) {
						JOptionPane.showMessageDialog(null, message, "Updated", JOptionPane.INFORMATION_MESSAGE);
						setDisplayView();
					}
					else
						JOptionPane.showMessageDialog(null, message, "Update Error", JOptionPane.ERROR_MESSAGE);
					
				} catch (Exception e1) {System.out.println("erreur catch "+e1.getMessage());
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Communication Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}

		};
		
		ActionListener jbDelete = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					String id = view.getpApp().getSupRoomView().getRudView().getDeleteView().getFormView().getJtf(0).getText();
					String choix = e.getActionCommand();
					String message =null;
				try {
					if(choix.equals("DELETE")) 
						message=delete(id);	
					else {
						int rep = JOptionPane.showConfirmDialog(null, "Are you sure you want to purge Room table", "Puge Room table?", JOptionPane.OK_CANCEL_OPTION);
						if(rep == JOptionPane.OK_OPTION){
							message=deleteAll();
						}
						
					}
					
					if(message!=null) {
						String[] m = message.split("-");
						if(m[2].equals("succusful")) {					
							JOptionPane.showMessageDialog(null, message, "Deleted", JOptionPane.INFORMATION_MESSAGE);
							setDisplayView();
						}
						else
							JOptionPane.showMessageDialog(null, message, "Delete Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Communication Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
			}

		};
		
		
		private List<Room> search(String[] form, Type_Room type_room, Wing_Room wing_room) throws Exception {
			if(!form[0].isEmpty() && form[1].isEmpty() && form[2].isEmpty()&&form[3].isEmpty()
					&&(form[4].equals("null")) &&(form[5].equals("null")))
			if(!form[0].isEmpty()) {
				if(!isInteger(form[0]))
				throw new Exception("ID number must be a number");
			}
			
			if(!form[1].isEmpty()) {
				if(!isInteger(form[1]))
					throw new Exception("Floor number must be a number");
			}
			if(!form[2].isEmpty()) {
				if(!isInteger(form[2]))
					throw new Exception("Room number must be a number");
			}
			if(!form[3].isEmpty()){
				if(!isInteger(form[3]))
				throw new Exception("M² must be a number");
			}
			
			
			
			Room room = new Room();
			if(!form[0].isEmpty())
				room.setId(Integer.valueOf(form[0]));
			if(!form[1].isEmpty())
				room.setFloor(Integer.valueOf(form[1]));
			if(!form[2].isEmpty())
				room.setRoom_number(Integer.valueOf(form[2]));
			if(!form[3].isEmpty())
				room.setM2(Integer.valueOf(form[3]));
			if (!form[4].equals("null"))
				room.setType_room(type_room);
			if (!form[5].equals("null"))
				room.setWing_room(wing_room);
			
			return servH.searchRoomToServer(room);
			
		}
		
	 protected List<Room> searchAll() throws IOException {
		 return servH.searchAllRoom();
			
		}

	protected String deleteAll() throws IOException {
			return servH.deleteAllRoom();
			
		}

	public String delete(String id) throws Exception {
		if(id.isEmpty())
			throw new Exception("Empty");
	 	if(!isInteger(id))
			throw new Exception("ID number must be a number");
			
	 	Room room = new Room();
	 	room.setId(Integer.valueOf(id));
	 	return servH.deleteRoom(room);
		}

	public String update(String[] form, Type_Room type_room, Wing_Room wing_room) throws Exception {
		if(!form[0].isEmpty() && form[1].isEmpty() && form[2].isEmpty()&&form[3].isEmpty()
				&&(form[4].equals("null")) &&(form[5].equals("null")))
			throw new Exception("A value must be specified for update");
		
		if(form[0].isEmpty()) {
			throw new Exception("ID number must be specified");
		}
		if(!form[0].isEmpty()) {
			if(!isInteger(form[0]))
			throw new Exception("ID number must be a number");
		}
		
		if(!form[1].isEmpty()) {
			if(!isInteger(form[1]))
				throw new Exception("Floor number must be a number");
		}
		if(!form[2].isEmpty()) {
			if(!isInteger(form[2]))
				throw new Exception("Room number must be a number");
		}
		if(!form[3].isEmpty()){
			if(!isInteger(form[3]))
			throw new Exception("M² must be a number");
		}
		
		
		Room room = new Room();
		if(!form[0].isEmpty())
			room.setId(Integer.valueOf(form[0]));
		if(!form[1].isEmpty())
			room.setFloor(Integer.valueOf(form[1]));
		if(!form[2].isEmpty())
			room.setRoom_number(Integer.valueOf(form[2]));
		if(!form[3].isEmpty())
			room.setM2(Integer.valueOf(form[3]));
		if (!form[4].equals("null"))
			room.setType_room(type_room);
		if (!form[5].equals("null"))
			room.setWing_room(wing_room);
		
			//send to server
			return servH.updateRoomToServer(room);
			
		}

	public String insert(String[] form, Type_Room type_room, Wing_Room wing_room) throws Exception {
		Integer room_number = null;
		if(!form[0].isEmpty() && form[1].isEmpty() && form[2].isEmpty()&&form[3].isEmpty()
				&&(form[4].equals("null")) &&(form[5].equals("null")))
			throw new Exception("A value must be specified for update");
			if(!isInteger(form[0]))
				throw new Exception("Floor number must be a number");
			if(!form[1].isEmpty()){
				if(!isInteger(form[1]))
					throw new Exception("Room number must be a number");
				else
					room_number = Integer.parseInt(form[1]);
			}
			if (!isInteger(form[2]))
				throw new Exception("M² msut be a number");
			if (form[3].equals("null"))
				throw new Exception("A type have to be selected");
			if (form[4].equals("null"))
				throw new Exception("A wing have to be selected");
			
			
			Room room = new Room();
			room.setFloor(Integer.parseInt(form[0]));
			room.setRoom_number(room_number);
			room.setM2(Integer.parseInt(form[2]));
			room.setType_room(type_room);
			room.setWing_room(wing_room);
			
			return servH.insertRoomToServer(room);
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
					view.getpApp().getSupRoomView().setCard("create");
					break;
					
				case "READ - UPDATE - DELETE":
					try {
						setDisplayView();//get all rooms in display view
						view.getpApp().getSupRoomView().setCard("rud");
					} catch (IOException e1) {}
					break;

				default:
					break;
				}
			}
			
		}
		
		public void setDisplayView() throws IOException {
			try {
			view.getpApp().getSupRoomView().getRudView().getReadView().setView((searchAll()));
			}catch(IOException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Communication Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		

	}
 
