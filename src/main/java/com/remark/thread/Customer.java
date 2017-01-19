package com.remark.thread;

import com.remark.Observer.CustomerObserver;
import com.remark.Observer.CustomerSubject;
import com.remark.entity.SuperMarket;
import com.remark.goods.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public class Customer extends Thread implements CustomerSubject{
    /**
     * 顾客观察者列表
     */
    private List<CustomerObserver> observers = new ArrayList<CustomerObserver>();

    @Override
    public void run() {
        SuperMarket superMarket = new SuperMarket();
        this.registerCustomerObserver(superMarket);
        Goods goods = superMarket.getRandomGoods();
        if(goods!=null){
            this.notifyCustomerObservers(goods);
        }
    }


    public void registerCustomerObserver(CustomerObserver o) {
        observers.add(o);
    }

    public void removeCustomerObserver(CustomerObserver o) {
        int index = observers.indexOf(o);
        if (index != -1) {
            observers.remove(o);
        }
    }

    public void notifyCustomerObservers(Goods goods) {
        for (CustomerObserver observer : observers) {
            observer.onCustomerLineUp(goods);
        }
    }
}
