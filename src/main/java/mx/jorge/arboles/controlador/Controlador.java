package mx.jorge.arboles.controlador;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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

	@PostMapping(value = "/height", produces = MediaType.APPLICATION_JSON_VALUE)
	public String obtenAltura(HttpServletRequest request) throws Exception{
		try {
			Arbol arbol = null;
			InputStream body = request.getInputStream();
			byte[] bytes = body.readAllBytes();
			String string = new String(bytes, StandardCharsets.UTF_8);
			JSONObject json = new JSONObject(string);
			JSONArray valores = new JSONArray(json.getJSONArray("toTree"));
			for (int i = 0; i < valores.length(); i++) {
				int valor = Integer.parseInt(valores.get(i).toString());
				if(i == 0) {
					arbol = new Arbol(valor);
					continue;
				}
				System.out.println(valor);
				arbol.agrega(valor);
			}
			return "{\"height\": " + arbol.altura() + "}";

		} catch (Exception e) {			
			System.out.println(e);
		}
		return "{\"height\": -1 }";
	}

}
