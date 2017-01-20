package com.remark.observers;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public interface GoodsSubject {
    public void registerGoodsObserver(GoodsObserver o);
    public void removeGoodsObserver(GoodsObserver o);
    public void notifyGoodsObservers();
}
