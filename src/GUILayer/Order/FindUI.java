package GUILayer.Order;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import ControlLayer.OrderCtrl;
import GUILayer.ButtonColumn;
import GUILayer.GlobalUI;
import ModelLayer.Order;
import ModelLayer.OrderLine;

public class FindUI  
{

	private JPanel contentPane;
	private JTable table;
	
	private DefaultTableModel model;
	private static JInternalFrame _frame;
	private static FindUI _instance;
	private JLabel lblOrderId;
	private JTextField txtOrderId;
	private JLabel lblCustomerId;
	private JTextField txtCustomerId;
	private JLabel lblSalesAsstId;
	private JTextField txtSalesAsstId;
	private JButton btnSearch;
	private String[] columnNames;
	private JScrollPane scrollPane;
	private JPanel gridPanel;
	
	//Controllers
	private OrderCtrl _orderCtrl;
	
	//ShowProductPanel
	private JPanel showOrderInfoPanel;
	private JLabel lblShowCustomer;
	private JLabel lblShowSalesAsst;
	private JLabel lblShowSaleNotes;
	private JLabel lblShowDiscount;
	private JLabel lblShowDiscountValue;
	private JLabel lblShowStatus;
	private JLabel lblShowOrderInformation;
	private JLabel lblShowOrderLines;
	private JTable tblOrderLines;
	private DefaultTableModel model2;
	private JScrollPane scrollPane2;
	private String[] columnNames2;
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new FindUI();
		
