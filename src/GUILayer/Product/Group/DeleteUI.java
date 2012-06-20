package GUILayer.Product.Group;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import ControlLayer.ProductCtrl;
import GUILayer.GlobalUI;

public class DeleteUI {

	private static DeleteUI _instance;
	private static JFrame _frame;
	private JPanel contentPane;
	private JButton btnDeleteProductGroup;
	private JTextField txtProdGroupId;
	private JLabel lblProdGroupId;
	
	//Controllers
	private ProductCtrl _prodCtrl;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new DeleteUI();
		
		return _frame;
	}
	
	private DeleteUI() 
	{
		_prodCtrl = new ProductCtrl();
		
		_frame = new JFrame();		
		_frame.setTitle("Slet produkt gruppe");
		_frame.setVisible(true);
		_frame.setResizable(false);
		_frame.setBounds(0, 0, 489, 100);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		_frame.setContentPane(contentPane);
		
		//Top layout
		JPanel topPanel = new JPanel();
		topPanel.setBounds(5, 0, 489, 69);
		topPanel.setLayout(null);
		txtProdGroupId = new JTextField();
		txtProdGroupId.setBounds(222, 12, 251, 20);
		txtProdGroupId.setColumns(10);
		txtProdGroupId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtProdGroupId.getText().length() > 0)
				{
					GlobalUI.checkIfInt(txtProdGroupId);
				}
			}
		});
		lblProdGroupId = new JLabel("Indtast produkt gruppe id: ");
		lblProdGroupId.setBounds(12, 12, 192, 15);
		topPanel.add(lblProdGroupId);
		topPanel.add(txtProdGroupId);
		btnDeleteProductGroup = new JButton("Slet");
		btnDeleteProductGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtProdGroupId.getText().length() > 0)
				{
					int prodGroupId = Integer.parseInt(txtProdGroupId.getText());
					deleteProductGroup(prodGroupId);
				}
			}
		});
		
		btnDeleteProductGroup.setBounds(222, 38, 122, 25);
		topPanel.add(btnDeleteProductGroup);
		contentPane.add(topPanel);
		JButton btnCancel = new JButton("Annuller");
		btnCancel.setBounds(356, 38, 117, 25);
		topPanel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		
		//Close window
		_frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_frame.dispose();
		        _instance = null;
			}
		});
	}
	
	private void deleteProductGroup(int prodGroupId)
	{
		boolean succeeded = false;
		try
		{
			int proceed = showConfirmDialog();
			if(proceed == 1)
			{
				succeeded = _prodCtrl.removeProductGroup(prodGroupId);
				if(succeeded)
					JOptionPane.showMessageDialog(null, "Produkte gruppen er nu slettet", "Gennemført!", JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Der skete en fejl da produkt gruppen skulle slettes", "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, "Handlingen blev afbrudt. Produkt gruppen blev ikke slettet", "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private int showConfirmDialog()
	{
		int option = JOptionPane.showConfirmDialog (null, "Er du sikker på du vil slettet denne produkt gruppe? Bemærk handlingen kan ikke fortrydes");
		if (option == JOptionPane.YES_OPTION )
			return 1;
		
		return 0;
	}

}
