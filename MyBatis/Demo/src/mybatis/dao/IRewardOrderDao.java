package mybatis.dao;

import mybatis.bean.RewardOrder;

import java.util.List;

/**
 * @Author: wangcf
 * @Date: 2019/4/13 18:50
 */
public interface IRewardOrderDao {

    /**
     * 获取所有悬赏列表
     *
     * @return
     */
    List<RewardOrder> queryRewardOrderList();

    /**
     * 根据ID获取悬赏令
     *
     * @return
     */
    RewardOrder queryRewardOrderById(int id);

    /**
     * 添加悬赏令
     */
    void saveRewradOrder(RewardOrder rewardOrder);
}
