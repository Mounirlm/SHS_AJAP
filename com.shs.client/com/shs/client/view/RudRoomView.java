package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.shs.commons.model.Room;

public class RudRoomView extends JPanel {
	private ColorsDimApp cdApp;
	private JPanel pSearchUpd;
	private SearchView searchView;
	private UpdateView updateView;
	private	DeleteView deleteView;
	private	ReadView readView;
	
	public RudRoomView() {
		super();
		cdApp = new ColorsDimApp();
		this.setLayout(new BorderLayout());
		
		//Search and Update view
		pSearchUpd = new JPanel(new GridLayout(3, 1));
		searchView = new SearchView();
		deleteView = new DeleteView();
		updateView = new UpdateView();
		
		readView = new ReadView();
		
		pSearchUpd.add(searchView);
		pSearchUpd.add(updateView);
		pSearchUpd.add(deleteView);
		
		
		this.add(pSearchUpd, BorderLayout.NORTH);
		this.add(readView, BorderLayout.CENTER);
	}
	
	public class SearchView extends JPanel{
		
		private FormView formView;
		public SearchView() {
			super();
			this.setLayout(new BorderLayout());
			Map<String, String> cols = new LinkedHashMap<>();
			cols.put("RESEARCH ONLY BY ID","");cols.put("BY TYPE","");cols.put("BY FLOOR","");cols.put("BY ROOM NUMBER","");
			
			ArrayList<String> buttons = new ArrayList<>();
			buttons.add("RESEARCH");buttons.add("RESEARCH ALL");
			
			formView = new FormView("Research Secured Room", cols, buttons, new ArrayList<String>(), "h", true);
			this.add(formView, BorderLayout.CENTER);
			
		}
		public FormView getFormView() {
			return this.formView;
		}
		
	}
	
	public class UpdateView extends JPanel{
		private FormView formView;
		
		public UpdateView() {
			super();
			this.setLayout(new BorderLayout());
			Map<String, String> cols = new LinkedHashMap<>();
			cols.put("ID","");cols.put("TYPE","");cols.put("FLOOR","");cols.put("ROOM NUMBER","");
			
			ArrayList<String> buttons = new ArrayList<>();
			buttons.add("UPDATE");
			
			ArrayList<String> labels = new ArrayList<>();
			
			formView = new FormView("Update Secured Room", cols, buttons,labels, "h",true);
			this.add(formView, BorderLayout.CENTER);
		}

		public FormView getFormView() {
			return formView;
		}
	}
	
	public class DeleteView extends JPanel{
		private FormView formView;
		
		public DeleteView() {
			super();
			this.setLayout(new BorderLayout());
			Map<String, String> cols = new LinkedHashMap<>();
			cols.put("ID","");
			
			ArrayList<String> buttons = new ArrayList<>();
			buttons.add("DELETE");buttons.add("DELETE ALL");
			
			ArrayList<String> labels = new ArrayList<>();
			
			formView = new FormView("Delete Secured Room", cols, buttons,labels, "h",true);
			this.add(formView, BorderLayout.CENTER);
		}

		public FormView getFormView() {
			return formView;
		}
	}
	
	public class ReadView extends JPanel{
		private List<ElementRead> elems;
		private LBTitle titleRead;
		private JPanel pElem;
		private JScrollPane scrollElem;
		public ReadView() {
			super();
			this.setLayout(new BorderLayout());
			//title			
			titleRead =new LBTitle("Display Secured Room");
			this.add(titleRead, BorderLayout.NORTH);
			
			//Elements
			pElem = new JPanel();
			elems = new ArrayList<>();
			scrollElem = new JScrollPane(pElem,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			this.add(scrollElem, BorderLayout.CENTER);
			
		}
		
		public void setView(List<Room> rooms) {
			elems.clear();
			pElem.removeAll();
			scrollElem.remove(pElem);
			this.remove(1);
			this.validate();
			pElem.setLayout(new GridLayout(500, 1));
			for (int i = 0; i < rooms.size(); i++) {
				elems.add(new ElementRead(rooms.get(i).getId(), rooms.get(i).getType_room(), rooms.get(i).getFloor(),rooms.get(i).getRoom_number(), false));
				pElem.add(elems.get(i));
			}
			scrollElem = new JScrollPane(pElem,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.add(scrollElem);
			this.revalidate();
		}
		
		public class ElementRead extends JPanel{
			private FormView formView;
			private Map<String, String> cols;
			
			public ElementRead(int id, String type, int floor,int room_number,boolean title) {
				super();
				this.setLayout(new BorderLayout());
				cols = new LinkedHashMap<>();
				cols.put("ID",""+id);cols.put("TYPE",type);cols.put("FLOOR",""+floor);cols.put("ROOM NUMBER",""+room_number);
				
				ArrayList<String> buttons = new ArrayList<>();
				
				ArrayList<String> labels = new ArrayList<>();
				labels.add("ID");labels.add("TYPE");labels.add("FLOOR");labels.add("ROOM NUMBER");
				
				formView = new FormView("Read Secured Room", cols, buttons,labels, "h",title,15,18,20);
				this.add(formView, BorderLayout.CENTER);
			}
			
			public FormView getFormView() {
				return formView;
			}
			public Map<String, String> getCols(){
				return cols;
			}
		}
		
		public void AddJBDeleteListnerReadView(ActionListener act) {
			for (int i = 0; i < elems.size(); i++) {
				elems.get(i).getFormView().addJBListner(act);
			}
		}

		public List<ElementRead> getElems() {
			return elems;
		}

		public LBTitle getTitle() {
			return titleRead;
		}

		public int getIdTupleByButton(Object source) {
			int id=0;
			for (int i = 0; i < elems.size(); i++) {
				List<JButton> buttons= elems.get(i).getFormView().getButtons();
				for  (JButton b : buttons) {
					if(b==source) {
						id=elems.get(i).getFormView().getIDtuple();
					}
				}
			}
			return id;
		}
		
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public UpdateView getUpdateView() {
		return updateView;
	}

	public ReadView getReadView() {
		return readView;
	}

	public DeleteView getDeleteView() {
		return deleteView;
	}



	
}
