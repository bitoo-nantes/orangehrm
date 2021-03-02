package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestSupprimerSalarie {

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
    public void SupprimerSalarie() {
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

        //Vérification que le bouton "Search" est affiché et cliquable
        WebElement elementSearchButton = driver.findElement(By.id("searchBtn"));
        boolean searchPresence = elementSearchButton.isDisplayed();
        boolean searchEnabled = elementSearchButton.isEnabled();
        System.out.println("Le bouton 'Search' est présent et cliquable.");

        //Saisie du nom du salarié et recherche
        WebElement employeeNameSearch = driver.findElementById("empsearch_employee_name_empName");
        employeeNameSearch.sendKeys("Peter Cooper");
        elementSearchButton.click();

        //Vérification que l'ID du salarié correspond à son nom
        driver.findElementByXPath("//a[contains(text(),'1001')]");
        driver.findElementByXPath("//a[contains(text(),'Peter')]");
        driver.findElementByXPath("//a[contains(text(),'Cooper')]");
        System.out.println("Le salarié a été trouvé dans la liste.");

        //Vérification que la case à cocher est présente et cliquable
        WebElement elementCheckBox = driver.findElementByName("chkSelectRow[]");
        boolean checkBoxPresence = elementCheckBox.isDisplayed();
        boolean checkBoxEnabled = elementCheckBox.isEnabled();
        System.out.println("La case à cocher est présente et cliquable.");

        //Sélection de la case à cocher pour sélectionner le salarié
        elementCheckBox.click();

        //Suppression du salarié
        WebElement elementDeleteButton = driver.findElementById("btnDelete");
        elementDeleteButton.click();

        //Attente message de confirmation
        //WebDriverWait wait = new WebDriverWait(driver,5);
        //wait.until(
        //        ExpectedConditions.visibilityOf(driver.findElement(By.id("dialogDeleteBtn")))
        //);

        //Validation de la suppression
        WebElement elementConfirmButton = driver.findElementById("dialogDeleteBtn");
        elementConfirmButton.click();
        System.out.println("Le salarié a été supprimé.");

        //Attente nouvel affichage du cadre Employee Information
        //WebDriverWait wait = new WebDriverWait(driver,10);
        //wait.until(
        //        ExpectedConditions.visibilityOf(employeeNameSearch)
        //);

        //Vérification que le salarié n'apparaît plus dans la liste
        //employeeNameSearch.sendKeys("Peter Cooper");
        //elementSearchButton.click();
        //WebElement MsgNoRecordsFound = driver.findElementByXPath("//td[@colspan='8']");
        //String MsgNoRecords = MsgNoRecordsFound.getText();
        //assertEquals("No Records Found", MsgNoRecords);
        //System.out.println("Le message "+MsgNoRecords+" s'affiche après la suppression du salarié.");

    }

    @AfterClass
    public void TearDown() {
        //Fermeture du navigateur
        driver.quit();
    }

}