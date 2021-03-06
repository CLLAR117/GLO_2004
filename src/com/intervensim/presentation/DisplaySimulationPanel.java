package com.intervensim.presentation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.intervensim.urgence.Noeud;
import com.intervensim.urgence.Urgence;
import com.intervensim.urgence.Vehicule;

class DisplaySimulationPanel extends JPanel implements MouseListener,
		MouseMotionListener {
	private boolean mouseDown = false;
	private Noeud selectedNoeud = null;
	private java.util.List<Noeud> noeuds = new java.util.ArrayList<Noeud>();
	private java.util.List<Urgence> urgences = new java.util.ArrayList<Urgence>();
	private java.util.List<Vehicule> vehicules = new java.util.ArrayList<Vehicule>();
	private int mouse_x;
	private int mouse_y;

	private int actionFlag = ACTION_FLAG_NULL;
	public static final int ACTION_FLAG_NODE_ADD = 1;
	public static final int ACTION_FLAG_VEHICULE_ADD = 2;
	public static final int ACTION_FLAG_URGENCE_ADD = 3;
	public static final int ACTION_FLAG_NULL = 0;

	private JPopupMenu nodeEditPopupMenu = new JPopupMenu("Popup");
	private JMenuItem nodeEditPopupMenuItemDelete = new JMenuItem("Supprimer");

	public DisplaySimulationPanel() 
	{
		nodeEditPopupMenuItemDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedNoeud.disconnectAll();
				delNoeud(selectedNoeud);
				selectedNoeud = null;
			}
		});
		nodeEditPopupMenu.add(nodeEditPopupMenuItemDelete);
	}

	public int getActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(int actionFlag) {
		this.actionFlag = actionFlag;
	}

	public void addNoeud(int x, int y) {
		noeuds.add(new Noeud(x, y));
		System.out.println("Added: " + x + ", " + y);
	}

	public void addNoeud(Noeud n) {
		if (!noeuds.contains(n)) {
			noeuds.add(n);
		}
	}

	public void delNoeud(Noeud n) {
		n.disconnectAll();
		noeuds.remove(n);
	}

	private Noeud getNoeudPos(int x, int y) {
		java.util.Iterator<Noeud> it = noeuds.iterator();
		while (it.hasNext()) {
			Noeud t = it.next();
			if (t.getPosX() - t.getSizeX() < x
					&& x < t.getPosX() + t.getSizeX()
					&& t.getPosY() - t.getSizeY() < y
					&& y < t.getPosY() + t.getSizeY()) {
				return t;
			}
		}
		return null;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		java.util.Iterator<Urgence> itu = urgences.iterator();
		while (itu.hasNext()) {
			Urgence u = itu.next();
			g.fillRect(u.location.getPosX() - 5, u.location.getPosY() - 5, 10,
					10);
		}
		g2d.setColor(Color.GREEN);
		java.util.Iterator<Vehicule> itv = vehicules.iterator();
		while (itv.hasNext()) {
			Vehicule v = itv.next();
			g.fillRect(v.getPosX() - 3, v.getPosY() - 3, 6, 6);
		}
		g2d.setColor(Color.BLACK);
		java.util.Iterator<Noeud> it = noeuds.iterator();
		while (it.hasNext()) {
			Noeud t = it.next();
			g2d.drawRect(t.getPosX() - t.getSizeX() / 2,
					t.getPosY() - t.getSizeY() / 2, t.getSizeX(), t.getSizeY());
			java.util.Iterator<Noeud> it1 = t.getConnectedIterator();
			while (it1.hasNext()) {
				Noeud t1 = it1.next();
				g2d.drawLine(t.getPosX(), t.getPosY(), t1.getPosX(),
						t1.getPosY());
			}
		}
		if (selectedNoeud != null && mouseDown == true && mouse_x != 0) {
			g2d.drawLine(selectedNoeud.getPosX(), selectedNoeud.getPosY(),
					mouse_x, mouse_y);
		}
		g2d.setColor(Color.RED);
		Noeud t = selectedNoeud;
		if (t != null) {
			g.fillRect(t.getPosX() - t.getSizeX() / 2,
					t.getPosY() - t.getSizeY() / 2, t.getSizeX(), t.getSizeY());
		}
	}

	private long temp_curtime = 0;

	public void mousePressed(MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON1) {
			mouseDown = true;
			// add a node
			if (actionFlag == ACTION_FLAG_NODE_ADD) {
				// test if node exists
				Noeud t = getNoeudPos(evt.getX(), evt.getY());
				if (t == null) {
					// add node if not
					addNoeud(evt.getX(), evt.getY());
					//remove flag
					actionFlag = ACTION_FLAG_NULL;
				}
			}
			//add a vehicule
			else if(actionFlag == ACTION_FLAG_VEHICULE_ADD)
			{
				Noeud t = getNoeudPos(evt.getX(), evt.getY());
				addVehicule(t, 3.0);
				
				//remove action flag
				actionFlag = ACTION_FLAG_NULL;
			}
			//add an urgence
			else if(actionFlag == ACTION_FLAG_URGENCE_ADD)
			{
				Noeud n = getNoeudPos(evt.getX(), evt.getY());
				addUrgence(n, 3, 12);
				
				//remove action flag
				actionFlag = ACTION_FLAG_NULL;
			}
			//select a node
			else{
				selectedNoeud = getNoeudPos(evt.getX(), evt.getY());
			}
			
		}
		if (evt.getButton() == MouseEvent.BUTTON2) {
			System.out.println("Mid button");
			if (selectedNoeud == null) {
				temp_curtime++;
				tick(temp_curtime);
			}
			if (vehicules.size() < 1) {
				addVehicule(3.0);
			} else {
				addUrgence(3, 12);
			}
		}
		if (evt.getButton() == MouseEvent.BUTTON3) {
			// test if click on node
			Noeud t = getNoeudPos(evt.getX(), evt.getY());
			if (t != null) {
				//select the node
				selectedNoeud = t;
				nodeEditPopupMenu.show(evt.getComponent(), evt.getX(),
						evt.getY());
			}
		}
		revalidate();
		repaint();
	}

	public void mouseReleased(MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON1) {
			if (mouseDown == true && selectedNoeud != null) {
				Noeud n = getNoeudPos(evt.getX(), evt.getY());
				if (n != null) {
					n.connectBoth(selectedNoeud);
				}
			}
			mouseDown = false;
			mouse_x = 0;
		}
		revalidate();
		repaint();
	}

	public void mouseClicked(MouseEvent evt) {
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}

	public void mouseMoved(MouseEvent evt) {
		mouse_x = evt.getX();
		mouse_y = evt.getY();
		revalidate();
		repaint();
	}

	public void mouseDragged(MouseEvent evt) {
		mouse_x = evt.getX();
		mouse_y = evt.getY();
		revalidate();
		repaint();
	}

	/**
	 * Add a vehicule
	 * @param v : int Vehicule speed
	 * @return boolean if Vehicule has been added or not
	 */
	public boolean addVehicule(double v) {
		if (selectedNoeud != null && vehicules.size() == 0) {
			Vehicule veh = new Vehicule(selectedNoeud, v);
			vehicules.add(veh);
			return true;
		}
		return false;
	}
	
	/**
	 * Add a vehicule
	 * @param n : Noeud The vehicule node
	 * @param v : int Vehicule speed
	 * @return boolean if Vehicule has been added or not
	 */
	public boolean addVehicule(Noeud n, double v)
	{
		if (n != null && vehicules.size() == 0) {
			Vehicule veh = new Vehicule(n, v);
			vehicules.add(veh);
			return true;
		}
		return false;
	}

	public boolean addUrgence(long t, long t_treatment) {
		if (selectedNoeud != null) {
			Urgence u = new Urgence(selectedNoeud, t, t_treatment);
			urgences.add(u);
			return true;
		}
		return false;
	}
	
	public boolean addUrgence(Noeud n, long t, long t_treatment) {
		if (n != null) {
			Urgence u = new Urgence(n, t, t_treatment);
			urgences.add(u);
			return true;
		}
		return false;
	}

	public void tick(long curtime) {
		System.out.println("tick " + curtime);
		java.util.Iterator<Vehicule> itv = vehicules.iterator();
		while (itv.hasNext()) {
			Vehicule v = itv.next();
			v.tick(curtime);
			if (!v.isFree()) {
				continue;
			}
			Urgence next_urgence = null;
			double dist_urgence = Double.MAX_VALUE;
			java.util.Iterator<Urgence> itu = urgences.iterator();
			while (itu.hasNext()) {
				Urgence u = itu.next();
				if (u.time > curtime || u.in_treatment == true) {
					continue;
				}
				double temp_d = v.distance(u.location);
				if (temp_d < dist_urgence) {
					next_urgence = u;
					dist_urgence = temp_d;
				}
			}
			if (dist_urgence < Double.MAX_VALUE && next_urgence != null) {
				v.treatUrgence(next_urgence, curtime);
			}
		}
		java.util.Iterator<Urgence> itu = urgences.iterator();
		while (itu.hasNext()) {
			Urgence u = itu.next();
			if (u.time_treatment_left <= 0) {
				urgences.remove(u);
				break;
			}
		}
	}
}
