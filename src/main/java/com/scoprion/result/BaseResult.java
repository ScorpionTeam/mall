package com.scoprion.result;

/**
 * Created on 2017/9/16.
 */
public class BaseResult {

    public static int SUCCESS = 1;

    public static int FAIL = 0;

    private int result;

    public BaseResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static BaseResult success(Object object) {
        return new SuccessResult(object);
    }

    public static BaseResult error(String code, String message) {
        return new ErrorResult(code, message);
    }

    public static BaseResult parameterError() {
        return new ErrorResult("0002", "参数错误");
    }

    public static BaseResult notFound() {
        return new ErrorResult("0003", "查无结果");
    }

    public static BaseResult authorizationError() {
        return new ErrorResult("0004", "授权信息错误");
    }

    public static BaseResult permissionDenied() {
        return new ErrorResult("0005", "拒绝访问");
    }

    public static BaseResult systemError() {
        return new ErrorResult("0006", "系统错误");
    }

    public static BaseResult limitError() {
        return new ErrorResult("0007", "访问过于频繁,请一分钟后再试");
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "result=" + result +
                '}';
    }
}
