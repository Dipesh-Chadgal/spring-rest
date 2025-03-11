package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.BlogEntity;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

    Optional<BlogEntity> getBlogById(long id);

	List<BlogEntity> findAll();

}
