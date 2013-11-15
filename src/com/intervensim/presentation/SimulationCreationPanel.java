package com.intervensim.presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.intervensim.simulation.Simulation;

public class SimulationCreationPanel extends JTabbedPane implements ActionListener{

	private static final String STRING_MENU_GLOBAL = "Général";
	private static final String STRING_MENU_EDIT = "Edition";
	private static final String STRING_MENU_SIMULATION = "Simulation";
	
	private static final String STRING_MENU_ITEM_NEW_SIMULATION = "Nouvelle simulation";
	private static final String STRING_MENU_ITEM_LOAD_SIMULATION = "Charger une simulation";
	private static final String STRING_MENU_ITEM_SAVE_SIMULATION = "Charger une simulation";
	
	private static final String STRING_MENU_EDITION_NODE_ADD = "Ajouter un noeud";
	private static final String STRING_MENU_EDITION_ARC_ADD = "Ajouter un arc";

	private Simulation simulation;
	private final DisplaySimulationPanel displaySimulationPanel;
	
	private JButton buttonLoadSimulation;
	private JButton buttonSaveSimulation;
	private JButton buttonNewSimulation;
	
	private JButton buttonNodeAdd;
	private JButton buttonArcAdd;

	public SimulationCreationPanel(DisplaySimulationPanel displaySimulationPanel) {

		this.displaySimulationPanel = displaySimulationPanel;

		JComponent globalComponent = makeGlobalComponent();
		addTab(STRING_MENU_GLOBAL, null, globalComponent, STRING_MENU_GLOBAL);
		setMnemonicAt(0, KeyEvent.VK_1);

		JComponent editComponent = makeEditComponent();
		addTab(STRING_MENU_EDIT, null, editComponent, STRING_MENU_EDIT);
		setMnemonicAt(1, KeyEvent.VK_2);
		
		JComponent panel3 = makeTextPanel("Simulation");
		addTab(STRING_MENU_SIMULATION, null, panel3, STRING_MENU_SIMULATION);
		setMnemonicAt(1, KeyEvent.VK_2);
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}
	
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
	
	protected JComponent makeEditComponent()
	{
		JPanel panel = new JPanel();
		
		buttonNodeAdd = new JButton(STRING_MENU_EDITION_NODE_ADD);
		buttonNodeAdd.addActionListener(this);
		panel.add(buttonNodeAdd);
		
		buttonArcAdd = new JButton(STRING_MENU_EDITION_ARC_ADD);
		panel.add(buttonArcAdd);
		
		return panel;
	}
	
	protected JComponent makeGlobalComponent()
	{
		JPanel panel = new JPanel();
		
		buttonLoadSimulation = new JButton(STRING_MENU_ITEM_LOAD_SIMULATION);
		panel.add(buttonLoadSimulation);
		
		buttonSaveSimulation = new JButton(STRING_MENU_ITEM_SAVE_SIMULATION);
		panel.add(buttonSaveSimulation);
		
		buttonNewSimulation = new JButton(STRING_MENU_ITEM_NEW_SIMULATION);
		panel.add(buttonNewSimulation);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == buttonNodeAdd)
		{
			displaySimulationPanel.setActionFlag(DisplaySimulationPanel.ACTION_FLAG_NODE_ADD);
		}
	}
}
