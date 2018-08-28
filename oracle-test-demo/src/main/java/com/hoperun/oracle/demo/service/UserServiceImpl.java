package com.hoperun.oracle.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestTemplate;

import com.alibaba.druid.util.StringUtils;
import com.hoperun.mysql.demo.dao.HpDevicesMapper;
import com.hoperun.mysql.demo.model.HpDevices;
import com.hoperun.mysql2.demo.dao.HpTestMapper;
import com.hoperun.mysql2.demo.model.HpTest;
import com.hoperun.oracle.demo.dao.ThistoryMapper;
import com.hoperun.oracle.demo.dao.UUIDMapper;
import com.hoperun.oracle.demo.dao.UserMapper;
import com.hoperun.oracle.demo.facade.UserService;
import com.hoperun.oracle.demo.model.Thistory;
import com.hoperun.oracle.demo.model.UUID;
import com.hoperun.oracle.demo.model.User;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ThistoryMapper thistoryMapper;

    @Autowired
    private UUIDMapper uuidMapper;

    @Autowired
    private HpDevicesMapper hpDevicesMapper;

    @Autowired
    private HpTestMapper hpTestMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private TransactionTemplate transactionTemplate;
    
    @Autowired
    private RestTemplate restTemplate;

    // 测试编程式事务
    // 执行编程式事务返回执行的结果
    // 为什么使用编程式事务？
    // 声明式事务中，如果调用了第三方的接口，而第三方的接口耗时较长，由于此连接时事务管理器分配的一个连接，直到此方法return或执行结束此释放此资源，效率太差
    // 使用编程式事务可以对代码块进行控制，代码块的执行结束即释放此资源，不会一直占用此数据库连接资源，效率较高
    // 所以涉及到调用第三方资源的最好使用编程式事务进行控制
    public void testTransaction()
    {
	Boolean flag = transactionTemplate.execute(new TransactionCallback<Boolean>()
	{

	    @Override
	    public Boolean doInTransaction(TransactionStatus status)
	    {
		// 在此执行调用第三方接口之前的操作，保存调用之前的执行状态，执行全部成功则返回布尔类型
		return null;
	    }
	});

	String result = "";
	if (flag)
	{
	    // 执行调用第三方接口的操作
	    // result = 调用第三方接口返回的字符串
	} else
	{
	    // 返回此接口已经调用过的信息返回
	}

	// 判断此字符串，是否是正确的返回状态
	if (StringUtils.isEmpty(result))
	{
	    // 没有成功
	} else
	{
	    // 成功
	    // 开启事务编程式事务处理
	    Boolean resultFlag = transactionTemplate.execute(new TransactionCallback<Boolean>()
	    {

		@Override
		public Boolean doInTransaction(TransactionStatus status)
		{
		    // 执行相应的更新操作并返回对应的boolean变量
		    return null;
		}
	    });
	}

    }
    
    //测试RestTemplate
    //使用此封装好的框架发送http请求
    public void testRestTemplate() {
	ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://", new ArrayList<String>(), User.class);
	User user  = responseEntity.getBody();
    }
    
    //测试发送https 请求
    public void sendHttps() {
	String url = "https://free-api.heweather.com/v5/forecast?city=CN101080101&key=5c043b56de9f4371b0c7f8bee8f5b75e";
        String resp = restTemplate.getForObject(url, String.class);
        System.out.println(resp);
    }
    

    @Override
    public List<User> getUserListInfo()
    {

	// TODO Auto-generated method stub

	List<User> userList = userMapper.getUserList();
	LOGGER.debug(userList.toString());
	return userList;

    }

    @Override
    public List<Thistory> getThistory()
    {
	// TODO Auto-generated method stub

	List<Thistory> thistoryList = new ArrayList<Thistory>();
	thistoryList = thistoryMapper.getThistoryList();
	if (thistoryList != null && !thistoryList.isEmpty())
	{
	    LOGGER.debug("thistoryList");
	    return thistoryList;
	}
	LOGGER.debug(thistoryList.toString());
	return thistoryList;
    }

    @Override
    public List<User> addUser(User userRequest)
    {
	// TODO Auto-generated method stub
	userMapper.insertSelective(userRequest);
	List<User> resultList = userMapper.getUserList();
	LOGGER.debug("user自增后返回的主键" + userRequest.getIdp());
	return resultList;
    }

    @Override
    public List<UUID> addUUID(UUID uuidRequest)
    {
	// TODO Auto-generated method stub

	uuidMapper.insertSelective(uuidRequest);
	LOGGER.debug("user自增后返回的主键" + uuidRequest.getId());
	List<UUID> resultList = uuidMapper.getUUIDList();

	return resultList;
    }

    @Override
    public List<HpDevices> getDeviceListInfo()
    {

	List<HpDevices> resultList = hpDevicesMapper.getDeviceList();
	LOGGER.debug(resultList.toString());
	return resultList;
    }

    @Transactional(value = "masterTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public List<User> deleteUser(User userRequest)
    {
	userMapper.deleteByPrimaryKey(userRequest.getIdp());
	int i = 1 / 0;

	return new ArrayList<User>();
    }

    @Override
    public List<HpTest> getTestListInfo()
    {
	List<HpTest> resultList = hpTestMapper.getListInfo();
	return resultList;
    }

    // @Transactional(value = "clusterTransactionManager2", propagation =
    // Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public List<HpTest> addTest(HpTest hptest)
    {
	hpTestMapper.insertSelective(hptest);

	// int i = 1 / 0;
	List<HpTest> resultList = hpTestMapper.getListInfo();
	return resultList;
    }

}
