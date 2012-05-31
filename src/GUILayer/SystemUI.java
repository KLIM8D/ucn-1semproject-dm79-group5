package GUILayer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SystemUI extends JFrame {

	private static final long serialVersionUID = 817624121973761535L;
	private JPanel pnlSystemLayout;

	public SystemUI() {
		setTitle(GlobalUI.systemInformation(01) + " - " + GlobalUI.systemInformation(02) + " (" + GlobalUI.systemInformation(03) + ")");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		setLocationRelativeTo(null);

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
		
		JMenu mnStock = new JMenu("Lager");
		mnFunctions.add(mnStock);
		
		JMenuItem mntmOpretProdukt = new JMenuItem("Opret produkt");
		mnStock.add(mntmOpretProdukt);
		
		JMenuItem mntmFindProdukt = new JMenuItem("Find produkt");
		mnStock.add(mntmFindProdukt);
		
		JMenuItem mntmSletProdukt = new JMenuItem("Slet produkt");
		mnStock.add(mntmSletProdukt);
		
		JMenu mnEconomy = new JMenu("Økonomi");
		mnFunctions.add(mnEconomy);
		
		JMenuItem mntmEconomyStatsSalesAssist = new JMenuItem("Generere statistik ud fra ekspedient");
		mnEconomy.add(mntmEconomyStatsSalesAssist);
		
		JMenuItem mntmEconomyStatsProduct = new JMenuItem("Generere statistik ud fra vare");
		mnEconomy.add(mntmEconomyStatsProduct);
		
		JMenu mnAbout = new JMenu("Om....");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAboutSystem = new JMenuItem("System");
		mntmAboutSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlobalUI.aboutSystem();
			}
		});
		mnAbout.add(mntmAboutSystem);
		
		JMenuItem mntmAboutLicense = new JMenuItem("Licens");
		mntmAboutLicense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlobalUI.aboutLicense();
			}
		});
		mnAbout.add(mntmAboutLicense);
	}

}
