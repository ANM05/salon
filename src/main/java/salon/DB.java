package salon;

import org.sql2o.*;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DB {
    private static URI dbUri;
    public static Sql2o sql2o;
  //  Logger logger = LoggerFactory.getLogger(DB.class);
    static {

        try {
            if (System.getenv("postgres://zapwsqjwnjotdb:92aa44f21eec47b91d4e12cbf41edc375306c9bf47583e7f23774eb769064631@ec2-174-129-208-118.compute-1.amazonaws.com:5432/d3l1m2dgler24r") == null) {
                dbUri = new URI("postgres://localhost:5432/hsalon");
            } else {
                dbUri = new URI(System.getenv("postgres://zapwsqjwnjotdb:92aa44f21eec47b91d4e12cbf41edc375306c9bf47583e7f23774eb769064631@ec2-174-129-208-118.compute-1.amazonaws.com:5432/d3l1m2dgler24r"));
            }
            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            String username = (dbUri.getUserInfo() == null) ? "antony" : dbUri.getUserInfo().split(":")[0];
            String password = (dbUri.getUserInfo() == null) ? "rob242" : dbUri.getUserInfo().split(":")[1];
            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
        } catch (URISyntaxException e ) {
          //  logger.error("Unable to connect to database.");
        }
    }
}