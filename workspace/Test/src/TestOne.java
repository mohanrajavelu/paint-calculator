package Sample;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class TestOne {

	public WebDriver driver; //creating webdriver instance
	final int feet = 400;
	final int gallon = 1;
	int length, width, height, calculatedGallon = 0, totalSqFeeet;
	String room, manipulatedValue = null, manipulatedFeet = null;
	int i = 0; 	
	
	
	public void requiredGallon_Application() {
	try	{
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mrajavelu\\workspace\\Test\\src\\Library\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("http://localhost:5000");
		System.out.println("Browser has opened the application");

		enterRooms();
		enterDimensionsPerRoom();

		WebElement amountOfFeet = driver.findElement(By.xpath("//td[2]"));
		WebElement gallons = driver.findElement(By.xpath("//td[3]"));

		System.out.println("Actual gallons provided in application :" + gallons.getText());
		System.out.println("Manipulated gallons as per math : " + manipulatedValue);
		
		if(totalSqFeeet>=400.00){
		
		Assert.assertTrue(amountOfFeet.getText().equals(manipulatedFeet.trim()));
		Assert.assertTrue(gallons.getText().equals(manipulatedValue.trim()));
		
		System.out.println("Total square feet is valid " + amountOfFeet.getText()
		+"\n" + "Gallons required is : " + gallons.getText());
		} else{
			System.out.println("Total square feet is invalid==>less than 400 as per requirement  ==>" + amountOfFeet.getText());
			System.out.println("Please try with next set of data");
		}
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
		driver.close();
		driver.quit();
	}

	public void enterRooms() {

		driver.findElement(By.name("rooms")).sendKeys(room);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
	}

	public void enterDimensionsPerRoom() {	
		//when we have one room		
		driver.findElement(By.name("length-0")).sendKeys(""+length);
		driver.findElement(By.name("width-0")).sendKeys(""+width);
		driver.findElement(By.name("height-0")).sendKeys(""+height);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
	}

	public int actualGallonsManipulation() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter length");
		length = scan.nextInt();
		System.out.println("Length is : " + length);
		
		System.out.println("Enter width");
		width = scan.nextInt();
		System.out.println("width is : " + width);
		
		System.out.println("Enter height");
		height = scan.nextInt();
		System.out.println("height is : " + height);		
		
		
		totalSqFeeet = length*width*height;
		System.out.println("Total amount of square Feet : " + totalSqFeeet);
		//For assertion, to compare string, integer totalSqFeet is concatenated with empty String
		manipulatedFeet = " " + totalSqFeeet;

		System.out.println("Number of Rooms to be painted : ");
		room = scan.next();
		int rooms = Integer.parseInt(room);

		while (i < rooms) {
			calculatedGallon = (totalSqFeeet / feet);
			System.out.println(
					"Gallons Manipulation for the feet, with respected to number of room is :" + room + " ===> " + calculatedGallon);
			i++;
		}
		manipulatedValue = " " + calculatedGallon;
		return calculatedGallon;
	}

	public static void main(String[] args) {
		TestOne ob = new TestOne();
		ob.actualGallonsManipulation(); //Manipulated value
		ob.requiredGallon_Application(); //comparing the data in the application with manipulated value
	}

}
