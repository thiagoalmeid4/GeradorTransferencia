package br.com.gerador.requisicao;

import java.io.IOException;

import com.google.gson.Gson;

import br.com.gerador.dto.TransferenciaPixDto;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequisicaoPix {
	public void executar(TransferenciaPixDto PixDto) throws IOException {
	 	OkHttpClient client = new OkHttpClient();
	 	Gson gson = new Gson();
	 	String json = gson.toJson(PixDto);
	 	MediaType HJSON = MediaType.get("Application/json; charset=utf-8");
	 	Request rt = new Request.Builder()
	 			.url("http://localhost:8080/conta/pix")
	 			.post(RequestBody.create(json,HJSON))
	 			.build();
	 	Response response = client.newCall(rt).execute();
	 	
	}

}
