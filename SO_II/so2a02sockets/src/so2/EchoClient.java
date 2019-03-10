package so2;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {

    private String address= null;
    private int sPort= 0;
    
    public EchoClient(String add, int p) {
	address= add;
	sPort=p;
    }
    
    
    public static void main(String[] args){
	// exigir os argumentos necessarios
	if (args.length < 3) {
	    System.err.println("Argumentos insuficientes:  java EchoClient ADDRESS PORT MESSAGE");
	    System.exit(1);
	}
	
	try {
	    String addr= args[0];
	    int p= Integer.parseInt(args[1]);
	    
	    EchoClient cl= new EchoClient(addr,p);
	    
	    // ler o texto a enviar ao servidor
	    String s= args[2];
	    
	    cl.go(s);   // faz o pretendido
	}
	catch (Exception e) {
	    System.out.println("Problema no formato dos argumentos");
	    e.printStackTrace();
	}
    }
    
    
    
    public void go(String msg)
    {
	
	// exercicio 1: mostrar a mensagem que vai ser enviada
	System.out.println("A minha mensagem: " + msg);
	
	
	// ... exercicio 3
        try{
            Socket sock = new Socket(address, sPort);
            //escrever
            OutputStream socketOut = sock.getOutputStream();
            socketOut.write(msg.getBytes());
            //ler
            InputStream socketIn = sock.getInputStream();
            byte[] b = new byte[1024];
            int lidos = socketIn.read(b);
            String resposta = new String(b, 0, lidos);
            //String resposta = socketIn.toString();
            System.out.println("Recebi: " + resposta);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Erro no envio da mensagem."); 
        }
	
    }
    
}
