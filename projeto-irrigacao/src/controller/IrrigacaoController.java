package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class IrrigacaoController implements Serializable {
	private static final long serialVersionUID = -8248257843709767818L;

	private String dados;
	String luminosidade;
	String umidadeAr;
	String temperaturaAr;
	String statusSolo;

	public String getDados() {
		System.out.println("chamou getDados");
		return dados;
	}

	public void setDados(String dados) {
		try {
			BufferedReader arquivo = new BufferedReader(new FileReader(
					"C:\\Users\\mathe\\OneDrive\\Documentos\\UNITINS\\ESTR DADOS\\ProjetoIrrigacao\\Historico_de_Log.txt"));

			List<String> arquivoArray = new ArrayList<String>();

			while (arquivo.ready()) {
				arquivoArray.add(arquivo.readLine());
			}

			arquivo.close();

			int quantidadeElementosLista = arquivoArray.size();
			// System.out.println("TOTAL DE LINHAS = " + arquivoArray.size());

			int posicaoUmidadeAr = quantidadeElementosLista - 5;
			umidadeAr = arquivoArray.get(posicaoUmidadeAr);

			int posicaoTemperaturaAr = quantidadeElementosLista - 4;
			temperaturaAr = arquivoArray.get(posicaoTemperaturaAr);

			int posicaoLuminosidade = quantidadeElementosLista - 3;
			luminosidade = arquivoArray.get(posicaoLuminosidade);

			int posicaoStatusSolo = quantidadeElementosLista - 2;
			statusSolo = arquivoArray.get(posicaoStatusSolo);

			this.dados = dados;

		} catch (Exception e) {
			System.out.println("falha na leitura");
			e.printStackTrace();
		}
	}

	public String getLuminosidade() {
		String retorno = "";
		if (dados != null) {
			String[] auxiliar = luminosidade.trim().split(":");
			retorno = auxiliar[1] + "/1023";
		}
		return retorno;
	}

	public String getStatusSolo() {
		String retorno = "";
		if (dados != null) {
			String[] auxiliar = statusSolo.split("Solo");
			retorno = auxiliar[1];
		}
		return retorno;
	}

	public String getUmidadeAr() {
		String retorno = "";
		if (dados != null) {
			String[] auxiliar = umidadeAr.trim().split(":");
			retorno = auxiliar[1];
		}
		return retorno;
	}

	public String getTemperaturaAr() {
		String retorno = "";
		if (dados != null) {
			String[] auxiliar = temperaturaAr.trim().split(":");
			retorno = auxiliar[1];
		}
		return retorno;
	}
}