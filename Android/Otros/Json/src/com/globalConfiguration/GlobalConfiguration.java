package com.globalConfiguration;

import com.json.Persona;


public class GlobalConfiguration {

	private Persona selected;
	private static GlobalConfiguration singletonObject;
	/**
	 * 
	 * @return Object GlobalConiguration 
	 */
	public static synchronized GlobalConfiguration getSingletonObject() {
		if (singletonObject == null) {
			singletonObject = new GlobalConfiguration();
		}
		return singletonObject;
	}

	public Persona getSelectedPersona() {
		return selected;
	}


	public void setSelectedPersona(Persona selectedPersona) {
		this.selected = selectedPersona;
	}
}