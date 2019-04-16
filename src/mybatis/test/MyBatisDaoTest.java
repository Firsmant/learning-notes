package mybatis.test;

import mybatis.bean.RewardOrder;
import mybatis.dao.IRewardOrderDao;
import mybatis.dao.RewardOrderDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: wangcf
 * @Date: 2019/4/13 18:42
 */
public class MyBatisDaoTest {
    private SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream("mybatis/SqlMapConfig.xml");
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    @Test
    public void testQueryRewardOrderList() {
        IRewardOrderDao rewardOrderDao = new RewardOrderDaoImpl(this.sqlSessionFactory);
        List<RewardOrder> list = rewardOrderDao.queryRewardOrderList();
        for (RewardOrder rewardOrder : list) {
            System.out.println(rewardOrder);
        }
    }

    @Test
    public void testQueryRewardOrderById() {
        IRewardOrderDao rewardOrderDao = new RewardOrderDaoImpl(this.sqlSessionFactory);
        int id = 1;
        RewardOrder rewardOrder = rewardOrderDao.queryRewardOrderById(id);
        System.out.println(rewardOrder);
    }

    @Test
    public void testSaveRewardOrder() {
        IRewardOrderDao rewardOrderDao = new RewardOrderDaoImpl(this.sqlSessionFactory);
        RewardOrder rewardOrder = new RewardOrder();
        rewardOrder.setRewardName("来此");
        rewardOrder.setRewardContent("尽快赶到此地，有重大发现！！！");
        rewardOrderDao.saveRewradOrder(rewardOrder);
    }

}
