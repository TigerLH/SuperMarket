package util;

import com.remark.util.RandomUtil;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/20.
 */
public class RandomUtilTest {
    @Test
    public void randomNumberTest(){
        for(int i =0 ;i<1000 ;i++){
            int num = RandomUtil.getRandomNumber(5,10);
            assertThat(num,greaterThanOrEqualTo(5));    //验证产生的随机数大于等于5
            assertThat(num,lessThanOrEqualTo(10));      //验证产生的随机数小于等于10
        }
    }
}
