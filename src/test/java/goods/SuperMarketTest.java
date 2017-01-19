package goods;

import com.remark.entity.SuperMarket;
import com.remark.goods.Goods;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public class SuperMarketTest {

    @Test
    public void testInit(){
        SuperMarket superMarket = new SuperMarket();
        superMarket.initGoods();
        assertThat(superMarket.getCount(),equalTo(45));
    }

    @Test
    public void testGetGoods(){
        SuperMarket superMarket = new SuperMarket();
        superMarket.initGoods();
        Goods goods = superMarket.getRandomGoods();
        assertThat(superMarket.getCount(),equalTo(45-1));
    }

}
