package ru.pract.sandwichcloud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.pract.sandwichcloud.entity.Sandwich;

public interface SandwichRepository extends CrudRepository<Sandwich, Long>, PagingAndSortingRepository<Sandwich, Long> {
}
