package goods;

import com.remark.market.SuperMarket;
import com.remark.market.cashier.Cashier;
import com.remark.market.goods.Apple;
import com.remark.market.goods.Cookie;
import com.remark.market.goods.MacBook;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public class SuperMarketTest {
    private int goodsCount = 45;
    private SuperMarket superMarket;

    @BeforeClass
    public void setUp(){
        superMarket = new SuperMarket();
        superMarket.initGoods();
        superMarket.initCashier();
    }

    /**
     * 货物初始化
     */
    @Test
    public void testInit(){
        assertThat(superMarket.getCount(),equalTo(goodsCount));
    }

    /**
     * 收银员初始化
     */
    @Test
    public void testInitCashier(){
        List<Cashier> list = superMarket.cashierList;
        assertThat(list.size(),equalTo(3));
        for(Cashier cashier : list){
            assertThat(cashier.isAlive(),equalTo(true));
        }
    }

    /**
     * 测试从超市获取商品
     */
    @Test
    public void testGetGoods(){
        superMarket.getRandomGoods();
        assertThat(superMarket.getCount(),equalTo(goodsCount-1));
        assertThat(superMarket.cashierList.size(),equalTo(3));
    }

    /**
     * 三个收银员,分别有0,1,2个顾客排队
     * 当有新的顾客排队时应选择顾客数为0的收银员
     */
    @Test
    public void customerLineUpTest(){
        List<Cashier> cashierList = new ArrayList<Cashier>();
        Cashier cashier1 = new Cashier("1",superMarket);
        Cashier cashier2 = new Cashier("2",superMarket);
        Cashier cashier3 = new Cashier("3",superMarket);
        cashier2.add(new Apple());
        cashier3.add(new MacBook());
        cashier3.add(new Cookie());
        cashierList.add(cashier1);
        cashierList.add(cashier2);
        cashierList.add(cashier3);
        superMarket.customerLineUp(cashierList,new Apple());
        assertThat(cashier1.getCustomerCount(),equalTo(1)); //验证分配到顾客数最少的收银员
    }

    @AfterMethod
    public void tearDown(){
        superMarket.initGoods();
    }
}
