package GUILayer;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.UIManager;

public class AboutUI {

	private static JFrame _frame;
	private static AboutUI _instance;
	private JPanel pnlAbout;

	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new AboutUI();
		
		return _frame;
	}
	
	private AboutUI() {
		_frame = new JFrame();
		_frame.setTitle("Om applikationen");
		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		_frame.setBounds(0, 0, 470, 415);
		_frame.setLocationRelativeTo(null);
		_frame.setAlwaysOnTop(true);
		_frame.setResizable(false);
		_frame.setVisible(true);
		pnlAbout = new JPanel();
		pnlAbout.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlAbout.setLayout(new BorderLayout(0, 0));
		_frame.setContentPane(pnlAbout);
		
		JTabbedPane tabAbout = new JTabbedPane(JTabbedPane.TOP);
		pnlAbout.add(tabAbout, BorderLayout.CENTER);
		
		JPanel pnlSystem = new JPanel();
		tabAbout.addTab("System", null, pnlSystem, null);
		pnlSystem.setLayout(null);
		
		JLabel lblTitle = new JLabel("Vestbjerg Byggecenter A/S");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTitle.setBounds(12, 12, 409, 22);
		pnlSystem.add(lblTitle);
		
		JLabel lblDescription = new JLabel(GlobalUI.systemInformation(01) + " - " + GlobalUI.systemInformation(02));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBounds(22, 54, 399, 15);
		pnlSystem.add(lblDescription);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 43, 409, 1);
		pnlSystem.add(separator);
		
		JLabel lblDevelopers = new JLabel("Udviklet af:");
		lblDevelopers.setBounds(22, 95, 126, 15);
		pnlSystem.add(lblDevelopers);
		
		JLabel lblDev01 = new JLabel("Alex Rønne Petersen (xtzgzorex@gmail.com)");
		lblDev01.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDev01.setBounds(22, 110, 399, 15);
		pnlSystem.add(lblDev01);
		
		JLabel lblDev02 = new JLabel("Chris Tindbæk (christind@hotmail.com)");
		lblDev02.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDev02.setBounds(22, 125, 384, 15);
		pnlSystem.add(lblDev02);
		
		JLabel lblDev03 = new JLabel("Mads Nielsen (contact@madsnielsen.net)");
		lblDev03.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDev03.setBounds(22, 140, 384, 15);
		pnlSystem.add(lblDev03);
		
		JLabel lblDev04 = new JLabel("Morten Klim Sørensen (mail@kl1m.dk)");
		lblDev04.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDev04.setBounds(22, 155, 384, 15);
		pnlSystem.add(lblDev04);
		
		JLabel lblGitHubTitle = new JLabel("GitHub repository:");
		lblGitHubTitle.setBounds(22, 210, 460, 15);
		pnlSystem.add(lblGitHubTitle);
		
		JLabel lblGitHubURL = new JLabel("https://github.com/iammadsnielsen/ucn-1semproject-dm79-group5");
		lblGitHubURL.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGitHubURL.setBounds(22, 225, 460, 15);
		pnlSystem.add(lblGitHubURL);
		
		JLabel lblRedmineTitle = new JLabel("Redmine URL:");
		lblRedmineTitle.setBounds(22, 250, 139, 15);
		pnlSystem.add(lblRedmineTitle);
		
		JLabel lblRedmineURL = new JLabel("http://redine-madsnielsen.rhcloud.com");
		lblRedmineURL.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRedmineURL.setBounds(22, 265, 419, 15);
		pnlSystem.add(lblRedmineURL);
		
		JPanel pnlLicense = new JPanel();
		tabAbout.addTab("Licens", null, pnlLicense, null);
		pnlLicense.setLayout(null);
		
		JTextArea txtLicense = new JTextArea();
		txtLicense.setBackground(UIManager.getColor("ComboBox.background"));
		txtLicense.setWrapStyleWord(true);
		txtLicense.setLineWrap(true);
		txtLicense.setEditable(false);
		txtLicense.setText("Copyright (c) 2012 Alex Rønne Petersen, Chris Tindbæk, Mads Nielsen, Morten Klim Sørensen\n\nPermission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the \"Software\"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:\n\nThe above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.\n\nTHE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.");
		txtLicense.setBounds(5, 5, 435, 337);
		pnlLicense.add(txtLicense);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
}
