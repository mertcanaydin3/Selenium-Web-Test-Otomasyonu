import org.junit.Test;

public class TestMain extends BaseTest {

    @Test
    public  void testYap() throws InterruptedException{

        checkTitle();
        girisKontrol("mertcanaydin222@gmail.com", "1998mert");
        aramaYap();
        ikinciSayfa();
        rastgeleUrun();
        fiyatDetay();
        sepeteEkle();   //BU ADIM VE SONRASI ÇALIŞMIYOR.
        sepeteGit();
        urunArttir();
        sepettenSil();



    }
}
