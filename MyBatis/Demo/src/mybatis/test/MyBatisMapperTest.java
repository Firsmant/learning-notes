package mybatis.test;

import mybatis.bean.RewardOrder;
import mybatis.dao.IRewardOrderDao;
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
 * @Date: 2019/4/17 21:57
 */
public class MyBatisMapperTest {
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream("mybatis/SqlMapConfig.xml");
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    @Test
    public void testQueryRewardOrderList() {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        IRewardOrderDao rewardOrderDao = sqlSession.getMapper(IRewardOrderDao.class);
        List<RewardOrder> list = rewardOrderDao.queryRewardOrderList();
        for (RewardOrder rewardOrder : list) {
            System.out.println(rewardOrder);
        }
    }
}
