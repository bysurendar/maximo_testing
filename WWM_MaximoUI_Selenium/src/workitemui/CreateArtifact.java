package workitemui;

public abstract class CreateArtifact {
	
	String fileName;	
	abstract void getLoginParams(String fileName);
	abstract void createArtifact(String fileName);

	   //template method
	public final void execute(){	      
		getLoginParams(fileName);		
		createArtifact(fileName);
	   }
}