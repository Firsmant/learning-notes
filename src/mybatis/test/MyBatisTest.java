package mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: wangcf
 * @Date: 2019/4/8 22:36
 */
public class MyBatisTest {

    private SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() throws IOException {
        //1.创建sqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //2.加载mybatis 的 SqlMapConfig.xml 配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
        //3.创建SqlSessionFactory对象
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    @Test
    public void RewardOrderTest() {
        //4.创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //5.执行SQL
        List<Object> rewardOrderList = sqlSession.selectList("queryRewardOrderList");
        for (Object rewardOrder : rewardOrderList) {
            System.out.println(rewardOrder);
        }
    }
}
