package GUILayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.beans.PropertyVetoException;

import javax.swing.JButton;

import GUILayer.Product.ShowAllUI;

public class SystemUI extends JFrame {

	private static final long serialVersionUID = 817624121973761535L;
	private JPanel pnlSystemLayout;
	private JDesktopPane desktopPane;

	public SystemUI() {
		setTitle(GlobalUI.systemInformation(01) + " - " + GlobalUI.systemInformation(02) + " (" + GlobalUI.systemInformation(03) + ")");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		setLocationRelativeTo(null);
		setResizable(false);

		pnlSystemLayout = new JPanel();
		pnlSystemLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlSystemLayout);
		pnlSystemLayout.setLayout(null);
		
		JPanel pnlQuickSelect = new JPanel();
		pnlQuickSelect.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlQuickSelect.setBounds(363, 163, 351, 353);
		pnlSystemLayout.add(pnlQuickSelect);
		pnlQuickSelect.setLayout(null);
		
		JLabel lblTitle = new JLabel("Vestbjerg Byggecenter A/S");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 21));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(12, 12, 327, 39);
		pnlQuickSelect.add(lblTitle);
		
		JSeparator sptDivider = new JSeparator();
		sptDivider.setForeground(Color.BLACK);
		sptDivider.setBounds(12, 55, 327, 1);
		pnlQuickSelect.add(sptDivider);
		
		JButton btnCreateOrder = new JButton("Ny ordre");
		btnCreateOrder.setBounds(12, 68, 327, 35);
		pnlQuickSelect.add(btnCreateOrder);
		
		JButton btnFindOrdre = new JButton("Find ordre");
		btnFindOrdre.setBounds(12, 115, 327, 35);
		pnlQuickSelect.add(btnFindOrdre);
		
		JButton btnNewRental = new JButton("Ny udlejning");
		btnNewRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUILayer.GlobalUI.getWindowStatus() == false) {
					new GUILayer.Rental.CreateUI().setVisible(true);
				}
			}
		});
		btnNewRental.setBounds(12, 162, 327, 35);
		pnlQuickSelect.add(btnNewRental);
		
		JButton btnFindRental = new JButton("Find udlejning");
		btnFindRental.setBounds(12, 209, 327, 35);
		pnlQuickSelect.add(btnFindRental);
		
		JButton btnReturnRental = new JButton("Returnering af udlejning");
		btnReturnRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUILayer.GlobalUI.getWindowStatus() == false) {
					new GUILayer.Rental.ReturnUI().setVisible(true);
				}
			}
		});
		btnReturnRental.setBounds(12, 256, 327, 35);
		pnlQuickSelect.add(btnReturnRental);
		
		JButton btnFindProduct = new JButton("Find produkt");
		btnFindProduct.setBounds(12, 303, 327, 35);
		pnlQuickSelect.add(btnFindProduct);
		
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
		mntmRentalNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUILayer.GlobalUI.getWindowStatus() == false) {
					new GUILayer.Rental.CreateUI().setVisible(true);
				}
			}
		});
		mnRental.add(mntmRentalNew);
		
		JMenuItem mntmRentalFind = new JMenuItem("Find udlejning");
		mnRental.add(mntmRentalFind);
		
		JMenuItem mntmRentalShowAll = new JMenuItem("Vis alle udlejninger");
		mnRental.add(mntmRentalShowAll);
		
		JMenuItem mntmRentalReturn = new JMenuItem("Retunering af udlejning");
		mntmRentalReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUILayer.GlobalUI.getWindowStatus() == false) {
					new GUILayer.Rental.ReturnUI().setVisible(true);
				}
			}
		});
		mnRental.add(mntmRentalReturn);
		
		JMenuItem mntmRentalOverDue = new JMenuItem("Overskredet afleveringsdato");
		mnRental.add(mntmRentalOverDue);
		
		JMenu mnRentalProducts = new JMenu("Udlejningsprodukter");
		mnRental.add(mnRentalProducts);
		
		JMenuItem mntmRentalProductNew = new JMenuItem("Nyt udlejningsprodukt");
		mntmRentalProductNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUILayer.GlobalUI.getWindowStatus() == false) {
					new GUILayer.Rental.Item.CreateUI().setVisible(true);
				}
			}
		});
		mnRentalProducts.add(mntmRentalProductNew);
		
		JMenuItem mntmRentalProductUpdate = new JMenuItem("Opdater udlejningsprodukt");
		mntmRentalProductUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUILayer.GlobalUI.getWindowStatus() == false) {
					new GUILayer.Rental.Item.UpdateUI().setVisible(true);
				}
			}
		});
		mnRentalProducts.add(mntmRentalProductUpdate);
		
		JMenuItem mntmRentalProductDelete = new JMenuItem("Slet udlejningsprodukt");
		mntmRentalProductDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUILayer.GlobalUI.getWindowStatus() == false) {
					new GUILayer.Rental.Item.DeleteUI().setVisible(true);
				}
			}
		});
		mnRentalProducts.add(mntmRentalProductDelete);
		
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
		mntmProductShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JInternalFrame showAllProducts = GUILayer.Product.ShowAllUI.createWindow();
					pnlSystemLayout.add(showAllProducts);
					try 
					{
						showAllProducts.setSelected(true);
					} 
					catch (PropertyVetoException ex) {}
			}
		});
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
