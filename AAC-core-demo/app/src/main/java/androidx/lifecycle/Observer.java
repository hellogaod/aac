package androidx.lifecycle;

/**
 * Copyright (C), 2019-2022, 佛生
 * FileName: Observer
 * Author: 佛学徒
 * Date: 2022/6/7 10:36
 * Description:
 * History:
 */
public interface Observer<T> {
    public void onChange(T t);
}
