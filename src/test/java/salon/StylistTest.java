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
            String deleteStylistsQuery = "DELETE FROM stylists *;";
            String deleteClientsQuery = "DELETE FROM clients *;";
            con.createQuery(deleteStylistsQuery).executeUpdate();
            con.createQuery(deleteClientsQuery).executeUpdate();
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
    @Test
    public void getId_instantiatesWithProperStylistIdFromDbase_int(){
        Stylist testStylist = new Stylist("John Doe","0743-987425", "doe@gmail.com");
        testStylist.save();
        assertTrue(testStylist.getId()>0);
    }
    @Test
    public void allStylists_returnsAllStylistsObjectsFromDbase_true(){
        Stylist stylistOne = new Stylist("John Doe","0743-987425", "doe@gmail.com");
        stylistOne.save();
        Stylist stylistTwo = new Stylist("Loice", "0723-654789", "loice@gmail.com");
        stylistTwo.save();
        assertEquals(true, Stylist.allStylists().get(0).equals(stylistOne));
        assertEquals(true, Stylist.allStylists().get(1).equals(stylistTwo));
    }
    @Test
    public void equals_returnsTrueIfStylistObjectsAreSame(){
        Stylist stylistOne = new Stylist("John Doe","0743-987425", "doe@gmail.com");
        Stylist stylistTwo = new Stylist("John Doe","0743-987425", "doe@gmail.com");
        assertTrue(stylistOne.equals(stylistTwo));
    }
    @Test
    public void save_savesIntoDataBase_true(){
        Stylist testStylist = new Stylist("John Doe","0743-987425", "doe@gmail.com");
        testStylist.save();
        assertTrue(Stylist.allStylists().get(0).equals(testStylist));
    }
    @Test
    public void save_assignsIdsToItemsSavedIntoDataBase(){
        Stylist testStylist = new Stylist("John Doe","0743-987425", "doe@gmail.com");
        testStylist.save();
        Stylist savedStylist = Stylist.allStylists().get(0);
        assertEquals(testStylist.getId(), savedStylist.getId());
    }
    @Test
    public void find_returnsObjectWithSameId_stylistTwo(){
        Stylist stylistOne = new Stylist("John Doe","0743-987425", "doe@gmail.com");
        stylistOne.save();
        Stylist stylistTwo = new Stylist("Loice", "0723-654789", "loice@gmail.com");
        stylistTwo.save();
        assertEquals(Stylist.find(stylistTwo.getId()), stylistTwo);
    }
}