package com.scoprion.result;


/**
 * Created on 2017/9/16.
 */
public class SuccessResult<T> extends BaseResult {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

//    public SuccessResult(Object data) {
//        this();
//        this.data = data;
//    }


    public SuccessResult(int result, T data) {
        super(result);
        this.data = data;
    }

    public SuccessResult() {
        super(SUCCESS);
    }

    @Override
    public String toString() {
        return "SuccessResult{" +
                "data=" + data +
                '}';
    }
}
