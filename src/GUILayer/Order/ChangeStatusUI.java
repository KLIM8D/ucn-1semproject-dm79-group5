package GUILayer.Order;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class ChangeStatusUI {

	private static JFrame _frame;
	private static ChangeStatusUI _instance;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new ChangeStatusUI();
		
		return _frame;
	}


	public ChangeStatusUI() {   
		_frame = new JFrame();
		_frame.setTitle("Ændre status på ordre");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 368, 135);
		_frame.setResizable(false);
		_frame.setVisible(true);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}

}
