package betservice;

import database.SelfUserDB;
import util.MySqlUtil;

public class BetService {

    public static void main(String[] args) {
        SelfUserDB user = new SelfUserDB();
        user.setConnectionToUse(MySqlUtil.getConnection());
        user.setLogname("llama");
        user.setFullname("crazy llama");
        user.setPassword("11235813");
        user.save();
    }
    
}
