package GUILayer.Product.Storage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ControlLayer.ProductLocationCtrl;
import GUILayer.GlobalUI;
import ModelLayer.ProductLocation;
import javax.swing.JComboBox;

public class AddProductUI 
{

	private static JFrame _frame;
	private static AddProductUI _instance;
	private JPanel contentPane;
	private JTextField txtProductNumber;
	private JTextField txtQuantity;
	private JComboBox<String> drpLocations;
	private ProductLocationCtrl _locController;	
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new AddProductUI();
		
		return _frame;
	}

	private AddProductUI() {
		_locController = new ProductLocationCtrl();
		
		_frame = new JFrame();
		_frame.setResizable(false);
		_frame.setTitle("Tilføj et produkt til et lager");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 173);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblStorageId = new JLabel("Produkt nummer");
		lblStorageId.setBounds(12, 14, 128, 15);
		contentPane.add(lblStorageId);
		
		txtProductNumber = new JTextField();
		txtProductNumber.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtProductNumber.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtProductNumber);
				}
			}
		});
		txtProductNumber.setColumns(10);
		txtProductNumber.setBounds(142, 12, 294, 19);
		contentPane.add(txtProductNumber);
		
		
		JLabel lblQuantity = new JLabel("Antal");
		lblQuantity.setBounds(12, 40, 110, 15);
		contentPane.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtQuantity.getText().length() > 0)
				{
					GlobalUI.checkIfInt(txtQuantity);
				}
			}
		});
		txtQuantity.setBounds(142, 38, 294, 19);
		txtQuantity.setColumns(10);
		contentPane.add(txtQuantity);		
		
		JLabel lblStorage = new JLabel("Lager");
		lblStorage.setBounds(12, 67, 70, 15);
		contentPane.add(lblStorage);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(319, 108, 117, 25);
		contentPane.add(btnCancel);
		
		JButton btnCreate = new JButton("Tilføj");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isFormValid())
					addItem();
				else
					JOptionPane.showMessageDialog(null, "Alle felter skal udfyldes", "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnCreate.setBounds(193, 108, 117, 25);
		contentPane.add(btnCreate);
		
		
		drpLocations = new JComboBox<String>();
		drpLocations.setBounds(142, 62, 294, 24);
		addData();
		contentPane.add(drpLocations);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void addItem() 
	{
		boolean succeeded = false;		
		try 
		{
			long itemNumber = Long.parseLong(txtProductNumber.getText());
			int quantity = Integer.parseInt(txtQuantity.getText());
			int locId = drpLocations.getSelectedIndex() + 1;
			succeeded = _locController.addProduct(locId, itemNumber, quantity);
			if(succeeded) 
			{
				JOptionPane.showMessageDialog(null, "Produktet er nu tilføjet til lageret", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				txtProductNumber.setText("");
				txtQuantity.setText("");
				drpLocations.setSelectedIndex(0);
			}
			else
				JOptionPane.showMessageDialog(null, "Der blev ikke fundet et produkt med det produkt nummer", "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void addData()
	{
		try 
		{
			for(ProductLocation loc : _locController.getAll())
				drpLocations.addItem(loc.getLocationName());
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private boolean isFormValid()
	{
		if(txtProductNumber.getText().length() > 0 && txtQuantity.getText().length() > 0)
			return true;
		
		return false;
	}
}
