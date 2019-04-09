package mybatis.test;

import mybatis.bean.RewardOrder;
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
    public void testQuerRewardOrderList() {
        //4.创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //5.执行SQL
        List<Object> rewardOrderList = sqlSession.selectList("queryRewardOrderList");
        for (Object rewardOrder : rewardOrderList) {
            System.out.println(rewardOrder);
        }
        sqlSession.close();
    }

    @Test
    public void testQueryRewardOrderById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int rewardId = 1;
        Object object = sqlSession.selectOne("queryRewardOrderById", rewardId);
        System.out.println(object.toString());
        sqlSession.close();
    }

    @Test
    public void testSaveRewardOrder() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RewardOrder rewardOrder = new RewardOrder();
        //rewardOrder.setRewardId("XXX123");
        rewardOrder.setRewardName("打饭");
        rewardOrder.setRewardContent("快去给我打饭吧，饿了！！！");
        sqlSession.insert("saveRewardOrder", rewardOrder);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateRewardOrder() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RewardOrder rewardOrder = new RewardOrder();
        rewardOrder.setRewardId("0");
        rewardOrder.setRewardName("修改好了！！！");
        sqlSession.update("updateRewardOrderById", rewardOrder);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteRewardOrderById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String rewardId = "0";
        sqlSession.delete("deleteRewardOrderById", rewardId);
        sqlSession.commit();
        sqlSession.close();
    }
}
