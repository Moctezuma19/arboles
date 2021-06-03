package mx.jorge.arboles.utilidades;

import java.util.LinkedList;
import java.util.Queue;

public class Arbol {

	private Vertice raiz;

	public Arbol(int valor) {
		this.raiz = new Vertice(valor);
	}

	private class Vertice {
		int value;
		int altura;
		Vertice padre;
		Vertice left;
		Vertice right;

		Vertice(int value) {
			value = value;
			right = null;
			left = null;
			padre = null;
			altura = 0;

		}
	}

	// metodo auxiliar para obtener la altura de un vertice
	private int obtenAltura(Vertice v) {
		return 1 + Math.min(obtenAltura(v.left),obtenAltura(v.right));
	}

	// metodo que obtiene la altura de una arbol
	public int altura() {
		return Math.max(obtenAltura(this.raiz.left), obtenAltura(this.raiz.right));
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

	// metodo para agregar un nuevo valor al arbol
	public void agrega(int value) {
		this.raiz = agregaRecursivo(raiz, value);
	}

}
