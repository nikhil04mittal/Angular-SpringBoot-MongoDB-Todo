package com.todo

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Controller that is used to expose SeoLink features to external partners
 */
@RestController
@CompileStatic
@RequestMapping(value = "ig/api/todo/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
class TodoController {

    @Autowired
    MongoTemplate mongoTemplate

    @Autowired
    TodoRepository todoRepository



    @RequestMapping(method = RequestMethod.GET, value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todo>> list() {
        List<Todo> todoList = todoRepository.findAll();
        return new ResponseEntity<List<Todo>>(todoList, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.POST, value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todo>> search(
            @RequestParam("status") TodoStatus status, @RequestParam("search") String criteria) {
        List<Todo> todoList = []
        if (status) {
            todoList = todoRepository.findByStatus(status)
        } else {
            todoList = todoRepository.findAll()
        }
        if (criteria) {
            todoList = todoList.collect { Todo todo ->
                if (todo.description.toLowerCase().contains(criteria.toLowerCase())) {
                    return todo
                }
            }
        }
        return new ResponseEntity<List<Todo>>(todoList, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.POST, value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody Todo todo) {
        todo.setStatus(TodoStatus.ACTIVE)
        todo.setDateCreated(new Date())
        String id = todoRepository.save(todo).id
        return new ResponseEntity<String>(id, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.PUT, value = "update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> update(@RequestBody TodoCo co, @PathVariable("id") String id) {
        Todo todo = todoRepository.findById(id)
        todo.description = co.description
        todoRepository.save(todo)
        return new ResponseEntity<Todo>(todo, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "status/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> statusUpdate(@RequestBody TodoCo co, @PathVariable("id") String id) {
        Todo todo = todoRepository.findById(id)
        TodoStatus todoStatus = co.status?.equalsIgnoreCase(TodoStatus.DONE.toString()) ? TodoStatus.DONE : TodoStatus.ACTIVE
        todo.status = todoStatus
        todoRepository.save(todo)
        return new ResponseEntity<Todo>(todo, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus delete(@PathVariable("id") String id) {
        todoRepository.delete(id)
        return HttpStatus.OK
    }
}



