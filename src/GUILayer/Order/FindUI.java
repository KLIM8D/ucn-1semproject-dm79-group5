package GUILayer.Order;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;

public class FindUI  {

	private static JInternalFrame _frame;
	private static FindUI _instance;
	private JPanel contentPane;
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new FindUI();
		
		return _frame;
	}

	private FindUI() {
		_frame = new JInternalFrame();
		_frame.setTitle("Find ordre");
		_frame.setClosable(true);
		_frame.setMaximizable(true);
		_frame.setVisible(true);
		_frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		_frame.setBounds(0, 0, 450, 300);
		_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		_frame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
		      public void internalFrameClosing(InternalFrameEvent e) {
		        _frame.dispose();
		        _instance = null;
		      }
		    });
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		_frame.setContentPane(contentPane);
	}

}
