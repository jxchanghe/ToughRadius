package org.toughradius.constant;

import java.util.ArrayList;
import java.util.List;


public class UserStatus {

    public final static IntConst Prepar = new IntConst(100,"δ����״̬");
    public final static IntConst Normal = new IntConst(101,"����״̬");
    public final static IntConst Pause = new IntConst(102,"ͣ��״̬");
    public final static IntConst Expire = new IntConst(104,"����״̬");
    
    public final static List<IntConst> UserStatusList = new ArrayList<IntConst>();
    
    static 
    {
        UserStatusList.add(Prepar);
        UserStatusList.add(Normal);
        UserStatusList.add(Pause);
        UserStatusList.add(Expire);
    }
    
}
