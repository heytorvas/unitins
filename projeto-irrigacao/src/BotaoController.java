
import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class BotaoController implements Serializable {

	private static final long serialVersionUID = 7043081925619872495L;

	public void entrarGrafico() {
		redirect("grafico.xhtml");
	}
	public void entrarMenu() {
		redirect("menu.xhtml");
	}
	
	public static void redirect(String url) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			addMessageError("Erro ao redirecionar a página.");
			e.printStackTrace();
		}
	}

	public static void addMessageError(String message) {
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(message));
	}

}
