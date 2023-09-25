package sk.jurcisin.vincent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "susers")
public class User {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_guid")
    private String userGuid;

    @Column(name = "user_name")
    private String userName;

    public User() {
    }

    public User(Integer userId, String userGuid, String userName) {
        this.userId = userId;
        this.userGuid = userGuid;
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userGuid=" + userGuid +
                ", userName='" + userName + '\'' +
                '}';
    }
}
