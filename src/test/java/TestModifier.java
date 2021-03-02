package test.java;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.testng.Assert.assertTrue;

public class TestModifier {

    WebDriver driver;
    String name = "Admin";
    String password = "admin123";
    String empNumber = "0011";
    String middleName = "Robert";

    @BeforeClass
    public void pageAuth() {
        String s = System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        //driver.quit(); pour supprimer toutes les sessions existantes de Firefox
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }

        driver.findElement(By.id("txtUsername")).sendKeys(name);
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();

        //l'utilisateur consulte la liste des employés et cherche l'employé à modifier
        driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']/b")).click();
        System.out.println(" L'utilisateur clique sur l'onglet PIM ");

        //L'utilisateur choisit le menu "regarder la liste des employés", entre un id et valide sa recherche
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        driver.findElement(By.id("empsearch_id")).sendKeys(empNumber);
        driver.findElement(By.id("searchBtn")).click();

    }

    @Test
    public void employeeUpdate() {
        // Le responsable RH choisit l'élément à modifier, le prenom du resultat de recherche (en vue d'ajouter un deuxième prénom)
        driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td[3]/a")).click();

        //la page Edit s'affiche ; écrire les assertions
        //click bouton Edit ?
        driver.findElement(By.id("btnSave")).click();

        //Insérer un middle name
        driver.findElement(By.id("personal_txtEmpMiddleName")).sendKeys(middleName);
        driver.findElement(By.id("btnSave")).click();

        //Retourner à la liste des employés et vérifier l'enregistrement de la modification
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        driver.findElement(By.id("empsearch_id")).sendKeys(empNumber);
        driver.findElement(By.id("searchBtn")).click();

        //Ajouter les assertions : le nouveau first and Middle Name contient John
        String firstMiddleName = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td[3]/a")).getText();

        if (firstMiddleName.contains(middleName)) {
            System.out.println(middleName + " est présent sur la page, le profil de l'utilisateur a été modifié ");
        } else {
            System.out.println(middleName + " n'est pas présent sur la page, le profil de l'utilisateur n'a pas été modifié ");
        }

        try    {
            Thread.sleep(5000);
        }
        catch(
                InterruptedException ie)

        {
        }

    }

    @Test
    public void takePhoto() throws Exception {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = new Robot().createScreenCapture(screenRect);

        File imageFile = new File("Modifier_CaptureEcran.png");

        ImageIO.write(capture, "png", imageFile );
        assertTrue(imageFile .exists());
        System.out.println("La capture d'écran est rangée dans le dossier suivant : C:/Users/marti/IdeaProjects/projet_final_formation");
    }



    @AfterClass
    public void tearDown() {
        System.out.println(" L'utilisateur ferme le navigateur ");
        //Fermeture du navigateur
        driver.quit();
    }

}
