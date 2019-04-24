package salon;

import org.sql2o.*;
import java.util.List;

public class Client{
    private String name;
    private String mobile;
    private String email;
    private int id;
    private int stylist_id;

    public Client(String name, String mobile, String email, int stylistId){
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        stylist_id = stylistId;
    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getMobile(){
        return mobile;
    }
    public int getId(){
        return id;
    }
    public int getStylistId(){
        return stylist_id;
    }

    public static List<Client> clientsAll(){
       String sql ="SELECT * FROM clients;";
        try(Connection connect = DB.sql2o.open()) {
        return connect.createQuery(sql).executeAndFetch(Client.class);
       }
    }
    @Override
    public boolean equals(Object testClient){
        if(!(testClient instanceof Client)){
            return false;
        }
        else{
            Client newClient = (Client) testClient;
            return this.getName().equals(newClient.getName()) &&
                    this.getId() == newClient.getId() &&
                    this.getStylistId() == newClient.getStylistId();
        }
    }
    public void save(){
        try(Connection connect = DB.sql2o.open()){
            String sql = "INSERT INTO clients (name, mobile, email, stylist_id) VALUES (:name, :mobile, :email, :stylist_id);";
            this.id = (int) connect.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("mobile", this.mobile)
                    .addParameter("email", this.email)
                    .addParameter("stylist_id", this.stylist_id)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static Client find(int id){
        try(Connection connect= DB.sql2o.open()){
            String sql ="SELECT * FROM clients WHERE id=:id;";
            Client client = connect.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
                    return client;
        }
    }
    public void update(String name, String mobile, String email){
        try(Connection connect = DB.sql2o.open()){
            String sql ="UPDATE clients SET (name, mobile, email) = (:name, :mobile, :email) WHERE id = :id;";
            connect.createQuery(sql)
            .addParameter("name", name)
                    .addParameter("mobile", mobile)
                    .addParameter("email", email)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}