import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

	 public static void main(String[] args) throws Exception {
		URL content1 = new URL("http://127.0.0.1:5000/?n1=5&n2=2");
		BufferedReader in1 = new BufferedReader(new InputStreamReader(content1.openStream()));
		String inputLineA;
		String varA = "";
		while ((inputLineA = in1.readLine()) != null) {
		    if(varA.isEmpty()) 
		    	varA = inputLineA;
		}
		in1.close();
		
		URL content2 = new URL("http://127.0.0.2:5000/?n1=5&n2=2");
		BufferedReader in2 = new BufferedReader(new InputStreamReader(content2.openStream()));
		String inputLineB;
		String varB = "";
		while ((inputLineB = in2.readLine()) != null) {
		    if(varB.isEmpty()) 
		    	varB = inputLineB;
		}
		in2.close();
		    
		System.out.println("variavel A = " + varA);
		System.out.println("variavel B = " + varB);
		int result = Integer.parseInt(varA) + Integer.parseInt(varB);
		System.out.println("\nsoma das variaveis = " + result);  
	 }     
}
