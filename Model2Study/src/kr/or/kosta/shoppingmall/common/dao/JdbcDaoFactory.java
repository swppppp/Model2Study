package kr.or.kosta.shoppingmall.common.dao;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import kr.or.kosta.shoppingmall.user.dao.JdbcUserDao;
import kr.or.kosta.shoppingmall.user.dao.UserDao;
import kr.or.kosta.shoppingmall.user.domain.User;

public class JdbcDaoFactory extends DaoFactory {
	
	private HashMap<String, Object> daos;
	
	public JdbcDaoFactory(String daoMapperLocation) {
		daos = new HashMap<String, Object>();
		
		// dao매핑정보 저장할 Property객체 생성
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(daoMapperLocation);
			prop.load(fis);
			Iterator keyIter = prop.keySet().iterator();
			System.out.println("----Dao생성목록---");
			while (keyIter.hasNext()) {
				String daoName = (String)keyIter.next();
				String JdbcDaoClass = prop.getProperty(daoName);
				
				//jdbcDao생성
				Object dao = Class.forName(JdbcDaoClass).newInstance();
				addDataSource(dao);
				daos.put(JdbcDaoClass, dao);
				System.out.println(JdbcDaoClass+": "+dao);
			} 
		}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	
	private void addDataSource(Object dao) {
		Class cls = dao.getClass();
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getDao(String daoName) {
		return daos.get(daoName);
	}
	public Object getDao(Class cls) {
		return getDao(cls.getName());
	}

//	@Override
//	public UserDao getUserDao(String daoName) {
//		return (UserDao)daos.get(daoName);
//	}
	
	
//	public BarDao getBarDao() {...};
//	public FooDao getFooDao() {...};
	
	public static void main(String[] args) throws Exception {
		String location = "C:/KOSTA187/workspace/Model2Study/WebContent/WEB-INF/dao-mapper.properties";
		DaoFactory factory = new JdbcDaoFactory(location);
		UserDao dao = (UserDao)factory.getDao("UserDao");
		System.out.println(dao);
		List<User> list =  dao.listAll();
//		List<User> list =  dao.listByPage(2);
//		List<User> list =  dao.listByPage(1, 15);
//		List<User> list =  dao.listByPage(1, 15, null, null);
//		List<User> list =  dao.listByPage(1, 15, "name", "김");
//		List<User> list =  dao.listByPage(new Params(1, 15, "name", "김"));
		for (User user : list) {
			System.out.println(user);				
		}
		
		
	}

}
