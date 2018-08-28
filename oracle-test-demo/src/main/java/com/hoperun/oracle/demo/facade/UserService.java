package com.hoperun.oracle.demo.facade;

import java.util.List;

import com.hoperun.mysql.demo.model.HpDevices;
import com.hoperun.mysql2.demo.model.HpTest;
import com.hoperun.oracle.demo.model.Thistory;
import com.hoperun.oracle.demo.model.UUID;
import com.hoperun.oracle.demo.model.User;

public interface UserService
{

    List<User> getUserListInfo();
    List<Thistory> getThistory();
    List<User> addUser(User userRequest);
    List<UUID> addUUID(UUID uuidRequest);
    List<HpDevices> getDeviceListInfo();
    List<User> deleteUser(User userRequest);
    List<HpTest> getTestListInfo();
    List<HpTest> addTest(HpTest hptest);
}
