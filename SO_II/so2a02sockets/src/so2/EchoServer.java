package so2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private int serverPort;	
    
    public EchoServer(int p) {
	serverPort= p;		
    }
    
    
    public static void main(String[] args) throws IOException{
	System.err.println("SERVER...");
	if (args.length<1) {
	    System.err.println("Missing parameter: port number");	
	    System.exit(1);
	}
	int p=0;
	try {
	    p= Integer.parseInt( args[0] );
	}
	catch (Exception e) {
	    e.printStackTrace();
	    System.exit(2);
	}
	
	
	EchoServer serv= new EchoServer(p);
	serv.servico();   // activa o servico
    }

    
    // activa o servidor no porto indicado em "serverPort"
    public void servico() throws IOException
    {
        // exercicio 2: inicializar um socket para aceitar ligacoes...
        Socket client = new Socket();
        try{
            ServerSocket sock = new ServerSocket(serverPort);
            client = sock.accept();
            
            DataOutputStream sout= new DataOutputStream(client.getOutputStream());
            BufferedReader breader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String resposta = breader.readLine();
            
            /*InputStream socketIn = client.getInputStream();
            byte[] b = new byte[1024];
            int lidos = socketIn.read(b);
            String resposta = new String(b, 0, lidos);*/

            System.out.println("Recebi: " + resposta);
            resposta = "2os_" + resposta + "_so2";
            System.out.println(resposta);
                        
            /*OutputStream socketOut = client.getOutputStream();
            socketOut.write(resposta.getBytes());*/
            sout.flush();

            client.close();
    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(client != null && client.isConnected()){
                client.close();
            }
        }


    }


}
