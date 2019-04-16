package mybatis.dao;

import mybatis.bean.RewardOrder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @Author: wangcf
 * @Date: 2019/4/13 18:56
 */
public class RewardOrderDaoImpl implements IRewardOrderDao {
    private SqlSessionFactory sqlSessionFactory;

    public RewardOrderDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<RewardOrder> queryRewardOrderList() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<RewardOrder> list = sqlSession.selectList("queryRewardOrderList");
        sqlSession.close();
        return list;
    }

    @Override
    public RewardOrder queryRewardOrderById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RewardOrder rewardOrder = sqlSession.selectOne("queryRewardOrderById", id);
        sqlSession.close();
        return rewardOrder;
    }

    @Override
    public void saveRewradOrder(RewardOrder rewardOrder) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("saveRewardOrder", rewardOrder);
        sqlSession.commit();
        sqlSession.close();
    }
}
