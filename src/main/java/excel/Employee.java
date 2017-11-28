package excel;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by oahnus on 2017/9/7
 * 10:35.
 */
@Data
public class Employee {
    private Long id;
    private String name;
    private String idCard;
    private Date bornDate;
    private String sex;
    private String telephone;

    public Employee name(String name) {
        this.name = name;
        return this;
    }

    public Employee idCard(String idCard) {
        this.idCard = idCard;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
        try {
            this.bornDate = sdf.parse(idCard.substring(6, 14));
        } catch (ParseException e) {
//            e.printStackTrace();
            this.bornDate = new Date();
        }
        return this;
    }

    public Employee sex(String sex) {
        this.sex = sex;
        return this;
    }

    public Employee telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }
}
