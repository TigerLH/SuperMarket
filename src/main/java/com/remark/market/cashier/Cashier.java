package com.remark.market.cashier;

import com.remark.observers.CashierObserver;
import com.remark.observers.CashierSubject;
import com.remark.observers.GoodsObserver;
import com.remark.util.RandomUtil;
import com.remark.market.SuperMarket;
import com.remark.market.goods.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author hong.lin
 * @description Cashier做为被观察者同时观察货物剩余情况
 * @date 2017/1/19.
 */
public class Cashier extends Thread implements CashierSubject,GoodsObserver{
    /**
     * 收银员观察者列表
     */
    private List<CashierObserver> observers = new ArrayList<CashierObserver>();
    /**
     * 阻塞队列,先到先处理
     */
    private LinkedBlockingQueue<Goods> linkedBlockingQueue = new LinkedBlockingQueue<Goods>();
    private boolean isOver = false;  //商品是否已销售完
    private int count = 0;   //处理的顾客请求数量
    private int cost = 0;
    private List<Integer> waiting_time = new ArrayList<Integer>();   //每一个顾客排队的时间
    private SuperMarket superMarket;
    public Cashier(String cashierName,SuperMarket superMarket){     //收银员的名称
        this.setName(cashierName);
        this.superMarket = superMarket;
        this.registerCashierObserver(superMarket);
    }

    /**
     * 获取已处理的顾客请求数量
     * @return
     */
    public int getCount(){
        return this.count;
    }

    /**
     * 获取当前收银员是否已处理完顾客请求
     * @return
     */
    public boolean isOver(){
        return this.isOver;
    }

    /**
     * 获取待处理的顾客请求数量
     * @return
     */
    public int getCustomerCount(){
    	return linkedBlockingQueue.size();
    }

    /**
     * 获取当前收银员所处理的顾客等待的时间列表
     * @return
     */
    public List<Integer> getWaitingTime(){
       return this.waiting_time;
    }

    /**
     * 获取该收银员耗费的总时间
     * @return
     */
    public int getCost(){
        return this.cost;
    }

    /**
     * 收银员结账
     */
    public void checkOut(){
        this.linkedBlockingQueue.poll();
        count++;
    }


    /**
     * 顾客排队
     * @param goods
     */
    public void add(Goods goods){
        linkedBlockingQueue.add(goods);
    }

    @Override
    public void run() {
        while(!isOver){
            if(linkedBlockingQueue.isEmpty()){
                continue;
            }
            waiting_time.add(cost);   //队伍的第一个顾客不用等待
            int costTime = RandomUtil.getRandomNumber(5,10);//收银员处理一个顾客所需时间5-10s
            try {
                Thread.sleep(costTime*1000);
                cost = cost+costTime;   //下一个顾客需要等待的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkOut();
        }
    }

    /**
     * 收到超市通知:商品销售完了,干完手中的活可以下班了
     */
    public void onGoodsSellout() {
    	 while(!linkedBlockingQueue.isEmpty()){
    		  continue;
    	 }
         isOver = true;
         this.notifyCashierObservers();
         this.stop();
    }

    public void registerCashierObserver(CashierObserver o) {
        observers.add(o);
    }

    public void removeCashierObserver(CashierObserver o) {
        int index = observers.indexOf(o);
        if (index != -1) {
            observers.remove(o);
        }
    }

    public void notifyCashierObservers() {
        for (CashierObserver observer: observers) {
            observer.onProcessingCompleted();
        }
    }
}
