package com.toto.rest.restapplication.restfullapplication.repository;

import com.toto.rest.restapplication.restfullapplication.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    public List<Todo> findByUsername(String username);
}
