import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {
	
	public static int times(int n1, int n2) throws Exception {
		URL content = new URL("http://localhost:5000/?n1="+n1+"&n2="+n2);
		BufferedReader in = new BufferedReader(new InputStreamReader(content.openStream()));
		
		String inputLine;
		String var = "";
		while ((inputLine = in.readLine()) != null) {
		    if(var.isEmpty()) 
		    	var = inputLine;
		}
		in.close();
		
		return Integer.parseInt(var);
	}
	
	public static int sum(int n1, int n2) throws Exception {
		URL content = new URL("http://localhost:5000/?n1="+n1+"&n2="+n2);
		BufferedReader in = new BufferedReader(new InputStreamReader(content.openStream()));
		
		String inputLine;
		String var = "";
		while ((inputLine = in.readLine()) != null) {
		    if(var.isEmpty()) 
		    	var = inputLine;
		}
		in.close();
		
		return Integer.parseInt(var);
	}

	 public static void main(String[] args) throws Exception {
		int n1 = 3;
		int n2 = 2;
		
		int varA = sum(n1, n2);
		int varB = times(n1, n2);
		System.out.println("variavel A = " + varA);
		System.out.println("variavel B = " + varB);
		
		int result = varA + varB;
		System.out.println("\nsoma das variaveis = " + result);     
	 }     
}