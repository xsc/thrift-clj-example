namespace java person.index

struct Person {
  1: optional string firstName,
  2: string lastName
  3: byte age
}

exception PersonNotFound {
  1: i32 id
}

service PersonIndex {
    bool storePerson(1:i32 id, 2:Person p),
    Person getPerson(1:i32 id) throws (1:PersonNotFound ex)
}
