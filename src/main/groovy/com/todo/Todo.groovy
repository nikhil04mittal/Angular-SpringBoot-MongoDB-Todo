package com.todo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

enum TodoStatus {
    ACTIVE("active"),
    DONE("done")

    String name

    TodoStatus(String name) {
        this.name = name
    }
}


@Document(collection = "todo")
class Todo {
    @Id
    String id
    String description
    TodoStatus status
    Date dateCreated
}
