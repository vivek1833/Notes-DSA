# LLD - Quick

## ✅ SOLID Principles Summary (with Code Examples)

### 1. S — Single Responsibility Principle (SRP)

_A class should have only one reason to change._

📌 **Key Idea** One class = one responsibility.

### ✅ Example:

```java
class Report {
  void getData() {
    // fetch data
  }
  void printReport() {
    // print data
  }
}

class ReportDataFetcher {
  void getData() { // fetch data
  }
}

class ReportPrinter {
  void printReport() { // print data
  }
}
```

### 2. O — Open/Closed Principle (OCP)

_Software entities should be open for extension, but closed for modification._

📌 **Key Idea** Add new behavior without modifying old code.

### ✅ Example:

```java
interface Shape {
  double area();
}

class Circle implements Shape {
  double radius;
  Circle(double r) {
    this.radius = r;
  }
  public double area() {
    return Math.PI * radius * radius;
  }
}

class Rectangle implements Shape {
  double w, h;
  Rectangle(double w, double h) {
    this.w = w;
    this.h = h;
  }
  public double area() {
    return w * h;
  }
}

class AreaCalculator {
  public double totalArea(List < Shape > shapes) {
    double total = 0;
    for (Shape s: shapes) total += s.area();
    return total;
  }
}
```

### 3. L — Liskov Substitution Principle (LSP)

_Subtypes must be substitutable for their base types._

📌 **Key Idea** Subclass objects should not break expectations.

### ✅ Example:

```java
class Bird {
  void fly() {
    System.out.println("Flying");
  }
}
```

```java
class Sparrow extends Bird { } // OK
```

```java
class Ostrich extends Bird {
  @Override
  void fly() {
    // breaks LSP
    throw new UnsupportedOperationException();
  }
}
```

```java
// FIX
interface Bird { }

interface FlyingBird extends Bird {
  void fly();
}

class Ostrich implements Bird { }

class Sparrow implements FlyingBird {
  public void fly() {
    System.out.println("Flying");
  }
}
```

### 4. I — Interface Segregation Principle (ISP)

_Clients should not be forced to implement interfaces they do not use._

�� **Key Idea** Favor small, specific interfaces.

### ✅ Example:

```java
// BAD  One fat interface
interface Machine {
  void print();
  void scan();
  void fax();
}

class OldPrinter implements Machine {
	public void print() {}
	public void scan() {} // Unused
	public void fax() {} // Unused
}
```

```java
// GOOD  Split interfaces
interface Printer {
	void print();
}

interface Scanner {
	void scan();
}

class SimplePrinter implements Printer {
	public void print() {}
}
```

### 5. D — Dependency Inversion Principle (DIP)

_Depend on abstractions, not concrete classes._

📌 **Key Idea** High-level modules shouldn’t depend on low-level modules
directly.

### ✅ Example:

```java
interface Switchable {
	void turnOn();
}

class LightBulb implements Switchable {
  public void turnOn() {
    System.out.println("Light turned on!");
  }
}

class Switch {
  Switchable device;
  Switch(Switchable d) {
    this.device = d;
  }

  void operate() {
	  device.turnOn();
	}
}
```

```java
// In Main
Switch s = new Switch(new LightBulb());
s.operate(); // Output: Light turned on!
```

### 🧾 Final Summary

| Principle | Definition                                  | Goal                       | Key Fix                        |
| --------- | ------------------------------------------- | -------------------------- | ------------------------------ |
| SRP       | A class should have one reason to change    | Separation of concerns     | Split class responsibilities   |
| OCP       | Open for extension, closed for modification | Flexible & extensible code | Use interfaces/inheritance     |
| LSP       | Subtypes must be replaceable for base types | Reliable polymorphism      | Use correct inheritance rules  |
| ISP       | No client should depend on unused methods   | Cleaner, focused contracts | Split large interfaces         |
| DIP       | Depend on abstractions, not concretions     | Loose coupling             | Use interfaces + DI containers |

## 🔥 CREATIONAL DESIGN PATTERNS

## Detailed & Simple

### ✅ 1. Singleton Pattern

### Make sure only one object is created in the entire app.

### 🎯 Real-Life Analogy:

You have **one electricity board** in a city. No matter how many people call, they all get the **same single instance**.

### 👨‍💻 Code Example (Java):

