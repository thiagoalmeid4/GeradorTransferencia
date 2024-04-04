package br.com.gerador.requisicao;

import java.io.IOException;

import com.google.gson.Gson;

import br.com.gerador.dto.TransferenciaTedDto;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequisicaoTed {
	public String executar(TransferenciaTedDto TedDto) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Gson gson = new Gson();
		String json = gson.toJson(TedDto);
		MediaType HJSON = MediaType.get("Application/json; charset=utf-8");
		Request rt = new Request.Builder().url("http://localhost:8080/conta/ted").post(RequestBody.create(json, HJSON))
				.build();
		Response response = client.newCall(rt).execute();
		return response.body().string();
	}

}
