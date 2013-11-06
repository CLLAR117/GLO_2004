package com.intervensim.presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.intervensim.simulation.Simulation;

public class MainWindowFrame extends JFrame {

	private final DisplaySimulationPanel displaySimulationPanel;
	private final SimulationCreationPanel simulationCreationPanel;

	public MainWindowFrame(Simulation simulation, DisplayManager displayManager) {
		super("IntervenSIM");
		setLayout(new BorderLayout());

		displaySimulationPanel = new DisplaySimulationPanel();
		displaySimulationPanel.addMouseListener(displaySimulationPanel);
		displaySimulationPanel.addMouseMotionListener(displaySimulationPanel);
		add(displaySimulationPanel, BorderLayout.CENTER);

		/* add the menu to the frame */
		simulationCreationPanel = new SimulationCreationPanel(
				displaySimulationPanel);
		setJMenuBar(simulationCreationPanel);

		setPreferredSize(new Dimension(800, 400));
		setLocation(300, 200);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
