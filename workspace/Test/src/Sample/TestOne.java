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
	String room;
	int i = 0;
	int value = 0;
	int calculatedGallon = 0;
	String manipulatedValue = null;
	String manipulatedFeet = null;
	public void launchApplication() {

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
		
		Assert.assertTrue(amountOfFeet.getText().equals(manipulatedFeet.trim()));
		Assert.assertTrue(gallons.getText().equals(manipulatedValue.trim()));

		driver.close();
		driver.quit();
	}

	public void enterRooms() {

		driver.findElement(By.name("rooms")).sendKeys(room);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
	}

	public void enterDimensionsPerRoom() {
		//when we have one room		
		driver.findElement(By.name("length-0")).sendKeys("20");
		driver.findElement(By.name("width-0")).sendKeys("20");
		driver.findElement(By.name("height-0")).sendKeys("2");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
	}

	public int actualGallonsManipulation() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Feed in amount of feet : ");
		int amountofFeeet = scan.nextInt();
		manipulatedFeet = " " + amountofFeeet;

		System.out.println("Number of Rooms to be painted : ");
		room = scan.next();
		int rooms = Integer.parseInt(room);

		while (i < rooms) {
			calculatedGallon = (amountofFeeet / feet);
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
		ob.launchApplication(); //comparing the data in the application with manipulated value
	}

}
