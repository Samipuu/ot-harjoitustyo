# JumpAddiction

Yksinkertainen sivulta kuvattu tasohyppely peli.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Samipuu/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/Samipuu/ot-harjoitustyo/tree/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/Samipuu/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Komentorivitoiminnot

Sovellus voidaan käynnistää komennolla

```
mvn compile exec:java -Dexec.mainClass=jumpaddiction.ui.Main
```

### Testien suorittaminen

Testit voidaan suorittaa komenolla

```
mvn test
```

Testikattavuusraportti komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla tiedoston target/site/jacoco/index.html

### Checkstyle

Tiedoston checkstyle.xml määrittelemät tarkastukset voidaan suorittaa komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
Raporttia voi tarkastella avaamalla tiedoston target/site/checkstyle.html
