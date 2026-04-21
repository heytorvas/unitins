import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

@Named
public class ChartJsView implements Serializable {

	private static final long serialVersionUID = -7987871128432472937L;

	private LineChartModel luminosidade;
	private LineChartModel temperatura;
	private LineChartModel umidade;
	private LineChartModel solo;

	@PostConstruct
	public void init() {
		createLineLumi();
		createLineUmi();
		createLineTemp();
		createLineSolo();
	}

	// LUMINOSIDADE
	public void createLineLumi() {

		Integer luminosidade0 = null;
		Integer luminosidade1 = null;
		Integer luminosidade2 = null;
		Integer luminosidade3 = null;
		Integer luminosidade4 = null;
		String[] auxiliar;

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

			System.out.println("debug1");
			// arrayTemperaturas.add(arquivoArray.get(quantidadeElementosLista - 3));
			// arrayTemperaturas.add(arquivoArray.get(quantidadeElementosLista - 9));

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 3)).split(":");
			// System.out.println(aux[1].trim());
			luminosidade0 = Integer.valueOf(auxiliar[1].trim());
			// System.out.println(luminosidade0);

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 9)).split(":");
			// System.out.println(aux[1].trim());
			luminosidade1 = Integer.valueOf(auxiliar[1].trim());

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 15)).split(":");
			// System.out.println(aux[1].trim());
			luminosidade2 = Integer.valueOf(auxiliar[1].trim());

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 21)).split(":");
			// System.out.println(aux[1].trim());
			luminosidade3 = Integer.valueOf(auxiliar[1].trim());

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 27)).split(":");
			// System.out.println(aux[1].trim());
			luminosidade4 = Integer.valueOf(auxiliar[1].trim());

			System.out.println("debug2");

		} catch (Exception e) {
			System.out.println("falha na leitura");
			e.printStackTrace();
		}

		luminosidade = new LineChartModel();
		ChartData data = new ChartData();

		LineChartDataSet dataSet = new LineChartDataSet();
		List<Number> values = new ArrayList<>();
		values.add(luminosidade4);
		values.add(luminosidade3);
		values.add(luminosidade2);
		values.add(luminosidade1);
		values.add(luminosidade0);

		dataSet.setData(values);
		dataSet.setFill(false);
		dataSet.setLabel("Historico");
		dataSet.setBorderColor("rgb(178, 34, 34)");
		dataSet.setLineTension(0.1);
		data.addChartDataSet(dataSet);

		List<String> labels = new ArrayList<>();
		labels.add("5");
		labels.add("4");
		labels.add("3");
		labels.add("2");
		labels.add("1");
		data.setLabels(labels);

		// Options
		LineChartOptions options = new LineChartOptions();
		Title title = new Title();
		title.setDisplay(true);
		options.setTitle(title);

		luminosidade.setOptions(options);
		luminosidade.setData(data);

	}

	// TEMPERATURA
	public void createLineTemp() {

		Double temperatura0 = null;
		Double temperatura1 = null;
		Double temperatura2 = null;
		Double temperatura3 = null;
		Double temperatura4 = null;
		String[] auxiliar;

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

			System.out.println("debug1");
			// arrayTemperaturas.add(arquivoArray.get(quantidadeElementosLista - 3));
			// arrayTemperaturas.add(arquivoArray.get(quantidadeElementosLista - 9));

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 4)).replaceAll("@C", "").split(":");
			// System.out.println(aux[1].trim());
			temperatura0 = Double.valueOf(auxiliar[1].trim());
			// System.out.println(luminosidade0);

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 10)).replaceAll("@C", "").split(":");
			// System.out.println(aux[1].trim());
			temperatura1 = Double.valueOf(auxiliar[1].trim());

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 16)).replaceAll("@C", "").split(":");
			// System.out.println(aux[1].trim());
			temperatura2 = Double.valueOf(auxiliar[1].trim());

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 22)).replaceAll("@C", "").split(":");
			// System.out.println(aux[1].trim());
			temperatura3 = Double.valueOf(auxiliar[1].trim());

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 28)).replaceAll("@C", "").split(":");
			// System.out.println(aux[1].trim());
			temperatura4 = Double.valueOf(auxiliar[1].trim());

			System.out.println("debug2");

		} catch (Exception e) {
			System.out.println("falha na leitura");
			e.printStackTrace();
		}

		temperatura = new LineChartModel();
		ChartData data = new ChartData();

		LineChartDataSet dataSet = new LineChartDataSet();
		List<Number> values = new ArrayList<>();
		values.add(temperatura4);
		values.add(temperatura3);
		values.add(temperatura2);
		values.add(temperatura1);
		values.add(temperatura0);

		dataSet.setData(values);
		dataSet.setFill(false);
		dataSet.setLabel("Historico");
		dataSet.setBorderColor("rgb(0, 0, 204)");
		dataSet.setLineTension(0.1);
		data.addChartDataSet(dataSet);

		List<String> labels = new ArrayList<>();
		labels.add("5");
		labels.add("4");
		labels.add("3");
		labels.add("2");
		labels.add("1");
		data.setLabels(labels);

		// Options
		LineChartOptions options = new LineChartOptions();
		Title title = new Title();
		title.setDisplay(true);
		options.setTitle(title);

		temperatura.setOptions(options);
		temperatura.setData(data);
	}

	// UMIDADE
	public void createLineUmi() {

		Double umidade0 = null;
		Double umidade1 = null;
		Double umidade2 = null;
		Double umidade3 = null;
		Double umidade4 = null;
		String[] auxiliar;

		try {
			BufferedReader arquivo = new BufferedReader(new FileReader(
					"C:\\Users\\\\mathe\\OneDrive\\Documentos\\UNITINS\\ESTR DADOS\\ProjetoIrrigacao\\Historico_de_Log.txt"));

			List<String> arquivoArray = new ArrayList<String>();

			while (arquivo.ready()) {
				arquivoArray.add(arquivo.readLine());
			}

			arquivo.close();

			int quantidadeElementosLista = arquivoArray.size();
			// System.out.println("TOTAL DE LINHAS = " + arquivoArray.size());

			System.out.println("debug1");
			// arrayTemperaturas.add(arquivoArray.get(quantidadeElementosLista - 3));
			// arrayTemperaturas.add(arquivoArray.get(quantidadeElementosLista - 9));

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 5)).replaceAll("%", "").split(":");
			// System.out.println(aux[1].trim());
			umidade0 = Double.valueOf(auxiliar[1].trim());
			// System.out.println(luminosidade0);

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 11)).replaceAll("%", "").split(":");
			// System.out.println(aux[1].trim());
			umidade1 = Double.valueOf(auxiliar[1].trim());

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 17)).replaceAll("%", "").split(":");
			// System.out.println(aux[1].trim());
			umidade2 = Double.valueOf(auxiliar[1].trim());

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 23)).replaceAll("%", "").split(":");
			// System.out.println(aux[1].trim());
			umidade3 = Double.valueOf(auxiliar[1].trim());

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 29)).replaceAll("%", "").split(":");
			// System.out.println(aux[1].trim());
			umidade4 = Double.valueOf(auxiliar[1].trim());

			System.out.println("debug2");

		} catch (Exception e) {
			System.out.println("falha na leitura");
			e.printStackTrace();
		}

		umidade = new LineChartModel();
		ChartData data = new ChartData();

		LineChartDataSet dataSet = new LineChartDataSet();
		List<Number> values = new ArrayList<>();
		values.add(umidade4);
		values.add(umidade3);
		values.add(umidade2);
		values.add(umidade1);
		values.add(umidade0);

		dataSet.setData(values);
		dataSet.setFill(false);
		dataSet.setLabel("Historico");
		dataSet.setBorderColor("rgb(0, 204, 0)");
		dataSet.setLineTension(0.1);
		data.addChartDataSet(dataSet);

		List<String> labels = new ArrayList<>();
		labels.add("5");
		labels.add("4");
		labels.add("3");
		labels.add("2");
		labels.add("1");
		data.setLabels(labels);

		// Options
		LineChartOptions options = new LineChartOptions();
		Title title = new Title();
		title.setDisplay(true);
		options.setTitle(title);

		umidade.setOptions(options);
		umidade.setData(data);
	}

	// SOLO
	public void createLineSolo() {

		Integer solo0 = null;
		Integer solo1 = null;
		Integer solo2 = null;
		Integer solo3 = null;
		Integer solo4 = null;
		String[] auxiliar;
		String aux = null;

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

			System.out.println("debug1");
			// arrayTemperaturas.add(arquivoArray.get(quantidadeElementosLista - 3));
			// arrayTemperaturas.add(arquivoArray.get(quantidadeElementosLista - 9));

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 2)).split("Solo");
			aux = auxiliar[1].trim();
			if (aux.equals("Seco")) {
				solo0 = -1;
			}
			if (aux.equals("Ideal")) {
				solo0 = 0;
			}
			if (aux.equals("Encharcado")) {
				solo4 = 1;
			}
			// System.out.println(luminosidade0);

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 8)).split("Solo");
			aux = auxiliar[1].trim();
			if (aux.equals("Seco")) {
				solo1 = -1;
			}
			if (aux.equals("Ideal")) {
				solo1 = 0;
			}
			if (aux.equals("Encharcado")) {
				solo1 = 1;
			}

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 14)).split("Solo");
			aux = auxiliar[1].trim();
			if (aux.equals("Seco")) {
				solo2 = -1;
			}
			if (aux.equals("Ideal")) {
				solo2 = 0;
			}
			if (aux.equals("Encharcado")) {
				solo2 = 1;
			}

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 20)).split("Solo");
			aux = auxiliar[1].trim();
			if (aux.equals("Seco")) {
				solo3 = -1;
			}
			if (aux.equals("Ideal")) {
				solo3 = 0;
			}
			if (aux.equals("Encharcado")) {
				solo3 = 1;
			}

			auxiliar = (arquivoArray.get(quantidadeElementosLista - 26)).split("Solo");
			aux = auxiliar[1].trim();
			if (aux.equals("Seco")) {
				solo4 = -1;
			}
			if (aux.equals("Ideal")) {
				solo4 = 0;
			}
			if (aux.equals("Encharcado")) {
				solo4 = 1;
			}

			System.out.println("debug2");

		} catch (Exception e) {
			System.out.println("falha na leitura");
			e.printStackTrace();
		}

		solo = new LineChartModel();
		ChartData data = new ChartData();

		LineChartDataSet dataSet = new LineChartDataSet();
		List<Number> values = new ArrayList<>();
		values.add(solo4);
		values.add(solo3);
		values.add(solo2);
		values.add(solo1);
		values.add(solo0);

		dataSet.setData(values);
		dataSet.setFill(false);
		dataSet.setLabel("Historico");
		dataSet.setBorderColor("rgb(255, 215, 0)");
		dataSet.setLineTension(0.1);
		data.addChartDataSet(dataSet);

		List<String> labels = new ArrayList<>();
		labels.add("5");
		labels.add("4");
		labels.add("3");
		labels.add("2");
		labels.add("1");
		data.setLabels(labels);

		// Options
		LineChartOptions options = new LineChartOptions();
		Title title = new Title();
		title.setDisplay(true);
		title.setText("-1 = SECO		| 		0 = IDEAL		| 		1 = ENCHARCADO");
		options.setTitle(title);

		solo.setOptions(options);
		solo.setData(data);
	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public LineChartModel getLuminosidade() {
		return luminosidade;
	}

	public void setLuminosidade(LineChartModel luminosidade) {
		this.luminosidade = luminosidade;
	}

	public LineChartModel getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(LineChartModel temperatura) {
		this.temperatura = temperatura;
	}

	public LineChartModel getUmidade() {
		return umidade;
	}

	public void setUmidade(LineChartModel umidade) {
		this.umidade = umidade;
	}

	public LineChartModel getSolo() {
		return solo;
	}

	public void setSolo(LineChartModel solo) {
		this.solo = solo;
	}

}