```java
class Logger {
  private static Logger logger;

  public static Logger getInstance() {
    if (logger == null) {
      synchronized (Logger.class) {
        if (logger == null) {
          logger = new Logger();
        }
      }
    }
    return logger;
  }
}

public class Main {
  public static void main(String[] args) {
    Logger logger1 = Logger.getInstance();
    Logger logger2 = Logger.getInstance();

    System.out.println(logger1 == logger2);
    // Output: true
  }
}

```

- Both are same objects
- Saves memory, avoids bugs
- Used in: Logger, Config, DB, etc.

### ✅ 2. Factory Method Pattern

### You donʼt create objects directly, you ask a factory to do it for you.

### 🎯 Real-Life Analogy:

You go to a chai shop and say: “Bhai ek chai do.ˮ You donʼt care **how** he makes it. He might give:

```
Masala chai
Green tea
Lemon tea
```

All made by **one shop** , based on your input.

### 👨‍💻 Code:

```java
interface Notification {
	void notifyUser();
}

class EmailNotification implements Notification {
  public void notifyUser() {
    System.out.println("Email sent");
  }
}

class SMSNotification implements Notification {
  public void notifyUser() {
    System.out.println("SMS sent");
  }
}

class NotificationFactory {
  public static Notification create(String type) {
    if (type.equals("EMAIL")) return new EmailNotification();
    else if (type.equals("SMS")) return new SMSNotification();
    return null;
  }
}
```

- You donʼt care how object is created
- Good when many subtypes Email, SMS, Push...

### ✅ 3. Abstract Factory Pattern

### Factory of factories — to create entire families of related objects.

### 🎯 Real-Life Analogy:

You want to buy furniture. You can choose:

```
VictorianStyleFactory
ModernStyleFactory
```

Each gives you:

```
Chair
Table
Sofa
```

```
→ all matching style.
```

### 👨‍💻 Code (simplified):

```java
interface Button {
  void paint();
}

class WinButton implements Button {
  public void paint() {
    System.out.println("Windows Button");
  }
}

class MacButton implements Button {
  public void paint() {
    System.out.println("Mac Button");
  }
}
```

```java
interface UIFactory {
	Button createButton();
}

class WinFactory implements UIFactory {
  public Button createButton() {
    return new WinButton();
  }
}

class MacFactory implements UIFactory {
  public Button createButton() {
    return new MacButton();
  }
}
```

Now use factory based on OS

```java
UIFactory factory = new WinFactory();
Button b = factory.createButton();
b.paint(); // Windows Button
```

- Clean family-based object creation
- Used in UI kits, themes, skins, OS-specific systems

### ✅ 4. Builder Pattern

### Use when creating big objects step by step.

### 🎯 Real-Life Analogy:

Youʼre at Subway 🍔:

```
First, you choose bread
Then sauce
Then veggies
Then cheese
```

Finally, the sandwich is ready.

### 👨‍💻 Code:

```java
class User {
  String name, email, pass, place;

  public User(Builder builder) {
    this.name = builder.name;
    this.email = builder.email;
    this.pass = builder.pass;
    this.place = builder.place;
  }

  public static class Builder {
    private final String email, pass;
    private String name, place;

    public Builder(String email, String pass) {
      this.email = email;
      this.pass = pass;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder place(String place) {
      this.place = place;
      return this;
    }

    public Builder build() {
      return new User(this);
    }
  }
}

public class Main {
  public static void main(String[] args) {
    User user = User.Builder("john@test.com", "1234")
      .name("John")
      .place("New York")
      .build();
  }
}
```

- Better than constructor with 5 params
- Cleaner to read and flexible

### ✅ 5. Prototype Pattern

### Clone an existing object instead of making new one from scratch.

### 🎯 Real-Life Analogy:

You fill one form and take 10 Xerox copies. All are same base, but editable.

### 👨‍💻 Code:

```java
class Mirror implement Clonable {
  private String glass, height, weight;

  public Mirror clone() throws CloneNotSupportedException {
    return (Mirror) super.clone();
  }
}

public class Main {
  public static void main(String[] args) throws CloneNotSupportedException {
    Mirror m1 = new Mirror();
    Mirror m2 = m1.clone();
  }
}
```

- Saves time
- Good for templates, objects with heavy setup

### ✅ FINAL SUMMARY

