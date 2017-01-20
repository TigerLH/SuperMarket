package com.remark.market;

import com.remark.observers.CashierObserver;
import com.remark.observers.GoodsObserver;
import com.remark.observers.GoodsSubject;
import com.remark.market.goods.Apple;
import com.remark.market.goods.Cookie;
import com.remark.market.goods.Goods;
import com.remark.market.goods.MacBook;
import com.remark.market.customer.CustomerManager;
import com.remark.market.cashier.Cashier;

import java.util.ArrayList;
import java.util.List;

/**超市做为一个主体应具备：管理货物及Cashier的功能
 * @author hong.lin
 * @description 超市做为被观察者通知Cashier货物剩余情况,同时做为观察者接收Cashier处理顾客请求的情况
 * @date 2017/1/19.
 */
public class SuperMarket implements GoodsSubject,CashierObserver{
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

    private Cashier cashier1 = null;
    private Cashier cashier2 = null;
    private Cashier cashier3 = null;

    private long start_time = 0;
    /**
     * 初始化商品
     */
    public void initGoods(){
        for(int i = 0;i < 15;i++){
            goodsList.add(new Apple());
            goodsList.add(new MacBook());
            goodsList.add(new Cookie());
        }
        System.out.println("商品上架完成");
    }

    /**
     * 收银员到位
     */
    public void initCashier(){
        cashier1 = new Cashier("张阿姨",this);
        cashier2 = new Cashier("罗小妹",this);
        cashier3 = new Cashier("李大叔",this);
        this.registerGoodsObserver(cashier1);
        this.registerGoodsObserver(cashier2);
        this.registerGoodsObserver(cashier3);
        cashier1.start();
        cashier2.start();
        cashier3.start();
        cashierList.add(cashier1);
        cashierList.add(cashier2);
        cashierList.add(cashier3);
        System.out.println("收银员已就位");
    }


    /**
     * 导购到位
     */
    public void initCustomerManager(){
    	CustomerManager customerManager = new CustomerManager(this);
    	System.out.println("导购已就位");
    	System.out.println("超市开业");
    	customerManager.productConstomer();
    }

    /**
     * 开始售卖商品
     */
    public void start(){
    	this.initGoods();
    	this.initCashier();
        start_time = System.currentTimeMillis();
    	this.initCustomerManager();
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
    public synchronized void getRandomGoods(){
        int size = this.getCount();
    	System.out.println("商品剩余:"+size);
        Goods goods = null;
        if(size>0){			//商品剩余数量大于0
        	int random = (int)(Math.random()*size);
	        goods = goodsList.get(random);
	        customerLineUp(goods);
	        goodsList.remove(goods);
        	if(getCount()==0){    //货架上没有商品则通知收银员
        		System.out.println("商品已选购完毕");
                this.notifyGoodsObservers();
        	}
        }            
    }

    
    /**
     *顾客选择人少的队伍排队
     */
    public void customerLineUp(Goods goods) {
        Cashier cashier_min = cashierList.get(0);
        int min = cashier_min.getCustomerCount();
        for(Cashier cashier : cashierList){
            int temp = cashier.getCustomerCount();
            if(temp<min){
                min = temp;
                cashier_min = cashier;
            }
        }
        cashier_min.add(goods);
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
     * 通知所有的Cashier:商品已经卖完,处理完手中的业务可以下班了
     */
    public void notifyGoodsObservers() {
        for (GoodsObserver observer : observers) {
            observer.onGoodsSellout();
        }
    }

    public void onProcessingCompleted() {
        boolean isAllProcessCompleted = true;
        for(Cashier cashier : cashierList){     //存在任意一个收银员未完成即为未完成
            if(!cashier.isOver()){
                isAllProcessCompleted = false;
            }
        }
        if(isAllProcessCompleted){
            System.out.println("所有顾客请求已处理完成");
            long totalTime = (System.currentTimeMillis()-start_time)/1000;
            System.out.println("从开始销售到售罄总共时间为:"+totalTime+"秒");
            List<Integer> total = new ArrayList<Integer>();
            for(Cashier cashier : cashierList){
                System.out.println(cashier.getName()+"处理了:"+cashier.getCount()+"个顾客请求,"+"耗费了:"+cashier.getCost()+"秒");
                total.addAll(cashier.getWaitingTime());
            }
            int count = 0;
            for(Integer time : total){
                count = count+time;
            }
            System.out.println("顾客平均等待时间为:"+count/45+"S");
            System.out.println("每个商品平均售出时间为:"+(double)totalTime/45+"S");
        }
    }

    public static void main(String ...args){
        SuperMarket superMarket = new SuperMarket();
        superMarket.start();
    }
}
