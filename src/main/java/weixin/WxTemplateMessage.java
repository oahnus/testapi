package weixin;

/**
 * Created by oahnus on 2017/4/20
 * 18:07.
 */
public class WxTemplateMessage {
    public static enum TemplateType {
        ORDER_STATE("ORDER_STATE_TEMPLATE_ID"),
        TAX_COMPLETE("TAX_COMPLETE_TEMPLATE_ID");

        private String templdateKey;
        TemplateType(String templateKey) {
            this.templdateKey = templateKey;
        }
    }

    public static String getOrderStateJson(String openId, String templateId,
                                           String first, String orderSerialId, String productName, String totalPrice, String remark) {
        return ("{\"touser\":\"" + openId + "\"," +
                "\"template_id\":\"" + templateId + "\"," +
                "\"url\":\"http://www.qiyewan.com/#/order\"," +
                "\"data\":{" +
                "  \"first\": {" +
                "    \"value\":\"" + first + "\"," +
                "    \"color\":\"#173177\"" +
                "  }," +
                "  \"keyword1\":{" +
                "  \"value\":\"" + orderSerialId + "\",\n" +
                "  \"color\":\"#173177\"\n" +
                "  },\n" +
                "  \"keyword2\": {\n" +
                "    \"value\":\"" + productName + "\",\n" +
                "    \"color\":\"#173177\"\n" +
                "  },\n" +
                "  \"keyword3\": {\n" +
                "    \"value\":\"" + totalPrice + "\",\n" +
                "    \"color\":\"#173177\"\n" +
                "  },\n" +
                "  \"remark\":{\n" +
                "    \"value\":\"" + remark + "\",\n" +
                "    \"color\":\"#173177\"\n" +
                "  }\n" +
                "}\n" +
                "}");
    }
}
