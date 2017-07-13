package workitemui;

public abstract class CreateArtifact {
	
	String fileName;
	int startAt = 0 ; 
	abstract void getLoginParams();
	abstract void createArtifact();

	//template method pattern
	public final void execute(){	      
		getLoginParams();		
		createArtifact();
	   }
	
	public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getFileName()
    {
    	return fileName;
    }

    public int getStartAt()
    {
    	return startAt;
    }    
    
    public void setStartAt(int startAt)
    {
    	this.startAt = startAt;
    }  
    
}