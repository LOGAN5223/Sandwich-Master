package ru.pract.sandwichcloud.security;

import org.springframework.data.repository.CrudRepository;
import ru.pract.sandwichcloud.entity.UserTable;

public interface UserRepository extends CrudRepository<UserTable, Long> {
    UserTable findByUsername(String name);
}
