package com.remark.market.customer;

import com.remark.market.SuperMarket;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public class Customer implements Runnable{
    private SuperMarket superMarket;
    
    public Customer(SuperMarket superMarket){
    	this.superMarket = superMarket;
    }
       
    
	public void run() {
		superMarket.getRandomGoods();
	}
}
