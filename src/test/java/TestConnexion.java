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
//import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static sun.misc.Version.println;

public class TestConnexion {

    WebDriver driver;
    String name = "Admin";
    String password = "admin123";

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

        driver.findElement(By.id("txtUsername")).sendKeys(name);
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();

    }

    @Test
    public void testAVerifier(){
        //Vérifier que l'utilisateur est connecté.

        String personnel = "Welcome";
        String connected = driver.findElement(By.id("welcome")).getText();
        System.out.println("Voici l'utilisateur connecté, nous lui souhaitons la bienvenue : " + connected);

        if (connected.contains(personnel)) {
            System.out.println(personnel + " est présent sur la page, l'utilisateur est connecté ");
        } else {
            System.out.println(personnel + " n'est pas présent sur la page, l'utilisateur n'est pas connecté ");
        }

        try {
            Thread.sleep(5000);
        }
        catch(InterruptedException ie){
        }

        //boolean isPresent;
        //boolean isVisible;

        //assertTrue(boutonlogin);
        //assertThat.boutonLogin.isPresent();
        //assertTrue(boutonlogin && );
        //assertThat.driver.findElement(By.id("btnLogin")).isVisible();
        //driver.findElement(By.id("btnLogin"))

    }

    @Test
    public void takePhoto() throws Exception {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = new Robot().createScreenCapture(screenRect);

        File imageFileConnec = new File("ConnexionOK_CaptureEcran.png");

        ImageIO.write(capture, "png", imageFileConnec );
        assertTrue(imageFileConnec .exists());
        System.out.println("La capture d'écran est rangée dans le dossier suivant : C:/Users/marti/IdeaProjects/projet_fin_formation");
    }

    @AfterClass
    public void tearDown() {
        //driver.quit(); pour supprimer toutes les sessions existantes du driver, ici Firefox
        //Fermeture du navigateur
        driver.quit();
    }
//
}
