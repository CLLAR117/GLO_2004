package com.intervensim.presentation;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.intervensim.simulation.Simulation;


public class DisplaySimulationPanel extends JPanel{
	
	private DisplayManager displayManager;
	private Simulation simulation;
	
	public DisplaySimulationPanel(DisplayManager displayManager, Simulation simulation)
	{
		this.displayManager = displayManager;
		this.simulation = simulation;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		displayManager.paintModel(g, simulation);
	}
}
