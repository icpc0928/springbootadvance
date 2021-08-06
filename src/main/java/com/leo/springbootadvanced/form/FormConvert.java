package com.leo.springbootadvanced.form;

//自訂泛型 S:原對象 T:目標對象
public interface FormConvert<S,T> {
    //新增某個方法叫做convert
    T convert(S s);
}
