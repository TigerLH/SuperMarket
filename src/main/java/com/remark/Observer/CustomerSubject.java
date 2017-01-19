package com.remark.Observer;

import com.remark.goods.Goods;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public interface CustomerSubject {
    public void registerCustomerObserver(CustomerObserver o);
    public void removeCustomerObserver(CustomerObserver o);
    public void notifyCustomerObservers(Goods goods);
}
