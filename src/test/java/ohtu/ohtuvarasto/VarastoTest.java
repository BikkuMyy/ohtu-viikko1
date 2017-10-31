package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    //yhden parametrin konstruktorin testit
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiLuoVirheellista() {
        Varasto virheellinen = new Varasto(-1);
        assertEquals(0, virheellinen.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    //kahden parametrin konstruktorin testit
    @Test
    public void tuplaKonstruktoriLuoOikeanVaraston() {
        Varasto tupla = new Varasto(10, 1);
        assertEquals(1, tupla.getSaldo(), vertailuTarkkuus);
        assertEquals(10, tupla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void tuplaKonstruktoriEiLuoVirheellista() {
        Varasto tupla = new Varasto(-1, -1);
        assertEquals(0, tupla.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, tupla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoKorkeintaanTilavuus() {
        Varasto tupla = new Varasto(1, 2);
        assertEquals(1, tupla.getSaldo(), vertailuTarkkuus);
    }

    //lisaystestit
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivistaMaaraaEiLisata() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void saldoKorkeintaanTilavuus() {
        varasto.lisaaVarastoon(11);
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0.0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    //ottotestit
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivistaMaaraaEiOteta() {
        varasto.otaVarastosta(-1);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otetaanKaikkiMitaVoidaan() {
        varasto.lisaaVarastoon(10);
        varasto.otaVarastosta(5);
        double maara = varasto.otaVarastosta(8);
        assertEquals(5, maara, vertailuTarkkuus);
    }

    @Test
    public void tulostusToimiiOikein() {
        String tuloste = varasto.toString();
        assertEquals(tuloste, "saldo = 0.0, vielä tilaa 10.0");
    }

}
