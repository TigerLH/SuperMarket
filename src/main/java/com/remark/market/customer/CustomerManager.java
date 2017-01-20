package com.remark.market.customer;

import com.remark.market.SuperMarket;
import com.remark.util.RandomUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hong.lin
 * @description 顾客管理:引导用户进场,根据商品数量控制进场数（导购）
 * @date 2017/1/20.
 */
public class CustomerManager {
	private ExecutorService pool = Executors.newCachedThreadPool();
	private SuperMarket superMarket;
	public CustomerManager(SuperMarket superMarket){
		this.superMarket = superMarket;
	}
	
	public void productConstomer(){
		while(superMarket.getCount()>0){	//剩余商品大于0
			try {
				Thread.sleep(RandomUtil.getRandomNumber(1,3)*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Customer customer = new Customer(superMarket);
			pool.execute(customer);
		}
	}
}
