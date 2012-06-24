package GUILayer.Order;

import java.awt.FlowLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;

public class CreateUI  {

	private static JInternalFrame _frame;
	private static CreateUI _instance;
	private JPanel contentPane;
	private JTextField txtPhoneNo;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtZipCode;
	private JTextField txtCity;
	private JSeparator separator_1;
	private JLabel lblDiscStatus;
	private JTextField txtDiscStatus;
	private JTextField txtIsBusiness;
	private JLabel lblIsBusiness;
	private JLabel lblNotes;
	private JTable tblOrder;
	private JButton btnPerform;
	private JTextField txtTotal;
	private JLabel lblCurrency_03;
	private JLabel lblCurrency_02;
	private JLabel lblCurrency_01;
	private JTextField txtTax;
	private JTextField txtCalcPrice;
	private JLabel lblCalcPrice;
	private JLabel lblTotal;
	private JLabel lblTax;
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new CreateUI();
		
		return _frame;
	}

	private CreateUI() {
		_frame = new JInternalFrame();
		_frame.setTitle("Ny ordre");
		_frame.setClosable(true);
		_frame.setVisible(true);
		_frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		_frame.setBounds(0, 0, 800, 600);
		_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		_frame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
		      public void internalFrameClosing(InternalFrameEvent e) {
		        _frame.dispose();
		        _instance = null;
		      }
		    });
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPhoneNo = new JLabel("Telefon");
		lblPhoneNo.setBounds(12, 12, 70, 15);
		contentPane.add(lblPhoneNo);
		
		JLabel lblName = new JLabel("Navn");
		lblName.setBounds(12, 36, 70, 15);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Adresse");
		lblAddress.setBounds(12, 60, 70, 15);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("By");
		lblCity.setBounds(12, 84, 70, 15);
		contentPane.add(lblCity);
		
		txtPhoneNo = new JTextField();
		txtPhoneNo.setBounds(78, 10, 198, 17);
		contentPane.add(txtPhoneNo);
		txtPhoneNo.setColumns(10);
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setColumns(10);
		txtName.setBounds(78, 35, 198, 17);
		contentPane.add(txtName);
		
		txtAddress = new JTextField();
		txtAddress.setEnabled(false);
		txtAddress.setColumns(10);
		txtAddress.setBounds(78, 60, 198, 17);
		contentPane.add(txtAddress);
		
		txtZipCode = new JTextField();
		txtZipCode.setEnabled(false);
		txtZipCode.setColumns(10);
		txtZipCode.setBounds(78, 85, 40, 19);
		contentPane.add(txtZipCode);
		
		txtCity = new JTextField();
		txtCity.setEnabled(false);
		txtCity.setColumns(10);
		txtCity.setBounds(131, 85, 145, 19);
		contentPane.add(txtCity);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 115, 281, 1);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(293, 12, 1, 158);
		contentPane.add(separator_1);
		
		lblDiscStatus = new JLabel("Rabatstatus");
		lblDiscStatus.setBounds(12, 130, 93, 15);
		contentPane.add(lblDiscStatus);
		
		txtDiscStatus = new JTextField();
		txtDiscStatus.setEnabled(false);
		txtDiscStatus.setColumns(10);
		txtDiscStatus.setBounds(117, 128, 159, 17);
		contentPane.add(txtDiscStatus);
		
		txtIsBusiness = new JTextField();
		txtIsBusiness.setEnabled(false);
		txtIsBusiness.setColumns(10);
		txtIsBusiness.setBounds(117, 153, 159, 17);
		contentPane.add(txtIsBusiness);
		
		lblIsBusiness = new JLabel("Er erhverv");
		lblIsBusiness.setBounds(12, 154, 106, 15);
		contentPane.add(lblIsBusiness);
		
		lblNotes = new JLabel("Salgsnote");
		lblNotes.setBounds(305, 12, 93, 15);
		contentPane.add(lblNotes);
		
		JTextArea txtNotes = new JTextArea();
		txtNotes.setLineWrap(true);
		txtNotes.setBounds(306, 36, 472, 134);
		contentPane.add(txtNotes);
		
		tblOrder = new JTable();
		tblOrder.setBounds(12, 182, 766, 237);
		contentPane.add(tblOrder);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.setBounds(661, 531, 117, 25);
		contentPane.add(btnCancel);
		
		btnPerform = new JButton("Udfør");
		btnPerform.setBounds(532, 531, 117, 25);
		contentPane.add(btnPerform);
		
		txtTotal = new JTextField();
		txtTotal.setEnabled(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(561, 479, 159, 17);
		contentPane.add(txtTotal);
		
		lblCurrency_03 = new JLabel("DKK");
		lblCurrency_03.setBounds(738, 479, 40, 15);
		contentPane.add(lblCurrency_03);
		
		lblCurrency_02 = new JLabel("DKK");
		lblCurrency_02.setBounds(738, 455, 40, 15);
		contentPane.add(lblCurrency_02);
		
		lblCurrency_01 = new JLabel("DKK");
		lblCurrency_01.setBounds(738, 431, 40, 15);
		contentPane.add(lblCurrency_01);
		
		txtTax = new JTextField();
		txtTax.setEnabled(false);
		txtTax.setColumns(10);
		txtTax.setBounds(561, 454, 159, 17);
		contentPane.add(txtTax);
		
		txtCalcPrice = new JTextField();
		txtCalcPrice.setEnabled(false);
		txtCalcPrice.setColumns(10);
		txtCalcPrice.setBounds(561, 429, 159, 17);
		contentPane.add(txtCalcPrice);
		
		lblCalcPrice = new JLabel("Samlet pris");
		lblCalcPrice.setBounds(449, 431, 106, 15);
		contentPane.add(lblCalcPrice);
		
		lblTotal = new JLabel("I alt");
		lblTotal.setBounds(449, 480, 106, 15);
		contentPane.add(lblTotal);
		
		lblTax = new JLabel("Heraf moms");
		lblTax.setBounds(449, 455, 94, 15);
		contentPane.add(lblTax);
	}
}
