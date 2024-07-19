package ru.pract.sandwichcloud.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.pract.sandwichcloud.entity.SandwichOrder;
import ru.pract.sandwichcloud.entity.UserTable;

import java.util.List;

public interface OrderRepository extends CrudRepository<SandwichOrder, Long> {
    List<SandwichOrder> findByUserTableIdOrderByPlacedAtDesc(UserTable userTable, Pageable pageable);
}
