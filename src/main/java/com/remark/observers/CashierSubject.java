package com.remark.observers;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public interface CashierSubject {
    void registerCashierObserver(CashierObserver o);
    void removeCashierObserver(CashierObserver o);
    void notifyCashierObservers();
}
