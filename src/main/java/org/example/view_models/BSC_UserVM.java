package org.example.view_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.entities.BSC_User;

import java.util.List;

@AllArgsConstructor
@Getter
public class BSC_UserVM {

    private String userName;
    private String hashedPassword;
    private String emailAddress;

    public static BSC_UserVM from(BSC_User user) {
        return new BSC_UserVM(user.getUserName(), user.getHashedPassword(), user.getEmailAddress());
    }

    public static List<BSC_UserVM> from(List<BSC_User> users) {
        return users.stream().map(BSC_UserVM::from).toList();
    }

}
