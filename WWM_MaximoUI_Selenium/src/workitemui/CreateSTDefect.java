package workitemui;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateSTDefect extends CreateArtifact{
		
		//common variables
		String URL = "" ;
		String username = "" ;
		String password = "" ;		
				
		//defect details
		String defectTitle = "" ;
		String defectSummary = "" ;
		String defectStepsToReproduce = "" ;
		String defectComments = "" ;
		String defectAttachments = "" ;
		String coe = "" ;
		String integratedRelease = "" ;
		String ownedBy = "" ;
		String defectSeverity = "" ;
		String defectPriority = "" ;
		String testPhase = "" ;
		String earliestPhaseDetectable = "" ;
		String defectType = "" ;
		String environment = "" ;
		String environmentOther = "" ;
		String defectTag = ""; 
	
		public void getLoginParams(String fileName) { 
			
			try
	        {
	            FileInputStream file = new FileInputStream(new File(fileName));
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet commonParamsSheet = workbook.getSheetAt(0);
	            //XSSFSheet defectListSheet = workbook.getSheetAt(1);
	 
	                      
	            Row row = null;
	            //System.out.println(workbook.getSheetName(0));
	         
	            if ("common".equals(workbook.getSheetName(0)) ) {
	            	for (int i = 1; i <= commonParamsSheet .getLastRowNum (); ++i) {
	            		row=(Row) commonParamsSheet.getRow(i);
	            		Iterator<Cell> cells = row.cellIterator();
	                
	            		while (cells.hasNext())
	            		{ 
	                	
	            			Cell cell = cells.next();
	            			switch (cell.getStringCellValue()) {
	                		
	                	 	case "URL":
	                	 		cells.hasNext();
	                	 		cell = cells.next();                	 		                	 		
	                	 		System.out.println("URL:" + cell.getStringCellValue() + "\n");
	                	 		this.URL = cell.getStringCellValue()  ;
	                	 		break;
	                	 	case "username":                	 		
	                	 		cells.hasNext();
	                	 		cell = cells.next();
	                	 		System.out.print("User ID: " + cell.getStringCellValue() + "\n");
	                	 		this.username = cell.getStringCellValue()  ;
	                	 		break;
	                	 	case "password":
	                	 		cells.hasNext();                	 		
	                	 		cell = cells.next();
	                	 		System.out.print("Password: " + cell.getStringCellValue() + "\n");
	                	 		this.password = cell.getStringCellValue()  ;                	 		
	                	 		break;
	                	 		
	            			} // switch
	            		} // while
	                                    		
	            	} // for loop
	            	
	            } // close if
	            
	            workbook.close();
	    		file.close();
			
	        }
			 catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
			
		}
										
		public void createArtifact(String filePath) {						
			 try
		        {
		            FileInputStream file = new FileInputStream(new File(filePath));
		 		            
		            @SuppressWarnings("resource")
					XSSFWorkbook workbook = new XSSFWorkbook(file);		 		            
		            XSSFSheet defectListSheet = workbook.getSheetAt(1);
		 		                      
		            Row row = null;
		           		            
		            System.out.println(workbook.getSheetName(1));	        		    		
		            
		            if  ("defectlist".equals(workbook.getSheetName(1))) {
		            	System.out.println ("inside defect list");
		            	for (int j = 1; j <= defectListSheet.getLastRowNum (); ++j) {
		            		row=(Row) defectListSheet.getRow(j);
		            		
		            		this.defectTitle = row.getCell(1).getStringCellValue() ;
		            		this.defectSummary = row.getCell(2).getStringCellValue() ;
		            		this.defectStepsToReproduce = row.getCell(3).getStringCellValue() ;
		            		this.defectComments = row.getCell(4).getStringCellValue() ;
		            		this.defectAttachments = row.getCell(5).getStringCellValue() ;
		            		this.coe = row.getCell(6).getStringCellValue() ;
		            		this.integratedRelease = row.getCell(7).getStringCellValue() ;
		            		this.ownedBy = row.getCell(8).getStringCellValue()  ;
		            		this.defectSeverity = row.getCell(9).getStringCellValue() ;
		            		this.defectPriority = row.getCell(10).getStringCellValue() ;
		            		this.testPhase = row.getCell(11).getStringCellValue() ;
		            		this.earliestPhaseDetectable = row.getCell(12).getStringCellValue() ;
		            		this.defectType = row.getCell(13).getStringCellValue() ;
		            		this.environment = row.getCell(14).getStringCellValue() ;
		            		this.environmentOther = row.getCell(15).getStringCellValue() ;
		            		this.defectTag = row.getCell(16).getStringCellValue(); 
		            		
		            		if (this.defectTitle == "" || this.defectSummary == "" || this.coe == "" || this.ownedBy == "" ||
		            		    this.defectSeverity == "" || this.testPhase == "" ||  this.earliestPhaseDetectable == "" || 
		            		    this.defectType == "" || this.environment == ""
		            				) { 
		            				throw new NullPointerException("One of the Mandatory Field Value is missing");
		            			
		            		}
		            		
		            		System.out.println("Title:" + this.defectTitle + "\n");
		            		System.out.println("Summary:" + this.defectSummary + "\n");
		            		System.out.println("Steps to Reproduce:" + this.defectStepsToReproduce + "\n");
		            		System.out.println("Comments:" + this.defectComments + "\n");
		            		System.out.println("Attachments:" + this.defectAttachments + "\n");
		            		System.out.println("Coe:" + this.coe + "\n");
		            		System.out.println("IR:" + this.integratedRelease + "\n");
		            		System.out.println("Owned By:" + this.ownedBy + "\n");
		            		System.out.println("Sev:" + this.defectSeverity + "\n");
		            		System.out.println("Priority:" + this.defectPriority + "\n");            		
		            		System.out.println("Test Phase:" + this.testPhase + "\n");
		            		System.out.println("Earliest Phase Detectable:" + this.earliestPhaseDetectable + "\n");
		            		System.out.println("Defect Type:" + this.defectType + "\n");
		            		System.out.println("Environment:" + this.environment + "\n");
		            		System.out.println("Environment-Name:" + this.environmentOther + "\n");
		            		System.out.println("Tag:" + this.defectTag + "\n"); 
		            		createDefect(this);
		            	}
		            	
		            }
		            	
		            workbook.close();
		    		file.close();
		            
		        }
			
		            
		        catch (Exception e) 
		        {
		            e.printStackTrace();
		        }
			 
		}
		
		
		 public static void createDefect(CreateSTDefect inputExcel) {
		        // Create a new instance of the Firefox driver
				   		              		
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile ffprofile = profile.getProfile("default");   
				   
		    	WebDriver driver = new FirefoxDriver(ffprofile);

		        driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);    	
		        driver.get(inputExcel.URL);
		    			        

		        WebElement element = driver.findElement(By.id("jazz_app_internal_LoginWidget_0_userId"));        
		        element.sendKeys(inputExcel.username);
		               
		        // Find the text input element by its name
		        WebElement passField = driver.findElement(By.id("jazz_app_internal_LoginWidget_0_password"));        
		        passField.sendKeys(inputExcel.password);
		         
		        clickWebElementByXpath(driver,STDefectWebObject.LOGINBUTTON_XPATH);               
		        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		                     
		        clickWebElementByID (driver,STDefectWebObject.WORKITEM_ID) ;                
		        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		        
		        WebElement defectElement = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/table/tbody/tr/td[1]/table/tbody/tr/td/div/div/table/tbody/tr[16]/td[1]/a/span/span"));        
		        waitForTextToClickable(driver,50,defectElement);
		        defectElement.click();        
		        driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
		                                   
		        typeOnWebElementByXpath(driver,STDefectWebObject.DEFECTTITLE_XPATH,inputExcel.defectTitle);
		        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		                
		        WebElement createdByUser = driver.findElement(By.xpath("//a[contains(text(),\"" + inputExcel.username + "\")]"));
		        waitForTextToClickable(driver,200,createdByUser);
		                      
		        WebElement linksTab= driver.findElement(By.xpath( STDefectWebObject.LINKSTAB_XPATH ));
		        System.out.println(linksTab.getText());  
		        
		        Actions action = new Actions(driver);
		        action.moveToElement(linksTab).click().perform();        
		        linksTab.click();
		                
		        if (inputExcel.defectAttachments != "") { 
		        	WebElement uploadButton = driver.findElement(By.xpath("//input[@class=\"UploadControl\" and @type=\"file\"]"));      
		        	uploadButton.sendKeys(inputExcel.defectAttachments);
		        
		        	Path p = Paths.get(inputExcel.defectAttachments);
		        	String fileName = p.getFileName().toString();
		        
		        	System.out.println("File Name: " + fileName);
		                
		        	WebElement uploadedFileName = driver.findElement(By.xpath("//a[contains(text(),\"" + fileName + "\")]"));
		        	waitForTextToAppear(driver,200,uploadedFileName);
		       
		        }
		        
		        clickWebElementByXpath(driver, STDefectWebObject.OVERVIEWTAB_XPATH);                
		        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		                
		        clickWebElementByXpath(driver, STDefectWebObject.EDITDESCRIPTION_XPATH);                 
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		                
		        WebElement editDescriptionFrame = driver.findElement(By.xpath("//iframe[@class=\"dijitEditorIFrame\"]"));
		        driver.switchTo().frame(editDescriptionFrame);
		        typeOnWebElementByTagName(driver,"body",inputExcel.defectSummary);
		        driver.switchTo().defaultContent();
		        
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		        
		        clickWebElementByXpath(driver, STDefectWebObject.STEPSTOREPRODUCE_XPATH);   
		     
		        List<WebElement> iframe_element=driver.findElements(By.xpath("//iframe[@class=\"dijitEditorIFrame\"]"));
		        System.out.println("Number of iframes: "+ iframe_element.size());
		        
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);      
		        driver.switchTo().frame(iframe_element.get(1));
		        WebElement stepsToReproduceFrameBody = driver.findElement(By.tagName("body"));
		        
		        stepsToReproduceFrameBody.sendKeys(inputExcel.defectStepsToReproduce);
		        driver.switchTo().defaultContent();
		        
		        clickWebElementByXpath(driver, STDefectWebObject.PREVIEWSTEPSTOREPRODUCE_XPATH);
		                    
		        typeOnWebElementByXpath(driver,"//select[option[@label=\"" + inputExcel.coe + "\"]]",inputExcel.coe);          
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		        
		        typeOnWebElementByXpath(driver,"//select[option[@value=\"_sGF4wRN4EeaEcPX-pP2TOA\"]]",inputExcel.integratedRelease); 
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		                             
		        Select select = new Select(driver.findElement(By.xpath( "//select[option[@label=\"" + inputExcel.ownedBy + "\"]]")));
		        select.selectByVisibleText(inputExcel.ownedBy);
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		                     
		        typeOnWebElementByXpath(driver,"//select[option[@label=\"" + inputExcel.defectSeverity + "\"]]",inputExcel.defectSeverity);          
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		                        
		        typeOnWebElementByXpath(driver,"//select[option[@label=\"" + inputExcel.defectPriority + "\"]]",inputExcel.defectPriority);                
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		                   
		        typeOnWebElementByXpath(driver,"//select[option[@label=\"" + inputExcel.testPhase + "\"]]",inputExcel.testPhase);          
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		           
		        typeOnWebElementByXpath(driver,"//select[option[@label=\"" + inputExcel.earliestPhaseDetectable + "\"]]",inputExcel.earliestPhaseDetectable); 
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		                        
		        typeOnWebElementByXpath(driver,"//select[option[@label=\"" + inputExcel.defectType + "\"]]",inputExcel.defectType);
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		                 
		        typeOnWebElementByXpath(driver,"//select[option[@label=\"" + inputExcel.environment + "\"]]",inputExcel.environment);
		        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		        
		        List<WebElement> environmenttextbox = driver.findElements(By.xpath( "//input[@dojoattachpoint=\"_input\"]"));                                
		        
		        environmenttextbox.get(1).sendKeys(inputExcel.environmentOther); 
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		                
		        typeOnWebElementByXpath(driver,"//textarea",inputExcel.defectTag);
		        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		              
		        clickWebElementByXpath(driver,STDefectWebObject.ADDCOMMENTS_XPATH);
		        
		        List<WebElement> iframe_element1=driver.findElements(By.xpath("//iframe[@class=\"dijitEditorIFrame\"]"));
		        System.out.println("Number of iframes: "+ iframe_element.size());        
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);     
		        driver.switchTo().frame(iframe_element1.get(0));                
		        typeOnWebElementByTagName(driver,"body",inputExcel.defectComments);
		        driver.switchTo().defaultContent();
		         
		        clickWebElementByXpath(driver, STDefectWebObject.SAVEBUTTON_XPATH );               
		        System.out.println("Page title is: " + driver.getTitle());    
		        driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		     
		        //Close the browser
		        driver.quit();          
		    }
			   
			   public static void waitForTextToClickable(WebDriver newDriver, int timeoutInSeconds, WebElement element) {
				    WebDriverWait wait = new WebDriverWait(newDriver,timeoutInSeconds);
				    wait.until(ExpectedConditions.elementToBeClickable(element));
				}
			   
			   public static void waitForTextToAppear(WebDriver newDriver, int timeoutInSeconds, WebElement element) {
				    WebDriverWait wait = new WebDriverWait(newDriver,timeoutInSeconds);
				    wait.until(ExpectedConditions.visibilityOf(element));
				}
			   
			   public static void clickWebElementByXpath(WebDriver newDriver, String xpath) {
				   	WebElement uiElement = newDriver.findElement(By.xpath( xpath));
				   	uiElement.click();
				}
			   
			   public static void clickWebElementByID(WebDriver newDriver, String id) {
				   	WebElement uiElement = newDriver.findElement(By.id(id));
				   	uiElement.click();
				}
			   	   
			   public static void typeOnWebElementByXpath(WebDriver newDriver, String xpath, String enterText) {
				   	WebElement uiElement = newDriver.findElement(By.xpath( xpath));
				   	uiElement.sendKeys(enterText);
				}
			   
			   public static void typeOnWebElementByTagName(WebDriver newDriver, String tagName, String enterText) {
				   	WebElement uiElement = newDriver.findElement(By.tagName( tagName));
				   	uiElement.sendKeys(enterText);
				}
			   
			   public static void selectValueFromDropDown(WebDriver newDriver, String xpath, String selectText) {
				   Select select = new Select(newDriver.findElement(By.xpath( xpath)));
			       select.selectByVisibleText(selectText);
				}
}