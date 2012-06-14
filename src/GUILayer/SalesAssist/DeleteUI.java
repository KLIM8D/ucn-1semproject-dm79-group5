package GUILayer.SalesAssist;

import java.awt.Component;
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

public class DeleteUI extends JFrame {

	private SalesAssistantCtrl _saController;
	private static final long serialVersionUID = 8303757512052314425L;
	protected static final Component frame = null;
	private JPanel contentPane;
	private JTextField txtSalesAssistId;

	public DeleteUI() {
		_saController = new SalesAssistantCtrl();
		
		setTitle("Slet ekspedient");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 450, 138);
		setLocationRelativeTo(null);
		setResizable(false);
		
		GUILayer.GlobalUI.setWindowStatus(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
				GUILayer.GlobalUI.setWindowStatus(false);
				setVisible(false);
			}
		});
		btnCancel.setBounds(319, 64, 117, 25);
		contentPane.add(btnCancel);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
			}
		});
	}
	
	private void deleteSalesAssist() {
		
		if(txtSalesAssistId.getText().length() == 0) {
			JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(20), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		else {
			try {
				int salesAsstId = 0;
				SalesAssistant sa = null;
				
				salesAsstId = Integer.parseInt(txtSalesAssistId.getText());
				sa = _saController.getSalesAssistant(salesAsstId);
				if(sa != null) {
					int result = JOptionPane.showConfirmDialog((Component) null, GlobalUI.messageHandling(17), "ADVARSEL!", JOptionPane.OK_CANCEL_OPTION);
					if(result == 0) {
						_saController.removeSalesAssistant(salesAsstId);
						setVisible(false);
						GUILayer.GlobalUI.setWindowStatus(false);
					}			
				}
				else {
					JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(18), "FEJL!", JOptionPane.WARNING_MESSAGE);
				}
				
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}