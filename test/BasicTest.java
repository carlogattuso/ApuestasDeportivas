import org.junit.*;
import java.util.*;

import play.db.jpa.GenericModel;
import play.test.*;
import models.*;

import javax.mail.Part;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

    @Test
    public void createAndRetrieveUser() {
        // Create a new user and save it
        Usuario u = new Usuario("carlo","carlo","Carlo","Gattuso Garrido","carlogc46@gmail.com",20,45).save();

        // Retrieve the user with e-mail address bob@gmail.com
        Usuario carlo = Usuario.find("byNombre", "Carlo").first();

        // Test
        assertNotNull(carlo);
        assertEquals("Carlo", carlo.getNombre());
        assertEquals("carlo",carlo.getPassword());
        assertEquals("carlo", carlo.getUsername());
        assertEquals("Gattuso Garrido", carlo.getApellidos());
        assertEquals("carlogc46@gmail.com", carlo.getCorreo());
        assertEquals(20, carlo.getEdad());
        assertEquals(45,carlo.getSaldo(),0.0);
    }
    @Test
    public void createAndRetrieveMatchDay() {
        // Create a new user and save it
        Jornada j = new Jornada(1).save();

        // Retrieve the user with e-mail address bob@gmail.com
        Jornada expected = Jornada.find("byNUM_JORNADA", 1).first();

        // Test
        assertNotNull(expected);
        assertEquals(1, expected.getNum_jornada());
    }
    @Test
    public void createAndRetrieveMatch() {
        // Create a new user and save it
        Partido p = new Partido(2019,5,15,13, 0,"Camp Nou","Barcelona","Real Madrid",1.5,3,2,"null").save();

        // Retrieve the user with e-mail address bob@gmail.com
        Partido expected = Partido.find("byEstadio", "Camp Nou").first();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 4);
        cal.set(Calendar.DAY_OF_MONTH, 15);
        Date fecha = new java.sql.Date(cal.getTimeInMillis());

        // Test
        assertNotNull(expected);
        assertEquals("2019-05-15", expected.getFecha().toString());
        assertEquals("13:00:00", expected.getHora().toString());
        assertEquals("Camp Nou", expected.getEstadio());
        assertEquals("Barcelona", expected.getLocal());
        assertEquals("Real Madrid", expected.getVisitante());
        assertEquals(1.5, expected.getCuota_1(),0.0);
        assertEquals(3, expected.getCuota_X(),0.0);
        assertEquals(2, expected.getCuota_2(),0.0);
        assertEquals("null", expected.getResultado());
    }

    @Test
    public void createBet() {
        // Create a new user and save it
        Usuario mario = new Usuario("mario","mario","Mario","Sanchez Boto","marioboto98@gmail.com",21,45).save();

        //Create a new match day
        Jornada j = new Jornada(1).save();

        //Create a new match
        Partido p = new Partido(2019,5,15,13, 0,"Camp Nou","Barcelona","Real Madrid",1.5,3,2,"null").save();

        // Create a new post
        Apuesta a1 = new Apuesta(5, "X").save();
        a1.usuario=mario;
        a1.jornada=j;
        a1.partido=p;
        a1.save();

        Apuesta expected = Apuesta.find("byPartido",p).first();

        assertNotNull(expected);
        assertEquals(5, expected.getImporte(),0.0);
        assertEquals("X", expected.getPronostico());
        assertEquals(mario, expected.getUsuario());
        assertEquals(j, expected.getJornada());
        assertEquals(p, expected.getPartido());
    }
    @Test
    public void createBetList() {

        Usuario mario = new Usuario("mario","mario","Mario", "Sanchez Boto", "marioboto98@gmail.com", 21, 45).save();

        //Create a new match day
        Jornada j = new Jornada(1).save();

        //Create a new match
        Partido p = new Partido(2019, 5, 15, 13, 0, "Camp Nou", "Barcelona", "Real Madrid", 1.5, 3, 2, "null").save();

        // Create a new post
        Apuesta a1 = new Apuesta(5, "X").save();
        a1.usuario = mario;
        a1.jornada = j;
        a1.partido = p;
        a1.save();

        Apuesta a2 = new Apuesta(10, "2").save();
        a2.usuario = mario;
        a2.jornada = j;
        a2.partido = p;
        a2.save();

        List<Apuesta> betList = Apuesta.find("byUsuario", mario).fetch();

        // Test
        assertNotNull(betList);
        assertEquals(2,betList.size());
    }
}
