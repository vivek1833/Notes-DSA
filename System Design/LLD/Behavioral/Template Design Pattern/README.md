# Template Design Pattern

## What is it?

The Template pattern defines the skeleton of an algorithm in a method, deferring some steps to subclasses. It lets subclasses redefine certain steps without changing the algorithm's structure.

## Real-World Example

**Online Shopping Checkout:** The checkout process (cart review, payment, confirmation) is the same, but payment methods or delivery options can vary. The template defines the steps, and subclasses fill in the details.

## Key Points

- Promotes code reuse
- Defines invariant parts of an algorithm
- Lets subclasses customize behavior
