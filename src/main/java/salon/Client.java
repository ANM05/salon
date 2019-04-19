package salon;

public class Client{
    private String name;
    private String mobile;
    private String email;
    private int id;
    private int stringid;

    public Client(String name, String mobile, String email, int stringId){
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        stringid = stringId;
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
}