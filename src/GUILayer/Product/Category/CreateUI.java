package GUILayer.Product.Category;

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
	private JTextField txtCatName;
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
		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 136);
		_frame.setLocationRelativeTo(null);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				_instance = null;
				_frame.dispose();
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
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void createCategory() {
		boolean succeeded = false;
		
		try {
			String categoryName = txtCatName.getText();
			succeeded = _productController.createProductCategory(categoryName);
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
