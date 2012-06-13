package GUILayer.Product;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
//import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ControlLayer.ProductCtrl;
import ControlLayer.ProductLocationCtrl;
import ModelLayer.Product;


public class ShowAllUI extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton editButton;
	private ProductCtrl _prodCtrl;
	
	public ShowAllUI() {
		_prodCtrl = new ProductCtrl();
		setTitle("Vis alle produkter");
		setClosable(true);
		setVisible(true);
		getContentPane().setLayout(new FlowLayout());
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 924, 562);
		setResizable(false);		
		GUILayer.GlobalUI.setWindowStatus(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		editButton = new JButton("Vis");
		
		String[] columnNames = {"Produkt nummer", "Produkt navn", "Minimums beholdning", "Maksimums beholdning", "Pris", "Kategori", " "};
		
		table = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				return false;
			}
		};
		Object[][] data = addData();
		table.setModel(new DefaultTableModel(data, columnNames));
		table.setPreferredScrollableViewportSize(new Dimension(880, 500));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		panel.add(scrollPane);
	}
	
	private Object[][] addData()
	{
		ArrayList<Product> products = makeCollection(_prodCtrl.getAllProducts());
		long listSize = products.size();
		Object[][] data = 
			{
					products.toArray(),
			};
		return data;
	}
	
	public <E> ArrayList<E> makeCollection(Iterable<E> iter)
	{
		ArrayList<E> list = new ArrayList<E>();
		for (E item : iter) 
			list.add(item);
		return list;
	}

}
