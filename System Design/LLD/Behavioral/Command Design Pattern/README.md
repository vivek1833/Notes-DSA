# Command Design Pattern

## What is it?

The Command pattern turns a request into a stand-alone object that contains all information about the request. This allows you to parameterize methods with different requests, queue them, or log them.

## Real-World Example

**Remote Control:** Think of a TV remote. Each button (command) can be pressed to perform an action (turn on, change channel, etc.), and you can even program macros (sequences of commands).

## Key Points

- Encapsulates a request as an object
- Supports undo/redo, queuing, and logging of requests
- Decouples sender and receiver
