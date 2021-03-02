package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List ;

import static org.testng.Assert.assertTrue;

public class TestConnexion_NonPassant {WebDriver driver;
    String nameNP = "Admin";
    String passwordNP = "admin124";

    @Test
    public void pageAuth() {
        String s = System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");

        try {
            Thread.sleep(5000);
        }
        catch(InterruptedException ie){
        }

        driver.findElement(By.id("txtUsername")).sendKeys(nameNP);
        driver.findElement(By.id("txtPassword")).sendKeys(passwordNP);
        driver.findElement(By.id("btnLogin")).click();

    }

    @Test
    public void testAVerifier(){
        //Vérifier que l'utilisateur n'est pas connecté.

        String credentialMsg = "valid";
        String notConnected = driver.findElement(By.id("spanMessage")).getText();
        System.out.println("L'utilisateur n'est pas connecté, le message affiché est : " + notConnected);

        if (notConnected.contains(credentialMsg)) {
            System.out.println("Le message " + notConnected + " est présent sur la page, l'utilisateur n'est pas connecté ");
        } else {
            System.out.println("Le message " + notConnected + " n'est pas présent sur la page. D'autres investigations sont nécessaires");
        }

        try {
            Thread.sleep(5000);
        }
        catch(InterruptedException ie){
        }

    }

    @Test
    public void takePhoto() throws Exception {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = new Robot().createScreenCapture(screenRect);

        File imageFileCNP = new File("ConnexionNP_CaptureEcran.png");

        ImageIO.write(capture, "png", imageFileCNP );
        assertTrue(imageFileCNP .exists());
        System.out.println("La capture d'écran est rangée dans le dossier suivant : C:/Users/marti/IdeaProjects/projet_fin_formation");
    }
    //
    @AfterClass
    public void tearDown() {
        //driver.quit(); pour supprimer toutes les sessions existantes du driver, ici Firefox
        //Fermeture du navigateur.

        driver.quit();
    }

}