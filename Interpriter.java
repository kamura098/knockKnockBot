package ircbot;

public class Interpriter {

private String line,name,holdName,jokeState;
private String botname;
private static long startTime,currTime;
private boolean quiet;


public Interpriter(){
botname=Ircbot.getName().toLowerCase();
startTime=0;
jokeState="";
holdName="";
quiet=false;
}//end of constructor

/**savas the entire line and the persons name then passes the message;*/
public String response(String message){
line=message;
currTime=System.currentTimeMillis();
if(currTime-startTime>120000)holdName="";
try{
int j = line.indexOf('!');
name=line.substring(1,j);
}
catch(Exception e){System.out.println("error getting name");return"";}
message=message.substring(1);
int i = message.indexOf(':');
message=message.substring(i+1);

if(message.startsWith("^AACTION")){
message=name+" "+message.substring(9,message.length()-2);
}

if(quiet==true){
if(name.equals("kamura")&&message.equals(botname+" come back")){
quiet=false;
return "I'm back!";
}
else return "";
}


return parse(message.toLowerCase());
}//end response

/**checks for context commands*/
private String parse(String message){
String output;
if(message.length()<2)return "";
if(name.equals("nanochan")||name.equals("larsonbot"))return "";

if(holdName.equals(name))return nockNock(message,jokeState);
if(message.equals(".bots"))return bots();
if(message.contains("hello "+botname))return hello();
if(message.contains("hi "+botname))return hello();
if(message.contains("fuck you "+botname))return insults();
if(message.contains("lewds "+botname))return lewds();
if(holdName.isEmpty()&&message.equals("knock knock"))return nockNock("","");
if(message.equals("get lost "+botname))return quiet();
return "";
}//end parse





/*
*methods for generating output txt
*/


/**will make the bot quiet if kamura issues the command*/
private String quiet(){
if(name.equals("kamura")){
quiet=true;
return";_; b-bye then";

}
else return insults();
}//end quiet

/**responds to insults*/
private String insults(){
String[] fu={"well fuck you too "+name,"rude","BE NICE!","no bully please","say that to my monitor you fucking bitch","fuck off "+name,"n-no you","shut yer geggy!","do you kiss your waifu with that mouth?!"};
int i=(int)(Math.random()*fu.length);
return fu[i];
}//ends insults

/**respond to lewds botname*/
private String lewds(){
String[] le={"s-stop it","I have not been programmed to love","BAD TOUCH! BAD TOUCH!","@_@","yuck!","WAH! don't do that.","/me blushes","/me lewds "+name+" back"};
int i=(int)(Math.random()*le.length);
return le[i];
}//end lewds


/**responds to knock knock jokes*/
private String nockNock(String message,String state){

if(state.isEmpty()){
holdName=name;
jokeState="who";
System.out.printf(holdName+"\n"+name+"\n");

startTime=System.currentTimeMillis();
return "who's there?";
}
else if(state.equals("who")){
if(message.equals("banana"))jokeState="";
else jokeState="xwho";
startTime=System.currentTimeMillis();
return message+" who?";
}
else if(state.equals("xwho")){
String[] response={"ha ha!","te he he!","pffff!","I...I don't get it","I've already heard that one.","lol my curcuits are in orbit!","bah ha ha!","uggghhh!","uh, I'm sure that joke was funny but my laugh chip is acting up","[laughs]","lol","kek",""};
jokeState="";
holdName="";
int i =(int)(Math.random()*response.length);
return response[i];
}
System.out.println(" an Error occured in nocknock l76");
return "";
}//end nock nock


/**generates a responce if the hello command is activated*/
private String hello(){
String[] greeting={"h-hi","fo' shizzle","hello","greetings human known as","pshh beat it","'ello 'ello","hey","how's it going"};
int i =(int)(Math.random()*greeting.length);
return greeting[i]+" "+name;
}//end of hello 

/**responds to the .bots command*/
private String bots(){
return "hello, I'm a java bot p-please be nice";
}//end of bots

}//end Interpriter