		return _frame;
	}

	@SuppressWarnings("serial")
	private FindUI() 
	{
		_orderCtrl = new OrderCtrl();
		
		_frame = new JInternalFrame();		
		_frame.setTitle("Find ordre");
		_frame.setClosable(true);
		_frame.setMaximizable(true);
		_frame.setVisible(true);
		_frame.setBounds(0, 0, 924, 562);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		
		
		//Search
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setBounds(5, 5, 904, 35);
		
		lblOrderId = new JLabel("Order ID: ");
		lblOrderId.setBounds(187, 10, 126, 15);
		searchPanel.add(lblOrderId);
		
		txtOrderId = new JTextField();
		txtOrderId.setBounds(318, 10, 110, 15);
		txtOrderId.setColumns(10);
		txtOrderId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtOrderId.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtOrderId);
				}
			}
		});
		searchPanel.add(txtOrderId);
		
		lblCustomerId = new JLabel("Kunde nummer: ");
		lblCustomerId.setBounds(433, 10, 103, 15);
		searchPanel.add(lblCustomerId);
		
		txtCustomerId = new JTextField();
		txtCustomerId.setBounds(541, 10, 110, 15);
		txtCustomerId.setColumns(10);
		searchPanel.add(txtCustomerId);
		
		lblSalesAsstId = new JLabel("Ekspedient nummer: ");
		lblSalesAsstId.setBounds(536, 10, 103, 15);
		searchPanel.add(lblSalesAsstId);
		
		txtSalesAsstId = new JTextField();
		txtSalesAsstId.setBounds(541, 10, 110, 15);
		txtSalesAsstId.setColumns(10);
		searchPanel.add(txtSalesAsstId);
		
		btnSearch = new JButton("Søg");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtOrderId.getText().length() > 0)
				{
					long orderId = Long.parseLong(txtOrderId.getText());
					addData(orderId);
				}
				if(txtCustomerId.getText().length() > 0 && txtOrderId.getText().length() <= 0)
				{
					long customerId = Long.parseLong(txtCustomerId.getText());
					addDataCustomer(customerId);
				}
				if(txtSalesAsstId.getText().length() > 0 && txtOrderId.getText().length() <= 0 && txtCustomerId.getText().length() <= 0)
				{
					int salesAsstId = Integer.parseInt(txtSalesAsstId.getText());
					addDataSalesAsst(salesAsstId);
				}
				if(txtOrderId.getText().length() <= 0 && txtSalesAsstId.getText().length() <= 0 && txtCustomerId.getText().length() <= 0)
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
		
		columnNames = new String[]{"Order ID", "Kunde", "Sælger", "Status", " "};
		
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
		
		//ShowProductPanel
		showOrderInfoPanel = new JPanel();
		showOrderInfoPanel.setBounds(5, 315, 904, 200);
		contentPane.add(showOrderInfoPanel);
		showOrderInfoPanel.setLayout(null);
		
		lblShowCustomer = new JLabel();
		lblShowCustomer.setBounds(12, 15, 350, 20);
		showOrderInfoPanel.add(lblShowCustomer);
		
		lblShowSalesAsst = new JLabel();
		lblShowSalesAsst.setBounds(12, 47, 350, 20);
		showOrderInfoPanel.add(lblShowSalesAsst);
		
		lblShowSaleNotes = new JLabel();
		lblShowSaleNotes.setBounds(12, 79, 350, 20);
		showOrderInfoPanel.add(lblShowSaleNotes);
		
		lblShowDiscount = new JLabel();
		lblShowDiscount.setBounds(12, 111, 350, 20);
		showOrderInfoPanel.add(lblShowDiscount);
		
		lblShowDiscountValue = new JLabel();
		lblShowDiscountValue.setBounds(12, 143, 350, 20);
		showOrderInfoPanel.add(lblShowDiscountValue);
		
		lblShowStatus = new JLabel();
		lblShowStatus.setBounds(12, 175, 350, 20);
		showOrderInfoPanel.add(lblShowStatus);
		
		lblShowOrderInformation = new JLabel("Order information:");
		lblShowOrderInformation.setVisible(false);
		lblShowOrderInformation.setFont(new Font("Dialog", Font.BOLD, 16));
		lblShowOrderInformation.setBounds(12, 0, 200, 15);
		showOrderInfoPanel.add(lblShowOrderInformation);
		
		lblShowOrderLines = new JLabel("Ordrelinjer:");
		lblShowOrderLines.setVisible(false);
		lblShowOrderLines.setFont(new Font("Dialog", Font.BOLD, 16));
		lblShowOrderLines.setBounds(444, 0, 200, 15);
		showOrderInfoPanel.add(lblShowOrderLines);
		
		//Orderlines
		columnNames2 = new String[]{"Produkt", "Antal"};
		
		tblOrderLines = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				return false;
			}
		};
		
		model2 = new DefaultTableModel();
		
		tblOrderLines.setModel(model2);
		tblOrderLines.setFillsViewportHeight(true);
		
		scrollPane2 = new JScrollPane(tblOrderLines);
		scrollPane2.setSize(450, 180);
		tblOrderLines.setPreferredScrollableViewportSize(new Dimension(300, 200));
    	scrollPane2.setPreferredSize(new Dimension(300, 200));
    	scrollPane2.setLocation(445, 29);
		showOrderInfoPanel.add(scrollPane2);
		//ShowOrderInfo end
	}
	
	private void addShowButton() 
	{
		@SuppressWarnings("serial")
		Action show = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                long orderId = Long.parseLong(table.getValueAt(row, 0).toString());
                showOrder(orderId);
		    }
		};
		ButtonColumn buttonColumn = new ButtonColumn(table, show, 4);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}
	
	private void addData()
	{
		ArrayList<Order> orders = makeCollection(_orderCtrl.getAllOrders());
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		for(Order order : orders)
		{
			Object[] row = new Object[]{order.getId(), order.getCustomer().getPerson().getName(), order.getSalesAsst().getPerson().getName(), order.getStatus().getStatusValue(), "Vis" };
			model.addRow(row);
		}
		addShowButton();
	}
	
	private void addData(long orderId)
	{
		Order order = _orderCtrl.getOrder(orderId);
		
		if(order != null)
		{
			Object[][] row = new Object[][]
			{
					{ order.getId(), order.getCustomer().getPerson().getName(), order.getSalesAsst().getPerson().getName(), order.getStatus().getStatusValue(), "Vis" }
			};
			model.setDataVector(row, columnNames);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet en ordre med ID'et: " + orderId, "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		
		addShowButton();
	}
	
	private void addDataCustomer(long customerId)
	{
		ArrayList<Order> orders = makeCollection(_orderCtrl.getCustomerOrders(customerId));
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		for(Order order : orders)
		{
			Object[] row = new Object[]{order.getId(), order.getCustomer().getPerson().getName(), order.getSalesAsst().getPerson().getName(), order.getStatus().getStatusValue(), "Vis" };
			model.addRow(row);
		}
		addShowButton();
	}
	
	private void addDataSalesAsst(int salesAsstId)
	{
		ArrayList<Order> orders = makeCollection(_orderCtrl.getSalesAssistantOrders(salesAsstId));
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		for(Order order : orders)
		{
			Object[] row = new Object[]{order.getId(), order.getCustomer().getPerson().getName(), order.getSalesAsst().getPerson().getName(), order.getStatus().getStatusValue(), "Vis" };
			model.addRow(row);
		}
		addShowButton();
	}
	
	private <E> ArrayList<E> makeCollection(Iterable<E> iter)
	{
		ArrayList<E> list = new ArrayList<E>();
		for (E item : iter) 
			list.add(item);
		return list;
	}

	private void showOrder(long orderId)
	{
		Order order = _orderCtrl.getOrder(orderId);
		
		if(order != null)
		{
			lblShowOrderInformation.setVisible(true);
			lblShowOrderLines.setVisible(true);
			lblShowCustomer.setText("Kunde: " + order.getCustomer().getPerson().getName() + " / " + Long.toString(order.getCustomer().getCustomerId()));
			lblShowSalesAsst.setText("Sælger: " + order.getSalesAsst().getPerson().getName());
			lblShowSaleNotes.setText("Salgs noter: " + order.getSalesNote());
			if(order.getDiscount() != null)
			{
				lblShowDiscount.setText("Rabat gruppe: " + GlobalUI.translateDiscountTypes(order.getDiscount().getDiscountType()));
				lblShowDiscountValue.setText("Rabat beløb: " + order.getDiscount().getDiscountValue().toString() + " kr.");
			}
			lblShowStatus.setText("Status: " + order.getStatus().getStatusValue());
			
			ArrayList<OrderLine> orderLines = makeCollection(order.getOrderLines());
			Object[][] data = {};
			model2.setDataVector(data, columnNames2);
			for(OrderLine line : orderLines)
			{
				Object[] row = new Object[]{ line.getProduct().getProduct().getItemName(), line.getQuantity() };
				model2.addRow(row);
			}
		}
	}
	
	private void autoScaleTable() {
		if(_frame.isMaximum())
        {
			gridPanel.setBounds(new Rectangle(0, 40, _frame.getWidth()-8, _frame.getHeight()-412));
        	table.setPreferredScrollableViewportSize(new Dimension(_frame.getWidth()-18, _frame.getHeight()-412));
        	scrollPane.setPreferredSize(new Dimension(_frame.getWidth()-18, _frame.getHeight()-412));
        	showOrderInfoPanel.setLocation(showOrderInfoPanel.getLocation().x, gridPanel.getLocation().y + gridPanel.getHeight()+10);
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
        else
        {
        	gridPanel.setBounds(new Rectangle(0, 40, 914, 250));
        	table.setPreferredScrollableViewportSize(new Dimension(904, 250));
        	scrollPane.setPreferredSize(new Dimension(904, 250));
        	//tblOrderLines.setPreferredScrollableViewportSize(new Dimension(300, 200));
        	//scrollPane2.setPreferredSize(new Dimension(300, 200));
        	showOrderInfoPanel.setLocation(5, 315);
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
	}
}
