package it.polito.tdp.borders.model;

public class Evento implements Comparable<Evento> {

	private int t;
	private Country stato;
	private int n;

	/**
	 * @param t
	 * @param stato
	 * @param n
	 */
	public Evento(int t, Country stato, int n) {
		super();
		this.t = t;
		this.stato = stato;
		this.n = n;
	}

	/**
	 * @return the t
	 */
	public int getT() {
		return t;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(int t) {
		this.t = t;
	}

	/**
	 * @return the stato
	 */
	public Country getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(Country stato) {
		this.stato = stato;
	}

	/**
	 * @return the n
	 */
	public int getN() {
		return n;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(int n) {
		this.n = n;
	}

	@Override
	public int compareTo(Evento o) {
		return -this.t - o.t;
	}

}
