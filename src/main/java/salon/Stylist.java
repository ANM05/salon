package salon;

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
}