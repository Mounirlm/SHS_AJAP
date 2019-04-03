package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class RudRoomView extends JPanel {
	private ColorsDimApp cdApp;
	private JPanel pSearchUpd;
	private SearchView searchView;
	private UpdateView updateView;
	private	ReadView readView;
	
	public RudRoomView() {
		super();
		cdApp = new ColorsDimApp();
		this.setLayout(new BorderLayout());
		
		//Search and Update view
		pSearchUpd = new JPanel(new GridLayout(2, 1));
		searchView = new SearchView();
		updateView = new UpdateView();
		readView = new ReadView();
		
		pSearchUpd.add(searchView);
		pSearchUpd.add(updateView);
		
		
		this.add(pSearchUpd, BorderLayout.NORTH);
		this.add(readView, BorderLayout.CENTER);
	}
	
	public class SearchView extends JPanel{
		
		private FormView formView;
		public SearchView() {
			super();
			this.setLayout(new BorderLayout());
			Map<String, String> cols = new LinkedHashMap<>();
			cols.put("ID","");cols.put("TYPE","");cols.put("FLOOR","");cols.put("ROOM NUMBER","");
			
			ArrayList<String> buttons = new ArrayList<>();
			buttons.add("SEARCH");
			
			formView = new FormView("Search Secured Room", cols, buttons, new ArrayList<String>(), "h", true);
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
	
	public class ReadView extends JPanel{
		private List<ElementRead> elems;
		
		public ReadView() {
			super();
			this.setLayout(new BorderLayout());
			//title			
			this.add(new LBTitle("Read Secured Room"), BorderLayout.NORTH);
			
			//Elements
			JPanel pElem = new JPanel(new GridLayout(50, 1));
			elems = new ArrayList<>();
			
			for (int i = 0; i < 50; i++) {
				elems.add(new ElementRead(1, "BedRoom", 3, false));
				pElem.add(elems.get(i));
			}
			JScrollPane scrollElem = new JScrollPane(pElem,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			this.add(scrollElem, BorderLayout.CENTER);
			
		}
		
		public class ElementRead extends JPanel{
			private FormView formView;
			private Map<String, String> cols;
			
			public ElementRead(int id, String type, int floor,boolean title) {
				super();
				this.setLayout(new BorderLayout());
				cols = new LinkedHashMap<>();
				cols.put("ID",""+id);cols.put("TYPE",type);cols.put("FLOOR",""+floor);cols.put("ROOM NUMBER",""+1);
				
				ArrayList<String> buttons = new ArrayList<>();
				buttons.add("DELETE");
				
				ArrayList<String> labels = new ArrayList<>();
				labels.add("ID");labels.add("TYPE");labels.add("FLOOR");labels.add("ROOM NUMBER");
				
				formView = new FormView("Read Secured Room", cols, buttons,labels, "h",title);
				this.add(formView, BorderLayout.CENTER);
			}
			
			public FormView getFormView() {
				return formView;
			}
			public Map<String, String> getCols(){
				return cols;
			}
		}
		
		public void AddlistnerUpadateReadView(ActionListener act) {
			for (int i = 0; i < elems.size(); i++) {
				elems.get(i).getFormView().addJBListner(act);
			}
		}

		public List<ElementRead> getElems() {
			return elems;
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



	
}
