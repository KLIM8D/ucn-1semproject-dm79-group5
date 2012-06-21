package GUILayer.Statistic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;

import ControlLayer.SalesAssistantCtrl;
import ControlLayer.StatisticCtrl;
import ModelLayer.SalesAssistant;

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

public class SalesAsstStatsUI {

	private JPanel contentPane;
	
	private static JInternalFrame _frame;
	private static SalesAsstStatsUI _instance;
	private JComboBox<String> numberOfSalesAsst;
	private JComboBox<String> drpSalesAsst;
	private JPanel pnlChart;
	private JPanel pnlSpecSalesAsst;
	private JLabel lbltotalOrders;
	private JLabel lbltotalValue;
	//Controllers
	private StatisticCtrl _statCtrl;
	private SalesAssistantCtrl _salesAsstCtrl;
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new SalesAsstStatsUI();
		
		return _frame;
	}
	
	private SalesAsstStatsUI() 
	{
		_statCtrl = new StatisticCtrl();
		_salesAsstCtrl = new SalesAssistantCtrl();
		
		_frame = new JInternalFrame();		
		_frame.setTitle("Generer statistik ud fra ekspedient");
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
        pnlSpecSalesAsst = new JPanel();
        pnlSpecSalesAsst.setLayout(null);
        JLabel lblChooseSalesAsst = new JLabel("Vælg ekspedient: ");
        lblChooseSalesAsst.setBounds(224, 9, 129, 15);
        pnlSpecSalesAsst.add(lblChooseSalesAsst);
        drpSalesAsst = new JComboBox<String>();
        drpSalesAsst.setBounds(358, 5, 199, 24);
        for(SalesAssistant sa : _salesAsstCtrl.getAllSalesAssistants())
        	drpSalesAsst.addItem(sa.getPerson().getName());
        
        pnlSpecSalesAsst.add(drpSalesAsst);
        tabbedPane.addTab("Bestemt ekspedient", null, pnlSpecSalesAsst, null);
        
        lbltotalOrders = new JLabel();
        lbltotalOrders.setBounds(224, 36, 137, 15);
        pnlSpecSalesAsst.add(lbltotalOrders);
        lbltotalValue = new JLabel();
        lbltotalValue.setBounds(402, 36, 155, 15);
        pnlSpecSalesAsst.add(lbltotalValue);
        createSalesAsstInfo(drpSalesAsst.getSelectedIndex()+1);
        
        JFreeChart chartTab1 = createChart(createDatasetForSalesAsst(drpSalesAsst.getSelectedIndex()+1));
        ChartPanel chartPanelTab1 = new ChartPanel(chartTab1);
        chartPanelTab1.setBounds(104, 63, 680, 420);
        pnlSpecSalesAsst.add(chartPanelTab1);
        
        drpSalesAsst.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) 
            {            	
            	pnlSpecSalesAsst.remove(4);
            	JFreeChart chartTab1 = createChart(createDatasetForSalesAsst(drpSalesAsst.getSelectedIndex()+1));            	
            	ChartPanel chartPanelTab1= new ChartPanel(chartTab1);
            	chartPanelTab1.setBounds(104, 63, 680, 420);
            	pnlSpecSalesAsst.add(chartPanelTab1);
            	createSalesAsstInfo(drpSalesAsst.getSelectedIndex()+1);
            	pnlSpecSalesAsst.updateUI();
            }
        });
        //Tab1 end
        
        //Tab2
		///Chart
        pnlChart = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tabbedPane.addTab("Top liste", null, pnlChart, null);
        
        JLabel lblNumberOfSalesAsst = new JLabel("Antal ekspedienter: ");
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

	private void createSalesAsstInfo(final int salesAsstId) 
	{
		long[] salesAsstValues = _statCtrl.generateStatsFromSalesAsst(salesAsstId);
        lbltotalOrders.setText("Total antal ordre: " + salesAsstValues[0]);
        lbltotalValue.setText("Totalt solgt for: " + salesAsstValues[1]);
	}
	
	
	private CategoryDataset createDataset(final int take) 
	{
        final String bottomString = "Solgt for total";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(Map.Entry<Long,SalesAssistant> entry : _statCtrl.getTopXForSalesAsst(take).entrySet())
        	dataset.addValue(entry.getKey(), bottomString, entry.getValue().getPerson().getName());

        return dataset;
    }
	
	private PieDataset createDatasetForSalesAsst(final int salesAsstId) 
	{
        DefaultPieDataset dataset = new DefaultPieDataset();
        long total = 0;
        for(Map.Entry<Long,SalesAssistant> entry : _statCtrl.getTopXForSalesAsst(10000).entrySet())
        {
        	if(entry.getValue().getSalesAssistantId() == salesAsstId)
        		dataset.setValue(entry.getValue().getPerson().getName(), entry.getKey());
        	else
        		total += entry.getKey();
        }
        dataset.setValue("Andre ekspedienter", total);
        return dataset;        
    }
	
	private JFreeChart createChart(PieDataset dataset) {
        
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

