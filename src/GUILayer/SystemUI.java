package GUILayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.beans.PropertyVetoException;
import javax.swing.JButton;


public class SystemUI extends JFrame implements ComponentListener {

	private static final long serialVersionUID = 817624121973761535L;
	private JPanel pnlSystemLayout;
	private JLayeredPane layeredPane;
	private JPanel pnlQuickSelect;

	public SystemUI() {
		setTitle(GlobalUI.systemInformation(01) + " - " + GlobalUI.systemInformation(02) + " (" + GlobalUI.systemInformation(03) + ")");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1024, 768));
		setResizable(true);
		pnlSystemLayout = new JPanel();
		pnlSystemLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlSystemLayout);
		pnlSystemLayout.setLayout(null);
		
		layeredPane = new JDesktopPane();
		layeredPane.setOpaque(false);
		layeredPane.setBounds(0, 0, getWidth(), getHeight());
		getContentPane().add(layeredPane, BorderLayout.CENTER);
		addComponentListener(this);
		
		//Quickselect menu
		pnlQuickSelect = new JPanel();
		pnlQuickSelect.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlQuickSelect.setBounds(363, 163, 351, 353);
		layeredPane.add(pnlQuickSelect, JLayeredPane.FRAME_CONTENT_LAYER);
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
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame newOrder = GUILayer.Order.CreateUI.createWindow();
				if(!layeredPane.isAncestorOf(newOrder))
				{
					layeredPane.add(newOrder, JLayeredPane.DEFAULT_LAYER);
				}
				layeredPane.moveToFront(newOrder);
				try 
				{
					newOrder.setSelected(true);
				} 
				catch (PropertyVetoException ex) {}
			}
		});
		btnCreateOrder.setBounds(12, 68, 327, 35);
		pnlQuickSelect.add(btnCreateOrder);
		
		JButton btnFindOrdre = new JButton("Find ordre");
		btnFindOrdre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame findOrder = GUILayer.Order.FindUI.createWindow();
				if(!layeredPane.isAncestorOf(findOrder))
				{
					layeredPane.add(findOrder, JLayeredPane.DEFAULT_LAYER);
				}
				layeredPane.moveToFront(findOrder);
				try 
				{
					findOrder.setSelected(true);
				} 
				catch (PropertyVetoException ex) {}
			}
		});
		btnFindOrdre.setBounds(12, 115, 327, 35);
		pnlQuickSelect.add(btnFindOrdre);
		
		JButton btnNewRental = new JButton("Ny udlejning");
		btnNewRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame createFrame = GUILayer.Rental.CreateUI.createWindow();
					createFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		btnNewRental.setBounds(12, 162, 327, 35);
		pnlQuickSelect.add(btnNewRental);
		
		JButton btnFindRental = new JButton("Find udlejning");
		btnFindRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame showAllRentals = GUILayer.Rental.ShowAllUI.createWindow();
				layeredPane.add(showAllRentals, JLayeredPane.DEFAULT_LAYER);
				layeredPane.moveToFront(showAllRentals);
				try 
				{
					showAllRentals.setSelected(true);
				} 
				catch (PropertyVetoException ex) {}
			}
		});
		btnFindRental.setBounds(12, 209, 327, 35);
		pnlQuickSelect.add(btnFindRental);
		
		JButton btnReturnRental = new JButton("Returnering af udlejning");
		btnReturnRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame returnFrame = GUILayer.Rental.ReturnUI.createWindow();
					returnFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		btnReturnRental.setBounds(12, 256, 327, 35);
		pnlQuickSelect.add(btnReturnRental);
		
		JButton btnFindProduct = new JButton("Vis alle produkter");
		btnFindProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JInternalFrame showAllProducts = GUILayer.Product.ShowAllUI.createWindow();
					if(!layeredPane.isAncestorOf(showAllProducts))
					{
						layeredPane.add(showAllProducts, JLayeredPane.DEFAULT_LAYER);
					}
					layeredPane.moveToFront(showAllProducts);
					try 
					{
						showAllProducts.setSelected(true);
					} 
					catch (PropertyVetoException ex) {}
			}
		});
		btnFindProduct.setBounds(12, 303, 327, 35);
		pnlQuickSelect.add(btnFindProduct);
		//Quickselect end
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//System menu
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
		//System menu end
		
		JMenu mnFunctions = new JMenu("Funktioner");
		menuBar.add(mnFunctions);
		
		//Order menu
		JMenu mnSales = new JMenu("Salg");
		mnFunctions.add(mnSales);
		
		JMenuItem mntmOrdreNew = new JMenuItem("Ny ordre");
		mntmOrdreNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame newOrder = GUILayer.Order.CreateUI.createWindow();
				if(!layeredPane.isAncestorOf(newOrder))
				{
					layeredPane.add(newOrder, JLayeredPane.DEFAULT_LAYER);
				}
				layeredPane.moveToFront(newOrder);
				try 
				{
					newOrder.setSelected(true);
				} 
				catch (PropertyVetoException ex) {}
			}
		});
		mnSales.add(mntmOrdreNew);
		
		JMenuItem mntmOrdreFind = new JMenuItem("Find ordre");
		mntmOrdreFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame findOrder = GUILayer.Order.FindUI.createWindow();
				if(!layeredPane.isAncestorOf(findOrder))
				{
					layeredPane.add(findOrder, JLayeredPane.DEFAULT_LAYER);
				}
				layeredPane.moveToFront(findOrder);
				try 
				{
					findOrder.setSelected(true);
				} 
				catch (PropertyVetoException ex) {}
			}
		});
		mnSales.add(mntmOrdreFind);
		
		JMenuItem mntmOrdreCancel = new JMenuItem("Ændre status på ordre");
		mntmOrdreCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame returnFrame = GUILayer.Order.ChangeStatusUI.createWindow();
				returnFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnSales.add(mntmOrdreCancel);
		//Order menu end
		
		//Rental menu
		JMenu mnRental = new JMenu("Udlejning");
		mnFunctions.add(mnRental);
		
		JMenuItem mntmRentalNew = new JMenuItem("Ny udlejning");
		mntmRentalNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame  createFrame = GUILayer.Rental.CreateUI.createWindow();
					createFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnRental.add(mntmRentalNew);
		
		JMenuItem mntmRentalShowAll = new JMenuItem("Vis alle udlejninger");
		mntmRentalShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame showAllRentals = GUILayer.Rental.ShowAllUI.createWindow();
				layeredPane.add(showAllRentals, JLayeredPane.DEFAULT_LAYER);
				layeredPane.moveToFront(showAllRentals);
				try 
				{
					showAllRentals.setSelected(true);
				} 
				catch (PropertyVetoException ex) {}
			}
		});
		mnRental.add(mntmRentalShowAll);
		
		JMenuItem mntmRentalReturn = new JMenuItem("Retunering af udlejning");
		mntmRentalReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame returnFrame = GUILayer.Rental.ReturnUI.createWindow();
					returnFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnRental.add(mntmRentalReturn);
		
		
		JMenu mnRentalProducts = new JMenu("Udlejningsprodukter");
		mnRental.add(mnRentalProducts);
		
		JMenuItem mntmRentalProductNew = new JMenuItem("Nyt udlejningsprodukt");
		mntmRentalProductNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame createFrame = GUILayer.Rental.Item.CreateUI.createWindow();
					createFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnRentalProducts.add(mntmRentalProductNew);
		
		JMenuItem mntmRentalProductUpdate = new JMenuItem("Opdater udlejningsprodukt");
		mntmRentalProductUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame updateFrame = GUILayer.Rental.Item.UpdateUI.createWindow();
					updateFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnRentalProducts.add(mntmRentalProductUpdate);
		
		JMenuItem mntmRentalProductDelete = new JMenuItem("Slet udlejningsprodukt");
		mntmRentalProductDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame deleteFrame = GUILayer.Rental.Item.DeleteUI.createWindow();
					deleteFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnRentalProducts.add(mntmRentalProductDelete);
		//Rental menu end
		
		//Customer menu
		JMenu mnCustomer = new JMenu("Kundekartotek");
		mnFunctions.add(mnCustomer);
		
		JMenuItem mntmCustomerCreate = new JMenuItem("Opret kunde");
		mntmCustomerCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame createFrame = GUILayer.Customer.CreateUI.createWindow();
				createFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnCustomer.add(mntmCustomerCreate);
		
		JMenuItem mntmCustomerUpdate = new JMenuItem("Opdater kunde information");
		mntmCustomerUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame updateFrame = GUILayer.Customer.UpdateUI.createWindow();
				updateFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnCustomer.add(mntmCustomerUpdate);
		
		JMenuItem mntmPersonDelete = new JMenuItem("Slet kunde");
		mntmPersonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame deleteFrame = GUILayer.Customer.DeleteUI.createWindow();
				deleteFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnCustomer.add(mntmPersonDelete);
		
		JMenuItem mntmCustomerAssoDiscGroup = new JMenuItem("Tilknyt kunde til rabatgruppe");
		mntmCustomerAssoDiscGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame assoDicsGroupFrame = GUILayer.Customer.AssoDiscGroupUI.createWindow();
				assoDicsGroupFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnCustomer.add(mntmCustomerAssoDiscGroup);
		
		
		JMenuItem mntmCustomerShowAll = new JMenuItem("Vis alle kunder");
		mntmCustomerShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame showAllCustomers = GUILayer.Customer.ShowAllUI.createWindow();
				layeredPane.add(showAllCustomers, JLayeredPane.DEFAULT_LAYER);
				layeredPane.moveToFront(showAllCustomers);
				try 
				{
					showAllCustomers.setSelected(true);
				} 
				catch (PropertyVetoException ex) {}
			}
		});
		mnCustomer.add(mntmCustomerShowAll);
		//Customer menu end
		
		//SalesAssistant menu
		JMenu mnSalesAssistant = new JMenu("Ekspedientkartotek");
		mnFunctions.add(mnSalesAssistant);
		
		JMenuItem mntmSalesAssistCreate = new JMenuItem("Opret ekspedient");
		mntmSalesAssistCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame createFrame = GUILayer.SalesAssist.CreateUI.createWindow();
					createFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnSalesAssistant.add(mntmSalesAssistCreate);
		
		JMenuItem mntmSalesAssistUpdate = new JMenuItem("Opdater ekspedient information");
		mntmSalesAssistUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame updateFrame = GUILayer.SalesAssist.UpdateUI.createWindow();
					updateFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnSalesAssistant.add(mntmSalesAssistUpdate);
		
		JMenuItem mntmSalesAssistDelete = new JMenuItem("Slet ekspedient");
		mntmSalesAssistDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame deleteFrame = GUILayer.SalesAssist.DeleteUI.createWindow();
					deleteFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnSalesAssistant.add(mntmSalesAssistDelete);
		
		JMenuItem mntmSalesAssistShowAll = new JMenuItem("Vis alle ekspedienter");
		mntmSalesAssistShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame showAllSalesAsst = GUILayer.SalesAssist.ShowAllUI.createWindow();
				if(!layeredPane.isAncestorOf(showAllSalesAsst))
				{
					layeredPane.add(showAllSalesAsst, JLayeredPane.DEFAULT_LAYER);
				}
				layeredPane.moveToFront(showAllSalesAsst);
				try 
				{
					showAllSalesAsst.setSelected(true);
				} 
				catch (PropertyVetoException ex) {}
			}
		});
		mnSalesAssistant.add(mntmSalesAssistShowAll);
		
		JMenuItem mntmSalesAssistChangePassword = new JMenuItem("Skift adgangskode");
		mntmSalesAssistChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame passwordFrame = GUILayer.SalesAssist.UpdatePwdUI.createWindow();
					passwordFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnSalesAssistant.add(mntmSalesAssistChangePassword);
		//SalesAssistant menu end
		
		//Product menu
		JMenu mnProduct = new JMenu("Produkt");
		mnFunctions.add(mnProduct);
		
		JMenuItem mntmProductCreate = new JMenuItem("Opret produkt");
		mntmProductCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame createFrame = GUILayer.Product.CreateUI.createWindow();
					createFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnProduct.add(mntmProductCreate);
		
		JMenuItem mntmProductShowAll = new JMenuItem("Vis alle produkter");
		mntmProductShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JInternalFrame showAllProducts = GUILayer.Product.ShowAllUI.createWindow();
					if(!layeredPane.isAncestorOf(showAllProducts))
					{
						layeredPane.add(showAllProducts, JLayeredPane.DEFAULT_LAYER);
					}
					layeredPane.moveToFront(showAllProducts);
					try 
					{
						showAllProducts.setSelected(true);
					} 
					catch (PropertyVetoException ex) {}
			}
		});
		mnProduct.add(mntmProductShowAll);
		
		JMenuItem mntmProductUpdate = new JMenuItem("Opdater produkt");
		mntmProductUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame updateFrame = GUILayer.Product.UpdateUI.createWindow();
					updateFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnProduct.add(mntmProductUpdate);
		
		JMenuItem mntmProductDelete = new JMenuItem("Slet produkt");
		mntmProductDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					GUILayer.Product.DeleteUI.createWindow();
				}
		});
		mnProduct.add(mntmProductDelete);
		
		JSeparator mntmProductSeparator = new JSeparator();
		mnProduct.add(mntmProductSeparator);
		//Product end
		
		//Product category sub-menu
		JMenu mnProductCat = new JMenu("Produkt kategorier");
		mnProduct.add(mnProductCat);
		
		JMenuItem mntmProductCatCreate = new JMenuItem("Opret produkt kategori");
		mntmProductCatCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame createFrame = GUILayer.Product.Category.CreateUI.createWindow();
					createFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnProductCat.add(mntmProductCatCreate);
		
		JMenuItem mntmProductCatShowAll = new JMenuItem("Vis alle produkt kategorier");
		mntmProductCatShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JInternalFrame showAllCatgories = GUILayer.Product.Category.ShowAllUI.createWindow();
					if(!layeredPane.isAncestorOf(showAllCatgories))
					{
						layeredPane.add(showAllCatgories, JLayeredPane.DEFAULT_LAYER);
					}
					layeredPane.moveToFront(showAllCatgories);
					try 
					{
						showAllCatgories.setSelected(true);
					} 
					catch (PropertyVetoException ex) {}
			}
		});
		mnProductCat.add(mntmProductCatShowAll);
		//Product-category end
		
		//Product-group sub-menu
		JMenu mnProductGroup = new JMenu("Produkt grupper");
		mnProduct.add(mnProductGroup);
		
		JMenuItem mntmProductGroupCreate = new JMenuItem("Opret produkt gruppe");
		mntmProductGroupCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame createFrame = GUILayer.Product.Group.CreateUI.createWindow();
					createFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnProductGroup.add(mntmProductGroupCreate);
		
		JMenuItem mntmProductGroupShowAll = new JMenuItem("Vis alle produkt grupper");
		mntmProductGroupShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JInternalFrame showAllProductGroups = GUILayer.Product.Group.ShowAllUI.createWindow();
					if(!layeredPane.isAncestorOf(showAllProductGroups))
					{
						layeredPane.add(showAllProductGroups, JLayeredPane.DEFAULT_LAYER);
					}
					layeredPane.moveToFront(showAllProductGroups);
					try 
					{
						showAllProductGroups.setSelected(true);
					} 
					catch (PropertyVetoException ex) {}
			}
		});
		mnProductGroup.add(mntmProductGroupShowAll);
		
		JMenuItem mntmProductGroupUpdate = new JMenuItem("Opdater produkt gruppe");
		mntmProductGroupUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame updateFrame = GUILayer.Product.Group.UpdateUI.createWindow();
					updateFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnProductGroup.add(mntmProductGroupUpdate);
		
		JMenuItem mntmProductGroupDelete = new JMenuItem("Slet produkt gruppe");
		mntmProductGroupDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					GUILayer.Product.Group.DeleteUI.createWindow();
			}
		});
		mnProductGroup.add(mntmProductGroupDelete);
		//Product-group end
		
		//Storage menu
		JMenu mnStorage = new JMenu("Lager");
		mnFunctions.add(mnStorage);
		
		JMenuItem mntmStorageCreate = new JMenuItem("Opret nyt lager");
		mntmStorageCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame createFrame = GUILayer.Product.Storage.CreateUI.createWindow();
					createFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnStorage.add(mntmStorageCreate);
		
		JMenuItem mntmStorageUpdate = new JMenuItem("Opdater lager information");
		mntmStorageUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame updateFrame = GUILayer.Product.Storage.UpdateUI.createWindow();
					updateFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnStorage.add(mntmStorageUpdate);
		
		JMenuItem mntmStorageDelete = new JMenuItem("Slet lager");
		mntmStorageDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame deleteFrame = GUILayer.Product.Storage.DeleteUI.createWindow();
					deleteFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnStorage.add(mntmStorageDelete);
		
		JMenuItem mntmStorageShowAll = new JMenuItem("Vis alle lagre");
		mntmStorageShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JInternalFrame showAllStorages = GUILayer.Product.Storage.ShowAllUI.createWindow();
					if(!layeredPane.isAncestorOf(showAllStorages))
					{
						layeredPane.add(showAllStorages, JLayeredPane.DEFAULT_LAYER);
					}
					layeredPane.moveToFront(showAllStorages);
					try 
					{
						showAllStorages.setSelected(true);
					} 
					catch (PropertyVetoException ex) {}
			}
		});
		mnStorage.add(mntmStorageShowAll);
		
		JSeparator mntmStorageSeparator = new JSeparator();
		mnStorage.add(mntmStorageSeparator);
		
		JMenuItem mntmStorageAddProduct = new JMenuItem("Tilføj et produkt til et lager");
		mntmStorageAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame addFrame = GUILayer.Product.Storage.AddProductUI.createWindow();
					addFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnStorage.add(mntmStorageAddProduct);
		
		JMenuItem mntmStorageShowAvailProduct = new JMenuItem("Vis tilgænglige produkter");
		mntmStorageShowAvailProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JInternalFrame showAllAvail = GUILayer.Product.Storage.ShowAllAvailbleUI.createWindow();
					if(!layeredPane.isAncestorOf(showAllAvail))
					{
						layeredPane.add(showAllAvail, JLayeredPane.DEFAULT_LAYER);
					}
					layeredPane.moveToFront(showAllAvail);
					try 
					{
						showAllAvail.setSelected(true);
					} 
					catch (PropertyVetoException ex) {}
			}
		});
		mnStorage.add(mntmStorageShowAvailProduct);
		//Storage menu end
		
		//Statistic menu
		JMenu mnStatistics = new JMenu("Statistik");
		mnFunctions.add(mnStatistics);
		
		JMenuItem mntmStatisticsSalesAssist = new JMenuItem("Generer statistik ud fra ekspedient");
		mntmStatisticsSalesAssist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JInternalFrame showSalesAsstStats = GUILayer.Statistic.SalesAsstStatsUI.createWindow();
					if(!layeredPane.isAncestorOf(showSalesAsstStats))
					{
						layeredPane.add(showSalesAsstStats, JLayeredPane.DEFAULT_LAYER);
					}
					layeredPane.moveToFront(showSalesAsstStats);
					try 
					{
						showSalesAsstStats.setSelected(true);
					} 
					catch (PropertyVetoException ex) {}
			}
		});
		mnStatistics.add(mntmStatisticsSalesAssist);
		
		JMenuItem mntmStatisticsCustomer = new JMenuItem("Generer statistik ud fra kunde");
		mntmStatisticsCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JInternalFrame showCustomerStats = GUILayer.Statistic.CustomerStatsUI.createWindow();
					if(!layeredPane.isAncestorOf(showCustomerStats))
					{
						layeredPane.add(showCustomerStats, JLayeredPane.DEFAULT_LAYER);
					}
					layeredPane.moveToFront(showCustomerStats);
					try 
					{
						showCustomerStats.setSelected(true);
					} 
					catch (PropertyVetoException ex) {}
			}
		});
		mnStatistics.add(mntmStatisticsCustomer);
		
		JMenuItem mntmStatisticsProduct = new JMenuItem("Generer statistik ud fra produkt");
		mntmStatisticsProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JInternalFrame showProductStats = GUILayer.Statistic.ProductStatsUI.createWindow();
					if(!layeredPane.isAncestorOf(showProductStats))
					{
						layeredPane.add(showProductStats, JLayeredPane.DEFAULT_LAYER);
					}
					layeredPane.moveToFront(showProductStats);
					try 
					{
						showProductStats.setSelected(true);
					} 
					catch (PropertyVetoException ex) {}
			}
		});
		mnStatistics.add(mntmStatisticsProduct);
		//Statistic menu end
		
		//About
		JMenu mnAbout = new JMenu("Om");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAboutApp = new JMenuItem("Applikationen");
		mntmAboutApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame aboutFrame = GUILayer.AboutUI.createWindow();
					aboutFrame.setLocationRelativeTo(pnlSystemLayout);
			}
		});
		mnAbout.add(mntmAboutApp);
	}
	
	@Override
	public void componentResized(ComponentEvent e) 
	{
		layeredPane.setBounds(0, 0, getWidth(), getHeight());
		int x = (getWidth() / 2) - (351 / 2);
		int y = (getHeight() / 3) - (353 / 2);
		pnlQuickSelect.setBounds(x, y, 351, 353);
    }

	@Override
	public void componentHidden(ComponentEvent arg0) 
	{
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) 
	{
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) 
	{
		
	}
}
