package com.intervensim.urgence;

public class Urgence {
	public long time;
	public long time_treatment;
	public Noeud location;

	public Urgence(Noeud n, long t, long t_treatment) {
		location = n;
		time = t;
		time_treatment = t_treatment;
	}
}