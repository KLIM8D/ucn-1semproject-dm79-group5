package GUILayer;

import java.awt.Component;
import javax.swing.JOptionPane;

public class GlobalUI {
	
	protected static final Component frame = null;
	private static String _systemTitle = "1. Semesters Projekt";
	private static String _systemDescription = "UCN-DM79, Gruppe 5";
	private static String _systemBuild = "build31052012";	
	private static boolean _isActive = false;
	
	public GlobalUI() {
		try {
			new GUILayer.LoginUI().setVisible(true);
		}
		
		catch (Exception err) {
			JOptionPane.showMessageDialog(frame, errorHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static String errorHandling(int code) {
        switch (code) {
            case 01:
                return "Der skal indtastes i fejlet 'Bruger ID'.";
            case 02:
                return "Der skal indtastes i fejlet 'Adgangskode'.";
            case 03:
            	return "Forkert id eller adgangskode.";
            case 04:
            	return "Feltet 'Bruger ID' kan kun indholde tal.";
        }
        return "En ukendt system fejl er hændt.";
    }
	
	public static String systemInformation(int code) {
		switch (code) {
			case 01:
				return _systemTitle;
			case 02:
				return _systemDescription;
			case 03:
				return _systemBuild;
		}
		return null;
	}
	
	public static boolean getWindowStatus() {
		return _isActive;
	}
	
	public static void setWindowStatus(boolean status) {
		_isActive = status;
	}
}