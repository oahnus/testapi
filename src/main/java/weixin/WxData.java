package weixin;

import lombok.Data;

/**
 * Created by oahnus on 2017/4/21
 * 10:07.
 */
@Data
public class WxData {
    private String value;
    private String color;

    public WxData(String value, String color) {
        this.value = value;
        this.color = color;
    }
}
