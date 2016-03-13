package cons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInterp {
	public void listenSocket(){
		//Create socket connection
		   try{
		     socket = new Socket("kq6py", 4321);
		     out = new PrintWriter(socket.getOutputStream(), 
		                 true);
		     in = new BufferedReader(new InputStreamReader(
		                socket.getInputStream()));
		   } catch (UnknownHostException e) {
		     System.out.println("Unknown host: kq6py");
		     System.exit(1);
		   } catch  (IOException e) {
		     System.out.println("No I/O");
		     System.exit(1);
		   }
		}
	public void actionPerformed(ActionEvent event){
		   Object source = event.getSource();

		   if(source == button){
		//Send data over socket
		      String text = textField.getText();
		      out.println(text);
		      textField.setText(new String(""));
		      out.println(text);
		   }
		//Receive text from server
		   try{
		     String line = in.readLine();
		     System.out.println("Text received: " + line);
		   } catch (IOException e){
		     System.out.println("Read failed");
		     System.exit(1);
		   }
		}  
	static String[] partsout(String[] array, int index)
	{
		 String [] result = new String[array.length-index];
		 for (int i=index; i<(array.length); i++)
		  {
			 result[i-index] = array[i];
			 
	  	  }
		return result;
	}
	@SuppressWarnings("null")
	public static void main(String[] args) {
				String[] parts;
		
				InputStreamReader isr = new InputStreamReader ( System.in );
		BufferedReader br = new BufferedReader ( isr );
				String s = null;
				System.out.printf("Enter String%n");
		try {
		   while ( (s = br.readLine ()) != null ) {
			  parts = s.split(" ");
			   switch (parts[0]) {
	            case "ping":  System.out.printf("Your command is ping%n");
	                     break;
	            case "echo":           	
	            	    	System.out.printf("Your command is 'echo' with the parameter %s%n",String.join(" ", partsout(parts,1)));
	                     break;
	            case "login": System.out.printf("Your command is 'login' with the parameters: name=%s, password=%s%n", parts[1], parts[2]);
	                     break;
	            case "list":  System.out.printf("Your command is 'list'%n");
	                     break;
	            case "msg":  System.out.printf("Your command is 'msg' with the name: %s, text of the msg is: %s%n",parts[1], String.join(" ", partsout(parts,2)));
	                     break;
	            case "file":  System.out.printf("Your command is 'file' with the parameters: username=%s, filename=%s%n", parts[1], parts[2]);
	                     break;
	            case "exit":  System.exit(0);
	                     break;
	            default: System.out.println("Invalid command");
	                     break;
	        }
		   }
		}
		catch ( IOException ioe ) {
		   // won't happen too often from the keyboard
		}
	}

}
