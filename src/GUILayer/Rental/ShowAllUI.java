package GUILayer.Rental;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ControlLayer.LeaseCtrl;
import GUILayer.GlobalUI;
import ModelLayer.Lease;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JCheckBox;
import javax.swing.JTextField;


public class ShowAllUI 
{

	private JPanel contentPane;
	private JTable table;
	
	private DefaultTableModel model;
	private static JInternalFrame _frame;
	private static ShowAllUI _instance;
	private String[] columnNames;
	private JScrollPane scrollPane;
	private JPanel gridPanel;
	private JLabel lblShowExpired;
	private JButton btnGetData;
	private JCheckBox chkShowExpired;
	private JLabel lblRentalId;
	private JTextField txtRentalId;
	
	//Controllers
	private LeaseCtrl _leaseCtrl;
	
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new ShowAllUI();
		
		return _frame;
	}
	
	@SuppressWarnings("serial")
	private ShowAllUI() 
	{
		_leaseCtrl = new LeaseCtrl();
		model = new DefaultTableModel();
		_frame = new JInternalFrame();		
		_frame.setTitle("Vis alle udlejninger");
		_frame.setClosable(true);
		_frame.setMaximizable(true);
		_frame.setVisible(true);
		_frame.setBounds(0, 0, 924, 330);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Search
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setBounds(5, 5, 904, 35);
		
		lblRentalId = new JLabel("Udlejnings ID:");
		searchPanel.add(lblRentalId);
		
		txtRentalId = new JTextField();
		txtRentalId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtRentalId.getText().length() > 0)
				{
					GlobalUI.checkIfInt(txtRentalId);
				}
			}
		});
		searchPanel.add(txtRentalId);
		txtRentalId.setColumns(10);
				
		lblShowExpired = new JLabel("Vis kun overskredet udlejninger ");
		lblShowExpired.setBounds(187, 10, 126, 15);
		searchPanel.add(lblShowExpired);
				
		btnGetData = new JButton("Hent data");
		btnGetData.setBounds(656, 5, 61, 25);
		btnGetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtRentalId.getText().length() > 0)
				{
					int leaseId = Integer.parseInt(txtRentalId.getText());
					addData(leaseId);
				}
				else if(chkShowExpired.isSelected())
					addDataExpiredLeases();
				else
					addData();
			}
		});
		
		chkShowExpired = new JCheckBox();
		searchPanel.add(chkShowExpired);
				
		
		searchPanel.add(btnGetData);
				
		addData();
				
		contentPane.add(searchPanel);
		//Search end
		
		//Grid / table
		gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(gridPanel);		
		
		columnNames = new String[]{"ID", "Kundenummer", "Produkt nummer", "Serie nummer", "Udlejnings dato", "Afleverings dato"};
		
		table = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				return false;
			}
		};
		
		
		
		table.setModel(model);
		table.setFillsViewportHeight(true);
		addData();
		
		scrollPane = new JScrollPane(table);
		gridPanel.add(scrollPane);		
		
		
		//Close window
		_frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		_frame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
		      public void internalFrameClosing(InternalFrameEvent e) {
		        _frame.dispose();
		        _instance = null;
		      }
		    });
		PropertyChangeListener propertyChangeListener = new PropertyChangeListener()
		{
	        public void propertyChange(PropertyChangeEvent evt) 
	        {
	            autoScaleTable();
	        }			
		};
		_frame.addPropertyChangeListener(propertyChangeListener );
		//Grid / Table end
	}
	
	private void addData()
	{
		ArrayList<Lease> leases = _leaseCtrl.getLease();
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		for(Lease lease : leases)
		{
			Object[] row = new Object[]{lease.getLeaseId() ,lease.getCustomer().getCustomerId(), lease.getSerialNumber().getItemNumber(), lease.getSerialNumber().getSerialNumber(), 
					formatter.format(lease.getRentStartDate()), formatter.format(lease.getRentEndDate())};
			model.addRow(row);
		}
	}
	
	private void addData(int leaseId)
	{
		Lease lease = _leaseCtrl.getLease(leaseId);
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		if(lease != null)
		{
			Object[] row = new Object[]{lease.getLeaseId() ,lease.getCustomer().getCustomerId(), lease.getSerialNumber().getItemNumber(), lease.getSerialNumber().getSerialNumber(), 
					formatter.format(lease.getRentStartDate()), formatter.format(lease.getRentEndDate())};
			model.addRow(row);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet nogen udlejning med det ID", "FEJL!", JOptionPane.WARNING_MESSAGE);
			txtRentalId.setText("");
		}
	}
	
	private void addDataExpiredLeases()
	{
		ArrayList<Lease> leases = _leaseCtrl.expiredLeases();
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		for(Lease lease : leases)
		{
			Object[] row = new Object[]{lease.getLeaseId() ,lease.getCustomer().getCustomerId(), lease.getSerialNumber().getItemNumber(), lease.getSerialNumber().getSerialNumber(), 
					formatter.format(lease.getRentStartDate()), formatter.format(lease.getRentEndDate())};
			model.addRow(row);
		}
	}
	
	private void autoScaleTable() 
	{
		if(_frame.isMaximum())
        {
			gridPanel.setBounds(new Rectangle(0, 40, _frame.getWidth()-8, _frame.getHeight()-120));
        	table.setPreferredScrollableViewportSize(new Dimension(_frame.getWidth()-18, _frame.getHeight()-120));
        	scrollPane.setPreferredSize(new Dimension(_frame.getWidth()-18, _frame.getHeight()-120));
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
        else
        {
        	gridPanel.setBounds(new Rectangle(0, 40, 914, 250));
        	table.setPreferredScrollableViewportSize(new Dimension(904, 250));
        	scrollPane.setPreferredSize(new Dimension(904, 250));
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
	}
}

