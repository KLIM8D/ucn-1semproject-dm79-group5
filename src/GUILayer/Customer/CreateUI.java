package GUILayer.Customer;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateUI {

	private static JFrame _frame;
	private static CreateUI _instance;
	private JPanel pnlAbout;

	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new CreateUI();
		
		return _frame;
	}
	
	private CreateUI() {
		_frame = new JFrame();
		_frame.setTitle("Opret kunde");
		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		_frame.setBounds(0, 0, 470, 415);
		_frame.setAlwaysOnTop(true);
		_frame.setResizable(false);
		_frame.setVisible(true);
		pnlAbout = new JPanel();
		pnlAbout.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlAbout.setLayout(new BorderLayout(0, 0));
		_frame.setContentPane(pnlAbout);
		
		JTabbedPane tabAbout = new JTabbedPane(JTabbedPane.TOP);
		pnlAbout.add(tabAbout, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabAbout.addTab("Privat", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabAbout.addTab("Erhverv", null, panel_1, null);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
}
