package mx.jorge.arboles.utilidades;

import java.util.LinkedList;

import org.json.JSONArray;

public class Arbol {

	private Vertice raiz;

	public Arbol() {
		raiz = null;
	}

	private class Vertice {
		int value;
		int distancia = 0;
		Vertice left;
		Vertice right;

		Vertice(int value) {
			this.value = value;
			this.right = null;
			this.left = null;
			this.distancia = 0;
		}
	}

	// metodo para agregar de forma recursiva un valor
	private Vertice agregaRecursivo(Vertice actual, int valor) {
		if (actual == null) {
			Vertice nuevo = new Vertice(valor);
			return nuevo;
		} else if (valor < actual.value) {
			actual.left = agregaRecursivo(actual.left, valor);
		} else if (valor > actual.value) {
			actual.right = agregaRecursivo(actual.right, valor);
		}

		return actual;
	}

	// metodo para agregar de forma recursiva un valor
	private Vertice busca(Vertice actual, int valor) {
		if (actual == null) {
			return null;
		}
		if (actual.value == valor) {
			return actual;
		}

		actual.left = busca(actual.left, valor);
		actual.right = busca(actual.right, valor);
		if (actual.left != null) {
			return actual.left;
		}

		return actual.right;
	}

	// metodo para agregar un nuevo valor al arbol
	public void agrega(int value) {
		this.raiz = agregaRecursivo(this.raiz, value);
	}

	// Obtiene una altura desde un vertice
	private int altura(Vertice v) {
		if (v == null) {
			return 0;
		}
		return 1 + Math.max(altura(v.left), altura(v.right));
	}

	// Obtiene la altura del arbol
	public int altura() {
		return altura(this.raiz) - 1;
	}

	/*
	 * metodo que aplica bfs al arbol y devuelve una lista En el orden que son
	 * visitados
	 */
	public JSONArray bfs() {
		LinkedList<Vertice> cola = new LinkedList<>();
		JSONArray visitados = new JSONArray();
		cola.addFirst(this.raiz);
		visitados.put(this.raiz.value);
		while (!cola.isEmpty()) {
			Vertice v = cola.removeLast();
			if (v.left != null) {
				cola.addFirst(v.left);
				visitados.put(v.left.value);
			}

			if (v.right != null) {
				cola.addFirst(v.right);
				visitados.put(v.right.value);
			}

		}
		return visitados;
	}

	/*
	 * Metodo que busca un valor en el arbol y devuelve los vecinos
	 * izquierdos y derechos
	 */
	public LinkedList<LinkedList<Integer>> vecinos(int valor) {
		LinkedList<Vertice> cola = new LinkedList<>();
		LinkedList<Vertice> visitados = new LinkedList<>();
		cola.addFirst(this.raiz);
		visitados.push(this.raiz);
		this.raiz.distancia = 0;

		while (!cola.isEmpty()) {
			Vertice v = cola.removeLast();
			if (v.left != null) {
				v.left.distancia = v.distancia + 1;
				cola.addFirst(v.left);
				visitados.push(v.left);

			}

			if (v.right != null) {
				v.right.distancia = v.distancia + 1;
				cola.addFirst(v.right);
				visitados.push(v.right);
			}

		}

		Vertice b = busca(this.raiz, valor);
		LinkedList<Integer> izq = new LinkedList<>();
		LinkedList<Integer> der = new LinkedList<>();
		for (Vertice v : visitados) {
			if (v.distancia == b.distancia) {
				if (v.value < b.value) {
					izq.add(v.value);
				} else if (v.value > b.value) {
					der.add(v.value);
				}

			}
		}
		LinkedList<LinkedList<Integer>> respuesta =  new LinkedList<>();
		respuesta.add(izq);
		respuesta.add(der);
		return respuesta;
	}

}
