# Factory Method Design Pattern

## What is it?

The Factory Method pattern defines an interface for creating objects, but lets subclasses decide which class to instantiate. It lets a class defer instantiation to subclasses.

## Real-World Example

**Document Editors:** When you create a new document in an editor, the application decides which type of document (Word, Excel, PowerPoint) to create based on your choice.

## Key Points

- Promotes loose coupling
- Allows subclasses to alter the type of objects created
- Useful for frameworks and libraries
