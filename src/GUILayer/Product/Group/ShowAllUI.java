package GUILayer.Product.Group;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ControlLayer.ProductCtrl;
import GUILayer.ButtonColumn;
import GUILayer.GlobalUI;
import ModelLayer.ProductGroup;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ShowAllUI {

	private JPanel contentPane;
	private JTable table;
	
	private DefaultTableModel model;
	private static JInternalFrame _frame;
	private static ShowAllUI _instance;
	private JLabel lblProdGroupId;
	private JTextField txtProdGroupId;
	private JButton btnSearch;
	private String[] columnNames;
	private JScrollPane scrollPane;
	private JPanel gridPanel;
	
	//Controllers
	private ProductCtrl _prodCtrl;
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new ShowAllUI();
		
		return _frame;
	}
	
	@SuppressWarnings("serial")
	private ShowAllUI() 
	{
		_prodCtrl = new ProductCtrl();
		
		_frame = new JInternalFrame();		
		_frame.setTitle("Vis alle produkt grupper");
		_frame.setClosable(true);
		_frame.setMaximizable(true);
		_frame.setVisible(true);
		_frame.setBounds(0, 0, 724, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		
		
		//Search
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setBounds(5, 5, 504, 35);
		
		lblProdGroupId = new JLabel("Produkt gruppe id: ");
		lblProdGroupId.setBounds(187, 10, 126, 15);
		searchPanel.add(lblProdGroupId);
		
		txtProdGroupId = new JTextField();
		txtProdGroupId.setBounds(318, 10, 110, 15);
		txtProdGroupId.setColumns(10);
		txtProdGroupId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtProdGroupId.getText().length() > 0)
				{
					GlobalUI.checkIfInt(txtProdGroupId);
				}
			}
		});
		searchPanel.add(txtProdGroupId);
		
		btnSearch = new JButton("Søg");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtProdGroupId.getText().length() > 0)
				{
					int catId = Integer.parseInt(txtProdGroupId.getText());
					addData(catId);
				}
				if(txtProdGroupId.getText().length() <= 0 && txtProdGroupId.getText().length() <= 0)
				{
					addData();
				}
			}
		});
		contentPane.setLayout(null);
		btnSearch.setBounds(656, 5, 61, 25);
		searchPanel.add(btnSearch);
		
		contentPane.add(searchPanel);
		//Search end
		
		//Grid / table
		gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(gridPanel);		
		
		columnNames = new String[]{"ID", "Titel", "Pris", "Antal varer", " "};
		
		table = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				if(columns != 4)
					return false;
				
				return true;
			}
		};
		
		model = new DefaultTableModel();
		
		table.setModel(model);
		table.setFillsViewportHeight(true);
		addData();
		
		scrollPane = new JScrollPane(table);
		gridPanel.add(scrollPane);		
		
		
		//Close window
		_frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		_frame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
		      public void internalFrameClosing(InternalFrameEvent e) {
		        _frame.dispose();
		        _instance = null;
		      }
		    });
		PropertyChangeListener propertyChangeListener = new PropertyChangeListener()
		{
	        public void propertyChange(PropertyChangeEvent evt) 
	        {
	            autoScaleTable();
	        }			
		};
		_frame.addPropertyChangeListener(propertyChangeListener );
		//Grid / Table end
	}

	private void addShowButton() {
		@SuppressWarnings("serial")
		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                int prodGroupId = Integer.parseInt(table.getValueAt(row, 0).toString());
                showGroupInfo(prodGroupId);
		    }
		};
		ButtonColumn buttonColumn = new ButtonColumn(table, delete, 4);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}
	
	private void addData()
	{
		ArrayList<ProductGroup> prodGroups = makeCollection(_prodCtrl.getAllProductGroups());
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		for(ProductGroup group : prodGroups)
		{
			Object[] row = new Object[]{group.getProductGroupId(), group.getProductGroupName(), group.getPrice().toString(), group.getItems().size() + " stk", "Vis" };
			model.addRow(row);
		}
		addShowButton();
	}
	
	private void addData(int prodGroupId)
	{
		ProductGroup group = _prodCtrl.getProductGroup(prodGroupId);
		
		if(group != null)
		{
			Object[][] row = new Object[][]
			{
					{group.getProductGroupId(), group.getProductGroupName(), group.getPrice().toString(), group.getItems().size() + " stk", "Vis" }
			};
			model.setDataVector(row, columnNames);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet en produkt gruppe med ID'et: " + prodGroupId, "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		
		addShowButton();
	}
	
	private void showGroupInfo(int prodGroupId)
	{
		try
		{
			JFrame frameShowInfo = ShowGroupItemsUI.createWindow(prodGroupId);
			GlobalUI.centerOnScreen(frameShowInfo);
		}
		catch(Exception ex)
		{
			System.out.print(ex);
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}

	
	private <E> ArrayList<E> makeCollection(Iterable<E> iter)
	{
		ArrayList<E> list = new ArrayList<E>();
		for (E item : iter) 
			list.add(item);
		return list;
	}
	
	private void autoScaleTable() {
		if(_frame.isMaximum())
        {
			gridPanel.setBounds(new Rectangle(0, 40, _frame.getWidth()-8, _frame.getHeight()-118));
        	table.setPreferredScrollableViewportSize(new Dimension(_frame.getWidth()-18, _frame.getHeight()-118));
        	scrollPane.setPreferredSize(new Dimension(_frame.getWidth()-18, _frame.getHeight()-118));
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
        else
        {
        	gridPanel.setBounds(new Rectangle(0, 40, 714, 200));
        	table.setPreferredScrollableViewportSize(new Dimension(704, 200));
        	scrollPane.setPreferredSize(new Dimension(704, 200));
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
	}
}

