package com.intervensim.urgence;

public class Urgence {
	public long time;
	public long time_treatment;
	public long time_treatment_left;
	public Noeud location;
	public boolean in_treatment;
	public Urgence(Noeud n, long t, long t_treatment) {
		location = n;
		time = t;
		time_treatment = t_treatment;
		time_treatment_left = t_treatment;
		in_treatment = false;
	}
}