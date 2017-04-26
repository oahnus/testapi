package weixin.template_message;

import weixin.WxConfig;

/**
 * Created by oahnus on 2017/4/21
 * 10:10.
 */
public class WxOrderStateTemplate extends WxTemplate {

    public static WxTemplate fillTemplate(String first, String orderSerialId, String productName, String totalPrice, String remark) {
        return new WxOrderStateTemplate()
                .setTouser(WxConfig.OPEN_ID)
                .setTemplate_id(WxConfig.getProperty("ORDER_STATE_TEMPLATE_ID"))
                .addData("first", new WxData(first, "#173177"))
                .addData("keyword1", new WxData(orderSerialId, "#173177"))
                .addData("keyword2", new WxData(productName,"#173177"))
                .addData("keyword3", new WxData(totalPrice, "#173177"))
                .addData("remark", new WxData(remark,"#173177"));
    }
}
