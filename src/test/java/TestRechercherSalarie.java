package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestRechercherSalarie {

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

        //Accès à la page PIM
        driver.findElementById("menu_pim_viewPimModule").click();
    }

    @Test
    public void RechercherSalarie() {
        //Accès à la liste d'employés
        WebElement elementEmployeeList = driver.findElementById("menu_pim_viewEmployeeList");
        elementEmployeeList.click();

        //Vérification que les champs de saisie sont vides et saisissables
        WebElement elementEmployeeName = driver.findElementById("empsearch_employee_name_empName");
        elementEmployeeName.clear();
        WebElement elementId = driver.findElementById("empsearch_id");
        elementId.clear();
        WebElement elementSupervisorName = driver.findElementById("empsearch_supervisor_name");
        elementSupervisorName.clear();

        boolean employeeNameEnabled = elementEmployeeName.isEnabled();
        System.out.println("Le champ de saisie 'Employee Name' est saisissable.");
        boolean idEnabled = elementId.isEnabled();
        System.out.println("Le champ de saisie 'Id' est saisissable.");
        boolean supervisorNameEnabled = elementSupervisorName.isEnabled();
        System.out.println("Le champ de saisie 'SupervisorName' est saisissable.");

        //Vérification des options par défaut dans les listes déroulantes
        WebElement elementEmploymentSatus = driver.findElementById("empsearch_employee_status");
        WebElement ValueEmploymentStatus = driver.findElementByXPath("//select[@id='empsearch_employee_status']//option[@value='0'][contains(text(),'All')]");
        String StatusOption = ValueEmploymentStatus.getText();
        assertEquals("All", StatusOption);
        System.out.println("L'option " +StatusOption+" est affichée dans la liste déroulante Employement Status.");

        WebElement elementJobTitle = driver.findElementById("empsearch_job_title");
        WebElement ValueJobTitle = driver.findElementByXPath("//select[@id='empsearch_job_title']//option[@value='0'][contains(text(),'All')]");
        String JobTitleOption = ValueJobTitle.getText();
        assertEquals("All", JobTitleOption);
        System.out.println("L'option "+JobTitleOption+" est affichée dans la liste déroulante Employement Status.");

        WebElement elementSubUnit = driver.findElementById("empsearch_sub_unit");
        WebElement ValueSubUnit = driver.findElementByXPath("//select[@id='empsearch_sub_unit']//option[@value='0'][contains(text(),'All')]");
        String SubUnitOption = ValueSubUnit.getText();
        assertEquals("All", SubUnitOption);
        System.out.println("L'option "+SubUnitOption+" est affichée dans la liste déroulante Employement Status.");

        //Vérification que le bouton "Search" est affiché et cliquable
        WebElement elementSearchButton = driver.findElementById("searchBtn");
        boolean searchPresence = elementSearchButton.isDisplayed();
        boolean searchEnabled = elementSearchButton.isEnabled();
        System.out.println("Le bouton 'Search' est présent et cliquable.");

        //Saisie du nom du salarié, recherche et vérification de sa présence dans la liste
        elementEmployeeName.sendKeys("Peter Cooper");
        elementSearchButton.click();
        driver.findElementByXPath("//a[contains(text(),'1001')]");
        driver.findElementByXPath("//a[contains(text(),'Peter')]");
        driver.findElementByXPath("//a[contains(text(),'Cooper')]");
        System.out.println("Le salarié a été trouvé dans la liste.");

    }

    @AfterClass
    public void TearDown() {
        //Fermeture du navigateur
        driver.quit();
    }

}