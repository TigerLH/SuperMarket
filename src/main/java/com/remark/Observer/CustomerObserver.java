package com.remark.Observer;

import com.remark.goods.Goods;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public interface CustomerObserver {
    public void onCustomerLineUp(Goods goods);
}
