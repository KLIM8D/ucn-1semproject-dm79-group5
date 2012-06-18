package GUILayer.Product.Category;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
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
import ModelLayer.ProductCategory;

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
	private JLabel lblCatId;
	private JTextField txtCatId;
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
		_frame.setTitle("Vis alle produkt kategorier");
		_frame.setClosable(true);
		_frame.setMaximizable(true);
		_frame.setVisible(true);
		_frame.setBounds(0, 0, 524, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		
		
		//Search
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setBounds(5, 5, 504, 35);
		
		lblCatId = new JLabel("Kategori id: ");
		lblCatId.setBounds(187, 10, 126, 15);
		searchPanel.add(lblCatId);
		
		txtCatId = new JTextField();
		txtCatId.setBounds(318, 10, 110, 15);
		txtCatId.setColumns(10);
		txtCatId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtCatId.getText().length() > 0)
				{
					GlobalUI.checkIfInt(txtCatId);
				}
			}
		});
		searchPanel.add(txtCatId);
		
		btnSearch = new JButton("Søg");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtCatId.getText().length() > 0)
				{
					int catId = Integer.parseInt(txtCatId.getText());
					addData(catId);
				}
				if(txtCatId.getText().length() <= 0 && txtCatId.getText().length() <= 0)
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
		
		columnNames = new String[]{"ID", "Kategori navn", " "};
		
		table = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				if(columns != 2)
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

	private void addDeleteButton() {
		@SuppressWarnings("serial")
		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                int catId = Integer.parseInt(table.getValueAt(row, 0).toString());
                deleteCategory(catId);
		    }
		};
		ButtonColumn buttonColumn = new ButtonColumn(table, delete, 2);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}
	
	private void addData()
	{
		ArrayList<ProductCategory> catgories = makeCollection(_prodCtrl.getAllCategories());
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		for(ProductCategory cat : catgories)
		{
			Object[] row = new Object[]{cat.getCategoryId(), cat.getCategoryName(), "Slet" };
			model.addRow(row);
		}
		addDeleteButton();
	}
	
	private void addData(int catId)
	{
		ProductCategory category = _prodCtrl.getCategory(catId);
		
		if(category != null)
		{
			Object[][] row = new Object[][]
			{
					{category.getCategoryId(), category.getCategoryName(), "Slet" }
			};
			model.setDataVector(row, columnNames);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet en kategori med ID'et: " + catId, "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		
		addDeleteButton();
	}
	
	private void deleteCategory(int catId)
	{
		boolean succeeded = false;
		try
		{
			
			int proceed = showConfirmDialog();
			if(proceed == 1)
			{
				succeeded = _prodCtrl.removeCategory(catId);
				if(succeeded)
				{
					JOptionPane.showMessageDialog(null, "Produkt kategorien er nu slettet", "Gennemført!", JOptionPane.WARNING_MESSAGE);
					addData();
				}
				else
					JOptionPane.showMessageDialog(null, "Der skete en fejl da kategorien skulle slettes", "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, "Handlingen blev afbrudt. Kategorien blev ikke slettet", "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private int showConfirmDialog()
	{
		int option = JOptionPane.showConfirmDialog (null, "Er du sikker på du vil slettet denne kategori? Bemærk handlingen kan ikke fortrydes");
		if (option == JOptionPane.YES_OPTION )
			return 1;
		
		return 0;
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
        	gridPanel.setBounds(new Rectangle(0, 40, 514, 200));
        	table.setPreferredScrollableViewportSize(new Dimension(504, 200));
        	scrollPane.setPreferredSize(new Dimension(504, 200));
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
	}
}

