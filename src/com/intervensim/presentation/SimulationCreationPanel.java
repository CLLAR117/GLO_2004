package com.intervensim.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.intervensim.simulation.Simulation;

public class SimulationCreationPanel extends JMenuBar implements ActionListener{
	
	private static final String STRING_NEW_SIMULATION = "Nouvelle simulation";
	private static final String STRING_LOAD_SIMULATION = "Charger une simulation";
	
	private Simulation simulation;
	private final DisplaySimulationPanel displaySimulationPanel;
	
	public SimulationCreationPanel(DisplaySimulationPanel displaySimulationPanel)
	{

		this.displaySimulationPanel = displaySimulationPanel;
		
		JMenu menu1 = new JMenu("Fichier");
		JMenu menu2 = new JMenu("Edition");
		/* differents choix de chaque menu */
		JMenuItem demarrer = new JMenuItem("Démarrer");
		JMenuItem fin = new JMenuItem("Fin");
		JMenuItem annuler = new JMenuItem("Annuler");
		JMenuItem copier = new JMenuItem("Copier");
		JMenuItem coller = new JMenuItem("Coller");

		/* Ajouter les choix au menu */
		menu1.add(demarrer);
		menu1.add(fin);
		menu2.add(annuler);
		menu2.add(copier);
		menu2.add(coller);
		/* Ajouter les menu sur la bar de menu */
		add(menu1);
		add(menu2);
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
