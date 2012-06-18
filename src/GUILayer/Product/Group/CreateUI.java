package GUILayer.Product.Group;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ControlLayer.ProductCtrl;
import GUILayer.GlobalUI;

public class CreateUI {

	private static JFrame _frame;
	private static CreateUI _instance;
	private JPanel contentPane;
	private JTextField txtGroupName;
	private JTextField txtPrice;
	private ProductCtrl _productController;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new CreateUI();
		
		return _frame;
	}

	private CreateUI() {
		_productController = new ProductCtrl();
		
		_frame = new JFrame();
		_frame.setResizable(false);
		_frame.setTitle("Opret produkt gruppe");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 166);
		_frame.setLocationRelativeTo(null);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGroup = new JLabel("Gruppe navn");
		lblGroup.setBounds(12, 12, 110, 15);
		contentPane.add(lblGroup);
		
		txtGroupName = new JTextField();
		txtGroupName.setBounds(119, 10, 317, 19);
		contentPane.add(txtGroupName);
		txtGroupName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Total pris");
		lblPrice.setBounds(12, 39, 70, 15);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(119, 39, 114, 19);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblCurrency = new JLabel("DKK");
		lblCurrency.setBounds(240, 41, 70, 15);
		contentPane.add(lblCurrency);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(319, 93, 117, 25);
		contentPane.add(btnCancel);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createGroup();
			}
		});
		btnCreate.setBounds(193, 93, 117, 25);
		contentPane.add(btnCreate);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void createGroup() {
		boolean succeeded = false;
		
		try {
			String groupName = txtGroupName.getText();
			String groupPrice = txtPrice.getText();
			succeeded = _productController.createProductGroup(groupName, groupPrice);
			if(succeeded) {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(05), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(06), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}
