package weixin.template_message;

import weixin.WxConfig;

import java.io.*;

/**
 * Created by oahnus on 2017/4/13
 * 10:55.
 */
public class WxTemplateMessageAPI {

    public static void main(String... args) throws IOException {
        String accessToken = WxConfig.getWxAccessToken(WxConfig.APP_ID, WxConfig.SECRET);
System.out.println("ACCESS_TOKEN=" + accessToken);

//        WxOrderStateTemplate.fillTemplate("订单成功",
//                "S223333",
//                "1元注册",
//                "1688",
//                "请及时付款")
//                .send(accessToken);

        boolean result = WxServiceProcessTemplate.fillTemplate("亲，本月代账进行至票据验收阶段",
                "北京极限好记有限公司",
                "代账",
                "票据验收",
                "王大锤",
                "有任何疑问，请致电财务专线：010-88888888")
                .send(accessToken);
System.out.println(result ? "success" : "false");
    }
}
