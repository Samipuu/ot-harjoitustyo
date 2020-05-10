# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne on kolmitasoinen. Koodin perusrakenne menee seuraavasti.

![pakkauskaavio](/dokumentaatio/kuvat/pakkauskaavio.png)

Pakkaus jumpaddiction.ui sisältää JavaFX:llä toteutetun käyttöliittymän sen alla jumpaddiction.game sisältää sovelluslogiikan ja jumpaddiction.map muodostaa ja lukee tekstitiedoston pohjalta pelin kartan.

Pakkaus jumpaddiction.ui:n kautta käytetään myös jumpaddiction.google luokkaa, josta lisätään tulokset tulostaululle ja luotaan nykyisen tulostaulun tilanne.  



## Käyttöliittymä

Käyttöliittymässä on neljä eri näkymää:

- Aloitusvalikko
- Peliruutu
- Asetukset-valikko
- Tuloslista

Jokainen näistä on toteutettu omana Scene-objektina. Nämä ovat yksikerrallaan näkyvissä sovelluksessa eli Stagessa. 

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta. Se ainoastaan kutsuu Game luokkaa annettavin asetuksin.

Asetukset ovat tallennettuna UI luokan muuttujiin, joten nämä säilyvät ainoastaan sovelluksen käynnissäolon ajan. Tuloslista päivitetään aina, kun tuloslistalla vaihdetaan vaikeusastetta tai tuloslista suljetaan ja avataan uudelleen. 

Alkuruudusta voidaan peli käynnistää, mennä asetukset-valikkoon ja tuloslistaan tai postua pelistä. Pelin käynnistäminen asettaa peliruudun aktiiviseksi. Pelin päätyttyä käyttäjän voittaessa/osuessa johonkin tulee aloitusvalikko automaattisesti jälleen näkyviin.

## Sovelluslogiikka

Toiminnallisuudesta vastaa Game luokka. Game luokalla on vastuulla seuraavat toiminnot:

- Pelaaja luokan kutsuminen. 
- Map paketin sisällä olevan map luokan kutsuminen.
- Animationtimer metodilla toteutettu moottori. 

Map luokka lataa määritetystä tiedostosta tason ja luo kartan pelille. 

Pelaaja luokassa on määritetty pelaajan alkusijainti ja ulkonäkö. 

Game luokka ei tarjoa luokan ulkopuolelle muita toimintoja kuin

- Scene getGameScene()

Tämä toiminto palauttaa pelinäkymän sitä kutsuvalle.

Luokkien suhdetta kuvaava luokka/pakkauskaavio:

![Luokka/Pakkauskaavio](/dokumentaatio/kuvat/luokka_pakkauskaavio2.png)

## Tietojen luku ja tallennus

Pakkaukset jumpaddiction.map ja jumpaddiction.google hoitavat pelin tarvitsemien tietojen lukemisen ja tallentamisen. 

Luokat noudattavat Data Access Object -suunnittelumallia ja ne on mahdollista korvata uusilla toteutuksilla, jos sovelluksen toimintapaa halutaan muuttaa. Luokkaa jumpaddiction.map käytetään suoraan sovelluslogiikasta, mutta on korvattavissa toisella toiminnallisuudella joka luo kartan GridPane näkymään. Luokkaa jumpaddiction.google käytetään jumpaddiction.ui.UI kautta, joten se on eristetty sovelluslogiikasta.

### Tiedostot

Sovellus lukee tason .txt tiedostosta. Aloitustaso on määritetty suoraan sovelluslogiikkaan, eikä jatkotasoja ole. 

Taso on kirjoitettu käyttäen TileMap periaatetta. Tiedosto sisältää 0,1,2,3,4 numeroita välein eroteltuna. Tiedoston alussa on kartan leveys ja korkeus. 

Sovellus myös lukee ja kirjoittaa Google Drive:ssa olevaan Google SpreadSheettiin tuloksia. Autentikointi SpreadSheettiin tapahtuu palvelutunnuksella, joka on määritetty service_account.p12 tiedostossa. SpreadSheet sisältää viisi eri taulukkoa, yksi jokaiselle vaikeusasteelle ja testeissä käytettävän test taulukon. 

