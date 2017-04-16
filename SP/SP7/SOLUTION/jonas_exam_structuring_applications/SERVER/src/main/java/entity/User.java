package entity;

import security.IUser;
import security.PasswordStorage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "USER")
public class User implements IUser, Serializable{

  @Column(length = 255, name = "PASSWORD_HASH",nullable = false)
  private String passwordHash; 
  
  @Id
  @Column(length = 35, name = "NAME",nullable = false)
  private String userName;
  
  @ManyToMany
  List<Role> roles = new ArrayList<>();
 
  public User() throws PasswordStorage.CannotPerformOperationException {
  }

  public User(String userName, String password) throws PasswordStorage.CannotPerformOperationException {
    this.userName = userName;
    this.passwordHash = PasswordStorage.createHash(password);
  }
  
  
  public void addRole(Role role) {
    roles.add(role);
    role.addUser(this);
  }
  
  public List<Role> getRoles(){
    return roles;
  }
    
  @Override
  public List<String> getRolesAsStrings() {
   if (roles.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList();
        for (Role role : roles) {
            rolesAsStrings.add(role.getRoleName());
        }
        return rolesAsStrings;
  }
 
  @Override
  public String getPassword() {
    return passwordHash;
  }

  public void setPassword(String password) {
    this.passwordHash = password;
  }

  @Override
  public String getUserName() {
    return userName;
  }
     
}