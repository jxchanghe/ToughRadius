package org.toughradius.constant;

import java.util.ArrayList;
import java.util.List;


public class UserStatus {

    public final static IntConst Prepar = new IntConst(100,"未激活状态");
    public final static IntConst Normal = new IntConst(101,"正常状态");
    public final static IntConst Pause = new IntConst(102,"停机状态");
    public final static IntConst Expire = new IntConst(104,"到期状态");
    
    public final static List<IntConst> UserStatusList = new ArrayList<IntConst>();
    
    static 
    {
        UserStatusList.add(Prepar);
        UserStatusList.add(Normal);
        UserStatusList.add(Pause);
        UserStatusList.add(Expire);
    }
    
}
