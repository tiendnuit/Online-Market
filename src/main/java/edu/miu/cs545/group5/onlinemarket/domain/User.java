package edu.miu.cs545.group5.onlinemarket.domain;

import edu.miu.cs545.group5.onlinemarket.config.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "user_type",
        discriminatorType = DiscriminatorType.STRING
)
public class User implements Serializable {
    @Id
    @GeneratedValue
    protected Long id;

    @NotBlank
    protected String firstName;

    @NotBlank
    protected String lastName;

    @NotBlank
    @Email
    protected String email;

    @Pattern(regexp="\\d{3}-\\d{3}-\\d{4}", message = "Phone must match format XXX-XXX-XXXX")
    protected String phone;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate birthDate;

    @Size(min=6, message = "Password must be 6 characters")
    protected String password;

    @Valid
    @Embedded
    protected Address address;

    protected String role;

    @Column(name = "active")
    private int active;

    public User() {
        role = Constants.ROLE_ADMIN;
    }

    public User(@NotBlank String firstName,
                @NotBlank String lastName,
                @NotBlank @Email String email,
                @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Phone must match format XXX-XXX-XXXX") String phone,
                @Past LocalDate birthDate,
                @Size(min = 6, message = "Password must be 6 characters") String password,
                @Valid Address address,
                String role, int active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.password = password;
        this.address = address;
        this.role = role;
        this.active = active;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
