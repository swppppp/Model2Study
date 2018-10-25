package kr.or.kosta.shoppingmall.common.service;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import kr.or.kosta.shoppingmall.common.dao.DaoFactory;
import kr.or.kosta.shoppingmall.user.dao.UserDao;
import kr.or.kosta.shoppingmall.user.domain.User;
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImple;

public class ServiceFactory {
	
	private Hashtable<String, Object> serviceList;
	
	public ServiceFactory(String serviceMapperLocation) {
		serviceList = new Hashtable<String, Object>();
		
		// service매핑정보 저장할 Property객체 생성
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(serviceMapperLocation);
			prop.load(fis);
			Iterator keyIter = prop.keySet().iterator();
			System.out.println("-----Service생성목록----");
			while (keyIter.hasNext()) {
				String serviceName = (String) keyIter.next();
				String serviceClassName = prop.getProperty(serviceName);
				Object serviceObject = Class.forName(serviceClassName).newInstance();
				serviceList.put(serviceClassName, serviceObject);
				System.out.println("찍히니????"+serviceClassName+": "+serviceObject);
			} 
		}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

	public Object getService(String serviceName) {
		return serviceList.get(serviceName);
	}
	public Object getService(Class cls) {
		return getService(cls.getName());
	}

	public static void main(String[] args) throws Exception {
		String location = "C:/KOSTA187/workspace/Model2Study/WebContent/WEB-INF/service-mapper.properties";
		ServiceFactory factory = new ServiceFactory(location);
		UserService userService = (UserService)factory.getService(UserServiceImple.class);
		
	}

}
