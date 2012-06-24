package GUILayer.Order;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;

import ControlLayer.CustomerCtrl;
import ControlLayer.OrderCtrl;
import ControlLayer.ProductLocationCtrl;
import GUILayer.GlobalUI;
import ModelLayer.Customer;
import ModelLayer.Discount;
import ModelLayer.OrderLine;
import ModelLayer.ProductLocation;
import ModelLayer.ProductPhysicalAvail;

public class CreateUI  {

	private static JInternalFrame _frame;
	private static CreateUI _instance;
	private JPanel contentPane;
	private JTextField txtPhoneNo;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtZipCode;
	private JTextField txtCity;
	private JSeparator separator_1;
	private JLabel lblDiscStatus;
	private JComboBox<String> drpDiscounts;
	private JTextField txtIsBusiness;
	private JLabel lblIsBusiness;
	private JLabel lblNotes;
	private JTable tblOrder;
	private JButton btnPerform;
	private JTextField txtTotal;
	private JLabel lblCurrency_03;
	private JLabel lblCurrency_02;
	private JLabel lblCurrency_01;
	private JTextField txtTax;
	private JTextField txtCalcPrice;
	private JLabel lblCalcPrice;
	private JLabel lblTotal;
	private JLabel lblTax;
	private JTextField txtItemnumber;
	private JTextField txtQuantity;
	private JLabel lblItemnumber;
	private JLabel lblQuantity;
	private JButton btnAddorderline;
	private String[] columnNames;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JTextArea txtNotes;
	
	//Controllers
	private CustomerCtrl _customerCtrl;
	private OrderCtrl _orderCtrl;
	private ProductLocationCtrl _locCtrl;
	
	//orderLines
	private ArrayList<OrderLine> _orderLines;
	private double _totalPrice;
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new CreateUI();
		
