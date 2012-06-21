package GUILayer.Customer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AssoDiscGroupUI {

	private static JFrame _frame;
	private static AssoDiscGroupUI _instance;
	private JPanel contentPane;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new AssoDiscGroupUI();
		
		return _frame;
	}


	public AssoDiscGroupUI() {
		_frame = new JFrame();
		_frame.setTitle("Tilknyt person til rabatgruppe");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 368, 286);
		_frame.setResizable(false);
		_frame.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