SpreadSheettiin on määritetty myös oma metodi, joka huolehtii tuloksien järjestämisestä. Metodia kutsutaan aina, kun tuloslistalle lisätään tulos.

## Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka sekvenssikaavion avustuksella.

### Asetusten muokkaaminen

Kun käyttäjä haluaa vaihtaa asetuksia. Valitsee hän ensin Settings-painikkeen, joka avaa asetukset valikon. Asetusta muuttaessa muuttaa Settings luokka UI luokassa olevia muuttujia seuraavasti:

![Asetukset sekvenssikaavio](/dokumentaatio/kuvat/asetukset_sekvenssi.png)

Painiketta settings painettaessa kutsuu UI-luokka Settings-luokan metodia getSettings() joka aktivoi asetusnäkymän aktiiviseksi pääikkunaan. Asetuksia muuttaessa kutsuu settings-luokka UI-luokan vastaavia setMetodeja asetuksesta riippuen. Käyttäjän ollessa valmis asetuksien muokkaamisen kanssa painaa hän Main menu-painiketta joka kutsuu UI-luokan mainMenu() metodia ja palaa näin ollen aloitusvalikkoon.

### Pelin käynnistäminen

Kun käyttäjä haluaa pelata peliä. Valitsee hän aloitusvalikossa Start-painikkeen. Kuvataan sekvenssi-kaaviolla tämän toiminta:

![Pelin aloitus sekvenssikaavio](/dokumentaatio/kuvat/pelin_aloitus_sekvenssi.png)

Painiketta start painaessa kutsuu UI Game luokan konstruktoria, joka puolestaa kutsuu Map ja Player luokan konstruktoreita. Kartan ja pelaajan luonnin jälkeen. UI kutsuu getGameScene() metodia, joka palauttaa peliruudun. UI asettaa tämän jälkeen peliruudun aktiiviseksi. 

### Tulosten tarkastelu

Kun käyttäjä haluaa tarkastella tulostaulua. Valitsee hän aloitusvalikosta leaderboards-painikkeen. Tämän jälkeen oletuksena avautuu helpon vaikeusasteen tulokset. Kuvataan toiminta sekvenssikaaviolla seuraavasti:

![Tulostaulu sekvenssikaavio](/dokumentaatio/kuvat/tulostaulu_sekvenssi.png)

Painiketta Leaderboards painettaessa UI kutsuu Leaderboards luokan getLeaderboard() metodia. Tämä ensin lataa helpon-tason tulostaulun Googlen luokan metodin readData() avulla verkosta ja asettaa tämän jälkeen tulostaulun aktiiviseksi pääikkunaan. Vastaavasti eri vaikeusasteista painettaessa lataa peli näiden tulostaulut Googlen SpreadSheetistä.

## Ohjelman rakenteeseen jääneet heikkoudet

### Käyttöliittymä

Settings luokan ulkoasu on määritelty kokonaan metodissa setupSettings(). Käyttöliittymän taustalla olevassa käytössä on myös epäjohdonmukaisuuksia. Osa näkymistä asetetaan aktiiviseksi UI luokassa kutsumalla Scene-objekti sinne ja osa asetetaan aktiiviseksi kyseisen luokan metodissa. 

Käyttöliittymän rakenne kannattaisi määritellä FXML-määrittelyllä. Tällöin saataisiin käyttöliittymästä selkeämpi ja ulkoasullisesti sitä saataisiin parannettua. 

### Sovelluslogiikka

Koko kartta piirretään vaikka kaikki ei olekkaan näkyvissä. Tähän tulisi rakentaa kamera, jonka alue on vain kerrallaan piirrettynä. Tämä aiheuttaa myös sen, että jokainen tiili tarkastetaan, vaikka ne eivät olisi näkyvissä. Sovelluksen optimoinnin kannalta olisi tärkeää, että vain näkyvät/kohdalla oleva tiili tarkastettaisiin. 

Myös ladattava kartta on tällä hetkellä suoraan määriteltynä logiikassa, vaikka kartan luku tukisi myös muiden karttojen lukemista.

  
