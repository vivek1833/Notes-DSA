# LLD - Quick

## ✅ SOLID Principles Summary (with Code Examples)

### 1. S — Single Responsibility Principle (SRP)

*A class should have only one reason to change.*

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

*Software entities should be open for extension, but closed for modification.*

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

*Subtypes must be substitutable for their base types.*

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
    throw new UnsupportedOperationException(); // breaks LSP
  }
}
```

```java
// FIX
interface Bird { }
```

```java
interface FlyingBird extends Bird {
  void fly();
}
```

```java
class Ostrich implements Bird { }
```

```java
class Sparrow implements FlyingBird {
  public void fly() {
    System.out.println("Flying");
  }
}
```

### 4. I — Interface Segregation Principle (ISP)

*Clients should not be forced to implement interfaces they do not use.*

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

*Depend on abstractions, not concrete classes.*

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

### 🧾 Final Summary Table (Notion-Ready)

```
Principle Definition Goal Key Fix
```

```
SRP One reason to change Separation of concerns Split class bresponsibilityy
```

```
OCP
Open for extension,
closed for modification
```

```
Flexible &
extensible code
```

```
Use
inheritance/interfaces
```

```
LSP Subclass cparent an replace Reliablepolymorphism Use corinheritancerect
```

```
ISP
No unnecessary
methods in interfaces Cleaner contracts Split fat interfaces
```

```
DIP Depend onabstractions Loose coupling Use interfaces & DI
```

## 🔥 CREATIONAL DESIGN PATTERNS —

## Detailed & Simple

### ✅ 1. Singleton Pattern

### Make sure only one object is created in the entire app.

### 🎯 Real-Life Analogy:

You have **one electricity board** in a city.

No matter how many people call, they all get the **same single instance**.

### 👨‍💻 Code Example (Java):

```java
class DBConnection {
	private static DBConnection instance;
	private DBConnection() {} // nobody can create it from outside
	public static DBConnection getInstance() {
		if (instance == null) instance = new DBConnection();
		return instance;
	}
}
```

Now wherever you need DB access:

```java
DBConnection db1 = DBConnection.getInstance();
DBConnection db2 = DBConnection.getInstance();
```

✅ db1 and db2 are **same object**

✅ Saves memory, avoids bugs

✅ Used in: Logger, Config, DB, etc.

### ✅ 2. Factory Method Pattern

### You donʼt create objects directly, you ask a factory to do it

### for you.

### 🎯 Real-Life Analogy:

You go to a chai shop and say: “Bhai ek chai do.ˮ

You donʼt care **how** he makes it.

He might give:

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
    if (type.equals("email")) return new EmailNotification();
    else if (type.equals("sms")) return new SMSNotification();
    return null;
  }
}
```

Usage:

```java
Notification n = NotificationFactory.create("sms");
n.notifyUser(); // prints SMS sent
```

✅ You donʼt care how object is created

✅ Good when many subtypes Email, SMS, Push…)

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

✅ Clean family-based object creation

✅ Used in UI kits, themes, skins, OS-specific systems

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
class Pizza {
  String dough, cheese, toppings;

  static class Builder {
    String dough, cheese, toppings;
    Builder setDough(String d) {
      dough = d;
      return this;
    }
    Builder setCheese(String c) {
      cheese = c;
      return this;
    }
    Builder setToppings(String t) {
      toppings = t;
      return this;
    }
    Pizza build() {
      Pizza p = new Pizza();
      p.dough = dough;
      p.cheese = cheese;
      p.toppings = toppings;
      return p;
    }
  }
}
```

Usage:

```java
Pizza p = new Pizza.Builder()
.setDough("thin")
.setCheese("mozzarella")
.setToppings("olives")
.build();
```

✅ Better than constructor with 5 params

✅ Cleaner to read and flexible

### ✅ 5. Prototype Pattern

### Clone an existing object instead of making new one from scratch.

### 🎯 Real-Life Analogy:

You fill one form 📝 and take 10 Xerox copies.

All are same base, but editable.

### 👨‍💻 Code:

```java
class Document implements Cloneable {
  String content;
  public Document clone() throws CloneNotSupportedException {
    return (Document) super.clone();
  }
}
```

Usage:

```java
Document d1 = new Document();
d1.content = "Agreement";
```

```java
Document d2 = d1.clone();
d2.content = "Modified Agreement";
```

✅ Saves time

✅ Good for templates, objects with heavy setup

### ✅ FINAL SUMMARY

```
Pattern When to Use Analogy
Singleton Only one object Only one prime minister
Factory Choose object by type Tea shop gives chai based on request
Abstract Factory Group of related objects Victorian vs Modern furniture set
Builder Step-by-step big object Subway sandwich
Prototype Clone object Xerox of a filled form
```

## 🎯 STRUCTURAL DESIGN PATTERNS —

## FULL SUMMARY

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

A folder can contain files or other folders — you can “open” both.

### 🧪 Code:

```java
interface Component {
  void show();
}

