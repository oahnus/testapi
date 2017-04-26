package weixin.template_message;

import weixin.WxConfig;

/**
 * Created by oahnus on 2017/4/21
 * 11:36.
 */
public class WxServiceProcessTemplate extends WxTemplate {
    public static WxTemplate fillTemplate(String first, String companyName, String serviceName, String process, String handlePerson, String remark) {
        return new WxOrderStateTemplate()
                .setTouser(WxConfig.OPEN_ID)
                .setTemplate_id(WxConfig.getProperty("SERVICE_PROCESS_TEMPLATE_ID"))
                .addData("first", new WxData(first, "#173177"))
                .addData("keyword1", new WxData(companyName, "#173177"))
                .addData("keyword2", new WxData(serviceName,"#173177"))
                .addData("keyword3", new WxData(process, "#173177"))
                .addData("keyword4", new WxData(handlePerson, "#173177"))
                .addData("remark", new WxData(remark,"#173177"));
    }
}
