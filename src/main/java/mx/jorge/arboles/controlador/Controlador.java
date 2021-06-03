package mx.jorge.arboles.controlador;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.jorge.arboles.utilidades.Arbol;

@RestController
@RequestMapping("/")
public class Controlador {
	/**
	 * Metodo que recibe la peticion para obtener la altura de un árbol de busqueda
	 * 
	 * @param request
	 * @return un JSON con la respuesta
	 */
	@PostMapping(value = "/height", produces = MediaType.APPLICATION_JSON_VALUE)
	public String obtenAltura(HttpServletRequest request) {
		try {
			Arbol arbol = new Arbol();
			InputStream body = request.getInputStream();
			byte[] bytes = body.readAllBytes();
			String string = new String(bytes, StandardCharsets.UTF_8);
			JSONObject json = new JSONObject(string);
			JSONArray valores = new JSONArray(json.getJSONArray("toTree"));
			for (int i = 0; i < valores.length(); i++) {
				int valor = Integer.parseInt(valores.get(i).toString());
				arbol.agrega(valor);
			}
			return "{\"height\": " + arbol.altura() + "}";

		} catch (Exception e) {
			System.out.println(e);
		}
		return "{\"height\": -1 }";
	}

	/**
	 * Metodo que recibe la peticion para obtener los vecinos de un nodo en un árbol
	 * de busqueda
	 * 
	 * @param request
	 * @return un JSON con la respuesta
	 */
	@PostMapping(value = "/neighbors", produces = MediaType.APPLICATION_JSON_VALUE)
	public String vecinos(HttpServletRequest request) {
		try {
			Arbol arbol = new Arbol();
			InputStream body = request.getInputStream();
			byte[] bytes = body.readAllBytes();
			String string = new String(bytes, StandardCharsets.UTF_8);
			System.out.println(string);
			JSONObject json = new JSONObject(string);
			JSONArray valores = new JSONArray(json.getJSONArray("toTree"));
			for (int i = 0; i < valores.length(); i++) {
				int valor = Integer.parseInt(valores.get(i).toString());
				arbol.agrega(valor);
			}
			LinkedList<LinkedList<Integer>> lista = arbol.vecinos(json.getInt("node"));
			JSONArray izq = new JSONArray();
			JSONArray der = new JSONArray();
			JSONObject respuesta = new JSONObject();
			JSONObject lr = new JSONObject();
			izq.putAll(lista.get(0));
			der.putAll(lista.get(1));

			if (lista.get(0).isEmpty()) {
				lr.put("left", "null");
			} else {
				lr.put("left", izq);
			}
			if (lista.get(1).isEmpty()) {
				lr.put("right", "null");
			} else {
				lr.put("right", der);
			}

			respuesta.put("neighbors", lr);
			return respuesta.toString();

		} catch (Exception e) {
			System.out.println(e);
		}
		return "{\"neighbors\": [-1,-1] }";
	}

	/**
	 * Metodo que recibe la peticion para hacer bfs en un árbol de búsqueda
	 * 
	 * @param request
	 * @return un JSON con la respuesta
	 */
	@PostMapping(value = "/bfs", produces = MediaType.APPLICATION_JSON_VALUE)
	public String bfs(HttpServletRequest request) {
		try {
			Arbol arbol = new Arbol();
			InputStream body = request.getInputStream();
			byte[] bytes = body.readAllBytes();
			String string = new String(bytes, StandardCharsets.UTF_8);
			JSONObject json = new JSONObject(string);
			JSONArray valores = new JSONArray(json.getJSONArray("toTree"));
			for (int i = 0; i < valores.length(); i++) {
				int valor = Integer.parseInt(valores.get(i).toString());
				arbol.agrega(valor);
			}
			JSONArray bf = arbol.bfs();
			JSONObject respuesta = new JSONObject();
			respuesta.put("bfs", bf);
			return respuesta.toString();

		} catch (Exception e) {
			System.out.println(e);
		}
		return "{\"bfs\": [-1,-1] }";
	}

}
