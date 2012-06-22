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

public class CreateUI 
{

	private static JFrame _frame;
	private static CreateUI _instance;
	private JPanel contentPane;
	private JTextField txtStorageName;
	private JTextField txtStorageAddress;
	private JTextField txtStorageZipCode;
	private JTextField txtStorageCity;
	private ProductLocationCtrl _locController;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new CreateUI();
		
		return _frame;
	}

	private CreateUI() {
		_locController = new ProductLocationCtrl();
		
		_frame = new JFrame();
		_frame.setResizable(false);
		_frame.setTitle("Opret nyt lager");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 166);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStorageName = new JLabel("Lager navn");
		lblStorageName.setBounds(12, 12, 110, 15);
		contentPane.add(lblStorageName);
		
		txtStorageName = new JTextField();
		txtStorageName.setBounds(119, 10, 317, 19);
		txtStorageName.setColumns(10);
		contentPane.add(txtStorageName);		
		
		JLabel lblStorageAddress = new JLabel("Adresse");
		lblStorageAddress.setBounds(12, 39, 70, 15);
		contentPane.add(lblStorageAddress);
		
		txtStorageAddress = new JTextField();
		txtStorageAddress.setBounds(119, 39, 317, 19);
		txtStorageAddress.setColumns(10);
		contentPane.add(txtStorageAddress);
		
		JLabel lblStorageZipCode = new JLabel("Post nr.");
		lblStorageZipCode.setBounds(12, 68, 70, 15);
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
		txtStorageZipCode.setBounds(119, 68, 114, 19);
		txtStorageZipCode.setColumns(10);
		contentPane.add(txtStorageZipCode);
		
		JLabel lblStorageCity = new JLabel("By");
		lblStorageCity.setBounds(238, 68, 70, 15);
		contentPane.add(lblStorageCity);
		
		txtStorageCity = new JTextField();
		txtStorageCity.setBounds(264, 66, 172, 19);
		txtStorageCity.setColumns(10);
		contentPane.add(txtStorageCity);		
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(319, 99, 117, 25);
		contentPane.add(btnCancel);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isFormValid())
					createStorage();
				else
					JOptionPane.showMessageDialog(null, "Alle felter skal udfyldes", "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnCreate.setBounds(193, 99, 117, 25);
		contentPane.add(btnCreate);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void createStorage() 
	{
		boolean succeeded = false;		
		try 
		{
			String storageName = txtStorageName.getText();
			String storageAddress = txtStorageAddress.getText();
			String storageCity = txtStorageCity.getText();
			int storageZipCode = Integer.parseInt(txtStorageZipCode.getText());
			succeeded = _locController.createProductLocation(storageName, storageAddress, storageCity, storageZipCode);
			if(succeeded) 
			{
				JOptionPane.showMessageDialog(null, "Lageret er nu oprettet", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "Lageret kunne ikke oprettes", "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private boolean isFormValid()
	{
		if(txtStorageName.getText().length() > 0 && txtStorageAddress.getText().length() > 0 &&
				txtStorageCity.getText().length() > 0 && txtStorageZipCode.getText().length() > 0)
			return true;
		
		return false;
	}
}
