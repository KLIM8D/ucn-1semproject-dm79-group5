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

import javax.swing.JSeparator;

public class UpdateUI 
{

	private static JFrame _frame;
	private static UpdateUI _instance;
	private JPanel contentPane;
	private JTextField txtStorageName;
	private JTextField txtStorageAddress;
	private JTextField txtStorageZipCode;
	private JTextField txtStorageCity;
	private ProductLocationCtrl _locController;
	private JTextField txtStorageId;
	private int storageId;
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new UpdateUI();
		
		return _frame;
	}

	private UpdateUI() {
		_locController = new ProductLocationCtrl();
		
		_frame = new JFrame();
		_frame.setResizable(false);
		_frame.setTitle("Opdater lager information");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 210);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStorageName = new JLabel("Lager navn");
		lblStorageName.setBounds(12, 61, 110, 15);
		contentPane.add(lblStorageName);
		
		txtStorageName = new JTextField();
		txtStorageName.setBounds(119, 59, 317, 19);
		txtStorageName.setColumns(10);
		contentPane.add(txtStorageName);		
		
		JLabel lblStorageAddress = new JLabel("Adresse");
		lblStorageAddress.setBounds(12, 88, 70, 15);
		contentPane.add(lblStorageAddress);
		
		txtStorageAddress = new JTextField();
		txtStorageAddress.setBounds(119, 88, 317, 19);
		txtStorageAddress.setColumns(10);
		contentPane.add(txtStorageAddress);
		
		JLabel lblStorageZipCode = new JLabel("Post nr.");
		lblStorageZipCode.setBounds(12, 117, 70, 15);
		contentPane.add(lblStorageZipCode);
		
		txtStorageZipCode = new JTextField();
		txtStorageZipCode.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtStorageZipCode.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtStorageZipCode);
				}
			}
		});
		txtStorageZipCode.setBounds(119, 117, 114, 19);
		txtStorageZipCode.setColumns(10);
		contentPane.add(txtStorageZipCode);
		
		JLabel lblStorageCity = new JLabel("By");
		lblStorageCity.setBounds(238, 117, 70, 15);
		contentPane.add(lblStorageCity);
		
		txtStorageCity = new JTextField();
		txtStorageCity.setBounds(264, 115, 172, 19);
		txtStorageCity.setColumns(10);
		contentPane.add(txtStorageCity);		
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(319, 148, 117, 25);
		contentPane.add(btnCancel);
		
		JButton btnCreate = new JButton("Opdater");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isFormValid())
					updateStorage();
				else
					JOptionPane.showMessageDialog(null, "Alle felter skal udfyldes", "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnCreate.setBounds(193, 148, 117, 25);
		contentPane.add(btnCreate);
		
		JLabel lblStorageId = new JLabel("Lager ID");
		lblStorageId.setBounds(12, 14, 110, 15);
		contentPane.add(lblStorageId);
		
		txtStorageId = new JTextField();
		txtStorageId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtStorageId.getText().length() > 0)
				{
					GlobalUI.checkIfInt(txtStorageId);
				}
			}
		});
		txtStorageId.setColumns(10);
		txtStorageId.setBounds(119, 12, 189, 19);
		contentPane.add(txtStorageId);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 47, 400, 2);
		contentPane.add(separator);
		
		JButton btnGetData = new JButton("Hent data");
		btnGetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtStorageId.getText().length() > 0)
				{
					storageId = Integer.parseInt(txtStorageId.getText());
					addData(storageId);
				}
			}
		});
		btnGetData.setBounds(319, 9, 117, 25);
		contentPane.add(btnGetData);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void updateStorage() 
	{
		boolean succeeded = false;		
		try 
		{
			String storageName = txtStorageName.getText();
			String storageAddress = txtStorageAddress.getText();
			String storageCity = txtStorageCity.getText();
			int storageZipCode = Integer.parseInt(txtStorageZipCode.getText());
			succeeded = _locController.updateLocation(storageId, storageName, storageAddress, storageCity, storageZipCode);
			if(succeeded) 
			{
				JOptionPane.showMessageDialog(null, "Lagerets information er nu opdateret", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "Lagerets information kunne ikke opdateres", "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void addData(int locId)
	{
		ProductLocation location = _locController.getLocation(locId);
		if(location != null)
		{
			txtStorageName.setText(location.getLocationName());
			txtStorageAddress.setText(location.getAddress());
			txtStorageCity.setText(location.getCity());
			txtStorageZipCode.setText(location.getZipCode() + "");
		}
		else
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet et lager med det ID", "FEJL!", JOptionPane.WARNING_MESSAGE);
	}
	
	private boolean isFormValid()
	{
		if(txtStorageName.getText().length() > 0 && txtStorageAddress.getText().length() > 0 &&
				txtStorageCity.getText().length() > 0 && txtStorageZipCode.getText().length() > 0)
			return true;
		
		return false;
	}
}
