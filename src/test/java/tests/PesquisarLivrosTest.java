package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class PesquisarLivrosTest {
    @Test
    public void BuscaLivro(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\porto\\Drivers\\chromedriver.exe");
        ChromeDriver navegador1 = new ChromeDriver();

        //Entrar em Submarino.com.br
        navegador1.get("http://www.submarino.com.br/categoria/livros");
        navegador1.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // e entrar no link do primeiro livro exibido na loja.
        navegador1.findElementByPartialLinkText("Livro -").click();

        //Ao entrar na página do livro verificar quem é o autor do livro.
        String AutorColuna1 = "//table[@class=\"table table-striped\"]/tbody//tr[3]/td[1]";
        By autorLinha1 = By.xpath(AutorColuna1);
        WebElement autorElemento1 = navegador1.findElement(autorLinha1);
        String textoAutor1 = autorElemento1.getText();

        String AutorColuna2 = "//table[@class=\"table table-striped\"]/tbody//tr[3]/td[2]";
        By autorLinha2 = By.xpath(AutorColuna2);
        WebElement autorElemento2 = navegador1.findElement(autorLinha2);
        String textoAutor2 = autorElemento2.getText();

        String ISBNColuna1 = "//table[@class=\"table table-striped\"]/tbody//tr[5]/td[1]";
        By linha1 = By.xpath(ISBNColuna1);
        WebElement ISBNElement1 = navegador1.findElement(linha1);
        String textoColuna1 = ISBNElement1.getText();

        String ISBNColuna2 = "//table[@class=\"table table-striped\"]/tbody//tr[5]/td[2]";
        By linha2 = By.xpath(ISBNColuna2);
        WebElement ISBNElement2 = navegador1.findElement(linha2);
        String textoColuna2 = ISBNElement2.getText();

        System.out.println(textoAutor1);
        System.out.println(textoAutor2);
        System.out.println(textoColuna1);
        System.out.println(textoColuna2);
        assertEquals("ISBN", textoColuna1);
        assertEquals("Autor(a)", textoAutor1);

        //Depois disso, o teste deverá entrar em mais duas lojas (ex: americanas.com.br, amazon.com.br, livrariacultura.com.br),
        ChromeDriver navegador2 = new ChromeDriver();
        navegador2.get("http://www.saraiva.com.br");
        navegador2.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador2.findElementByName("q").sendKeys(textoColuna2);
        navegador2.findElementByName("q").submit();
        //navegador2.findElement(By.className(class="nm-product-img-container"));
        navegador2.findElementByPartialLinkText("nm-product-img-container").click();

        String LivrSaraiva = "//table[@class=\"contributor livedata\"]";
        By detAutor = By.xpath(LivrSaraiva);
        WebElement autorSaraiva = navegador2.findElement(detAutor);
        String textoAutorSaraiva = autorSaraiva.getText();
        System.out.println(textoAutorSaraiva);

        ChromeDriver navegador3 = new ChromeDriver();
        navegador3.get("http://www.livrariacultura.com.br/");
        navegador3.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador3.findElementByName("q").sendKeys(textoColuna2);
        navegador3.findElementByName("q").submit();

        String LivrCult = "type=\"search\"";
        By detalhaAutor = By.xpath(LivrCult);
        WebElement autorCult = navegador3.findElement(detalhaAutor);
        String textoAutorCult = autorCult.getText();

        // buscar pelo mesmo livro através do ISBN e verificar se o autor é o mesmo,
        // se for passar o teste, se não for falhar o teste.
        System.out.println(textoAutorSaraiva);
        System.out.println(textoAutorCult);

        assertEquals(textoAutor2,textoAutorSaraiva);
        assertEquals( textoAutor2,textoAutorCult);
        navegador1.quit();
        navegador2.quit();
        navegador3.quit();


    }
}
