# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne on kolmitasoinen. Koodin perusrakenne menee seuraavasti.

Pakkaus jumpaddiction.ui sisältää JavaFX:llä toteutetun käyttöliittymän sen alla jumpaddiction.game sisältää sovelluslogiikan ja jumpaddiction.map muodostaa ja lukee tekstitiedoston pohjalta pelin tason. 

## Käyttöliittymä

Käyttöliittymässä on kolme eri näkymää:

- Alkuruutu
- Peliruutu
- Loppuruutu

Jokainen näistä on toteutettu omana Scene-objektina. Nämä ovat yksikerrallaan näkyvissä sovelluksessa eli Stagessa. 

Alkuruudusta voidaan peli käynnistää tai postua pelistä. Pelin käynnistäminen asettaa peliruudun aktiiviseksi. Pelin päätyttyä käyttäjän voittaessa/osuessa johonkin tulee loppuruutu automaattisesti näkyviin. Loppuruudusta voidaan peli käynnistää uudelleen tai poistua sovelluksesta.

## Sovelluslogiikka

Toiminnallisuudesta vastaa Game luokka. Game luokalla on vastuulla seuraavat toiminnot:

- Pelaaja luokan kutsuminen. 
- Map luokan kutsuminen.
- Animationtimer metodilla toteutettu moottori. 

Map luokka lataa määritetystä tiedostosta tason ja luo kartan pelille. 

Pelaaja luokassa on määritetty pelaajan alkusijainti ja ulkonäkö. 

![Luokka/pakkauskaavio](/dokumentaatio/kuvat/luokka_pakkauskaavio.png)

![Sekvenssikaavio](/dokumentaatio/kuvat/sekvenssi_jumpAddiction.png)
