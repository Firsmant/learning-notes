package mybatis.test;

import mybatis.bean.RewardOrder;
import mybatis.dao.IRewardOrderDao;
import mybatis.mybatisSpringConfig.RewardOrderDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: wangcf
 * @Date: 2019/4/27 9:41
 */
public class MybatisSpringDaoTest {
    private ApplicationContext context;

    @Before
    public void setUp() {
        //创建IOC容器
        this.context = new ClassPathXmlApplicationContext("classpath:mybatis/mybatisSpringConfig/applicationContext.xml");
    }

    @Test
    public void testQueryRewardOrderList() {
        IRewardOrderDao rewardOrderDao = (IRewardOrderDao) this.context.getBean("rewardOrderDaoImpl");
        List<RewardOrder> list = rewardOrderDao.queryRewardOrderList();
        for (RewardOrder rewardOrder : list) {
            System.out.println(rewardOrder);
        }
    }

    @Test
    public void testQueryRewardOrderById() {
        IRewardOrderDao rewardOrderDao = (IRewardOrderDao) this.context.getBean("rewardOrderDaoImpl");
        RewardOrder rewardOrder = rewardOrderDao.queryRewardOrderById(1);
        System.out.println(rewardOrder);
    }


}
