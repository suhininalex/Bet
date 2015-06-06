package betservice;

import database.SelfUserDB;
import logic.SelfUser;
import util.DBBusinessFactories;
import util.EntityProvider;
import util.MySqlUtil;

public class BetService {

    public static void main(String[] args) {
        EntityProvider.setBusinessFactoris(new DBBusinessFactories());
        SelfUser user = EntityProvider.getBusinessFactories().getSelfUserInstance(MySqlUtil.getConnection());
        user.setLogname("llama");
        user.setFullname("crazy llama");
        user.setPassword("11235813");
        user.save();
    }
    
}