class File implements Component {
  String name;
  File(String name) {
    this.name = name;
  }

  public void show() {
    System.out.println("File: " + name);
  }
}

class Folder implements Component {
  String name;
  List < Component > children = new ArrayList < > ();
  Folder(String name) {
    this.name = name;
  }
  public void add(Component c) {
    children.add(c);
  }
  public void show() {
    System.out.println("Folder: " + name);
    for (Component c: children) c.show();
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

## Patterns for Notion ✅

```
Pattern Intent Real-Life Analogy Key Concepts
```

```
Adapter Convert one intanother erface to Phone charconverter ger Compatibility
```

```
Composite Tree structure of objects Folders + files Hierarchical
objects
Proxy Control access to object ATM PIN check Access control
```

```
Decorator Add features withoutmodifying original Pizza toppings Layeringbehavior
```

```
Facade Unified intcomplex systemerface to a Amazon "PlaceOrderˮ Simplification
```

```
Flyweight Share common datmemory a to save Characteditor er pool in Reuse
```

```
Bridge Decouple abstraction frimplementation om Universal Remote Flexibility
```

## 🧠 Behavioral Patterns – Summary

### 1. Observer Pattern

### When one object changes state, all its dependents get notified automatically.

### ✅ Code Snippet:

```java
interface Observer {
  void update(String news);
}

class NewsAgency {
  List < Observer > observers = new ArrayList < > ();
  String news;

  void addObserver(Observer o) {
    observers.add(o);
  }

  void updateNews(String news) {
    this.news = news;
    for (Observer o: observers) o.update(news);
  }
}

class BBC implements Observer {
  public void update(String news) {
    System.out.println("BBC " + news);
  }
}
```

### 2. Strategy Pattern

### Choose an algorithm/behavior at runtime.

### ✅ Code Snippet:

```java
interface FlyStrategy {
  void fly();
}

class RocketFly implements FlyStrategy {
  public void fly() {
    System.out.println("Flying with rocket");
  }
}

class Duck {
  FlyStrategy fs;
  void setFlyStrategy(FlyStrategy fs) {
    this.fs = fs;
  }
  void performFly() {
    fs.fly();
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
abstract class SupportHandler {
  SupportHandler next;
  void setNext(SupportHandler n) {
    next = n;
  }

  void handle(String msg) {
    if (canHandle(msg)) process(msg);
    else if (next != null) next.handle(msg);
  }

  abstract boolean canHandle(String msg);
  abstract void process(String msg);
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

```
Pattern Purpose 1-liner)
Observer Notify multiple objects about state changes
Strategy Swap algorithms/behaviors at runtime
Command Encapsulate a request/action
Chain of Resp. Pass request along a chain of handlers
```

```
State Object behavior changes based on internal state
Mediator Central controller handles communication
Iterator Traverse collections without exposing structure
Template Method Define algorithm skeleton with custom steps
Visitor Add new operations to object structure
Memento Capture and restore object state
```

## 🧾 Summary Chart

```
Topic Meaning Example
Interface Segregation
ISP
```

```
Don't force classes to implement
unused methods
```

```
Split Printable,
Scannable
Dependency
Inversion DIP
```

```
High-level code should depend on
interface, not concrete class
```

```
Use Switchable not
LightBulb
Inversion of Control
IoC Let external code supply dependencies Pass Engine to Car
```

```
Feature Factory Method Abstract Factory
```

```
Goal
Create one object based on
input Create families of related objects
```

```
Example ShapeFactory.createShape("circle") UIFactory.createButton()createCheckbox() +
```

```
Number of Products One product at a time
```

```
Multiple related products But ton Checkbox, etc.)
```

```
Extensibility Easy to add new shape types Easy to add ne(e.g., Linux UIw product families
```

### 🔄 When to Prefer Composition

```
Prefer Composition When... Why
You want to reuse logic across unrelated
classes
```

```
Inheritance doesnʼt allow cross-hierarchy
reuse
You need runtime behavior changes You can swap out components
```

```
You want to follow SOLID principles EspeciallResponsibility Open/Closed and Singley
```

```
You want loose coupling Easier to test and maintain
```

### 📑 Summary Chart

```
Inheritance Composition
"Is-A" relationship "Has-A" relationship
Static behavior Dynamic behavior (swap at runtime)
Tight coupling Loose coupling
Code reuse through parent Code reuse through delegation
Hard to change hierarchy Easy to plug-and-play parts
```

```java
interface SoundBehavior {
  void makeSound();
}

class Bark implements SoundBehavior {
  public void makeSound() {
    System.out.println("Bark");
  }
}

class Howl implements SoundBehavior {
  public void makeSound() {
    System.out.println("Howl");
  }
}

class Dog {
  SoundBehavior behavior;
  public Dog(SoundBehavior behavior) {
    this.behavior = behavior;
  }
  public void makeSound() {
    behavior.makeSound();
  }
  public void setBehavior(SoundBehavior b) {
    this.behavior = b;
  }
}
```

❌ **Wrong Interpretation** ✅ **Correct Interpretation**

“If it’s on 5 servers, it’s
distributed”

```
Itʼ s only distributed if those servers communicate
over a network to coordinate different parts of the
system
```

“Scaling a monolith to 10 VMs =
distributed”

```
Nope — youʼre just cloning the same app; there's no
distributed logic
```

“Running microservices on a
single server = not distributed”

```
Still distributed , because each service is
autonomous and talks over network (e.g. HTTP,
gRPC
```

“Only microservices =
distributed”

```
Even replicated databases , cache clusters, or event
buses = distributed
```

System Is it
Distributed?
Why?

Single Java monolith scaled to 5
servers ❌ No

```
Just replicated — no inter-node
communication
```

Same monolith, but each server
has own DB
✅ Yes Now state is distributed  CAP
applies

Microservices on 1 machine ✅ Yes Separate componentcommunicating via netswork

Redis Cluster with 3 shards ✅ Yes Coordination needed between
Redis nodes

Kafka brokers (even on 1
machine) ✅ Yes

```
Multiple brokers coordinating topic
partitions
```

Topic Key Idea Tools / Solutions

Authentication Identity verification Sessions, Tokens, MFA

Authorization Access control RBAC, ABAC

OAuth Delegate user access via provider Google, Facebook Login

JWT Stateless auth with encoded claims JWT librarJWT, JWT.io)ies (e.g., Java

Secure
Transmission Encrypt communication HTTPS, TLS, SSL

SQL Injection Prevent SQL command injection Prepared statements, ORM

XSS Prevent script injection in browser Escaping, CSP

CSRF Prevent request forgery CSRF tcookiesokens, SameSite

General Best
Practices

```
Secure headers, logging,
monitoring, rate-limiting
```

```
Helmet Node.js), Spring
Security, etc.
```

Concept Key Idea Tools / Examples

Load Balancing Distribute traffic evenly & avoidbottlenecks Nginx, HAProxy, AWS ELB

Health Checks Remove unhealthy servers Liveness/readiness probes

Disaster
Recovery

```
Plan for outages & regional
failures
```

```
Pilot light, warm standby, active-
active
```

Data Replication Copy data across nodes favailability or Master-slave, mastasync/sync er-master,

Backup
Snapshot + versioned data for
rollback AWS S3, Glacier, RDS snapshots

Auto-Failover Switch to standby systems onfailure DNS failover, Kubernetesreadiness probes