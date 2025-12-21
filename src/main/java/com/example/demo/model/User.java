// Inside User.java

// Required for JPA
public User() {}

// Required for the Test file to compile
public User(String email, String password, String role, String name) {
    this.email = email;
    this.password = password;
    this.role = role;
    // Note: If you don't have a 'name' field yet, you might need 
    // to add 'private String name;' to this class as well.
}