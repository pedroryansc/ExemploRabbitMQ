package exemplo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Destinatario {

	private final static String NOME_FILA = "olá";
	
	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		
		factory.setHost("localhost");
		
		Connection conexao = factory.newConnection();
		
		Channel canal = conexao.createChannel();
		
		canal.queueDeclare(NOME_FILA, false, false, false, null);
		
		System.out.println("[*] Esperando por mensagens. Para cancelar, pressione CTRL + C");
		
		DeliverCallback deliverCallback = (destinatarioTag, entrega) -> {
			String mensagem = new String(entrega.getBody(), "UTF-8");
			
			System.out.println("[x] Mensagem '" + mensagem + "' recebida");
		};
		
		canal.basicConsume(NOME_FILA, true, deliverCallback, destinatarioTag -> { });
		
	}
}