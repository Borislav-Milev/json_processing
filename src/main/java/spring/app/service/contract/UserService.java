package spring.app.service.contract;

import spring.app.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User addUser(User user);

    User updateUser(User user);

    User deleteUser(long id);

    long getUsersCount();
}
