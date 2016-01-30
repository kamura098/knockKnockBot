package ircbot;

import java.io.*;
import java.net.*;

public class Ircbot{

private static String BOT_NAME="knockknockbot";
public static void main(String[] args) throws Exception{
Interpriter answer=new Interpriter();

String server = "irc.freenode.net";
String channel = "#lainchan";

System.out.println("made variables");

Socket socket = new Socket(server,6667);
BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

System.out.println("made socket and read write");

writer.write("NICK "+BOT_NAME+"\r\n");
writer.write("USER "+BOT_NAME+" 8 * : javabot\r\n");
writer.flush();

System.out.println("made name");

String line=null;
while ((line = reader.readLine())!=null){
if(line.indexOf("004")>=0){
break;
}
else if(line.indexOf("433")>=0){
System.out.println("nick in use");
return;
}

}
System.out.println("added name");

writer.write("JOIN "+channel+"\r\n");
writer.flush();

System.out.println("joined channel");

while((line=reader.readLine())!=null){
if(line.startsWith("PING ")){
writer.write("PONG "+line.substring(5)+"\r\n");
writer.flush();
}
else{
String output=answer.response(line);
if(!output.isEmpty()){
writer.write("PRIVMSG "+channel+" :"+output+"\r\n");
writer.flush();
System.out.printf(line+"\n"+output+"\n");
}
}
}

}//end main

public static String getName(){
return BOT_NAME;
}//end getName

}//end ircbot
