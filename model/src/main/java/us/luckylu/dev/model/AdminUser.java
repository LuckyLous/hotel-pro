package us.luckylu.dev.model;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

public class AdminUser {
    @ApiModelProperty(value="用户id")
    private Integer id;

    @ApiModelProperty(value="用户名")
    private String username;

    @ApiModelProperty(value="密码")
    private String password;

    @ApiModelProperty(value="手机号")
    private String phone;

    @ApiModelProperty(value="邮箱")
    private String email;

    @ApiModelProperty(value="真实姓名")
    private String realName;

    @ApiModelProperty(value="性别")
    private String sex;

    @ApiModelProperty(value="备注")
    private String remark;

    @ApiModelProperty(value="注册时间")
    private LocalDateTime regisDate;

    @ApiModelProperty(value="用户类型id")
    private Integer adminUserRoleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public LocalDateTime getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(LocalDateTime regisDate) {
        this.regisDate = regisDate;
    }

    public Integer getAdminUserRoleId() {
        return adminUserRoleId;
    }

    public void setAdminUserRoleId(Integer adminUserRoleId) {
        this.adminUserRoleId = adminUserRoleId;
    }
}