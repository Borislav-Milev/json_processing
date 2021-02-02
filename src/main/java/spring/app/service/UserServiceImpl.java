package spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.app.dao.UserRepository;
import spring.app.domain.User;
import spring.app.exception.NonExistingEntityException;
import spring.app.service.contract.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NonExistingEntityException(String.format("User with ID %d does not exist.", id))
        );
    }

    @Transactional
    @Override
    public User addUser(User user) {
        user.setId(null);
        return this.userRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        getUserById(user.getId());
        return this.userRepository.save(user);
    }

    @Transactional
    @Override
    public User deleteUser(long id) {
        User removed = getUserById(id);
        this.userRepository.deleteById(id);
        return removed;
    }

    @Override
    public long getUsersCount() {
        return this.userRepository.count();
    }
}
