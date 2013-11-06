package com.intervensim.presentation;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.intervensim.simulation.Simulation;

public class SimulationCreationPanel extends JPanel implements ActionListener{
	
	private static final String STRING_NEW_SIMULATION = "Nouvelle simulation";
	private static final String STRING_LOAD_SIMULATION = "Charger une simulation";
	
	private final JButton newSimulationButton;
	private final JButton loadSimulationButton;
	
	private Simulation simulation;
	private final DisplaySimulationPanel displaySimulationPanel;
	
	public SimulationCreationPanel(DisplaySimulationPanel displaySimulationPanel)
	{
		this.displaySimulationPanel = displaySimulationPanel;
		
		setLayout(new GridLayout(8, 1));
		setPreferredSize(new Dimension(200, 400));
		
		newSimulationButton = new JButton(STRING_NEW_SIMULATION);
		newSimulationButton.addActionListener(this);
		add(newSimulationButton);
		
		loadSimulationButton = new JButton(STRING_LOAD_SIMULATION);
		loadSimulationButton.addActionListener(this);
		add(loadSimulationButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(STRING_NEW_SIMULATION)){
			System.out.println("New Simulation");
		}
		else if(e.getActionCommand().equals(STRING_LOAD_SIMULATION)){
			//TODO load simulation
		}
	}
	
	public void setSimulation(Simulation simulation){
		this.simulation = simulation;
	}
}
