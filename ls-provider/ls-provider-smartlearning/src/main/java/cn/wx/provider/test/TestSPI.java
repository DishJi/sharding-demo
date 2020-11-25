package cn.wx.provider.test;

import org.apache.shardingsphere.sharding.spi.ShardingAlgorithm;

import java.util.ServiceLoader;

/**
 * @Description TODO
 * @Author zgt
 * @Date 2020/11/25 11:19
 */
public class TestSPI {

    public static void main(String[] args) {
        ServiceLoader<ShardingAlgorithm> serviceLoader = ServiceLoader.load(ShardingAlgorithm.class);
        for(ShardingAlgorithm shardingAlgorithm : serviceLoader){
            System.out.println(shardingAlgorithm.getType());
        }
    }

}
