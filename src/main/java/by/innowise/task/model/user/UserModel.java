package by.innowise.task.model.user;

import by.innowise.task.model.BaseModel;

import java.util.Objects;

public class UserModel extends BaseModel {
    public enum Role{
        APPLICANT,
        ADMIN
    }

    private String name;
    private String password;
    private String email;
    private Role role;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("id:").append(getId()).append("\n");
        sb.append("name:").append(name).append("\n");
        sb.append("password:").append(password).append("\n");
        sb.append("email:").append(email).append("\n");
        sb.append("role:").append(role);
        return sb.toString();
    }

    @Override
    public boolean equals(Object object){
        if(object == null){
            return false;
        }

        if(!(object instanceof UserModel other)){
            return false;
        }

        if(object == this){
            return true;
        }

        return other.getId() == this.getId() &&
                Objects.equals(other.getName(), this.getName()) &&
                Objects.equals(other.getPassword(), this.getPassword()) &&
                Objects.equals(other.getEmail(), this.getEmail()) &&
                other.getRole() == this.getRole();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        result = prime * result + password.hashCode();
        result = prime * result + email.hashCode();
        result = prime * result + role.hashCode();

        return result;
    }
}
