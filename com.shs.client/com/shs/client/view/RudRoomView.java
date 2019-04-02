package com.shs.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
		
		//Read View
		
		
		this.add(pSearchUpd, BorderLayout.NORTH);
		this.add(readView, BorderLayout.CENTER);
	}
	
	public class SearchView extends JPanel{
		private JLabel title;
		private ColorsDimApp cdApp = new ColorsDimApp();//TODO METTRE EN STATIC
		private JTextField jtfRoomType;
		private JTextField jtfFloor;
		private JTextField jtfID;
		private JButton validateButton;
		
		public SearchView() {
			super();
			this.setLayout(new BorderLayout());
			//title
			title= new JLabel("Search Secured Room");
			title.setOpaque(true);
			title.setBackground(Color.decode("#afabab"));
			title.setForeground(cdApp.getBgApp());
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setFont(new Font("Arial", Font.BOLD, 30));
			title.setBorder(new LineBorder(cdApp.getBgTitle(), 2));
			
			
			//Search Form
			JPanel pForm = new JPanel(new GridLayout(1,4));
			jtfRoomType = new JTextField("Bedroom");
			jtfFloor = new JTextField();
			jtfID = new JTextField();
			
			//pForm.add(new JPanel());
			JLabel lbID =new JLabel("ID");
			lbID.setOpaque(true);
			lbID.setBackground(cdApp.getBgThem());
			lbID.setForeground(cdApp.getBgApp());
			lbID.setHorizontalAlignment(JLabel.CENTER);
			lbID.setFont(new Font("Arial", Font.BOLD, 15));
			lbID.setBorder(new LineBorder(Color.BLACK, 1));
			jtfID.setFont(new Font("Arial", Font.BOLD, 11));
			JPanel pId = new JPanel(new BorderLayout());
			pId.add(lbID, BorderLayout.NORTH);
			pId.add(jtfID, BorderLayout.CENTER);
			pForm.add(pId);
			
			
			JLabel lbType =new JLabel("TYPE");
			lbType.setOpaque(true);
			lbType.setBackground(cdApp.getBgThem());
			lbType.setForeground(cdApp.getBgApp());
			lbType.setHorizontalAlignment(JLabel.CENTER);
			lbType.setFont(new Font("Arial", Font.BOLD, 15));
			lbType.setBorder(new LineBorder(Color.BLACK, 1));
			jtfRoomType.setFont(new Font("Arial", Font.BOLD,11));
			JPanel pType = new JPanel(new BorderLayout());
			pType.add(lbType, BorderLayout.NORTH);
			pType.add(jtfRoomType, BorderLayout.CENTER);
			pForm.add(pType);

			
			JLabel lbFloor =new JLabel("FLOOR");
			lbFloor.setOpaque(true);
			lbFloor.setBackground(cdApp.getBgThem());
			lbFloor.setForeground(cdApp.getBgApp());
			lbFloor.setHorizontalAlignment(JLabel.CENTER);
			lbFloor.setFont(new Font("Arial", Font.BOLD, 15));
			lbFloor.setBorder(new LineBorder(Color.BLACK, 1));
			jtfFloor.setFont(new Font("Arial", Font.BOLD, 11));
			JPanel pFloor = new JPanel(new BorderLayout());
			pFloor.add(lbFloor, BorderLayout.NORTH);
			pFloor.add(jtfFloor, BorderLayout.CENTER);
			pForm.add(pFloor);
			
			
			//Panel Valider
			JPanel pValidate = new JPanel();
			validateButton = new JButton("SEARCH");
			validateButton.setBackground(cdApp.getBgThem());
			validateButton.setForeground(cdApp.getBgApp());
			validateButton.setHorizontalAlignment(JLabel.CENTER);
			validateButton.setFont(new Font("Arial", Font.BOLD, 20));
			pValidate.add("search",validateButton);
			pForm.add(pValidate);
			
			this.add(title, BorderLayout.NORTH);
			this.add(pForm, BorderLayout.CENTER);
		}
	}
	
	public class UpdateView extends JPanel{
		private JLabel title;
		private ColorsDimApp cdApp = new ColorsDimApp();//TODO METTRE EN STATIC
		private JTextField jtfRoomType;
		private JTextField jtfFloor;
		private JLabel lbIDValue;
		private JButton validateButton;
		
		public UpdateView() {
			super();
			this.setLayout(new BorderLayout());
			//title
			title = new JLabel("Update Secured Room");
			title.setOpaque(true);
			title.setBackground(Color.decode("#afabab"));
			title.setForeground(cdApp.getBgApp());
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setFont(new Font("Arial", Font.BOLD, 30));
			title.setBorder(new LineBorder(cdApp.getBgTitle(), 2));
			
			
			//Search Form
			JPanel pForm = new JPanel(new GridLayout(1,8));
			jtfRoomType = new JTextField("Bedroom");
			jtfFloor = new JTextField();
			lbIDValue = new JLabel("1");
			
			//pForm.add(new JPanel());
			JLabel lbID =new JLabel("ID");
			lbID.setOpaque(true);
			lbID.setBackground(cdApp.getBgThem());
			lbID.setForeground(cdApp.getBgApp());
			lbID.setHorizontalAlignment(JLabel.CENTER);
			lbID.setFont(new Font("Arial", Font.BOLD, 15));
			lbID.setBorder(new LineBorder(Color.BLACK, 1));
			lbIDValue.setFont(new Font("Arial", Font.BOLD, 11));
			lbIDValue.setOpaque(true);
			lbIDValue.setBackground(cdApp.getBgApp());
			JPanel pId = new JPanel(new BorderLayout());
			pId.add(lbID, BorderLayout.NORTH);
			pId.add(lbIDValue, BorderLayout.CENTER);
			pForm.add(pId);
			
			
			JLabel lbType =new JLabel("TYPE");
			lbType.setOpaque(true);
			lbType.setBackground(cdApp.getBgThem());
			lbType.setForeground(cdApp.getBgApp());
			lbType.setHorizontalAlignment(JLabel.CENTER);
			lbType.setFont(new Font("Arial", Font.BOLD, 15));
			lbType.setBorder(new LineBorder(Color.BLACK, 1));
			jtfRoomType.setFont(new Font("Arial", Font.BOLD,11));
			JPanel pType = new JPanel(new BorderLayout());
			pType.add(lbType, BorderLayout.NORTH);
			pType.add(jtfRoomType, BorderLayout.CENTER);
			pForm.add(pType);

			//pForm.add(new JPanel());
			
			JLabel lbFloor =new JLabel("FLOOR");
			lbFloor.setOpaque(true);
			lbFloor.setBackground(cdApp.getBgThem());
			lbFloor.setForeground(cdApp.getBgApp());
			lbFloor.setHorizontalAlignment(JLabel.CENTER);
			lbFloor.setFont(new Font("Arial", Font.BOLD, 15));
			lbFloor.setBorder(new LineBorder(Color.BLACK, 1));
			jtfFloor.setFont(new Font("Arial", Font.BOLD, 11));
			JPanel pFloor = new JPanel(new BorderLayout());
			pFloor.add(lbFloor, BorderLayout.NORTH);
			pFloor.add(jtfFloor, BorderLayout.CENTER);
			pForm.add(pFloor);
			
			
			//Panel Valider
			JPanel pValidate = new JPanel();
			validateButton = new JButton("UPDATE");
			validateButton.setBackground(cdApp.getBgThem());
			validateButton.setForeground(cdApp.getBgApp());
			validateButton.setHorizontalAlignment(JLabel.CENTER);
			validateButton.setFont(new Font("Arial", Font.BOLD, 20));
			pValidate.add("update",validateButton);
			pForm.add(pValidate);
			
			this.add(title, BorderLayout.NORTH);
			this.add(pForm, BorderLayout.CENTER);
		}
	}
	
	public class ReadView extends JPanel{
		private JLabel title;
		private ColorsDimApp cdApp = new ColorsDimApp();//TODO METTRE EN STATIC
		private List<ElementRead> elems;
		
		public ReadView() {
			super();
			this.setLayout(new BorderLayout());
			//title
			title = new JLabel("Read Secured Room");
			title.setOpaque(true);
			title.setBackground(Color.decode("#afabab"));
			title.setForeground(cdApp.getBgApp());
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setFont(new Font("Arial", Font.BOLD, 30));
			title.setBorder(new LineBorder(cdApp.getBgTitle(), 2));
			
			this.add(title, BorderLayout.NORTH);
			
			//Elements
			
			JPanel pElem = new JPanel(new GridLayout(50, 1));
			elems = new ArrayList<>();
			
			for (int i = 0; i < 50; i++) {
				elems.add(new ElementRead(1, "BedRoom", 3));
				pElem.add(elems.get(i));
			}
			JScrollPane scrollElem = new JScrollPane(pElem,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			this.add(scrollElem, BorderLayout.CENTER);
			
		}
		
		public class ElementRead extends JPanel{
			private JLabel lbRoomTypeVal;
			private JLabel lbFloorVal;
			private JLabel lbIDVal;
			private JButton updateButton;
			private JButton deleteButton;
			
			public ElementRead(int id, String type, int floor) {
				super();
				this.setLayout(new BorderLayout());
				JPanel pForm = new JPanel(new GridLayout(1,8));
				lbRoomTypeVal = new JLabel(type);
				lbFloorVal = new JLabel(""+floor);
				lbIDVal = new JLabel(""+id);
				
				//pForm.add(new JPanel());
				JLabel lbID =new JLabel("ID");
				lbID.setOpaque(true);
				lbID.setBackground(cdApp.getBgThem());
				lbID.setForeground(cdApp.getBgApp());
				lbID.setHorizontalAlignment(JLabel.CENTER);
				lbID.setFont(new Font("Arial", Font.BOLD, 15));
				lbID.setBorder(new LineBorder(Color.BLACK, 1));
				lbIDVal.setFont(new Font("Arial", Font.BOLD, 11));
				lbIDVal.setOpaque(true);
				lbIDVal.setBackground(cdApp.getBgApp());
				JPanel pId = new JPanel(new BorderLayout());
				pId.add(lbID, BorderLayout.NORTH);
				pId.add(lbIDVal, BorderLayout.CENTER);
				pForm.add(pId);
				
				
				JLabel lbType =new JLabel("TYPE");
				lbType.setOpaque(true);
				lbType.setBackground(cdApp.getBgThem());
				lbType.setForeground(cdApp.getBgApp());
				lbType.setHorizontalAlignment(JLabel.CENTER);
				lbType.setFont(new Font("Arial", Font.BOLD, 15));
				lbType.setBorder(new LineBorder(Color.BLACK, 1));
				lbRoomTypeVal.setFont(new Font("Arial", Font.BOLD,11));
				lbRoomTypeVal.setOpaque(true);
				lbRoomTypeVal.setBackground(cdApp.getBgApp());
				JPanel pType = new JPanel(new BorderLayout());
				pType.add(lbType, BorderLayout.NORTH);
				pType.add(lbRoomTypeVal, BorderLayout.CENTER);
				pForm.add(pType);

				//pForm.add(new JPanel());
				
				JLabel lbFloor =new JLabel("FLOOR");
				lbFloor.setOpaque(true);
				lbFloor.setBackground(cdApp.getBgThem());
				lbFloor.setForeground(cdApp.getBgApp());
				lbFloor.setHorizontalAlignment(JLabel.CENTER);
				lbFloor.setFont(new Font("Arial", Font.BOLD, 15));
				lbFloor.setBorder(new LineBorder(Color.BLACK, 1));
				lbFloorVal.setFont(new Font("Arial", Font.BOLD, 11));
				lbFloorVal.setOpaque(true);
				lbFloorVal.setBackground(cdApp.getBgApp());
				JPanel pFloor = new JPanel(new BorderLayout());
				pFloor.add(lbFloor, BorderLayout.NORTH);
				pFloor.add(lbFloorVal, BorderLayout.CENTER);
				pForm.add(pFloor);
				
				
				//Panel Valider
				JPanel pUpdateDelete = new JPanel(new GridLayout(1, 2));
				updateButton = new JButton("UPDATE");
				updateButton.setBackground(cdApp.getBgThem());
				updateButton.setForeground(cdApp.getBgApp());
				updateButton.setHorizontalAlignment(JLabel.CENTER);
				updateButton.setFont(new Font("Arial", Font.BOLD, 20));
				
				deleteButton = new JButton("DELETE");
				deleteButton.setBackground(cdApp.getBgThem());
				deleteButton.setForeground(cdApp.getBgApp());
				deleteButton.setHorizontalAlignment(JLabel.CENTER);
				deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
				
				pUpdateDelete.add("update",updateButton);
				pUpdateDelete.add("delete",deleteButton);
				pForm.add(pUpdateDelete);
				
				this.add(pForm, BorderLayout.CENTER);
			}
		}
	}
}
