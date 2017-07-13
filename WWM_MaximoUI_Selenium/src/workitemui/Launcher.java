package workitemui;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException; 
import org.apache.commons.io.FileUtils; 

public class Launcher {
	public static void main (String[] args)  {
		
		// create the command line parser
		CommandLineParser parser = new DefaultParser();

        // create the Options
        Options options = new Options();
        options.addOption("f", "input", true, "Input excel filename with fullpath");
        options.addOption("a", "action", false, "perform action");
        options.addOption("s", "startAt", true, "Start the Counter for Defect");
		
        String inputFileName = "";
        int startAt = 1;
        
        try { 
        // parse the command line arguments
        	CommandLine line = parser.parse(options, args);
        	
		// validate that input file has been set
        	if (line.hasOption("input")) {
        		inputFileName = line.getOptionValue("input").trim();
        		System.out.println(inputFileName);
        	}
        	
        	if (line.hasOption("startAt")) {
        		startAt = Integer.parseInt(line.getOptionValue("startAt").trim());
        		System.out.println(startAt);
        	}
        	
        } 
        catch (Exception e) {
        	e.printStackTrace();
        	
        }
		
						
		CreateArtifact defectClass = new CreateSTDefect();	
		defectClass.setFileName(inputFileName);
		defectClass.setStartAt(startAt) ;
		defectClass.execute();		
		} 	
}