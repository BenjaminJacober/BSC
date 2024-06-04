package org.example.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.entities.User;

import java.util.List;

@NoArgsConstructor
@Getter
// todo: maybe create distinct register/authenticate DTOS.
public class UserVM {

	private Long id;

	//	@NotNull
//	@NotEmpty
//	@Size(min = 2, max = 50)
	// todo: can also be null, when we login... need solution for validation and not creating 10 VMs
	private String username;

	// can be null and empty, when we send it to the frontend
	private String password;

	@NotNull
	@NotEmpty
	@Email(regexp = ".+@.+\\..+") // todo: check regex, but should be good
	private String email;

	public UserVM(Long id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}

	public static UserVM from(User user) {
		return new UserVM(user.getId(), user.getUsername(), user.getEmail());
	}

	public static List<UserVM> from(List<User> users) {
		return users.stream().map(UserVM::from).toList();
	}

	@Override
	public String toString() {
		return "BSC_UserVM{" +
				"id=" + id +
				", userName='" + username + '\'' +
				", emailAddress='" + email + '\'' +
				'}';
	}
}
