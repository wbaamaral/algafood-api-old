package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {
	
	@Autowired
	private NotificadorProperties properties;
	
	public NotificadorEmail() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.println("Host: " + properties.getHostServidor());
		System.out.println("Porta: " + properties.getPortaServidor());
		
		System.out.printf("Notificando %s através do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}

	public void setCaixaAlta(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