		return _frame;
	}
	
	@SuppressWarnings("serial")
	private CreateUI()
	{
		_customerCtrl = new CustomerCtrl();
		_orderCtrl = new OrderCtrl();
		_locCtrl = new ProductLocationCtrl();
		_orderLines = new ArrayList<OrderLine>();
		_frame = new JInternalFrame();
		_frame.setTitle("Ny ordre");
		_frame.setClosable(true);
		_frame.setVisible(true);
		_frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		_frame.setBounds(0, 0, 800, 600);
		_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		_frame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
		      public void internalFrameClosing(InternalFrameEvent e) {
		        _frame.dispose();
		        _instance = null;
		      }
		    });
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPhoneNo = new JLabel("Telefon");
		lblPhoneNo.setBounds(12, 12, 70, 15);
		contentPane.add(lblPhoneNo);
		
		JLabel lblName = new JLabel("Navn");
		lblName.setBounds(12, 36, 70, 15);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Adresse");
		lblAddress.setBounds(12, 60, 70, 15);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("By");
		lblCity.setBounds(12, 84, 70, 15);
		contentPane.add(lblCity);
		
		txtPhoneNo = new JTextField();
		txtPhoneNo.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtPhoneNo.getText().length() > 0)
				{				
					GlobalUI.checkIfLong(txtPhoneNo);				
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					long data = Long.parseLong(txtPhoneNo.getText());
					getCustomerInfo(data);
				}
			}
		});
		txtPhoneNo.setBounds(78, 10, 198, 17);
		contentPane.add(txtPhoneNo);
		txtPhoneNo.setColumns(10);
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setColumns(10);
		txtName.setBounds(78, 35, 198, 17);
		contentPane.add(txtName);
		
		txtAddress = new JTextField();
		txtAddress.setEnabled(false);
		txtAddress.setColumns(10);
		txtAddress.setBounds(78, 60, 198, 17);
		contentPane.add(txtAddress);
		
		txtZipCode = new JTextField();
		txtZipCode.setEnabled(false);
		txtZipCode.setColumns(10);
		txtZipCode.setBounds(78, 85, 40, 19);
		contentPane.add(txtZipCode);
		
		txtCity = new JTextField();
		txtCity.setEnabled(false);
		txtCity.setColumns(10);
		txtCity.setBounds(131, 85, 145, 19);
		contentPane.add(txtCity);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 115, 281, 1);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(293, 12, 1, 158);
		contentPane.add(separator_1);
		
		lblDiscStatus = new JLabel("Rabatstatus");
		lblDiscStatus.setBounds(12, 130, 93, 15);
		contentPane.add(lblDiscStatus);
		
		drpDiscounts = new JComboBox<String>();
		drpDiscounts.setBounds(117, 128, 159, 17);
		contentPane.add(drpDiscounts);
		
		txtIsBusiness = new JTextField();
		txtIsBusiness.setEnabled(false);
		txtIsBusiness.setColumns(10);
		txtIsBusiness.setBounds(117, 153, 159, 17);
		contentPane.add(txtIsBusiness);
		
		lblIsBusiness = new JLabel("Er erhverv");
		lblIsBusiness.setBounds(12, 154, 106, 15);
		contentPane.add(lblIsBusiness);
		
		lblNotes = new JLabel("Salgsnote");
		lblNotes.setBounds(305, 12, 93, 15);
		contentPane.add(lblNotes);
		
		txtNotes = new JTextArea();
		txtNotes.setLineWrap(true);
		txtNotes.setBounds(306, 36, 472, 134);
		contentPane.add(txtNotes);
		
	
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(661, 531, 117, 25);
		contentPane.add(btnCancel);
		
		btnPerform = new JButton("Udfør");
		btnPerform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createOrder();
			}
		});
		btnPerform.setBounds(532, 531, 117, 25);
		contentPane.add(btnPerform);
		
		txtTotal = new JTextField();
		txtTotal.setEnabled(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(561, 479, 159, 17);
		contentPane.add(txtTotal);
		
		lblCurrency_03 = new JLabel("DKK");
		lblCurrency_03.setBounds(738, 479, 40, 15);
		contentPane.add(lblCurrency_03);
		
		lblCurrency_02 = new JLabel("DKK");
		lblCurrency_02.setBounds(738, 455, 40, 15);
		contentPane.add(lblCurrency_02);
		
		lblCurrency_01 = new JLabel("DKK");
		lblCurrency_01.setBounds(738, 431, 40, 15);
		contentPane.add(lblCurrency_01);
		
		txtTax = new JTextField();
		txtTax.setEnabled(false);
		txtTax.setColumns(10);
		txtTax.setBounds(561, 454, 159, 17);
		contentPane.add(txtTax);
		
		txtCalcPrice = new JTextField();
		txtCalcPrice.setEnabled(false);
		txtCalcPrice.setColumns(10);
		txtCalcPrice.setBounds(561, 429, 159, 17);
		contentPane.add(txtCalcPrice);
		
		lblCalcPrice = new JLabel("Samlet pris");
		lblCalcPrice.setBounds(449, 431, 106, 15);
		contentPane.add(lblCalcPrice);
		
		lblTotal = new JLabel("I alt");
		lblTotal.setBounds(449, 480, 106, 15);
		contentPane.add(lblTotal);
		
		lblTax = new JLabel("Heraf moms");
		lblTax.setBounds(449, 455, 94, 15);
		contentPane.add(lblTax);
		
		txtItemnumber = new JTextField();
		txtItemnumber.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtItemnumber.getText().length() > 0)
				{				
					GlobalUI.checkIfLong(txtItemnumber);				
				}
			}
		});
		txtItemnumber.setBounds(175, 431, 223, 19);
		contentPane.add(txtItemnumber);
		txtItemnumber.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtQuantity.getText().length() > 0)
				{				
					GlobalUI.checkIfInt(txtQuantity);				
				}
			}
		});
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(175, 464, 223, 19);
		contentPane.add(txtQuantity);
		
		lblItemnumber = new JLabel("Produkt nummer");
		lblItemnumber.setBounds(12, 435, 145, 15);
		contentPane.add(lblItemnumber);
		
		lblQuantity = new JLabel("Antal");
		lblQuantity.setBounds(12, 466, 70, 15);
		contentPane.add(lblQuantity);
		
		btnAddorderline = new JButton("Tilføj");
		btnAddorderline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtItemnumber.getText().length() > 0 && txtQuantity.getText().length() > 0)
				{
					long itemNumber = Long.parseLong(txtItemnumber.getText());
					int quantity = Integer.parseInt(txtQuantity.getText());
					createOrderLine(itemNumber, quantity);
					addOrderLineData();
					calcTotalPrice();
					txtItemnumber.setText("");
					txtQuantity.setText("");
				}
			}
		});
		btnAddorderline.setBounds(280, 495, 117, 25);
		contentPane.add(btnAddorderline);
		
		tblOrder = new JTable();
		
		columnNames = new String[]{"Produkt nummer", "Produkt navn", "Antal", "Enheds pris", "Moms", "Samlet pris"};
		
		tblOrder = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				return false;
			}
		};
		tblOrder.setBounds(12, 182, 766, 237);
		model = new DefaultTableModel();
		
		tblOrder.setModel(model);
		tblOrder.setFillsViewportHeight(true);
		
		scrollPane = new JScrollPane(tblOrder);
		scrollPane.setSize(766, 237);
		tblOrder.setPreferredScrollableViewportSize(new Dimension(766, 237));
    	scrollPane.setPreferredSize(new Dimension(766, 237));
    	scrollPane.setLocation(12, 182);
		contentPane.add(scrollPane);
	}
	
	private void getCustomerInfo(long customerId)
	{
		Customer cust = _customerCtrl.getCustomer(customerId);
		if(cust != null)
		{
			txtName.setText(cust.getPerson().getName());
			txtAddress.setText(cust.getPerson().getAddress());
			txtZipCode.setText(cust.getPerson().getZipCode() + "");
			txtCity.setText(cust.getPerson().getCity());
			if(cust.getIsBusiness())
				txtIsBusiness.setText("Ja");
			else
				txtIsBusiness.setText("Nej");
			for(Discount disc : cust.getDiscounts())
				drpDiscounts.addItem(GlobalUI.translateDiscountTypes(disc.getDiscountType()));
		}
		else
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet nogen kunde med det telefon nummer", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void createOrderLine(long itemNumber, int quantity)
	{
		ProductPhysicalAvail bestFound = null;
    	for (ProductLocation loc : _locCtrl.getAll())
    	{
        	for (ProductPhysicalAvail ppa : loc.getProductCollection().values())
        	{
            	if (ppa.getProduct().getItemNumber() == itemNumber && (bestFound == null || ppa.getQuantity() > bestFound.getQuantity()))
                	bestFound = ppa;
        	}
    	}

    	if(bestFound != null)
    	{	
    		OrderLine line = new OrderLine(bestFound, quantity);
    		_totalPrice += Double.parseDouble(line.getProduct().getProduct().getPrice().toString())*line.getQuantity();
    		_orderLines.add(line);
    	}
    	else
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet et produkt med dette produkt nummer", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void addOrderLineData()
	{
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		for(OrderLine line : _orderLines)
		{
			Object[] row = new Object[]{ line.getProduct().getProduct().getItemNumber(), line.getProduct().getProduct().getItemName(), line.getQuantity(), calcMoms(line.getProduct().getProduct().getPrice().toString()), getMoms(line.getProduct().getProduct().getPrice().toString()), line.getProduct().getProduct().getPrice().toString() };
			model.addRow(row);
		}
	}
	
	private double calcMoms(String price)
	{
		double currentPrice = Double.parseDouble(price);
		double tax = currentPrice*0.20;
		return currentPrice-tax;
	}
	
	private double getMoms(String price)
	{
		double currentPrice = Double.parseDouble(price);
		double tax = currentPrice*0.20;
		return tax;
	}
	
	private void calcTotalPrice()
	{
		txtTotal.setText(_totalPrice + "");
		txtTax.setText(getMoms(_totalPrice + "") + "");
		txtCalcPrice.setText(calcMoms(_totalPrice + "") + "");
	}
	
	private void createOrder()
	{
		try
		{
			long orderId = 0;
			orderId = _orderCtrl.createOrder(GlobalUI.getLoggedInUser().getSalesAssistantId(), Long.parseLong(txtPhoneNo.getText()), txtNotes.getText(), drpDiscounts.getSelectedIndex()+1, 1);
			if(orderId != 0)
			{
				for(OrderLine line : _orderLines)
					_orderCtrl.addOrderLine(orderId, line.getProduct().getProduct().getItemNumber(), line.getQuantity());
				
				JOptionPane.showMessageDialog(null, "Ordren er nu oprettet!", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "Der skete en fejl under oprettelsen af ordren", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}