| Pattern          | When to Use                                 | Analogy                                     |
| ---------------- | ------------------------------------------- | ------------------------------------------- |
| Singleton        | Only one instance should exist              | One prime minister of a country             |
| Factory          | Create object based on input/type           | Tea shop preparing chai based on your order |
| Abstract Factory | Create families of related objects together | Victorian vs. Modern full furniture set     |
| Builder          | Build complex objects step-by-step          | Making a custom Subway sandwich             |
| Prototype        | Clone an existing fully built object        | Xerox copy of a filled form                 |

## 🎯 STRUCTURAL DESIGN PATTERNS

### 1. Adapter

### ✅ Purpose:

Convert one interface into another that a client expects.

### 💡 Analogy:

Charging a USB C phone with a micro-USB cable using an **adapter**.

### 🧪 Code:

```java
class OldRazorpay {
  void makePayment(int rupees) {
    System.out.println("Paid ₹" + rupees + " using Razorpay");
  }
}

interface PaymentProcessor {
  void pay(int amount);
}

class RazorPayAdapter implements PaymentProcessor {
  OldRazorpay rp;
  RazorPayAdapter(OldRazorpay rp) {
    this.rp = rp;
  }
  public void pay(int amount) {
    rp.makePayment(amount);
  }
}
```

### 2. Composite

### ✅ Purpose:

Treat individual objects and compositions (group of objects) the same way.

### 💡 Analogy:

A folder can contain files or other folders — you can "open" both.

### 🧪 Code:

```java
interface Component {
  void show();
}

class File implements Component {
  @Override
  public void show() {
    System.out.println("File");
  }
}

class Folder implements Component {
  List<Component> components = new ArrayList<>();

  public void add(Component component) {
    this.componenets.add(component);
  }

  @Override
  public void show() {
    for(Component component : this.components) {
      component.show();
    }
  }
}

public class Main {
  public static void main(String[] args) {
    Folder folder = new Folder();
    folder.add(new File());
    folder.add(new Folder());
    folder.show();
  }
}
```

### 3. Proxy

### ✅ Purpose:

Control access to an object.

### 💡 Analogy:

ATM asks for a PIN before giving access to your account.

### 🧪 Code:

```java
interface ATM {
  void withdraw(int amount, int pin);
}

class RealATM implements ATM {
  public void withdraw(int amount, int pin) {
    System.out.println("Withdrew ₹" + amount);
  }
}

class ProxyATM implements ATM {
  int correctPin = 1234;
  RealATM atm = new RealATM();

  public void withdraw(int amount, int pin) {
    if (pin == correctPin) atm.withdraw(amount, pin);
    else System.out.println("Incorrect PIN");
  }
}
```

### 4. Decorator

### ✅ Purpose:

Add features to objects dynamically without changing the base class.

### 💡 Analogy:

Topping a pizza with cheese, olives, etc.

### 🧪 Code:

```java
interface Pizza {
  String getDescription();
  int getCost();
}

class PlainPizza implements Pizza {
  public String getDescription() {
    return "Plain Pizza";
  }
  public int getCost() {
    return 200;
  }
}

abstract class PizzaDecorator implements Pizza {
  protected Pizza pizza;
  PizzaDecorator(Pizza pizza) {
    this.pizza = pizza;
  }
}

class Cheese extends PizzaDecorator {
  Cheese(Pizza p) {
    super(p);
  }
  public String getDescription() {
    return pizza.getDescription() + ", Chees
    e "; }
    public int getCost() {
      return pizza.getCost() 50;
    }
  }
```

### 5. Facade

### ✅ Purpose:

Simplify a complex system by providing a single entry point.

### 💡 Analogy:

Amazon’s “Place Order” button internally calls cart, payment, and shipping.

### 🧪 Code:

```java
class CartService {
  void add(String item) {
    System.out.println(item + " added to cart");
  }
}
class PaymentService {
  void pay(String mode) {
    System.out.println("Paid via " + mode);
  }
}
class ShippingService {
  void ship(String addr) {
    System.out.println("Shipping to " + addr);
  }
}

class OrderFacade {
  CartService cart = new CartService();
  PaymentService pay = new PaymentService();
  ShippingService ship = new ShippingService();

  void placeOrder(String item, String mode, String addr) {
    cart.add(item);
    pay.pay(mode);
    ship.ship(addr);
  }
}
```

### 6. Flyweight

### ✅ Purpose:

Minimize memory by sharing common data among many objects.

