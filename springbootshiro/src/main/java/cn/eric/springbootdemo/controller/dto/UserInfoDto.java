package cn.eric.springbootdemo.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: UserInfoDto
 * @Description: TODO
 * @company lsj
 * @date 2019/5/16 10:03
 **/
@Getter
@Setter
@ToString
public class UserInfoDto {


    private Long id;

    @NotBlank
    @Length(min=3,max=10)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,3-9]))\\d{8}$", message="手机号格式不正确")
    private String phone;

    @Min(value = 18)
    @Max(value = 200)
    private int age;

    @NotBlank
    @Length(min=6,max=12,message="昵称长度为6-12")
    private String nickname;
}
