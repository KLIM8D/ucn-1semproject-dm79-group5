package GUILayer;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
			JOptionPane.showMessageDialog(frame, messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static String messageHandling(int code) {
        switch (code) {
            case 01:
                return "Der skal indtastes i feltet 'Bruger ID'.";
            case 02:
                return "Der skal indtastes i feltet 'Adgangskode'.";
            case 03:
            	return "Forkert id eller adgangskode.";
            case 04:
            	return "Feltet kan kun indholde tal.";
            case 05: 
            	return "Oprettelsen er gennemført.";
            case 06:
            	return "Produkt kategorien blev ikke fundet eller også findes dette produkt allerede.";
            case 07: 
            	return "Produkt kategorien findes allerede i systemet.";
            case 10:
            	return "Oprettelsen blev IKKE gennemført.";
            case 11:
            	return "Returnering gennemført.";
            case 12:
            	return "Returnering blev IKKE gennemført.";
            case 13:
            	return "Opdateringen er gennemført.";
            case 14:
            	return "Opdateringen blev IKKE gennemført.";
            case 15:
            	return "Produktet er fjernet.";
            case 16:
            	return "Produktet blev IKKE fjernet.";
            case 17:
            	return "Er du sikker på du vil slette denne ekspedient?";
            case 18:
            	return "En ekspedient med det indtastede id blev ikke fundet.";
            case 20:
            	return "Der skal indtastes i feltet 'Ekspedient id'.";
            case 21:
            	return "Der skal mindst indtastes 6 tegn i fejlet 'Ny adgangskode'.";
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
	
	public static void checkIfInt(JTextField data) {
		try {
			Integer.parseInt(data.getText());
		}

		catch (NumberFormatException err) {
			JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(04), "FEJL!", JOptionPane.WARNING_MESSAGE);
			data.setText(null);
		}
	}
}