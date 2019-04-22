package salon;

import java.util.List;
import org.sql2o.*;
import java.time.LocalDateTime;

public class Stylist{
    private String name;
    private String mobile;
    private String email;
    private int id;

    public Stylist(String name, String mobile, String email){
       this.name = name;
       this.mobile = mobile;
       this.email = email;

    }
    public String getName(){
        return name;
    }
    public String getMobile(){
        return mobile;
    }
    public String getEmail(){
        return email;
    }
    public int getId(){
        return id;
    }
    public static List<Stylist> allStylists(){
        String sql = "SELECT * FROM stylists;";
        try(Connection connect= DB.sql2o.open()){
            return connect.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }
    @Override
    public boolean equals(Object testStylist){
        if(!(testStylist instanceof Stylist)){
            return false;
        }
        else{
            Stylist newStylist = (Stylist) testStylist;
            return this.getName().equals(newStylist.getName()) &&
            this.getId() == newStylist.getId();
        }
    }
    public void save(){
        try(Connection connect = DB.sql2o.open()){
            String sql= "INSERT INTO stylists (name, mobile, email) VALUES (:name, :mobile, :email);";
            this.id = (int) connect.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("mobile", this.mobile)
                    .addParameter("email", this.email)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static Stylist find(int id){
        try(Connection connect = DB.sql2o.open()){
            String sql = "SELECT * FROM stylists WHERE id=:id;";
            Stylist stylist = connect.createQuery(sql)
            .addParameter("id", id)
                    .executeAndFetchFirst(Stylist.class);
            return stylist;
        }
    }
    public List<Client> getClients(){
        try(Connection connect = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where stylist_id=:id";
            return connect.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Client.class);
        }
    }
}