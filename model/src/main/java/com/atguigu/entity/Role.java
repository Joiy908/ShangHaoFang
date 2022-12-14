package com.atguigu.entity;

public class Role extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	public Role() {
	}

	public Role(String roleName, String roleCode, String description) {
		this.roleName = roleName;
		this.roleCode = roleCode;
		this.description = description;
	}

	public Role(Long id ,String roleName, String roleCode, String description) {
		this.setId(id);
		this.roleName = roleName;
		this.roleCode = roleCode;
		this.description = description;
	}

	//角色名称
	private String roleName;
	//角色编码   
	private String roleCode;
	//描述   
	private String description;

	public void setRoleName(String value) {
		this.roleName = value;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleCode(String value) {
		this.roleCode = value;
	}
	
	public String getRoleCode() {
		return this.roleCode;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return "Role{" +
				"roleName='" + roleName + '\'' +
				", roleCode='" + roleCode + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}

