package com.kkb.mybatis.demo;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.kkb.mybatis.po.User;
import com.kkb.mybatis.po.UserQueryVO;

public class MybatisDemo {
	
	private SqlSessionFactory sqlSessionFactory;

	/**
	 * @Before注解的方法会在@Test注解的方法之前执行
	 * 
	 * @throws Exception
	 */
	@Before
	public void init() throws Exception {
		//指定全局配置文件路径
		String resource = "SqlMapConfig.xml";
		//加载资源文件（全局配置文件和映射文件）
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//还有构建者模式，去创建SqlSessionFactory对象
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream );
		
		//设计模式分三类23种：创建型（5）、结构型（7）、行为型（11）
	}
	@Test
	public void testFindUserById() {
		//由SqlSessionFactory工厂去创建SqlSession（会话）
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//调用sqlsession接口，去实现数据库的增删改查操作
		//参数1：statement的id值（可以不加namespace）：namespace+"."+statementID
		//参数2：唯一入参
		User user = sqlSession.selectOne("findUserById", 1);
		
		System.out.println(user);
		//释放资源
		sqlSession.close();
	}
	@Test
	public void testFindUserListByName() {
		//由SqlSessionFactory工厂去创建SqlSession（会话）
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//调用sqlsession接口，去实现数据库的增删改查操作
		List<User> list = sqlSession.selectList("findUserListByName", "Pan");
		
		System.out.println(list);
		//释放资源
		sqlSession.close();
	}
	@Test
	public void testInsertUser() {
		//由SqlSessionFactory工厂去创建SqlSession（会话）
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		User user = new User();
		user.setName("kkbes");
		user.setEmail("test@cn.com");
		user.setPassword("123456");
		user.setProfilesId(12);
		user.setBanned("N");
		user.setSuspended("Y");
		//user.setSex("1");
		
		//调用sqlsession接口，去实现数据库的增删改查操作
		sqlSession.insert("test.insertUser", user);
		
		sqlSession.commit();
		
		//释放资源
		sqlSession.close();
	}
	@Test
	public void testInsertUserByVO() {
		//由SqlSessionFactory工厂去创建SqlSession（会话）
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		UserQueryVO vo = new UserQueryVO();
		
		User user = new User();
		user.setName("Slis");
		user.setEmail("test@cn.com");
		user.setPassword("123456");
		user.setProfilesId(2);
		user.setBanned("N");
		user.setSuspended("Y");
		
		vo.setUser(user);
		
		//调用sqlsession接口，去实现数据库的增删改查操作
		sqlSession.insert("test.insertUserByVO", vo);
		
		//释放资源
		sqlSession.close();
	}
	
	@Test
	public void testResultMap() {
		//由SqlSessionFactory工厂去创建SqlSession（会话）
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//调用sqlsession接口，去实现数据库的增删改查操作
		//参数1：statement的id值（可以不加namespace）：namespace+"."+statementID
		//参数2：唯一入参
		User user = sqlSession.selectOne("test.testResultMap", 1);
		
		System.out.println(user);
		//释放资源
		sqlSession.close();
	}
	@Test
	public void findUserAndOrderRstMap() {
		//由SqlSessionFactory工厂去创建SqlSession（会话）
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//调用sqlsession接口，去实现数据库的增删改查操作
		//参数1：statement的id值（可以不加namespace）：namespace+"."+statementID
		//参数2：唯一入参
		List<User> users = sqlSession.selectList("test.findUserAndOrderRstMap");

		for (int i = 0; i < users.size(); i++){
			System.out.println(users.get(i));
		}
		//System.out.println(user);
		//释放资源
		sqlSession.close();
	}
}
