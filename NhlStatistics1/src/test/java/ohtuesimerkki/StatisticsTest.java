package ohtuesimerkki;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    private Reader readerStub = new Reader() {

        @Override
        public List getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
            
            players.add(new Player("Parise", "MIN", 18, 20));
            players.add(new Player("Koivu", "MIN", 11, 26));
            players.add(new Player("Suter", "MIN", 4, 28));
            players.add(new Player("Setoguchi", "MIN", 13, 14));
            players.add(new Player("Heatley", "MIN", 11, 10));
            
            players.add(new Player("St Louis", "TBL", 17, 43));
            players.add(new Player("Stamkos", "TBL", 29, 28));
            players.add(new Player("Ovechkin", "WSH", 32, 24));
            players.add(new Player("Crosby", "PIT", 15, 41));
            players.add(new Player("Kane", "CHI", 23, 32));
            players.add(new Player("Staal", "CAR", 18, 35));
            
            return players;
        }
    };
    
    private Statistics stats;
    
    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void pelaajanHakuToimii(){
        Player tulos = stats.search("Koivu");
        assertEquals(tulos.getName(), "Koivu");
        assertEquals(tulos.getTeam(), "MIN");
        assertEquals(tulos.getGoals(), 11);
        assertEquals(tulos.getAssists(), 26);
        assertEquals(tulos.getPoints(), 37);
    }
    
    @Test
    public void hakuJoukkueellaToimii(){
        List<Player> minnesota = new ArrayList();
        List<Player> pelaajat = readerStub.getPlayers();
        for (Player pelaaja : pelaajat){
            if (pelaaja.getTeam().equals("MIN")){
                minnesota.add(pelaaja);
            }
        }
        pelaajat = stats.team("MIN");
        for (int i = 0; i < minnesota.size(); i++) {
            assertEquals(minnesota.get(i).toString(), pelaajat.get(i).toString());
        }
    }
    
    @Test
    public void palauttaaNullJosEiPelaajaa(){
        Player tulos = stats.search("Granlund");
        assertEquals(null, tulos);
    }
    
    @Test
    public void pisteRohmujenHakuToimii(){
        List<Player> pisterohmut = new ArrayList();
        List<Player> pelaajat = readerStub.getPlayers();
        
        Collections.sort(pelaajat);
        for (int i = 0; i < 6; i++) {
            pisterohmut.add(pelaajat.get(i));
        }
        pelaajat = stats.topScorers(6);
        for (int i = 0; i < pisterohmut.size(); i++) {
            assertEquals(pisterohmut.get(i).toString(), pelaajat.get(i).toString());
        }
    }

}
