package GUILayer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class SystemUI extends JFrame {

	private static final long serialVersionUID = 817624121973761535L;
	private JPanel pnlSystemLayout;

	public SystemUI() {
		setTitle(GlobalUI.systemInformation(01) + " - " + GlobalUI.systemInformation(02) + " (" + GlobalUI.systemInformation(03) + ")");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		setLocationRelativeTo(null);
		setResizable(false);

		pnlSystemLayout = new JPanel();
		pnlSystemLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlSystemLayout.setLayout(new BorderLayout(0, 0));
		setContentPane(pnlSystemLayout);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFiles = new JMenu("Filer");
		menuBar.add(mnFiles);
		
		JMenuItem mntmLogout = new JMenuItem("Logud");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GUILayer.LoginUI().setVisible(true);
				dispose();
			}
		});
		mnFiles.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Afslut");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		mnFiles.add(mntmExit);
		
		JMenu mnFunctions = new JMenu("Funktioner");
		menuBar.add(mnFunctions);
		
		JMenu mnSales = new JMenu("Salg");
		mnFunctions.add(mnSales);
		
		JMenuItem mntmOrdreNew = new JMenuItem("Ny ordre");
		mnSales.add(mntmOrdreNew);
		
		JMenuItem mntmOrdreFind = new JMenuItem("Find ordre");
		mnSales.add(mntmOrdreFind);
		
		JMenuItem mntmOrdreCancel = new JMenuItem("Annullering af ordre");
		mnSales.add(mntmOrdreCancel);
		
		JMenu mnRental = new JMenu("Udlejning");
		mnFunctions.add(mnRental);
		
		JMenuItem mntmRentalNew = new JMenuItem("Ny udlejning");
		mnRental.add(mntmRentalNew);
		
		JMenuItem mntmRentalFind = new JMenuItem("Find udlejning");
		mnRental.add(mntmRentalFind);
		
		JMenuItem mntmRentalReturn = new JMenuItem("Retunering af udlejning");
		mnRental.add(mntmRentalReturn);
		
		JMenu mnCustomer = new JMenu("Kundekartotek");
		mnFunctions.add(mnCustomer);
		
		JMenuItem mntmPersonCreate = new JMenuItem("Opret person");
		mnCustomer.add(mntmPersonCreate);
		
		JMenuItem mntmPersonFind = new JMenuItem("Find person");
		mnCustomer.add(mntmPersonFind);
		
		JMenuItem mntmPersonDelete = new JMenuItem("Slet person");
		mnCustomer.add(mntmPersonDelete);
		
		JMenu mnSalesAssistant = new JMenu("Ekspedientkartotek");
		mnFunctions.add(mnSalesAssistant);
		
		JMenuItem mntmSalesAssistCreate = new JMenuItem("Opret ekspedient");
		mnSalesAssistant.add(mntmSalesAssistCreate);
		
		JMenuItem mntmSalesAssistInfo = new JMenuItem("Vis ekspedient information");
		mnSalesAssistant.add(mntmSalesAssistInfo);
		
		JMenuItem mntmSalesAssistDelete = new JMenuItem("Slet ekspedient");
		mnSalesAssistant.add(mntmSalesAssistDelete);
		
		JMenuItem mntmSalesAssistShowAll = new JMenuItem("Vis alle ekspedienter");
		mnSalesAssistant.add(mntmSalesAssistShowAll);
		
		JMenuItem mntmSalesAssistChangePassword = new JMenuItem("Skift adgangskode");
		mnSalesAssistant.add(mntmSalesAssistChangePassword);
		
		JMenu mnProduct = new JMenu("Produkt");
		mnFunctions.add(mnProduct);
		
		JMenuItem mntmProductCreate = new JMenuItem("Opret produkt");
		mntmProductCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUILayer.GlobalUI.getWindowStatus() == false) {
					new GUILayer.Product.CreateUI().setVisible(true);
				}
			}
		});
		mnProduct.add(mntmProductCreate);
		
		JMenuItem mntmProductFind = new JMenuItem("Find produkt");
		mnProduct.add(mntmProductFind);
		
		JMenuItem mntmProductShowAll = new JMenuItem("Vis alle produkter");
		mnProduct.add(mntmProductShowAll);
		
		JMenuItem mntmProductUpdate = new JMenuItem("Opdater produkt");
		mnProduct.add(mntmProductUpdate);
		
		JMenuItem mntmProductDelete = new JMenuItem("Slet produkt");
		mnProduct.add(mntmProductDelete);
		
		JSeparator mntmProductSeparator = new JSeparator();
		mnProduct.add(mntmProductSeparator);
		
		JMenu mnProductCat = new JMenu("Produkt kategorier");
		mnProduct.add(mnProductCat);
		
		JMenuItem mntmProductCatCreate = new JMenuItem("Opret produkt kategori");
		mntmProductCatCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUILayer.GlobalUI.getWindowStatus() == false) {
					new GUILayer.Product.Category.CreateUI().setVisible(true);
				}
			}
		});
		mnProductCat.add(mntmProductCatCreate);
		
		JMenuItem mntmProductCatFind = new JMenuItem("Find produkt kategori");
		mnProductCat.add(mntmProductCatFind);
		
		JMenuItem mntmProductCatShowAll = new JMenuItem("Vis alle produkt kategorier");
		mnProductCat.add(mntmProductCatShowAll);
		
		JMenuItem mntmProductCatDelete = new JMenuItem("Slet produkt kategori");
		mnProductCat.add(mntmProductCatDelete);
		
		JMenu mnProductGroup = new JMenu("Produkt grupper");
		mnProduct.add(mnProductGroup);
		
		JMenuItem mntmProductGroupCreate = new JMenuItem("Opret produkt gruppe");
		mntmProductGroupCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUILayer.GlobalUI.getWindowStatus() == false) {
					new GUILayer.Product.Group.CreateUI().setVisible(true);
				}
			}
		});
		mnProductGroup.add(mntmProductGroupCreate);
		
		JMenuItem mntmProductGroupAddToGroup = new JMenuItem("Tilføj et produkt til en produkt gruppe");
		mnProductGroup.add(mntmProductGroupAddToGroup);
		
		JMenuItem mntmProductGroupUpdate = new JMenuItem("Opdater produkt gruppe");
		mnProductGroup.add(mntmProductGroupUpdate);
		
		JMenuItem mntmProductGroupFind = new JMenuItem("Find produkt gruppe");
		mnProductGroup.add(mntmProductGroupFind);
		
		JMenuItem mntmProductGroupShowAll = new JMenuItem("Vis alle produkt grupper");
		mnProductGroup.add(mntmProductGroupShowAll);
		
		JMenuItem mntmProductGroupDelete = new JMenuItem("Slet produkt gruppe");
		mnProductGroup.add(mntmProductGroupDelete);
		
		JMenu mnStatistics = new JMenu("Statistik");
		mnFunctions.add(mnStatistics);
		
		JMenuItem mntmStatisticsSalesAssist = new JMenuItem("Generere statistik ud fra ekspedient");
		mnStatistics.add(mntmStatisticsSalesAssist);
		
		JMenuItem mntmStatisticsProduct = new JMenuItem("Generere statistik ud fra vare");
		mnStatistics.add(mntmStatisticsProduct);
		
		JMenu mnAbout = new JMenu("Om");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAboutApp = new JMenuItem("Applikationen");
		mntmAboutApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUILayer.GlobalUI.getWindowStatus() == false) {
					new GUILayer.AboutUI().setVisible(true);
				}
				
			}
		});
		mnAbout.add(mntmAboutApp);
	}

}
