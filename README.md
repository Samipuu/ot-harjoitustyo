# JumpAddiction

Yksinkertainen sivulta kuvattu tasohyppely peli.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Samipuu/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

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
