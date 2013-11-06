package com.intervensim.urgence;

public class Vehicule {
	private int pos_x;
	private int pos_y;
	private Noeud present;
	private java.util.List<Noeud> path;
	private Urgence urgence;
	private double vitesse;
	private long time;

	public Vehicule(Noeud n, double v) {
		path = null;
		present = n;
		vitesse = v;
		time = 0;
		pos_x = n.getPosX();
		pos_y = n.getPosY();
	}

	public boolean treatUrgence(Urgence u, long curtime) {
		path = present.getPathTo(u.location);
		if (path == null) {
			return false;
		}
		urgence = u;
		time = curtime;
		tick(curtime);
		return true;
	}

	public boolean isFree() {
		if (urgence == null) {
			return true;
		}
		if (urgence.time_treatment <= 0) {
			return true;
		}
		return false;
	}

	/* Returns distance left to move (when path was shorter than distance) */
	private double moveonPath(double distance) {
		if (urgence.location == present) {
			return distance;
		}
		if (path == null) {
			return distance;
		}
		while (distance > 0) {
			Noeud n = path.get(1);
			double d = n.distance(present);
		}
		return distance;
	}

	public void tick(long curtime) {

	}
}
