package org.example.view_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.entities.User;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserVM {

    private Long id;
    private String userName;
    private String hashedPassword;
    private String emailAddress;

    public static UserVM from(User user) {
        return new UserVM(user.getId(), user.getUserName(), user.getHashedPassword(), user.getEmailAddress());
    }

    public static List<UserVM> from(List<User> users) {
        return users.stream().map(UserVM::from).toList();
    }

    @Override
    public String toString() {
        return "BSC_UserVM{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
