import Base.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class BaseTest extends BaseClass {

    public  double fiyatBilgisi, sepetFiyatBilgisi;


    public void checkTitle()throws InterruptedException{

        checkUrl();

       Actions actions = new Actions(webDriver);
       actions.moveToElement(webDriver.findElement(By.xpath("//div[@data-cy='header-user-menu']"))).build().perform();
        Thread.sleep(2000);
        clickElementByXPath("a","data-cy","header-login-button");


    Thread.sleep(2000);


    }


    public  void girisKontrol(String userName, String password) throws  InterruptedException{



        findElementByXPathS("//div[@class='clearfix']//input[@name='kullanici']").sendKeys(userName);
        findElementByXPathS("//div[@class='clearfix']//input[@name='sifre']").sendKeys(password);
        findElementByXPathS("//input[@id='gg-login-enter']").click();
        Thread.sleep(3000);
    }

    public void aramaYap() throws  InterruptedException{

        ElementSendKey(By.cssSelector("input[name='k']"), "Bilgisayar");
        ElementToBeClickableAndClick(By.xpath(("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button")));
        Thread.sleep(6000);
    }

    public void ikinciSayfa() throws InterruptedException{

        JavascriptExecutor je = (JavascriptExecutor) webDriver;
        WebElement ikinciSayfa = webDriver.findElement(By.cssSelector("li[class='next-link']"));
        je.executeScript("arguments[0].scrollIntoView(true);",ikinciSayfa);
        ElementToBeClickableAndClick(By.cssSelector("li[class='next-link']"));
        checkSecondPage();
    }

    public void rastgeleUrun() throws  InterruptedException
    {

        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='clearfix']//ul//li//a[@class='product-link']"));


        Random random = new Random();
        int randomProduct = random.nextInt(products.size());
        int productNumberLi = randomProduct+1;

        WebElement clickedItem = findElementByXPathS("//div[@class='clearfix']//ul//li["+productNumberLi+"]//h3//span");
        String clickedItemString = clickedItem.getText();
        System.out.println("Clicked Item : "+clickedItemString);

        products.get(randomProduct).click();
        Thread.sleep(8000);

        clickElementByXPathS("//div[@id='sp-addbasket-button']//form//button//span[@class='action-text']");
        Thread.sleep(8000);

    }

    public void fiyatDetay() throws  InterruptedException
    {
        fiyatBilgisi= fiyatConvert(By.id("sp-price-lowPrice"));
        System.out.println("Ürün Detay Sayfasındaki Fiyat: "+fiyatBilgisi);
    }


    public void sepeteEkle() throws  InterruptedException{

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        WebElement sepeteEkleme = webDriver.findElement(By.cssSelector("button[id='add-to-basket']"));
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",sepeteEkleme);
        ElementToBeClickableAndClick(By.cssSelector("button[id='add-to-basket']"));
        Thread.sleep(3000);

    }

    public void sepeteGit() throws  InterruptedException{
        webDriver.get(url);
        ElementToBeClickableAndClick(By.name("cart"));
    }

    public void urunArttir() throws  InterruptedException{

        WebElement webElement = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div[1]/div[2]/div[6]/div[2]/div[2]/div[4]/div/div[2]/select"));
        Select select = new Select(webElement);
        select.selectByValue("2");
        WebElement adet = select.getFirstSelectedOption();
        System.out.println("Seçilen Ürün Sayısı : "+adet.getText());
        Thread.sleep(2000);

    }

    public void sepettenSil() throws  InterruptedException{
        ElementToBeClickableAndClick(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div[1]/div[2]/div[6]/div[2]/div[2]/div[3]/div/div[2]/div/a[1]/i"));
        checkCart();
        sepetFiyatBilgisi = fiyatConvert(By.cssSelector("div[class='total-price']"));
        System.out.println("Sepetteki Ürünüm Fiyatı : "+sepetFiyatBilgisi);
        if(fiyatBilgisi == sepetFiyatBilgisi){
            System.out.println(("Fiyatlar Aynı."));
        }
        else {
            System.out.println("Fiyatlar Farklı.");
            Assert.fail();

        }
    }

















}


