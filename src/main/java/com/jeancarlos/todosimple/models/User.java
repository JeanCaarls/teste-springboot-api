package com.jeancarlos.todosimple.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
    private interface Createuser{};
    private interface UpdateUser{};

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = Createuser.class)
    @NotEmpty(groups = Createuser.class)
    @Size(groups = Createuser.class, min = 2, max = 25)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 20, nullable = false)
    @NotEmpty(groups = {Createuser.class, UpdateUser.class})
    @NotNull(groups = {Createuser.class, UpdateUser.class})
    @Size(groups = {Createuser.class, UpdateUser.class}, min = 8, max = 20)
    private String password;

    // private List<Task> task = new ArrayList<Task>();

    public User() {};

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User user))
            return false;
        User other = (User) obj;
        if (this.id == null)
            if (other.id != null)
                return false;
        else if (!this.id.equals(other.id))
            return false;
        return Objects.equals(this.id, other.id) &&
                Objects.equals(this.username, other.username) &&
                Objects.equals(this.password, other.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
}
