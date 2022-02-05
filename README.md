<div id="top"></div>

<!--  LOGO -->
<br />
<div align="center">
  <a href="https://www.honorbuy.com/">
    <img src="/images/logo.jpg" alt="Logo" width="338" height="100">
  </a>
  <h2 align="center">Testiranje web stranice honorbuy.com</h2>
</div>

<!-- Sadržaj -->
  <h2>Sadržaj</h2>
  <ul>
    <li><a href="#o-projektu">O projektu</a></li>
    <li><a href="#korištene-tehnologije">Korištene tehnologije</a></li>
    <li><a href="#detalji">Detalji</a>
      <ul>
        <li><a href="#LogIn-test">LogIn test</a></li>
        <li><a href="#SetBackInStockReminder-test">SetBackInStockReminder test</a></li>
        <li><a href="#addToCart-test">AddToCart test</a></li>
        <li><a href="#EmptyCart-test">EmptyCart test</a></li>
        <li><a href="#AddToWishList-test">AddToWishList test</a></li>
      </ul>
    </li>
    <li><a href="#autor">Autor</a></li>
  </ul>

<!-- O projektu -->
## O projektu

Ovaj projekt razvijen je za kolegij Metode i tehnike testiranja programske podrške. 
Sastoji se od ukupno pet testnih slučajeva, u kojima se testira prijava korisnika, dodavanje proizvoda u košaricu, uklanjanje proizvoda iz košarice, pretplaćivanje 
na obavijest o postojanju proizvoda u zalihama ukoliko je trenutno rasprodan, te zaključno dodavanje proizvoda na listu favorita (wishlist). 
Saznajte više na [odjeljku detalji](#detalji).

<p align="right">(<a href="#top">back to top</a>)</p>

## Korištene tehnologije

Ovaj odjeljak sadrži sve veće biblioteke,radne okvire korištene za razvoj projekta:

* [Java](https://www.java.com/en/)
* [Maven](https://maven.apache.org/)
* [Katalon](https://www.katalon.com/resources-center/blog/katalon-automation-recorder/)
* [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/)
* [TestNG](https://testng.org/doc/)
* [Maven Surefire Report Plugin](https://maven.apache.org/surefire/maven-surefire-report-plugin/)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Detalji -->
## Detalji

### LogIn test
Testiranje prijave korisnika s valjanim podacima. Realizacija uvoza valjanih podataka olakšana je klasom `Credentials.java`, koja kao svojstvo sadrži email i loziku:

    public class Credentials {
        private String email;
        private String password;

        Credentials(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
    
Prilikom pokretanja, chromedriver navigira pretraživač do lokacije `https://www.honorbuy.com/`, nakon čega se odabire link 
s tekstom "Log in". Zatim, upisuju se validni podaci iz privatne datoteke u odgovarajuća polja na web stranici. Klika se gumb za 
prijavu, nakon čega se provjerava pojavljuje li se vlastito ime i prezime korisnika na vrhu stranice, što bi značilo da je korisnik 
prijavljen.
  
### SetBackInStockReminder test
Testiranje mogućnosti pretplate na obavijest od primitku proizvoda u skladište nakon rasprodavanja.
Budući da je za sve ostale testove potrebno prijavljivanje korisnika, funkcija prijave izdvojena je u posebnu klasu `Validator.java`:
    
    public class Validator {

        static void logIn(WebDriver driver){
            Credentials credentials = new Credentials("x", "y");

            try {
                File file = new File("C:\\Users\\Domagoj\\Desktop\\credentials.txt");
                Scanner myReader = new Scanner(file);
                String[] input = new String[2];
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    input = data.split(",");
                }
                myReader.close();
                credentials = new Credentials(input[0], input[1]);
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            driver.findElement(By.className("login")).click();
            driver.findElement(By.id("email")).clear();
            driver.findElement(By.id("email")).sendKeys(credentials.getEmail());
            driver.findElement(By.id("passwd")).clear();
            driver.findElement(By.id("passwd")).sendKeys(credentials.getPassword());
            driver.findElement(By.id("SubmitLogin")).click();
            Assert.assertEquals(driver.findElement(
                By.xpath("//*[@id=\"header_user_info\"]/div[3]/a/span")).getText(),
                "Domagoj Bunoza"
            );
        }
    }

Nakon prijave odabire se jedan proizvod koji trenutno nije dostupan, te se odabire opcija pretplate na dostupnost proizvoda putem odgovarajućeg gumba. Nakon toga 
čeka se dijalog koji se prikazuje jer je navedena email adresa već pretplaćena na dostupnost odabranog proizvoda.

### AddToCart test
Testiranje dodavanja proizvoda u košaricu. Za navigaciju korištena je klasa `Navigator.java`, koja ovoj testnoj klasi služi za 
navigaciju na početnu stranicu i dodavanje proizvoda u košaricu:


    public class Navigator {
        static void goToHomePage(WebDriver driver){
            driver.findElement(By.xpath("//img[@alt='HonorBuy Shop']")).click();
        }

        static void add2ProductsToCart(WebDriver driver){
            driver.findElement(By.xpath("//*[@id=\"product_list\"]/div/div[2]/div[1]/div/div[1]/a")).click();
            driver.findElement(By.xpath("//div[@id='quantity_wanted_p']/div/a[2]")).click();
            driver.findElement(By.name("Submit")).click();
            driver.findElement(By.xpath("//div[@id='shopping_cart']/a/span")).click();
        }
    }

Nakon dodavanja dva proizvoda u košaricu, navigira se unutar košaricu, gdje se provjerava postoje li dva proizvoda unutar košarice.


### EmptyCart test
Testiranje uklanjanja proizvoda iz košarice. 
Nakon prijave korisnika i dodavanja proizvoda u košaricu, navigira se unutar košarice, a zatim se uklanjaju proizvodi iz košarice koji su 
dodani metodom klase `Navigator.java` dodatnom metodom iste klase:

    static void emptyCart(WebDriver driver){
        driver.findElement(By.id("2047_19687_0_3178870")).click();
    }

Na ovaj način ispraznjuje se košarice, nakon čega se provjerava pojavljuje li se upozorenje koje glasi "Your shopping cart is empty."


### AddToWishList test
Testiranje dodavanja proizvoda na listu želja/favorita (wishlist).

Nakon prijave, odabire se određena kategorija na web stranici. Proizvodi se sortiraju na način da se prvi prikazuju proizvodi koji su dostupni i u zalihama.
Prilikom odabira jednog proizvoda, njegovo ime se pohranjuje u lokalnu varijablu. Na stranici proizvoda odabire se gumb kojim se proizvod dodaje na popis želja/favorita.
Zatim, navigira se do stranice "Wishlist", gdje se može vidjeti kako postoji proizvod u listi želja. Pritiskom na gumb "View", otkriva se proizvod koji se nalazi
na popisu želja korisnika. Provjerava se jednakost imena proizvoda iz liste želja i prethodno pohranjenog imena u lokalnoj varijabli.

Nakon pokretanja svih testova, stvara se izvještaj sure-fire-report plugina:

<a href="target/surefire-reports/index.html">
    <img src="/images/sure-fire-report.png" alt="report" width="1142" height="565">
  </a>

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Autor -->
## Autor

Domagoj Bunoza - student 1. diplomskog studija programskog inženjerstva (DRC)

