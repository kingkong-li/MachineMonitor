package com.emt.fatri.machinemonitormvp.base;

/**
 * view interface,所有View必须实现此接口
 *
 *
 */
public interface BaseView<T> {

    void setPresenter(T presenter);
}
