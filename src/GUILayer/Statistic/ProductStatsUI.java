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

import ControlLayer.ProductCtrl;
import ControlLayer.StatisticCtrl;
import GUILayer.GlobalUI;
import ModelLayer.Product;

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

public class ProductStatsUI {

	private JPanel contentPane;
	
	private static JInternalFrame _frame;
	private static ProductStatsUI _instance;
	private JComboBox<String> drpNumberOfCustomers;
	private JTextField txtItemNumber;
	private JPanel pnlChart;
	private JPanel pnlSpecProduct;
	private JLabel lbltotalOrders;
	private JLabel lbltotalValue;
	//Controllers
	private ProductCtrl _productCtrl;
	private StatisticCtrl _statCtrl;
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new ProductStatsUI();
		
		return _frame;
	}
	
	private ProductStatsUI() 
	{
		_productCtrl = new ProductCtrl();
		_statCtrl = new StatisticCtrl();
		
		_frame = new JInternalFrame();		
		_frame.setTitle("Generer statistik ud fra produkt");
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
        pnlSpecProduct = new JPanel();
        pnlSpecProduct.setLayout(null);
        JLabel lblItemNumber = new JLabel("Indtast produkt nummer: ");
        lblItemNumber.setBounds(154, 9, 199, 15);
        pnlSpecProduct.add(lblItemNumber);
        
        txtItemNumber = new JTextField();
        txtItemNumber.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtItemNumber.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtItemNumber);
				}
			}
		});
        txtItemNumber.setBounds(358, 5, 199, 24);
        pnlSpecProduct.add(txtItemNumber);
        
        JButton btnGetData = new JButton("Hent data");
        btnGetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtItemNumber.getText().length() > 0)
				{
					long customerId = Long.parseLong(txtItemNumber.getText());
					createProductInfo(customerId);
				}
			}
		});
        btnGetData.setBounds(565, 4, 117, 25);
        pnlSpecProduct.add(btnGetData);
        
        tabbedPane.addTab("Bestemt produkt", null, pnlSpecProduct, null);
        
        lbltotalOrders = new JLabel();
        lbltotalOrders.setBounds(224, 36, 137, 15);
        pnlSpecProduct.add(lbltotalOrders);
        lbltotalValue = new JLabel();
        lbltotalValue.setBounds(402, 36, 155, 15);
        pnlSpecProduct.add(lbltotalValue);
        
        
        
        //Tab1 end
        
        //Tab2
		///Chart
        pnlChart = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tabbedPane.addTab("Top liste", null, pnlChart, null);
        
        JLabel lblNumberOfCustomers = new JLabel("Antal produkter: ");
        pnlChart.add(lblNumberOfCustomers);
        
        drpNumberOfCustomers = new JComboBox<String>();
        drpNumberOfCustomers.addItem("10");
        drpNumberOfCustomers.addItem("100");
        drpNumberOfCustomers.addItem("500");
        drpNumberOfCustomers.addItem("1000");
        

        pnlChart.add(drpNumberOfCustomers);
        
		final CategoryDataset dataset = createDataset(10);
		
		final JFreeChart chart = ChartFactory.createBarChart(
	            "Top 10",        			// chart title
	            "Produkter",                // domain axis label
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
        
        
        
        
        drpNumberOfCustomers.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                chart.setTitle("Top " + drpNumberOfCustomers.getSelectedItem().toString());
                final CategoryDataset dataset = createDataset(Integer.parseInt(drpNumberOfCustomers.getSelectedItem().toString()));
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

	private void createProductInfo(final long itemNumber) 
	{
		Product prod = _productCtrl.getProduct(itemNumber);
		if(prod != null)
		{
			long[] customerValues = _statCtrl.generateStatsFromProduct(itemNumber);
        	lbltotalOrders.setText("Total antal ordre: " + customerValues[0]);
        	lbltotalValue.setText("Totalt købt for: " + customerValues[1]);
        
        	JFreeChart chartTab1 = createChart(createDatasetForProduct(itemNumber));
        	ChartPanel chartPanelTab1 = new ChartPanel(chartTab1);
        	chartPanelTab1.setBounds(104, 63, 680, 420);
        	pnlSpecProduct.add(chartPanelTab1,5);
        	pnlSpecProduct.updateUI();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet et produkt med produkt nummeret: " + itemNumber, "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
			
	}
	
	
	private CategoryDataset createDataset(final int take) 
	{
        final String bottomString = "Solgt for total";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(Map.Entry<Long,Product> entry : _statCtrl.getTopXForProduct(take).entrySet())
        	dataset.addValue(entry.getKey(), bottomString, entry.getValue().getItemName());

        return dataset;
    }
	
	private PieDataset createDatasetForProduct(final long itemNumber) 
	{
        DefaultPieDataset dataset = new DefaultPieDataset();
        long total = 0;
        for(Map.Entry<Long,Product> entry : _statCtrl.getTopXForProduct(100000).entrySet())
        {
        	if(entry.getValue().getItemNumber() == itemNumber)
        		dataset.setValue(entry.getValue().getItemName(), entry.getKey());
        	else
        		total += entry.getKey();
        }
        dataset.setValue("Andre produkter", total);
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

