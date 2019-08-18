package us.luckylu.dev.model;

import io.swagger.annotations.ApiModelProperty;

public class AdminUserRole {
    @ApiModelProperty(value="用户类型id")
    private Integer id;

    @ApiModelProperty(value="用户类型")
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}