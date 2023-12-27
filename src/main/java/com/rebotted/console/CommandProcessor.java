package com.rebotted.console;

/**
 * 
 * @author RS-Emulators
 *
 */
public interface CommandProcessor {
	
	boolean command(String[] cmd);
	
	String help();
}
