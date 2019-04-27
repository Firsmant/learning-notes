package mybatis.mybatisSpringConfig;

import mybatis.bean.RewardOrder;
import mybatis.dao.IRewardOrderDao;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @Author: wangcf
 * @Date: 2019/4/27 9:34
 */
public class RewardOrderDaoImpl extends SqlSessionDaoSupport implements IRewardOrderDao {

    @Override
    public List<RewardOrder> queryRewardOrderList() {
        //获取SqlSession对象
        SqlSession sqlSession = super.getSqlSession();
        //执行操作
        List<RewardOrder> list = sqlSession.selectList("queryRewardOrderList");
        //不需要关闭sqlSession
        return list;
    }

    @Override
    public RewardOrder queryRewardOrderById(int id) {
        SqlSession sqlSession = super.getSqlSession();
        RewardOrder rewardOrder = sqlSession.selectOne("queryRewardOrderById", id);
        return rewardOrder;
    }

    @Override
    public void saveRewradOrder(RewardOrder rewardOrder) {
        SqlSession sqlSession = super.getSqlSession();
        sqlSession.insert("saveRewardOrder", rewardOrder);
    }
}
