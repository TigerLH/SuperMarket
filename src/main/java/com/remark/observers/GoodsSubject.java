package com.remark.observers;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public interface GoodsSubject {
    void registerGoodsObserver(GoodsObserver o);
    void removeGoodsObserver(GoodsObserver o);
    void notifyGoodsObservers();
}
