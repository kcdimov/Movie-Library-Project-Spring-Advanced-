package bg.softuni.movies.models.bindings;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserRegisterBM {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String email;


    public UserRegisterBM() {
    }

    @NotBlank(message = "PLease enter username!")
    @Length(min = 5, max= 15, message = "Username must be between 5 and 15 characters!")
    public String getUsername() {
        return username;
    }

    public UserRegisterBM setUsername(String username) {
        this.username = username;
        return this;
    }
    @NotBlank(message = "PLease enter first name!")
    @Min(value = 2)
    @Max(value = 10, message = "The name cannot be more than 10 characters and les than 2")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotBlank(message = "PLease enter last name!")
    @Min(value = 2)
    @Max(value = 10, message = "The name cannot be more than 10 characters and les than 2")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotBlank(message = "Password cannot be empty")
    @Length(min = 3, message = "Password must be minimum 3 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotBlank(message = "Password cannot be empty")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please enter valid email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
