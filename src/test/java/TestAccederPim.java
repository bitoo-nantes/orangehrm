package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAccederPim {

    private FirefoxDriver driver;

    @BeforeClass
    public void setUp() {
        String s = System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        //Lancement du site
        driver.get("https://opensource-demo.orangehrmlive.com");

        //Connexion en tant qu'admin
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
    }

    @Test
    public void AccederPim() {
        //Accès au menu PIM
        driver.findElementById("menu_pim_viewPimModule").click();

        //Vérification que l'élément "Employee List" est présent et cliquable
        WebElement elementEmployeeList = driver.findElementById("menu_pim_viewEmployeeList");
        boolean employeeListPresence = elementEmployeeList.isDisplayed();
        System.out.println("L'élément 'Employee List' est présent.");
        boolean employeeListEnabled = elementEmployeeList.isEnabled();
        System.out.println("L'élément 'Employee List' est cliquable.");

        //Vérification que l'élément "Add Employee" est présent et cliquable
        WebElement elementAddEmployee = driver.findElementById("menu_pim_addEmployee");
        boolean addEmployeePresence = elementAddEmployee.isDisplayed();
        System.out.println("L'élément 'Add Employee' est présent : "+addEmployeePresence);
        boolean addEmployeeEnabled = elementAddEmployee.isEnabled();
        System.out.println("L'élément 'Add Employee' est cliquable : "+addEmployeeEnabled);

    }

    @AfterClass
    public void TearDown() {
        //Fermeture du navigateur
        driver.quit();
    }

}
