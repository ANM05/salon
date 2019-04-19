package salon;
import org.junit.*;
import org.junit.Assert.*;
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

}