# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin testain JUnitilla sekä manuaalisesti tehtyjen järjestelmätaso testien kautta.

## Automaattitestaus

### Sovelluslogiikka

Automatisoidut testit kohdistuvat luokkiin [jumpaddiction.game](/src/main/java/jumpaddiction/game), [jumpaddiction.map](/src/main/java/jumpaddiction/map) ja [jumpaddiction.google](/src/main/java/jumpaddiction/map). Sovelluslogiikkaa jumpaddiction.game luokassa on testattu yhdellä integraatiotestillä ja useilla yksikkötesteillä. Kaikki testit löytyvät luokasta [JumpAddictionTest](/src/test/java/JumpAddictionTest.java).

Integraatiotesti on tehty varmistamaan ensimmäisen tason lataamisen onnituminen ja animaation pyöriminen ilman virheilmoituksia. Testissä simuloidaan sovelluksen käynnistäminen ja pelissä häviäminen. 

Yksikkötestejä on tehty varmistamaan kriittisten toimintojen oikein toimivuus sovelluslogiikassa.

### DAO-luokat

DAO-luokkia jumpaddiction.google ja jumpaddiction.map on testattu erinlaisin yksikkötestein. Jumpaddiction.map luokkaa myös yhdessä jumpaddiction.game luokan kanssa integraatiotestissä. 

jumpaddiction.map luokkaa on testattu lukemalla ensimmäisen tason dokumenttia.

jumpaddiction.google luokkaa on testattu hyödyntämällä Googlen SpreadSheet palveluun tehdyllä erillisellä testaus taulukolla. 

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testausten rivikattavuus on 93% ja haarautumakattavuus on 85%.

![Jacoco_raportti](/dokumentaatio/kuvat/testikattavuus.png)

Testaamatta jäi tilanteet käyttäjän pelin voittamisesta ja kartalta tippumisesta.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on ladattu ja sitä on testattu [käyttöohjeen](/dokumentaatio/kayttoohje.md) kuvaamalla tavalla. Testausta on suoritettu Linux- ja Windows-ympäristöissä.

### Toiminnallisuudet

Kaikki [määritelydokumentin](/dokumentaatio/vaatimusmaarittely.md) ja [käyttöohjeen](/dokumentaatio/kayttoohje.md) listaamat toiminnalisuudet on käyty läpi. Sovellusta on yritetty käyttää tarkoituksena saada se hajoamaan/toimimaan niin kuin sen ei kuuluisi. 

## Sovellukseen jääneet laatuongelmat

Pelin voittaessa tulos menee aina tulostaululle, vaikka käyttäjä peruuttaisi nimensyötön. Myöskään nimimerkissä ei ole mitään rajoitteita ja saattaisi näin ollen joutua trollien uhriksi. 
