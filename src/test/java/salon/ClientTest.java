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
            String deleteStylistsQuery = "DELETE FROM stylists *;";
            String deleteClientsQuery = "DELETE FROM clients *;";
            con.createQuery(deleteStylistsQuery).executeUpdate();
            con.createQuery(deleteClientsQuery).executeUpdate();
        }
    }
    @Test
    public void Client_instantiatesCorrectly_true(){
    Client testClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
    assertEquals(true, testClient instanceof Client);
    }
    @Test
    public void getName_instantiatesWithClientName_String(){
        Client testClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
        assertEquals("Anne O.", testClient.getName());
    }
    @Test
    public void getEmail_instantiatesWithEmail_String(){
        Client testClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
        assertEquals("anne0@gmail.com", testClient.getEmail());
    }
    @Test
    public void getMobile_instantiatesWithClientMobile_String(){
        Client testClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
        assertEquals("0745-456-673", testClient.getMobile());
    }
    @Test
    public void getId_clientInstantiatesWithIdAttribute_int(){
        Client testClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
        testClient.save();
        assertTrue(testClient.getId()>0);
    }
    @Test
    public void clientsAll_returnsAllTaskObjectsFromDatabase_true(){
        Client firstClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
        firstClient.save();
        Client secondClient = new Client("Yvone", "0732-786-932", "yvone@gmail.com", 2);
        secondClient.save();
        assertEquals(true, Client.clientsAll().get(0).equals(firstClient));
        assertEquals(true, Client.clientsAll().get(1).equals(secondClient));
    }
    @Test
    public  void equals_returnsTrueIfClientObjectsAreSame(){
        Client firstClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
        Client secondClient = new Client("Yvone", "0732-786-932", "yvone@gmail.com", 2);
        assertTrue(firstClient.equals(secondClient));
    }
    @Test
    public void save_returnsTrueIfClientObjectsAreSame(){
        Client testClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
        testClient.save();
        assertTrue(Client.clientsAll().get(0).equals(testClient));
    }
    @Test
    public void save_assignsUniqueIdToOjectsSavedInDatabase(){
        Client testClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
        testClient.save();
        Client savedClient = Client.clientsAll().get(0);
        assertEquals(testClient.getId(), savedClient.getId());
    }
    @Test
    public void find_returnsClientWithSameId_firstClient(){
        Client firstClient = new Client("Anne O.", "0745-456-673", "anne0@gmail.com", 1);
        firstClient.save();
        Client secondClient = new Client("Yvone", "0732-786-932", "yvone@gmail.com", 2);
        secondClient.save();
        assertEquals(Client.find(firstClient.getId()), firstClient);
    }
}