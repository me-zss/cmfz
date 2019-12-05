package com.shun.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class TextCodeUtil {
    private static DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FuBeUxx4oL9qJBoJVjm", "5JPsNoua3qL6Hp3JpphYtoAiXUfynu");
    private static IAcsClient client = new DefaultAcsClient(profile);
    private static CommonRequest request = new CommonRequest();
    static{
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("SignName", "AJSCSJ");
        request.putQueryParameter("TemplateCode", "SMS_179605661");
    }
    public static boolean sendCode(String phone,String code) {
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            return "OK".equals(response.getData().split(",")[0].split(":")[1].replaceAll("\"",""));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
