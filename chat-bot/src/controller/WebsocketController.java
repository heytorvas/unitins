package controller;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import dao.TesteDAO;
 
@ServerEndpoint("/websocketendpoint")
public class WebsocketController {
	TesteDAO dao = new TesteDAO();
     
    @OnOpen
    public void onOpen(){
        System.out.println("Open Connection ...");
    }
     
    @OnClose
    public void onClose(){
        System.out.println("Close Connection ...");
    }
     
    @OnMessage
    public String onMessage(String message){

    	String echoMsg;
    	
        //interpretarTexto
        if(dao.sqlAcharArquivo(message) == true) {
        	echoMsg = "[+]procurando arquivo no banco de dados";
        	echoMsg = dao.getResposta();
        }
        else {
        	echoMsg = "posso ajudar?";
        }
        
       
        return echoMsg;
    }
 
    @OnError
    public void onError(Throwable e){
        e.printStackTrace();
    }
    

 
}