### 💡 Analogy:

Text editor reuses same Character objects (‘A’, ‘B’, ‘C’) for different positions.

### 🧪 Code:

```java
class Char {
  char c;
  Char(char c) {
    this.c = c;
  }
  void draw(int pos) {
    System.out.println("Char " + c + " at " + pos);
  }
}

class CharFactory {
  static Map < Character, Char > map = new HashMap < > ();
  static Char get(char c) {
    return map.computeIfAbsent(c, k→ new Char(k));
  }
}
```

### 7. Bridge

### ✅ Purpose:

Separate abstraction from implementation so both can vary independently.

### 💡 Analogy:

Remote Control (abstraction) works with TV, Fan, AC (implementations).

### 🧪 Code:

```java
interface Device {
  void turnOn();
}

class TV implements Device {
  public void turnOn() {
    System.out.println("TV ON");
  }
}

abstract class Remote {
  protected Device device;
  Remote(Device d) {
    device = d;
  }

  abstract void pressOn();
}

class BasicRemote extends Remote {
  BasicRemote(Device d) {
    super(d);
  }
  public void pressOn() {
    device.turnOn();
  }
}
```

## 📊 FINAL SUMMARY CHART – Structural

| Pattern   | Intent                                         | Real-Life Analogy                | Key Concepts                    |
| --------- | ---------------------------------------------- | -------------------------------- | ------------------------------- |
| Adapter   | Convert one interface into another             | Phone charger adapter            | Compatibility, translation      |
| Composite | Represent part–whole tree structures           | Folders and files                | Hierarchical objects, recursion |
| Proxy     | Control access to an object                    | ATM with PIN verification        | Access control, lazy loading    |
| Decorator | Add features without modifying the original    | Pizza with customizable toppings | Layered behavior, composition   |
| Facade    | Provide a simple interface to a complex system | Amazon “Place Order” button      | Simplification, unified API     |
| Flyweight | Share common data to save memory               | Character pool in a text editor  | Reuse, intrinsic vs extrinsic   |
| Bridge    | Decouple abstraction from implementation       | Universal remote control         | Flexibility, separation         |

## 🧠 Behavioral Patterns

### 1. Observer Pattern

### When one object changes state, all its dependents get notified automatically.

### ✅ Code Snippet:

```java
interface Observer {
  void update();
}

class Railway implements Observer {
  @Override
  public void update() {
    System.out.println("Train is delayed");
  }
}

class Airport implements Observer {
  @Override
  public void update() {
    System.out.println("Flight is delayed");
  }
}

class Station {
  List <Observer> observers = new ArrayList<>();

  public void add(Observer o) {
    this.observers.add(o);
  }

  public void notifyObservers() {
    for(Observer o : observers) {
      o.update();
    }
  }
}
```

### 2. Strategy Pattern

### Choose an algorithm/behavior at runtime.

### ✅ Code Snippet:

```java
interface Strategy {
  public void pay();
}

class Cash implements Strategy {
  @Override
  public void pay() {
    System.out.println("Paid via cash");
  }
}

class Card implements Strategy {
  @Override
  public void pay() {
    System.out.println("Paid via card");
  }
}

class PaymentStrategy {
  Strategy strategy;

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  public void pay() {
    strategy.pay();
  }
}

class Main {
  public static void main(String[] args) {
    PaymentStrategy ps = new PaymentStrategy();
    ps.setStrategy(new Cash());
    ps.pay();

    ps.setStrategy(new Card());
    ps.pay();
  }
}
```

### 3. Command Pattern

### Encapsulates a command (like a button press) into an object.

### ✅ Code Snippet:

```java
interface Command {
  void execute();
}

class PlayCommand implements Command {
  MusicPlayer player;
  PlayCommand(MusicPlayer p) {
    player = p;
  }
  public void execute() {
    player.play();
  }
}

class RemoteControl {
  Command cmd;
  void setCommand(Command cmd) {
    this.cmd = cmd;
  }
  void press() {
    cmd.execute();
  }
}
```

### 4. Chain of Responsibility

### Pass the request along a chain until someone handles it.

### ✅ Code Snippet:

