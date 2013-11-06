package com.intervensim.urgence;

public class Noeud {
	private int posX;
	private int posY;
	private int sizeX;
	private int sizeY;
	private java.util.List<Noeud> connections;

	public Noeud(int x, int y) {
		posX = x;
		posY = y;
		sizeX = 4;
		sizeY = 4;
		connections = new java.util.ArrayList<Noeud>();
	}

	public void setPos(int x, int y) {
		posX = x;
		posY = y;
	}

	public void connect(Noeud n) {
		if (n == this) {
			return;
		}
		if (!connections.contains(n)) {
			connections.add(n);
		}
	}

	public void connectBoth(Noeud n) {
		connect(n);
		n.connect(this);
	}

	public void disconnect(Noeud n) {
		connections.remove(n);
	}

	public void disconnectBoth(Noeud n) {
		disconnect(n);
		n.disconnect(this);
	}

	public void disconnectAll() {
		java.util.Iterator<Noeud> it = connections.iterator();
		while (it.hasNext()) {
			Noeud n = it.next();
			n.disconnect(this);
		}
	}

	public java.util.Iterator<Noeud> getConnectedIterator() {
		return connections.iterator();
	}

	public double distance(Noeud target) {
		return Math.sqrt(Math.pow(target.getPosY() - posY, 2)
				+ Math.pow(target.getPosX() - posX, 2));
	}

	public double pathLenght(java.util.List<Noeud> path) {
		double ret = 0;
		java.util.Iterator<Noeud> it = path.iterator();
		if (!it.hasNext()) {
			return ret;
		}
		Noeud n1 = it.next();
		while (it.hasNext()) {
			Noeud n2 = it.next();
			ret += n1.distance(n2);
			n1 = n2;
		}
		return ret;
	}

	public double _distance = Double.MAX_VALUE;
	public boolean _visited = false;
	public Noeud _previous = null;

	public void getPathHelper(java.util.List<Noeud> p) {
		if (_visited == false) {
			_visited = true;
			java.util.Iterator<Noeud> it = connections.iterator();
			while (it.hasNext()) {
				Noeud next = it.next();
				if (_distance + distance(next) < next._distance) {
					next._distance = _distance + distance(next);
					next._previous = this;
					if (!p.contains(next)) {
						p.add(next);
					}
				}
			}
		}
	}

	private Noeud lowestNotVisited(java.util.List<Noeud> p) {
		Noeud ret = null;
		java.util.Iterator<Noeud> it = p.iterator();
		while (it.hasNext()) {
			Noeud next = it.next();
			if (next._visited == true) {
				continue;
			}
			if (ret == null) {
				ret = next;
				continue;
			}
			if (next._distance < ret._distance) {
				ret = next;
			}
		}
		return ret;

	}

	private void resetNoeudsPathFinding(java.util.List<Noeud> p) {
		java.util.Iterator<Noeud> it = p.iterator();
		while (it.hasNext()) {
			Noeud next = it.next();
			next._distance = Double.MAX_VALUE;
			next._visited = false;
			next._previous = null;
		}
	}

	public java.util.List<Noeud> getPathTo(Noeud n) {
		java.util.List<Noeud> temp_list = new java.util.ArrayList<Noeud>();
		Noeud temp = n;
		temp_list.add(temp);
		temp._distance = 0;
		while (true) {
			temp_list.add(temp);
			temp.getPathHelper(temp_list);
			temp = lowestNotVisited(temp_list);
			if (temp == null) {
				break;
			}
		}
		if (n._visited == false) {
			resetNoeudsPathFinding(temp_list);
			return null;
		}
		temp = this;
		java.util.List<Noeud> path = new java.util.ArrayList<Noeud>();
		while (true) {
			path.add(temp);
			if (temp._previous == null) {
				break;
			}
			temp = temp._previous;
		}
		resetNoeudsPathFinding(temp_list);
		return path;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	/**
	 * get x
	 * @return int
	 */
	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}
}
