package GUILayer.Product.Category;

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

	private static final long serialVersionUID = 3162463258133538444L;
	protected static final Component frame = null;
	private JPanel contentPane;
	private JTextField txtCatName;
	private ProductCtrl _productController;

	public CreateUI() {
		_productController = new ProductCtrl();
		
		setResizable(false);
		setTitle("Opret produkt gruppe");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 136);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		GUILayer.GlobalUI.setWindowStatus(true);
		
		JLabel lblCatName = new JLabel("Kategori navn");
		lblCatName.setBounds(12, 12, 107, 15);
		contentPane.add(lblCatName);
		
		txtCatName = new JTextField();
		txtCatName.setBounds(137, 10, 299, 19);
		contentPane.add(txtCatName);
		txtCatName.setColumns(10);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
				setVisible(false);
			}
		});
		btnCancel.setBounds(319, 64, 117, 25);
		contentPane.add(btnCancel);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCategory();
			}
		});
		btnCreate.setBounds(190, 64, 117, 25);
		contentPane.add(btnCreate);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
			}
		});
	}
	
	private void createCategory() {
		boolean succeeded = false;
		
		try {
			String categoryName = txtCatName.getText();
			succeeded = _productController.createProductCategory(categoryName);
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
