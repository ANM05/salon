package salon;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest{
    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "antony", "rob242");
    }
    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM clients *;";
            con.createQuery(sql).executeUpdate();
        }
    }
    @Test
    public void Client_instantiatesCorrectly_true(){
    Client testClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
    assertEquals(true, testClient instanceof Client);
    }
    @Test
    public void Client_instantiatesWithClientName_String(){
        Client testClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
        assertEquals("Anne O.", testClient.getName());
    }
    @Test
    public void Client_instantiatesWithEmail_String(){
        Client testClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
        assertEquals("anne0@gmail.com", testClient.getEmail());
    }
    @Test
    public void Client_instantiatesWithClientMobile_String(){
        Client testClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
        assertEquals("0745-456-673", testClient.getMobile());
    }
}