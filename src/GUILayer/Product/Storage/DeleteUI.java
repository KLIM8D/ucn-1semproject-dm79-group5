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

public class DeleteUI 
{

	private static JFrame _frame;
	private static DeleteUI _instance;
	private JPanel contentPane;
	private ProductLocationCtrl _locController;
	private JTextField txtStorageId;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new DeleteUI();
		
		return _frame;
	}

	private DeleteUI() {
		_locController = new ProductLocationCtrl();
		
		_frame = new JFrame();
		_frame.setResizable(false);
		_frame.setTitle("Opdater lager information");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 100);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(319, 43, 117, 25);
		contentPane.add(btnCancel);
		
		JButton btnCreate = new JButton("Slet");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtStorageId.getText().length() > 0)
					updateStorage();
			}
		});
		btnCreate.setBounds(193, 43, 117, 25);
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
		txtStorageId.setBounds(119, 12, 317, 19);
		contentPane.add(txtStorageId);
		
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
			int proceed = showConfirmDialog();
			if(proceed == 1)
			{
				int storageId = Integer.parseInt(txtStorageId.getText());
				succeeded = _locController.removeLocation(storageId);
				if(succeeded) 
				{
					JOptionPane.showMessageDialog(null, "Lageret er nu slettet", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
					_instance = null;
					_frame.dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "Lagerets information kunne ikke opdateres", "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, "Handlingen blev afbrudt. Lageret blev ikke slettet", "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private int showConfirmDialog()
	{
		int option = JOptionPane.showConfirmDialog (null, "Er du sikker på du vil slette lageret? Bemærk handlingen kan ikke fortrydes");
		if (option == JOptionPane.YES_OPTION )
			return 1;
		
		return 0;
	}
}
