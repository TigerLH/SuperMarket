package com.remark.entity;

import com.remark.Observer.CustomerObserver;
import com.remark.Observer.GoodsObserver;
import com.remark.Observer.GoodsSubject;
import com.remark.goods.Apple;
import com.remark.goods.Cookie;
import com.remark.goods.Goods;
import com.remark.goods.MacBook;
import com.remark.thread.Cashier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hong.lin
 * @description 超市应具有导向顾客及管理收银员的功能
 * @date 2017/1/19.
 */
public class SuperMarket implements GoodsSubject,CustomerObserver {
    /**
     * 商品观察者列表
     */
    private List<GoodsObserver> observers = new ArrayList<GoodsObserver>();

    /**
     * 超市库存商品列表
     */
    public List<Goods> goodsList = new ArrayList<Goods>();

    /**
     * 收银员列表
     */
    public List<Cashier> cashierList = new ArrayList<Cashier>();

    /**
     * 初始化商品
     */
    public void initGoods(){
        for(int i = 0;i < 15;i++){
            goodsList.add(new Apple());
            goodsList.add(new MacBook());
            goodsList.add(new Cookie());
        }
    }

    /**
     * 收银员到位
     */
    public void initCashier(){
        Cashier cashier1 = new Cashier("张阿姨");
        Cashier cashier2 = new Cashier("罗小妹");
        Cashier cashier3 = new Cashier("李大叔");
        cashier1.start();
        cashier2.start();
        cashier3.start();
        cashierList.add(cashier1);
        cashierList.add(cashier2);
        cashierList.add(cashier3);
        this.registerGoodsObserver(cashier1);
        this.registerGoodsObserver(cashier2);
        this.registerGoodsObserver(cashier2);
    }

    /**
     * 获取剩余商品数量
     * @return
     */
    public int getCount(){
        return goodsList.size();
    }

    /**
     * 多线程操作,使用同步锁
     * 获取一件随机商品
     * @return Goods
     */
    public synchronized Goods getRandomGoods(){
        int size = this.getCount();
        Goods goods = null;
        if(size>0){         //商品剩余数量大于0
            int random = (int)(Math.random()*size);
            goods = goodsList.get(random);
            goodsList.remove(goods);
        }else{              //卖完了则通知收银员
            this.notifyGoodsObservers();
        }
        return goods;
    }


    public void registerGoodsObserver(GoodsObserver o) {
        observers.add(o);
    }

    public void removeGoodsObserver(GoodsObserver o) {
        int index = observers.indexOf(o);
        if (index != -1) {
            observers.remove(o);
        }
    }

    /**
     * 通知所有的Cashier:商品已经卖完,可以下班了
     */
    public void notifyGoodsObservers() {
        for (GoodsObserver observer : observers) {
            observer.onGoodsSellout();
        }
    }


    /**
     *顾客选择人少的队伍排队
     */
    public void onCustomerLineUp(Goods goods) {
        Cashier cashier_min = null;
        int min = cashierList.get(0).getCount();
        for(Cashier cashier : cashierList){
            int temp = cashier.getCount();
            if(temp<min){
                min = temp;
                cashier_min = cashier;
            }
        }
        cashier_min.add(goods);
    }
}
