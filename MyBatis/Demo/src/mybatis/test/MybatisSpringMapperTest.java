package mybatis.test;

import mybatis.bean.RewardOrder;
import mybatis.dao.IRewardOrderDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: wangcf
 * @Date: 2019/4/27 9:41
 */
public class MybatisSpringMapperTest {
    private ApplicationContext context;

    @Before
    public void setUp() {
        //创建IOC容器
        this.context = new ClassPathXmlApplicationContext("classpath:mybatis/mybatisSpringConfig/applicationContext.xml");
    }

    @Test
    public void testQueryRewardOrderList() {
        IRewardOrderDao rewardOrderDao = (IRewardOrderDao) this.context.getBean("rewardOrderDaoImplMapper");
        List<RewardOrder> list = rewardOrderDao.queryRewardOrderList();
        for (RewardOrder rewardOrder : list) {
            System.out.println(rewardOrder);
        }
    }
}
