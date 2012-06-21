package GUILayer.Statistic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;

import ControlLayer.CustomerCtrl;
import ControlLayer.StatisticCtrl;
import GUILayer.GlobalUI;
import ModelLayer.Customer;

import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.JTabbedPane;

public class CustomerStatsUI {

	private JPanel contentPane;
	
	private static JInternalFrame _frame;
	private static CustomerStatsUI _instance;
	private JComboBox<String> numberOfSalesAsst;
	private JTextField txtPhoneNumber;
	private JPanel pnlChart;
	private JPanel pnlSpecCustomer;
	private JLabel lbltotalOrders;
	private JLabel lbltotalValue;
	//Controllers
	private CustomerCtrl _custCtrl;
	private StatisticCtrl _statCtrl;
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new CustomerStatsUI();
		
		return _frame;
	}
	
	private CustomerStatsUI() 
	{
		_custCtrl = new CustomerCtrl();
		_statCtrl = new StatisticCtrl();
		
		_frame = new JInternalFrame();		
		_frame.setTitle("Generer statistik ud fra kunde");
		_frame.setClosable(true);
		_frame.setMaximizable(false);
		_frame.setVisible(true);
		_frame.setBounds(0, 0, 924, 582);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Tab panel
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(12, 12, 890, 526);
        contentPane.add(tabbedPane);
        
        //Tab1
        pnlSpecCustomer = new JPanel();
        pnlSpecCustomer.setLayout(null);
        JLabel lblPhoneNumber = new JLabel("Indtast telefon nummer: ");
        lblPhoneNumber.setBounds(177, 9, 176, 15);
        pnlSpecCustomer.add(lblPhoneNumber);
        
        txtPhoneNumber = new JTextField();
        txtPhoneNumber.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtPhoneNumber.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtPhoneNumber);
				}
			}
		});
        txtPhoneNumber.setBounds(358, 5, 199, 24);
        pnlSpecCustomer.add(txtPhoneNumber);
        
        JButton btnGetData = new JButton("Hent data");
        btnGetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtPhoneNumber.getText().length() > 0)
				{
					long customerId = Long.parseLong(txtPhoneNumber.getText());
					createCustomerInfo(customerId);
				}
			}
		});
        btnGetData.setBounds(565, 4, 117, 25);
        pnlSpecCustomer.add(btnGetData);
        
        tabbedPane.addTab("Bestemt kunde", null, pnlSpecCustomer, null);
        
        lbltotalOrders = new JLabel();
        lbltotalOrders.setBounds(224, 36, 137, 15);
        pnlSpecCustomer.add(lbltotalOrders);
        lbltotalValue = new JLabel();
        lbltotalValue.setBounds(402, 36, 155, 15);
        pnlSpecCustomer.add(lbltotalValue);
        
        
        
        //Tab1 end
        
        //Tab2
		///Chart
        pnlChart = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tabbedPane.addTab("Top liste", null, pnlChart, null);
        
        JLabel lblNumberOfSalesAsst = new JLabel("Antal kunder: ");
        pnlChart.add(lblNumberOfSalesAsst);
        
        numberOfSalesAsst = new JComboBox<String>();
        numberOfSalesAsst.addItem("10");
        numberOfSalesAsst.addItem("20");
        numberOfSalesAsst.addItem("50");
        numberOfSalesAsst.addItem("100");
        

        pnlChart.add(numberOfSalesAsst);
        
		final CategoryDataset dataset = createDataset(10);
		
		final JFreeChart chart = ChartFactory.createBarChart(
	            "Top 10",        			// chart title
	            "Ekspedienter",             // domain axis label
	            "Kroner",                   // range axis label
	            dataset,                 	// data
	            PlotOrientation.VERTICAL,
	            true,                     	// include legend
	            true,                     	// tooltips?
	            false                     	// URL generator?  Not required...
	        );
		
		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customization...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(875, 270));
        pnlChart.add(chartPanel);
        
        
        
        
        numberOfSalesAsst.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                chart.setTitle("Top " + numberOfSalesAsst.getSelectedItem().toString());
                final CategoryDataset dataset = createDataset(Integer.parseInt(numberOfSalesAsst.getSelectedItem().toString()));
                plot.setDataset(dataset);
            }
        });
        //tab2 END
		
		//Close window
		_frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		_frame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
		      public void internalFrameClosing(InternalFrameEvent e) {
		        _frame.dispose();
		        _instance = null;
		      }
		    });
	}

	private void createCustomerInfo(final long customerId) 
	{
		Customer cust = _custCtrl.getCustomer(customerId);
		if(cust != null)
		{
			long[] customerValues = _statCtrl.generateStatsFromCustomer(customerId);
        	lbltotalOrders.setText("Total antal ordre: " + customerValues[0]);
        	lbltotalValue.setText("Totalt købt for: " + customerValues[1]);
        
        	JFreeChart chartTab1 = createChart(createDatasetForCustomer(customerId));
        	ChartPanel chartPanelTab1 = new ChartPanel(chartTab1);
        	chartPanelTab1.setBounds(104, 63, 680, 420);
        	pnlSpecCustomer.add(chartPanelTab1,5);
        	pnlSpecCustomer.updateUI();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet en kunde med telefon nummeret: " + customerId, "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
			
	}
	
	
	private CategoryDataset createDataset(final int take) 
	{
        final String bottomString = "Købt for total";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(Map.Entry<Long,Customer> entry : _statCtrl.getTopXForCustomer(take).entrySet())
        	dataset.addValue(entry.getKey(), bottomString, entry.getValue().getPerson().getName());

        return dataset;
    }
	
	private PieDataset createDatasetForCustomer(final long customerId) 
	{
        DefaultPieDataset dataset = new DefaultPieDataset();
        long total = 0;
        for(Map.Entry<Long,Customer> entry : _statCtrl.getTopXForCustomer(10000).entrySet())
        {
        	if(entry.getValue().getCustomerId() == customerId)
        		dataset.setValue(entry.getValue().getPerson().getName(), entry.getKey());
        	else
        		total += entry.getKey();
        }
        dataset.setValue("Andre kunder", total);
        return dataset;        
    }
	
	private JFreeChart createChart(PieDataset dataset) 
	{
        
        JFreeChart chart = ChartFactory.createPieChart(
            "% af totalt salg",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
        
    }
}

