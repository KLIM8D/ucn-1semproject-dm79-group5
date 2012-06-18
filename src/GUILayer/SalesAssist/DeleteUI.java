package GUILayer.SalesAssist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import ControlLayer.SalesAssistantCtrl;
import ModelLayer.SalesAssistant;
import GUILayer.GlobalUI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DeleteUI {

	private SalesAssistantCtrl _saController;
	private static JFrame _frame;
	private static DeleteUI _instance;
	private JPanel contentPane;
	private JTextField txtSalesAssistId;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new DeleteUI();
		
		return _frame;
	}

	private DeleteUI() {
		_saController = new SalesAssistantCtrl();
		
		_frame = new JFrame();
		_frame.setTitle("Slet ekspedient");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 138);
		_frame.setLocationRelativeTo(null);
		_frame.setResizable(false);
		_frame.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSalesAssistId = new JLabel("Ekspedient Id");
		lblSalesAssistId.setBounds(12, 12, 121, 15);
		contentPane.add(lblSalesAssistId);
		
		txtSalesAssistId = new JTextField();
		txtSalesAssistId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtSalesAssistId.getText().length() > 0)
				{				
					GlobalUI.checkIfInt(txtSalesAssistId);				
				}
			}
		});
		txtSalesAssistId.setBounds(127, 10, 309, 19);
		contentPane.add(txtSalesAssistId);
		txtSalesAssistId.setColumns(10);
		
		JButton btnDelete = new JButton("Slet");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSalesAssist();
			}
		});
		btnDelete.setBounds(190, 64, 117, 25);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(319, 64, 117, 25);
		contentPane.add(btnCancel);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void deleteSalesAssist() {
		
		if(txtSalesAssistId.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(20), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		else {
			try {
				int salesAsstId = 0;
				SalesAssistant sa = null;
				
				salesAsstId = Integer.parseInt(txtSalesAssistId.getText());
				sa = _saController.getSalesAssistant(salesAsstId);
				if(sa != null) {
					int result = JOptionPane.showConfirmDialog(null, GlobalUI.messageHandling(17), "ADVARSEL!", JOptionPane.OK_CANCEL_OPTION);
					if(result == 0) {
						_saController.removeSalesAssistant(salesAsstId);
						JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(22), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
						_instance = null;
						_frame.dispose();
					}			
				}
				else {
					JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(18), "FEJL!", JOptionPane.WARNING_MESSAGE);
				}
				
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
