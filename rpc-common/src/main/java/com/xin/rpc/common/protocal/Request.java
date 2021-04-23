package com.xin.rpc.common.protocal;

import com.xin.rpc.common.codec.protostuff.ProtostuffDecoder;
import com.xin.rpc.common.codec.protostuff.ProtostuffEncoder;

import java.util.Arrays;

/**
 * @Description : 请求体
 * @Author : xin
 * @Created : 2021-04-23 11:26 上午
 */
public class Request {
    private String requestId;
    private String interfaceName;
    private String serviceVersion;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String className) {
        this.interfaceName = className;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId='" + requestId + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", serviceVersion='" + serviceVersion + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }

    public static class RequestProtostuffEncoder extends ProtostuffEncoder<Request> {
        public RequestProtostuffEncoder() {
            super(Request.class);
        }
    }

    public static class RequestProtostuffDecoder extends ProtostuffDecoder<Request> {
        public RequestProtostuffDecoder() {
            super(Request.class);
        }
    }
}