```java
interface Handler {
  void handle();
  void setNext(Handler h);
}

class ErrorHandler implements Handler {
  Handler next;

  @Override
  public void handle() {
    System.out.println("Error handled");
  }

  @Override
  public void setNext(Handler h) {
    this.next = h;
  }
}

class InfoHandler implements Handler {
  Handler next;

  @Override
  public void handle() {
    System.out.println("Info handled");
  }

  @Override
  public void setNext(Handler h) {
    this.next = h;
  }
}
```

### 5. State Pattern

### Object behavior changes with internal state.

### ✅ Code Snippet:

```java
interface State {
  void handle();
}

class EditState implements State {
  public void handle() {
    System.out.println("Editing...");
  }
}

class Editor {
  State state;
  void setState(State s) {
    state = s;
  }
  void press() {
    state.handle();
  }
}
```

### 6. Mediator Pattern

### Central object controls communication between components.

### ✅ Code Snippet:

```java
class SmartHomeMediator {
  List < Device > devices;
  void send(String msg, Device sender) {
    for (Device d: devices)
      if (d != sender) d.receive(msg);
  }
}
```

### 7. Iterator Pattern

### Access elements without exposing internal structure.

### ✅ Code Snippet:

```java
interface Iterator {
  boolean hasNext();
  String next();
}

class BookRepo {
  String[] books = {
    "IT",
    "1984"
  };
  class BookIterator implements Iterator {
    int i = 0;
    public boolean hasNext() {
      return i < books.length;
    }
    public String next() {
      return books[i++];
    }
  }
}
```

### 8. Template Method Pattern

### Defines skeleton of an algorithm with steps customizable in subclasses.

### ✅ Code Snippet:

```java
abstract class Poster {
  final void post() {
    login();
    create();
    logout();
  }
  abstract void login();
  abstract void create();
  abstract void logout();
}
```

### 9. Visitor Pattern

### Add new operations to objects without changing their classes.

### ✅ Code Snippet:

```java
interface Shape {
  void accept(ShapeVisitor v);
}

interface ShapeVisitor {
  void visitCircle(Circle c);
}

class Circle implements Shape {
  public void accept(ShapeVisitor v) {
    v.visitCircle(this);
  }
}
```

### 10. Memento Pattern

### Save and restore object state (e.g., undo).

### ✅ Code Snippet:

```java
class Memento {
  private String state;
  Memento(String s) {
    state = s;
  }
  String getState() {
    return state;
  }
}

class Editor {
  String text = "";
  void write(String s) {
    text += s;
  }
  Memento save() {
    return new Memento(text);
  }
  void restore(Memento m) {
    text = m.getState();
  }
}
```

## 📋 Final Summary Table

| Pattern         | Purpose (1-liner)                                        |
| --------------- | -------------------------------------------------------- |
| Observer        | Notify multiple subscribers when an object changes       |
| Strategy        | Swap algorithms or behaviors at runtime                  |
| Command         | Encapsulate a request as an object                       |
| Chain of Resp.  | Pass a request through a chain of handlers               |
| State           | Change object behavior based on internal state           |
| Mediator        | Central controller manages communication between objects |
| Iterator        | Traverse a collection without exposing its internals     |
| Template Method | Define an algorithm skeleton with overridable steps      |
| Visitor         | Add new operations without modifying object structure    |
| Memento         | Capture and restore an object’s state                    |

## 🧾 Summary Chart

Here is the **fully cleaned, structured, Notion-ready version** of everything you pasted.
All grammar, formatting, spacing, alignment, and wording corrected.
No extra commentary — only the fixed content.

---

# **SOLID (Fixed & Clean)**

## **DIP — Dependency Inversion Principle**

| Topic                      | Meaning                                       | Example                  |
| -------------------------- | --------------------------------------------- | ------------------------ |
| Dependency Inversion (DIP) | High-level code should depend on abstractions | Use Switchable, not Bulb |
| Inversion of Control (IoC) | External code supplies dependencies           | Pass Engine to Car       |

---

# **Factory Method vs Abstract Factory**

| Feature       | Factory Method                     | Abstract Factory                              |
| ------------- | ---------------------------------- | --------------------------------------------- |
| Goal          | Create one object based on input   | Create families of related objects            |
| Example       | ShapeFactory.createShape("circle") | UIFactory.createButton() + createCheckbox()   |
| # of Products | One product at a time              | Multiple related products (Button, Checkbox)  |
| Extensibility | Easy to add new shapes             | Easy to add new product families (e.g. Linux) |

---

# **When to Prefer Composition**

