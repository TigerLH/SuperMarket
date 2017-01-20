package goods;

import com.remark.market.SuperMarket;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public class SuperMarketTest {
    private int goodsCount = 45;
    /**
     * 测试货物初始化
     */
    @Test
    public void testInit(){
        SuperMarket superMarket = new SuperMarket();
        superMarket.initGoods();
        assertThat(superMarket.getCount(),equalTo(goodsCount));
    }


    /**
     * 测试从超市获取商品
     */
    @Test
    public void testGetGoods(){
        SuperMarket superMarket = new SuperMarket();
        superMarket.initGoods();
        superMarket.initCashier();
        superMarket.getRandomGoods();
        assertThat(superMarket.getCount(),equalTo(goodsCount-1));
        assertThat(superMarket.cashierList.size(),equalTo(3));
    }

}
