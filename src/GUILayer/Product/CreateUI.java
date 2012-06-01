package GUILayer.Product;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CreateUI extends JFrame {

	private static final long serialVersionUID = 7199391358909768134L;
	private JPanel _pnlCreateProduct;

	public CreateUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Opret product");
		setBounds(0, 0, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		GUILayer.GlobalUI.setWindowStatus(true);

		_pnlCreateProduct = new JPanel();
		_pnlCreateProduct.setBorder(new EmptyBorder(5, 5, 5, 5));
		_pnlCreateProduct.setLayout(new BorderLayout(0, 0));
		setContentPane(_pnlCreateProduct);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
			}
		});
	}
}