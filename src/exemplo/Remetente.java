package exemplo;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Remetente {

	private final static String NOME_FILA = "olá";
	
	public static void main(String[] argv) throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
		
		factory.setHost("localhost");
		
		try(Connection conexao = factory.newConnection();
			Channel canal = conexao.createChannel()){
			canal.queueDeclareNoWait(NOME_FILA, false, false, false, null);
			
			String mensagem = "Olá, mundo!";
			
			canal.basicPublish("", NOME_FILA, null, mensagem.getBytes());
			
			System.out.println("[x] Mensagem '" + mensagem + "' enviada");
		}
		
	}
}