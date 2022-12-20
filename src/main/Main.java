package main;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import telas.Principal;

public class Main {

	public static void main(String args[]) {
		FlatDarkLaf.setup();

		Principal principal = new Principal();
		
		try {
		    UIManager.setLookAndFeel( new FlatDarkLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		principal.setVisible(true);
	}
}