package cc.moondust.utils;

import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;

/**
 * Created by MIKU on 2017/3/21.
 */
public class AlibabaUtil {

    public static AlibabaAliqinFcSmsNumSendRequest getRequest(String phone , String code){
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setExtend("123456");
        request.setSmsType("normal");
        request.setSmsFreeSignName("网站插画分享服务");
        request.setSmsParamString("{\"code\":\""+code+"\"}");
        request.setRecNum(phone);
        request.setSmsTemplateCode("SMS_12961487");
        return request;
    }
}
