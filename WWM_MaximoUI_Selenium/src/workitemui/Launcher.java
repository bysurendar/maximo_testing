package workitemui;

public class Launcher {
	public static void main (String[] args) {		
		CreateArtifact defectClass = new CreateSTDefect();	
		defectClass.fileName = args[0] ;
		defectClass.execute();		
		} 	
}