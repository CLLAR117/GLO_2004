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
		if (u.in_treatment == true) {
			return false;
		}
		path = present.getPathTo(u.location);
		if (path == null) {
			return false;
		}
		urgence = u;
		time = curtime;
		u.in_treatment = true;
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
			Noeud n = null;
			if (path.size() > 1) {
				n = path.get(1);
			} else {
				n = path.get(0);
			}
			double d = n.distance(pos_x, pos_y);
			if (distance - d < 0) {
				pos_x += (int)((distance/d) * ((double)(n.getPosX() - pos_x)));
				pos_y += (int)((distance/d) * ((double)(n.getPosY() - pos_y)));
				distance = 0;
			} else {
				distance = distance - d;
				pos_x = n.getPosX();
				pos_y = n.getPosY();
				path.remove(0);
				present = n;
				if (urgence.location == present) {
					return distance;
				}
			}
		}
		return distance;
	}

	public void tick(long curtime) {
		long t = curtime - time;
		time = curtime;
		if (t <= 0 || urgence == null) {
			return;
		}
		if (urgence.time > curtime) {
			return;
		}
		if (urgence.time > (curtime - t)) {
			t =  t - (urgence.time - (curtime - t));
		}
		if (present != urgence.location) {
			t = (int)(moveonPath(vitesse * t)/vitesse);
		}
		urgence.time_treatment_left -= t;
		if (urgence.time_treatment_left <= 0) {
			urgence = null;
		}
	}
	public int getPosX() {
		return pos_x;
	}

	public int getPosY() {
		return pos_y;
	}
	public double distance(Noeud n) {
		java.util.List<Noeud> p = present.getPathTo(n);
		if (p == null || urgence != null) {
			return Double.MAX_VALUE;
		}
		return present.pathLenght(p);
	}
}
