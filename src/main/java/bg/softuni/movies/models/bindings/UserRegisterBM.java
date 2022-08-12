package bg.softuni.movies.models.bindings;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRegisterBM {

    @NotBlank(message = "PLease enter username!")
    @Length(min = 5, max= 15, message = "Username must be between 5 and 15 characters!")
    private String username;

    @NotBlank(message = "PLease enter first name!")
    private String firstName;
    @NotBlank(message = "PLease enter last name!")
    private String lastName;
    @NotBlank(message = "Password cannot be empty")
    @Length(min = 3, message = "Password must be minimum 3 characters")
    private String password;
    @NotBlank(message = "Password cannot be empty")
    private String confirmPassword;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please enter valid email")
    private String email;


    public UserRegisterBM() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBM setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
