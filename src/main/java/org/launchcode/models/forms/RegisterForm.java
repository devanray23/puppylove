package org.launchcode.models.forms;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterForm {

    @Email
    private String email;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Pattern(regexp = "(\\S){4,20}", message = "Password must have 4-20 characters")
    private String password;

    @NotNull(message = "Passwords to not match")
    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPasswordForRegistration();
    }

    private void checkPasswordForRegistration() {
        if (!getPassword().equals(verifyPassword)) {
            verifyPassword = null;
        }
    }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
