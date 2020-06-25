package codegym.service;

import codegym.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Iterable<User> showAll();
    User findById(Long id);
    void save(User user);
    void remove(Long id);
}
