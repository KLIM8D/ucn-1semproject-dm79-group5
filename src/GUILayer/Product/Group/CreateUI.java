package GUILayer.Product.Group;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ControlLayer.ProductCtrl;
import GUILayer.GlobalUI;

public class CreateUI extends JFrame {

	private static final long serialVersionUID = 2789828641175829001L;
	protected static final Component frame = null;
	private JPanel contentPane;
	private JTextField txtGroupName;
	private JTextField txtPrice;
	private ProductCtrl _productController;

	public CreateUI() {
		_productController = new ProductCtrl();
		
		setResizable(false);
		setTitle("Opret produkt gruppe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
				GUILayer.GlobalUI.setWindowStatus(false);
				setVisible(false);
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
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
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
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(05), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				GUILayer.GlobalUI.setWindowStatus(false);
			}
			else {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(06), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}
