package com.intervensim.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.intervensim.simulation.Simulation;

public class SimulationCreationPanel extends JMenuBar implements ActionListener{
	
	private static final String STRING_MENU_FILE = "Fichier";
	private static final String STRING_MENU_ITEM_NEW_SIMULATION = "Nouvelle simulation";
	private static final String STRING_MENU_ITEM_LOAD_SIMULATION = "Charger une simulation";
	
	private Simulation simulation;
	private final DisplaySimulationPanel displaySimulationPanel;
	
	private final JMenu fileMenu;
	private final JMenuItem newSimulationMenuItem;
	private final JMenuItem loadSimulationMenuItem;
	
	public SimulationCreationPanel(DisplaySimulationPanel displaySimulationPanel)
	{

		this.displaySimulationPanel = displaySimulationPanel;
		
		fileMenu = new JMenu(STRING_MENU_FILE);
		
		newSimulationMenuItem = new JMenuItem(STRING_MENU_ITEM_NEW_SIMULATION);
		newSimulationMenuItem.addActionListener(this);
		loadSimulationMenuItem = new JMenuItem(STRING_MENU_ITEM_LOAD_SIMULATION);
		loadSimulationMenuItem.addActionListener(this);


		/* Add items */
		fileMenu.add(newSimulationMenuItem);
		fileMenu.add(loadSimulationMenuItem);
		/* add menus */
		add(fileMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(STRING_MENU_ITEM_NEW_SIMULATION)){
			System.out.println("New Simulation");
		}
		else if(e.getActionCommand().equals(STRING_MENU_ITEM_LOAD_SIMULATION)){
			//TODO load simulation
		}
	}
	
	public void setSimulation(Simulation simulation){
		this.simulation = simulation;
	}
}
