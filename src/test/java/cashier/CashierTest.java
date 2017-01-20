package cashier;

import com.remark.market.SuperMarket;
import com.remark.market.cashier.Cashier;
import com.remark.market.goods.Apple;
import com.remark.market.goods.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
/**
 * @author hong.lin
 * @description
 * @date 2017/1/20.
 */
public class CashierTest {
    private String cashierName = "cashier";
    private  Cashier cashier = null;

    @BeforeClass
    public void setUp(){
        Apple apple = new Apple();
        apple.setName("Apple");
        apple.setPrice(12.02);
        Cookie cookie = new Cookie();
        cookie.setName("Cookie");
        cookie.setPrice(8.8);
        SuperMarket superMarket = new SuperMarket();
        cashier = new Cashier(cashierName,superMarket);
        cashier.add(apple);
        cashier.add(cookie);
    }


    @Test
    public void checkOutTest(){
        assertThat(cashier.getCustomerCount(),equalTo(2)); //待处理的顾客数量
        assertThat(cashier.getCount(),equalTo(0)); //已处理的顾客数量
        cashier.checkOut();
        assertThat(cashier.getCustomerCount(),equalTo(1));
        assertThat(cashier.getCount(),equalTo(1));
        cashier.notifyCashierObservers();
    }


}
