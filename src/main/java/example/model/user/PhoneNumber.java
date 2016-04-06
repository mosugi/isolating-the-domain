package example.model.user;

import example.model.user.validation.OnRegister;
import example.model.user.validation.OnUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

public class PhoneNumber {

    @NotBlank(message = "電話番号を入力してください", groups = OnUpdate.class)
    @Pattern(regexp = "([0-9]{2,4}-[0-9]{2,4}-[0-9]{2,4})?", message = "xx-xxxx-xxxxの形式で入力してください", groups = {OnRegister.class, OnUpdate.class})
    String text;

    public PhoneNumber(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }
}
