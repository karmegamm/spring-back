package com.SpringCrud.Springcrud.DTO;


public class UpdateDTO {
    private String username;
    private String email;
    private String current_password;
    private String old_password;

    public UpdateDTO() {
    }

    public UpdateDTO(String username, String email, String old_password,String current_password) {
        this.username = username;
        this.email = email;
        this.current_password = current_password;
        this.old_password = old_password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrent_password() {
        return current_password;
    }

    public void setCurrent_password(String current_password) {
        this.current_password = current_password;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    @Override
    public String toString() {
        return "UpdateDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", current_password='" + current_password + '\'' +
                ", old_password='" + old_password + '\'' +
                '}';
    }


}
