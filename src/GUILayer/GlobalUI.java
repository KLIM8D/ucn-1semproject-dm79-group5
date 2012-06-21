package GUILayer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GlobalUI {
	protected static final Component frame = null;
	private static String _systemTitle = "1. Semesters Projekt";
	private static String _systemDescription = "UCN-DM79, Gruppe 5";
	private static String _systemBuild = "build31052012";	
	
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
            case 22:
            	return "Ekspedienten er slettet.";
            case 23:
            	return "Kodeordet for ekspedienten er ændret.";
            case 24:
            	return "Kunden blev slettet fra systemet.";
            case 25:
            	return "Kunden eksisterer IKKE i systemet.";
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
	
	public static void checkIfInt(JTextField data) {
		try {
			Integer.parseInt(data.getText());
		}

		catch (NumberFormatException err) {
			JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(04), "FEJL!", JOptionPane.WARNING_MESSAGE);
			data.setText(null);
		}
	}
	
	public static void checkIfLong(JTextField data) {
		try {
			Long.parseLong(data.getText());
		}

		catch (NumberFormatException err) {
			JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(04), "FEJL!", JOptionPane.WARNING_MESSAGE);
			data.setText(null);
		}
	}
	
	//Added because of stupid a bug with setLocationRelativeTo(null) for some JFrames or JDialogs
	public static void centerOnScreen(final Component c)
	{
	    final int width = c.getWidth();
	    final int height = c.getHeight();
	    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (screenSize.width / 2) - (width / 2);
	    int y = (screenSize.height / 2) - (height / 2);
	    c.setLocation(x, y);
	}
}