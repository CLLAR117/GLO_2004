package com.intervensim.main;

import com.intervensim.presentation.DisplayManager;
import com.intervensim.presentation.MainWindowFrame;
import com.intervensim.simulation.Simulation;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MainWindowFrame(new Simulation(), new DisplayManager());
	}

}
