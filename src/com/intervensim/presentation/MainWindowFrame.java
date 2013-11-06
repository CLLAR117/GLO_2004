package com.intervensim.presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.intervensim.simulation.Simulation;

public class MainWindowFrame extends JFrame{
	
	private final DisplaySimulationPanel displaySimulationPanel;
	private final SimulationCreationPanel simulationCreationPanel;
	
	public MainWindowFrame(Simulation simulation, DisplayManager displayManager)
	{
		super("Demonstration du GUI avec Afficheur");
		setLayout(new BorderLayout());
		
		displaySimulationPanel = new DisplaySimulationPanel(displayManager, simulation);
		add(displaySimulationPanel, BorderLayout.CENTER);
		
		simulationCreationPanel = new SimulationCreationPanel(displaySimulationPanel);
		add(simulationCreationPanel,BorderLayout.WEST);
		
		setPreferredSize(new Dimension(800, 400));
		setLocation(300, 200);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}