| Prefer Composition When…                      | Why                                          |
| --------------------------------------------- | -------------------------------------------- |
| You need logic reuse across unrelated classes | Inheritance can’t cross hierarchies          |
| You need runtime behavior changes             | Components can be swapped dynamically        |
| To follow SOLID (SRP & OCP especially)        | Composition avoids rigid hierarchies         |
| You want loose coupling                       | Easier testing, maintenance, and flexibility |

---

# **Composition vs Inheritance (Summary)**

```
| Inheritance               | Composition                       |
|---------------------------|-----------------------------------|
| “Is-A” relationship       | “Has-A” relationship              |
| Static behavior           | Dynamic behavior (swap at runtime)|
| Tight coupling            | Loose coupling                    |
| Code reuse via parent     | Reuse via delegation              |
| Hard to change hierarchy  | Easy to plug-and-play components  |
```

---

# **Composition Example **

```java
interface SoundBehavior {
  void makeSound();
}

class Bark implements SoundBehavior {
  public void makeSound() { System.out.println("Bark"); }
}

class Howl implements SoundBehavior {
  public void makeSound() { System.out.println("Howl"); }
}

class Dog {
  private SoundBehavior behavior;

  public Dog(SoundBehavior behavior) {
    this.behavior = behavior;
  }

  public void makeSound() {
    behavior.makeSound();
  }

  public void setBehavior(SoundBehavior newBehavior) {
    this.behavior = newBehavior;
  }
}
```

---

# **Distributed Systems — Corrected Interpretations**

```
❌ Wrong: “If it's on 5 servers, it's distributed.”
✅ Correct: Only distributed if nodes communicate over a network.
```

```
❌ Wrong: “Scaling monolith to 10 VMs = distributed.”
✅ Correct: It’s just replication; no distributed logic.
```

```
❌ Wrong: “Microservices on one machine = not distributed.”
✅ Correct: Still distributed; services communicate over network protocols.
```

```
❌ Wrong: “Only microservices are distributed.”
✅ Correct: DB clusters, caches, message brokers are also distributed.
```

---

# **Distributed or Not? (Fixed Table)**

| System                                    | Distributed? | Why                                          |
| ----------------------------------------- | ------------ | -------------------------------------------- |
| Monolith on 5 servers                     | ❌ No        | Pure replication; no inter-node coordination |
| Monolith where each server has its own DB | ✅ Yes       | State is distributed; CAP tradeoffs appear   |
| Microservices on 1 machine                | ✅ Yes       | Network calls between autonomous services    |
| Redis Cluster (3 shards)                  | ✅ Yes       | Nodes coordinate & share data                |
| Kafka brokers (even on 1 machine)         | ✅ Yes       | Multiple brokers coordinate topic partitions |

---

# **Security Concepts (Fixed)**

| Topic               | Key Idea                           | Tools / Solutions               |
| ------------------- | ---------------------------------- | ------------------------------- |
| Authentication      | Identity verification              | Sessions, Tokens, MFA           |
| Authorization       | Access control                     | RBAC, ABAC                      |
| OAuth               | Delegated user access              | Google Login, OAuth providers   |
| JWT                 | Stateless auth with claims         | JWT libraries, jwt.io           |
| Secure Transmission | Encrypt data in transit            | HTTPS, TLS, SSL                 |
| SQL Injection       | Prevent SQL code injection         | Prepared statements, ORM        |
| XSS                 | Prevent script injection           | Escaping, CSP                   |
| CSRF                | Prevent cross-site request forgery | CSRF tokens, SameSite cookies   |
| Best Practices      | General security hardening         | Headers, logging, rate-limiting |

---

# **Scalability & Reliability (Fixed)**

| Concept           | Key Idea                                | Tools / Examples                         |
| ----------------- | --------------------------------------- | ---------------------------------------- |
| Load Balancing    | Distribute traffic evenly               | Nginx, HAProxy, AWS ELB                  |
| Health Checks     | Remove unhealthy nodes                  | Liveness / Readiness probes              |
| Disaster Recovery | Plan for regional or service failure    | Pilot light, warm standby, active-active |
| Data Replication  | Copy data across nodes for availability | Master-slave, master-master              |
| Backup            | Versioned snapshots for rollback        | AWS S3, Glacier, RDS snapshots           |
| Auto-Failover     | Automatically switch to standby systems | DNS failover, Kubernetes probes          |

---
