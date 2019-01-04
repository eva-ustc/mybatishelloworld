import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import ustc.sse.bean.Department;
import ustc.sse.bean.HostHolder;
import ustc.sse.bean.User;
import ustc.sse.mapper.DeptMapper;
import ustc.sse.xmlmapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author LRK
 * @project_name mybatishelloworld
 * @package_name PACKAGE_NAME
 * @date 2018/12/26 16:07
 * @description God Bless, No Bug!
 */
public class MybatisTest {

    HostHolder hostHolder = new HostHolder();
    public SqlSessionFactory getSqlSessionFactory() {

        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);

            return new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private SqlSession getSqlSession() {
        SqlSessionFactory factory = getSqlSessionFactory();
        return factory.openSession(true);
    }

    @Test
    public void test(){
        SqlSession session = getSqlSession();
        User user = session.selectOne("ustc.sse.xmlmapper.UserMapper.getUserById", 1);
        System.out.println(user);
    }
    @Test
    public void testInsert(){
        SqlSession session = getSqlSession();
        User user = new User();
        user.setLastName("eva");
        user.setEmail("eva@qq.com");
        user.setGender("0");
        session.insert("ustc.sse.xmlmapper.UserMapper.insertUser",user);
        session.commit();
    }

    @Test
    public void testMapper(){
        SqlSession session = getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User();
       user.setLastName("杨帆大佬");
       user.setGender("1");
       user.setEmail("yangfan@qq.com");
       hostHolder.setUsers(user);
      mapper.insertUser(user);
//        User user = mapper.getUserById(2);
        System.out.println(user);
        System.out.println(mapper.getClass());
    }
    @Test
    public void testget(){
        SqlSession session = getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        User user = mapper.getUserById("1079961649097609216");
//        User user = mapper.getUserById(2);
        System.out.println(user);
        System.out.println(mapper.getClass());
    }

    @Test
    public void testMapperInsert(){
        SqlSession session = getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User();
        user.setLastName("eva");
        user.setEmail("eva@qq.com");
        user.setGender("0");
        mapper.insertUser(user);
        System.out.println(user);

        session.commit();
    }

    @Test
    public void testFirstLevelCache(){

        SqlSession session = getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.getUserById("1");
        System.out.println(user);
        User user1 = mapper.getUserById("1");
        System.out.println(user1);
    }

    @Test
    public void testDept(){
        SqlSession session = getSqlSession();
        DeptMapper mapper = session.getMapper(DeptMapper.class);
//        Department department = mapper.getDeptById(1);
        Department department = new Department();
        department.setDeptName("市场部");
        mapper.insertDept(department);
        System.out.println(department);
    }

    @Test
    public void testSecondLevelCache(){
        // 二级缓存需在同一个factory下才有效
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession(true);
        SqlSession session2 = factory.openSession(true);

        UserMapper mapper = session.getMapper(UserMapper.class);
        UserMapper mapper2 = session2.getMapper(UserMapper.class);

        User user = mapper.getUserById("2");
        session.close();
        System.out.println(user);
        User user2 = mapper2.getUserById("2");
        System.out.println(user==user2);
    }
    @Test
    public void testDeptSecondLevelCache(){
        // 二级缓存需在同一个factory下才有效
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession(true);
        SqlSession session2 = factory.openSession(true);

        DeptMapper mapper = session.getMapper(DeptMapper.class);
        DeptMapper mapper2 = session2.getMapper(DeptMapper.class);

        Department dept = mapper.getDeptById(2);
        session.close();
        System.out.println(dept);
        Department dept2 = mapper2.getDeptById(2);
        System.out.println(dept==dept2);
    }


}
