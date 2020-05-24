package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

public class Simulatore {
	// Modello
	private Graph<Country, DefaultEdge> grafo;

	// Coda Prioritaria
	private PriorityQueue<Evento> queue;

	// Prametri simulazione
	private int N_MIGRANTI = 1000;
	private Country partenza;

	// Valori di Output
	private int T;
	private Map<Country, Integer> stanziali;

	public void init(Country partenza, Graph<Country, DefaultEdge> grafo) {
		this.partenza = partenza;
		this.grafo = grafo;

		// Inizializazione
		this.T = 1;
		this.stanziali = new HashMap<Country, Integer>();
		this.grafo.vertexSet().forEach(c -> stanziali.put(c, 0));
		this.queue = new PriorityQueue<Evento>();

		this.queue.add(new Evento(T, partenza, N_MIGRANTI));
	}

	public void run() {
		Evento e;
		while ((e = this.queue.poll()) != null) {
			this.T = e.getT();
			int nPersone = e.getN();
			Country stato = e.getStato();

			List<Country> vicini = Graphs.neighborListOf(this.grafo, stato);
			int migranti = (nPersone / 2) / vicini.size();
			int new_T = e.getT() + 1;

			if (migranti > 0) {
				vicini.forEach(c -> queue.add(new Evento(new_T, c, migranti)));
			}

			int n_stanziali = nPersone - migranti * vicini.size();
			this.stanziali.put(stato, this.stanziali.get(stato) + n_stanziali);
		}
	}

	public Map<Country, Integer> getStanziali() {
		return this.stanziali;
	}

	public int getT() {
		return this.T;
	}

}
