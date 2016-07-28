package com.todo

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "todo", path = "todo")
public interface TodoRepository extends MongoRepository<Todo, String> {

    public List<Todo> findByStatus(@Param("value") Boolean status);

    public List<Todo> findByDescription(@Param("value") String description);

    public Todo findById(@Param("value") String todoId)

}