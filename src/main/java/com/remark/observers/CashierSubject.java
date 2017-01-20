package com.remark.observers;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/19.
 */
public interface CashierSubject {
    public void registerCashierObserver(CashierObserver o);
    public void removeCashierObserver(CashierObserver o);
    public void notifyCashierObservers();
}
