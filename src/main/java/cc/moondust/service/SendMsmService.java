package cc.moondust.service;

import cc.moondust.exception.UnKnowException;
import cc.moondust.utils.AlibabaUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MIKU on 2017/3/21.
 */
@Service
public class SendMsmService {

    @Autowired
    DefaultTaobaoClient defaultTaobaoClient;

    public boolean sendMsmCode(String phone, String code) throws UnKnowException {
        boolean res;
        AlibabaAliqinFcSmsNumSendRequest request = AlibabaUtil.getRequest(phone, code);
        try {
            AlibabaAliqinFcSmsNumSendResponse response = defaultTaobaoClient.execute(request);
            res = response.isSuccess();
        } catch (ApiException e) {
            throw new UnKnowException(500, e.getMessage());
        }
        return res;
    }

}
