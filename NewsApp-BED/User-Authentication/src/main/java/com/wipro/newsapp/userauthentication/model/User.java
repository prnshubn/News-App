package com.wipro.newsapp.userauthentication.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column
    @NotBlank(message = "name required")
    private String name;

    @Column
    @NotBlank(message = "email required")
    private String email;
    @Column
    @NotBlank(message = "password required")
    private String password;

    @Column
    private Boolean active;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdOn;

    @Column
    @NotBlank(message = "role required")
    private String role;

    public User(Long user_id, @NotBlank(message = "name required") String name,
                @NotBlank(message = "email required") String email,
                @NotBlank(message = "password required") String password, Boolean active, LocalDate createdOn,
                String role) {
        super();
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.createdOn = createdOn;
        this.role = role;
    }

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
