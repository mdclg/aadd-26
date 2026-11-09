package aadd.beans.comunicacion;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class WebSocketController implements Serializable {

	@Inject
	private WebSocketSender webSocketSender;

	private String userName;

	private String message;

	public void sendMessage() {
		webSocketSender.send(String.format("%s: %s", userName, message));
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {

		return message;

	}

	public void setMessage(String message) {

		this.message = message;

	}

}