package cz.edu.upce.fei.datamanager.data;

public enum Role {
    USER("user"), ADMIN("admin");

    private String roleName;

    private Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

}
