# Apollo User Guide

**Apollo** is a simple command-line **task management chatbot** that helps you keep track of tasks.
All tasks are saved locally, so your tasks persist between runs.

---

## Quick start

1. Run Apollo from your IDE or terminal (how you run it depends on your project setup).
2. Type commands described in this guide to add, list, find, mark/unmark, delete, undo, and exit.

---

## Features

### 1. View all tasks

Show all tasks currently stored in Apollo.

**Command**

```
list
```

**Example output**

```
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][ ] Submit report (by: 2025-10-01)
3. [E][ ] Korea trip (from: 2025-12-01 to: 2025-12-11)
```

> Note: `list` reads from the local storage file (`./data/apollo.txt`) so it reflects the saved tasks.

---

### 2. Add tasks

Apollo supports three task types: **ToDo**, **Deadline**, and **Event**.

#### ToDo

Adds a task with only a description.

**Command**

```
todo <DESCRIPTION>
```

**Example**

```
todo Read book
```

**Example output**

```
Got it. I've added this task:
  [T][ ] Read book
Now you have 1 task in the list.
```

---

#### Deadline

Adds a task with a due date.

**Command**

```
deadline <DESCRIPTION> /by <DATE>
```

**Example**

```
deadline Return library book /by 2025-12-01
```

**Example output**

```
Got it. I've added this task:
  [D][ ] Return library book (by: 2025-12-01)
Now you have 2 tasks in the list.
```

---

#### Event

Adds a task with a start and end time.

**Command**

```
event <DESCRIPTION> /from <START> /to <END>
```

**Example**

```
event Korea trip /from 2025-12-01 /to 2025-12-11
```

**Example output**

```
Got it. I've added this task:
  [E][ ] Korea trip (from: 2025-12-01 to: 2025-12-11)
Now you have 3 tasks in the list.
```

---

### 3. Delete a task

Removes a task by number (the number shown by `list`).

**Command**

```
delete <TASK_NUMBER>
```

**Example**

```
delete 2
```

**Example output**

```
Noted. I've removed this task:
  [D][ ] Return library book (by: 2025-12-01)
Now you have 2 tasks in the list.
```

---

### 4. Find tasks

Search tasks by keyword (case-insensitive). Shows matching tasks only.

**Command**

```
find <KEYWORD>
```

**Example**

```
find book
```

**Example output**

```
Here are the matching tasks in your list:
1. [T][ ] Read book
```

---

### 5. Mark / Unmark

Mark a task as done or not done.

**Commands**

```
mark <TASK_NUMBER>
unmark <TASK_NUMBER>
```

**Example**

```
mark 1
```

**Example output**

```
Nice! I've marked this task as done:
  [T][X] Read book
```

**Example**

```
unmark 1
```

**Example output**

```
OK, I've marked this task as not done yet:
  [T][ ] Read book
```

---

### 6. Undo

Undo reverts the most recent *modifying* command. Non-modifying commands such as `list` or `find` are ignored by undo when determining what to revert.

**Supported undoable commands:** adding tasks (`todo`, `deadline`, `event`), `delete`, `mark`, `unmark`, and other modifying actions that change the task list.

**Command**

```
undo
```

**Examples**

* If you do:

  ```
  todo Write essay
  undo
  ```

  the `todo` is removed.

* If you do:

  ```
  mark 5
  list
  undo
  ```

  `undo` will revert the `mark 5` (it skips `list` when deciding what to undo).

**Example output after undoing an add:**

```
Noted. I've removed this task:
  [T][ ] Write essay
Now you have 0 tasks in the list.
```

---

### 7. Exit

Exit Apollo.

**Command**

```
bye
```

**Example output**

```
Bye. Hope to see you again soon!
```

---

## Command summary

| Command    | Usage format                            | Description                 |
|------------|-----------------------------------------|-----------------------------|
| `list`     | `list`                                  | Show all tasks              |
| `todo`     | `todo DESCRIPTION`                      | Add a ToDo task             |
| `deadline` | `deadline DESCRIPTION /by DATE`         | Add a Deadline task         |
| `event`    | `event DESCRIPTION /from START /to END` | Add an Event task           |
| `delete`   | `delete TASK_NUMBER`                    | Delete a task               |
| `find`     | `find KEYWORD`                          | List tasks matching keyword |
| `mark`     | `mark TASK_NUMBER`                      | Mark a task as done         |
| `unmark`   | `unmark TASK_NUMBER`                    | Unmark a task               |
| `undo`     | `undo`                                  | Undo last modifying command |
| `bye`      | `bye`                                   | Exit Apollo                 |

---

## Notes on command format

* Words in **<UPPER\_CASE>** are parameters that you must provide.
  Example: `todo Buy milk` → `<DESCRIPTION>` = "Buy milk".
* Parameters can generally be typed in a natural order as in the examples above.
* Extra parameters are not excepted. For example, entering `bye bye` will trigger a reminder: *"Were you trying to leave? The correct format is: bye"*

---

## Storage

* Apollo stores tasks in `./data/apollo.txt`.
* Changes from commands that alter the task list (`todo`, `deadline`, `event`, `delete`, `mark`, `unmark`, etc.) are persisted automatically to this file when they run successfully.
* When Apollo starts it attempts to load tasks from this file.

---

## Troubleshooting & tips

* If the data file `./data/apollo.txt` is missing, Apollo will create it automatically the next time it runs.
* Use `list` to check current task numbers before calling `delete`, `mark`, or `unmark`.
* `undo` only reverts the last modifying command (it ignores listing or find commands when searching for what to undo).

---

## Contact / Feedback

If something is unclear or you find a bug, please open an issue in the repo or contact the project maintainer.

---

Enjoy using Apollo! 🚀
