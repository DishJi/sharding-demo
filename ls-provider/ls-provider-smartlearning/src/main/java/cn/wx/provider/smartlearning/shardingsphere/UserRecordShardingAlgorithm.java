package cn.wx.provider.smartlearning.shardingsphere;


import lombok.SneakyThrows;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

/**
 * @Description TODO 根据user第一次登录时间分表的分片规则
 * @Author zgt
 * @Date 2020/10/14 10:45
 */
public class UserRecordShardingAlgorithm implements StandardShardingAlgorithm<Integer> {

    private boolean allowRangeQuery;

    private Properties props = new Properties();

    public UserRecordShardingAlgorithm(){

    }

    private boolean isAllowRangeQuery() {
        return Boolean.parseBoolean(this.props.getOrDefault("allow-range-query-with-record-sharding", Boolean.FALSE.toString()).toString());
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        if (this.allowRangeQuery) {
            return collection;
        } else {
            throw new UnsupportedOperationException("Since the property of `allow-range-query-with-user_record-sharding` is false, inline sharding algorithm can not tackle with range query.");
        }
    }

    @SneakyThrows
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue){
        //collection:["t_order_1","t_order_2"],preciseShardingValue:{"logicTableName":"t_order","columnName":"order_id","value":396416249350848512}
        //name为两张订单表 t_order_1 和 t_order_2
        return "user_record";
    }



    @Override
    public void init() {
        this.allowRangeQuery = this.isAllowRangeQuery();
    }

    @Override
    public String getType() {
        return "UserRecordShardingAlgorithm";
    }

    @Override
    public Properties getProps() {
        return props;
    }

    @Override
    public void setProps(Properties props) {
        this.props = props;
    }
}
