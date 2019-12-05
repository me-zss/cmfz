package com.shun;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.shun.util.TextCodeUtil;
import org.junit.Test;

public class TestAliyunText {
    @Test
    public void testText() {
        String phone = "18089712515";
        String code = "ILoveYou";
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FuBeUxx4oL9qJBoJVjm", "5JPsNoua3qL6Hp3JpphYtoAiXUfynu");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "AJSCSJ");
        request.putQueryParameter("TemplateCode", "SMS_179605661");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testStatus() {
        TextCodeUtil.sendCode("17624408212","jdsffk d");
    }
}
