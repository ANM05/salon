package salon;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest{
    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "antony", "rob242");
    }
    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM stylists *;";
            con.createQuery(sql).executeUpdate();
        }
    }
    @Test
    public void Stylist_instantiatesCorrectly_true(){
        Stylist testStylist = new Stylist("John Doe","0743-987425", "doe@gmail.com");
        assertEquals(true, testStylist instanceof Stylist);
    }
    @Test
    public void getName_instantiatesWithStylistName_String(){
        Stylist testStylist = new Stylist("John Doe","0743-987425", "doe@gmail.com");
        assertEquals("John Doe", testStylist.getName());
    }
    @Test
    public void getMobile_instantiatesWithStylistMobileNumber_String(){
        Stylist testStylist = new Stylist("John Doe","0743-987425", "doe@gmail.com");
        assertEquals("0743-987425", testStylist.getMobile());
    }
    @Test
    public void getEmail_instantiatesCorrectlyWithTheStylistEmail_String(){
        Stylist testStylist = new Stylist("John Doe","0743-987425", "doe@gmail.com");
        assertEquals("doe@gmail.com", testStylist.getEmail());
    }
}