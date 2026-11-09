package aadd.beans.comunicacion;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

@Named
@ApplicationScoped
public class WebSocketSender implements Serializable {

	@Inject
	@Push
	private PushContext pushContext;

	public void send(String message) {

		System.out.println("Enviando mensaje: " + message);

		pushContext.send(message);

	}

}