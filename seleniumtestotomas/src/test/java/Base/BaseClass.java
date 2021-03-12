package Base;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class BaseClass {

    public WebDriver webDriver;
    protected  static  String url = "https://www.gittigidiyor.com";


@Before
public  void openChrome(){

    System.setProperty("webdriver.chrome.driver", "C:\\Users\\can-m\\IdeaProjects\\seleniumtestotomas\\chromedriver.exe");
    webDriver = new ChromeDriver();
    webDriver.get(url);
    webDriver.manage().window().maximize();
}

public void checkUrl(){
    Assert.assertTrue(url.contains("https://www.gittigidiyor.com"));
    System.out.println("Ana Sayfa Kontrolü Yapıldı."+url);
}

public void checkLogin() {
    Assert.assertNotNull("/html/body/div[1]/header/div[3]/div/div/div/div[3]/div/div[1]/div");
    System.out.println("Login Kontrolü Yapıldı."+"/html/body/div[1]/header/div[3]/div/div/div/div[3]/div/div[1]/div");


}


public void checkSecondPage(){
    Assert.assertTrue("https://www.gittigidiyor.com/arama/?k=Bilgisayar&sf=2".contains("https://www.gittigidiyor.com/arama/?k=Bilgisayar&sf=2"));
    System.out.println("İkinci Sayfa Kontrolü Yapıldı.");
}


public void checkCart(){
    Assert.assertTrue("//div//h2[text()='Sepetinizde ürün bulunmamaktadır.'].".contains("Sepetinizde ürün bulunmamaktadır."));
    System.out.println("Sepetin Boş Olup Olmadığı Kontrol Edildi.");
}
private WebElement findElement(By element)
    {

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
        WebElement webElement = webDriverWait.
                until(ExpectedConditions.presenceOfElementLocated(element));
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;

    }
    public void ElementToBeClickableAndClick(By element){

        WebDriverWait wait = new WebDriverWait(webDriver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        System.out.println(element+" ");

        //Bu işlemi internetten hazır olarak aldım.
    }

    public boolean IsElementVisible(By element)
    {

        try
        {
            WebDriverWait wait = new WebDriverWait(webDriver,1);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
            return false;
        }
        catch (Exception e)
        {
            return true;
        }

    }


    public void clickElementByXPath(String tag,String element, String xpath){
        webDriver.findElement(By.xpath("//"+tag+"[@"+element+"='"+xpath+"']")).click();
    }

    public WebElement findElementByXPathS(String xpath){
        WebElement element = webDriver.findElement(By.xpath(xpath));
        return element;
    }

    public void clickElementByXPathS(String xpath){
        webDriver.findElement(By.xpath(xpath)).click();
    }
    public void ElementSendKey(By element,String sendkey)
    {

        WebDriverWait wait = new WebDriverWait(webDriver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(sendkey);
        System.out.println(element+" Sayfanın Tıklanabilir Olması Beklendi ve Tıklandı.");

    }


    // ELEMENT İŞLEMLERİNİ İNTERNETTEN HAZIR KOD OLARAK BULUP PROJEME ENTEGRE ETTİM.


    public double fiyatConvert(By priceText){
    String[] elementStringList = findElement(priceText).getText().trim().split(" ");
        String elementString = elementStringList[0].replaceAll(",", "");
        return Double.parseDouble(elementString);
    }


    @After
    public void chromeKapat() throws  InterruptedException{

    Thread.sleep(1000);
    webDriver.quit();
    }

}

