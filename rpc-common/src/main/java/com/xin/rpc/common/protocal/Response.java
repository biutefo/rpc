package com.xin.rpc.common.protocal;

import com.xin.rpc.common.codec.protostuff.ProtostuffDecoder;
import com.xin.rpc.common.codec.protostuff.ProtostuffEncoder;

/**
 * @Description : 响应体
 * @Author : xin
 * @Created : 2021-04-23 11:26 上午
 */
public class Response {
    private String requestId;
    private Exception exception;
    private Object result;

    public boolean hasException() {
        return exception != null;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "requestId='" + requestId + '\'' +
                ", exception=" + exception +
                ", result=" + result +
                '}';
    }

    public static class ResponseProtostuffEncoder extends ProtostuffEncoder<Response> {
        public ResponseProtostuffEncoder() {
            super(Response.class);
        }
    }

    public static class ResponseProtostuffDecoder extends ProtostuffDecoder<Response> {
        public ResponseProtostuffDecoder() {
            super(Response.class);
        }
    }
}
