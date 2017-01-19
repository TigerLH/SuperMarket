package com.remark.thread;

import com.remark.Observer.GoodsObserver;
import com.remark.goods.Goods;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public class Cashier extends Thread implements GoodsObserver{
    /**
     * 阻塞队列,先到先处理
     */
    public LinkedBlockingQueue<Goods> linkedBlockingQueue = new LinkedBlockingQueue<Goods>();
    public boolean isOver = false;  //商品是否已销售完
    public int count = 0;   //处理的顾客请求数量
    public int cost = 0;
    public Cashier(String cashierName){     //收银员的名称
        this.setName(cashierName);
    }

    /**
     * 获取处理的顾客请求数量
     * @return
     */
    public int getCount(){
        return this.count;
    }

    /**
     * 获取该收银员耗费的总时间
     * @return
     */
    public int getCost(){
        return this.cost;
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
            linkedBlockingQueue.poll();
            count++;
            int costTime = (int)((Math.random())*5+5);//收银员处理一个顾客所需时间
            cost = cost+costTime;
            try {
                Thread.sleep(costTime*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 收到超市通知:商品销售完了,终于可以下班了
     */
    public void onGoodsSellout() {
        isOver = true;
        System.out.println("我是:"+this.getName());
        System.out.println("今天我处理了:"+this.getCount()+"个顾客请求");
        System.out.println("耗费了:"+this.getCost()+"秒");
    }
}
