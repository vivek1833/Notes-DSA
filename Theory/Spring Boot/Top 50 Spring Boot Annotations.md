### `@SpringBootApplication`

**Explanation:** Marks the main class of a Spring Boot application and combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

```java
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

---

### `@RestController`

**Explanation:** Indicates that a class is a RESTful controller, combining `@Controller` and `@ResponseBody`.

```java
@RestController
public class UserController {
    @GetMapping("/ping")
    public String ping() {
        return "OK";
    }
}
```

---

### `@RequestMapping`

**Explanation:** Maps web requests to specific handler methods, can be applied at the class or method level.

```java
@RequestMapping("/users")
public class UserController {
}
```

---

### `@GetMapping`

**Explanation:** Shortcut for `@RequestMapping(method = RequestMethod.GET)`, maps HTTP GET requests to handler methods.

```java
@GetMapping("/users/{id}")
public String get(@PathVariable int id) {
    return "User " + id;
}
```

---

### `@PostMapping`

**Explanation:** Shortcut for `@RequestMapping(method = RequestMethod.POST)`, maps HTTP POST requests to handler methods.

```java
@PostMapping("/users")
public String create(@RequestBody String body) {
    return "Created";
}
```

---

### `@PutMapping`

**Explanation:** Shortcut for `@RequestMapping(method = RequestMethod.PUT)`, maps HTTP PUT requests to handler methods.

```java
@PutMapping("/users/{id}")
public String update(@PathVariable int id) {
    return "Updated";
}
```

---

### `@DeleteMapping`

**Explanation:** Shortcut for `@RequestMapping(method = RequestMethod.DELETE)`, maps HTTP DELETE requests to handler methods.

```java
@DeleteMapping("/users/{id}")
public String delete(@PathVariable int id) {
    return "Deleted";
}
```

---

### `@PatchMapping`

**Explanation:** Shortcut for `@RequestMapping(method = RequestMethod.PATCH)`, maps HTTP PATCH requests to handler methods.

```java
@PatchMapping("/users/{id}")
public String patch(@PathVariable int id) {
    return "Patched";
}
```

---

### `@Autowired`

**Explanation:** Automatically wires dependencies in Spring beans, can be applied to fields, constructors, or methods.

```java
@Autowired
private UserService userService;
```

---

### `@Component`

**Explanation:** Marks a Java class as a Spring bean.

```java
@Component
public class EmailClient {
}
```

---

### `@Service`

**Explanation:** Indicates that a class contains business logic, a specialization of `@Component`.

```java
@Service
public class UserService {
}
```

---

### `@Repository`

**Explanation:** Indicates that a class is a data access object (DAO), a specialization of `@Component`.

```java
@Repository
public class UserRepository {
}
```

---

### `@Controller`

**Explanation:** Marks a class as a web controller in a Spring MVC application.

```java
@Controller
public class PageController {
}
```

---

### `@RequestBody`

**Explanation:** Maps the HTTP request body to a method parameter in a controller.

```java
@PostMapping("/login")
public String login(@RequestBody LoginRequest req) {
    return "OK";
}
```

---

### `@ResponseBody`

**Explanation:** Maps the return value of a method to the HTTP response body.

```java
@ResponseBody
@GetMapping("/raw")
public String raw() {
    return "data";
}
```

---

### `@PathVariable`

**Explanation:** Binds a method parameter to a URI template variable.

```java
@GetMapping("/users/{id}")
public int id(@PathVariable int id) {
    return id;
}
```

---

### `@RequestParam`

**Explanation:** Binds a method parameter to a web request parameter.

```java
@GetMapping("/search")
public String search(@RequestParam String q) {
    return q;
}
```

---

### `@RequestHeader`

**Explanation:** Binds a method parameter to a web request header.

```java
@GetMapping("/agent")
public String agent(@RequestHeader("User-Agent") String ua) {
    return ua;
}
```

---

### `@CookieValue`

**Explanation:** Binds a method parameter to a cookie value.

```java
@GetMapping("/cookie")
public String cookie(@CookieValue("token") String token) {
    return token;
}
```

---

### `@ModelAttribute`

**Explanation:** Binds a method parameter or method return value to a named model attribute and exposes it to a web view.

```java
@PostMapping("/form")
public String submit(@ModelAttribute User user) {
    return "ok";
}
```

---

### `@SessionAttributes`

**Explanation:** Indicates the names of model attributes that should be stored in the session.

```java
@SessionAttributes("user")
@Controller
public class SessionController {
}
```

---

### `@ExceptionHandler`

**Explanation:** Indicates the method to be invoked when an exception is thrown.

```java
@ExceptionHandler(RuntimeException.class)
public String handle() {
    return "error";
}
```

---

### `@ControllerAdvice`

**Explanation:** Allows the handling of exceptions across the whole application in a single global handler.

```java
@ControllerAdvice
public class GlobalHandler {
}
```

---

### `@CrossOrigin`

**Explanation:** Enables Cross-Origin Resource Sharing (CORS) on a method or class.

```java
@CrossOrigin(origins = "*")
@GetMapping("/public")
public String open() {
    return "open";
}
```

---

### `@Configuration`

**Explanation:** Declares a class as a configuration class, typically used with `@Bean` methods.

```java
@Configuration
public class AppConfig {
}
```

---

### `@Bean`

**Explanation:** Indicates that a method produces a bean to be managed by the Spring container.

```java
@Bean
public ObjectMapper mapper() {
    return new ObjectMapper();
}
```

---

### `@Primary`

**Explanation:** Indicates that a bean should be given preference when multiple candidates are qualified to autowire a single-valued dependency.

```java
@Primary
@Bean
public DataSource mainDs() {
    return new HikariDataSource();
}
```

---

### `@Value`

**Explanation:** Injects values from properties files or other sources into Spring beans.

```java
@Value("${server.port}")
private int port;
```

---

### `@PropertySource`

**Explanation:** Provides a convenient and declarative mechanism for adding a set of PropertySources to Spring’s Environment.

```java
@PropertySource("classpath:custom.properties")
@Configuration
public class PropConfig {
}
```

---

### `@EnableAutoConfiguration`

**Explanation:** Enables Spring Boot’s auto-configuration mechanism.

```java
@EnableAutoConfiguration
public class AutoConfig {
}
```

---

### `@Conditional`

**Explanation:** Conditionally includes or excludes beans based on a condition.

```java
@Conditional(MyCondition.class)
@Bean
public Feature feature() {
    return new Feature();
}
```

---

### `@Profile`

**Explanation:** Specifies the profiles a bean is eligible for registration in, controlling which beans are loaded in which environments.

```java
@Profile("dev")
@Bean
public DataSource devDs() {
    return new HikariDataSource();
}
```

---

### `@Scope`

**Explanation:** Configures the scope of a bean, such as singleton, prototype, request, session, etc.

```java
@Scope("prototype")
@Component
public class Task {
}
```

---

### `@Lazy`

**Explanation:** Delays the initialization of a bean until it is first requested.

```java
@Lazy
@Component
public class HeavyBean {
}
```

---

### `@Async`

**Explanation:** Indicates that a method should be executed asynchronously.

```java
@Async
public void sendEmail() {
}
```

---

### `@Scheduled`

**Explanation:** Schedules a method to be run at fixed intervals.

```java
@Scheduled(fixedRate = 5000)
public void run() {
}
```

---

### `@EnableScheduling`

**Explanation:** Enables Spring’s scheduled task execution capability.

```java
@EnableScheduling
@Configuration
public class SchedulerConfig {
}
```

---

### `@Transactional`

**Explanation:** Demarcates transactional boundaries on a method or class.

```java
@Transactional
public void save() {
}
```

---

### `@EnableTransactionManagement`

**Explanation:** Enables Spring’s annotation-driven transaction management.

```java
@EnableTransactionManagement
@Configuration
public class TxConfig {
}
```

---

### `@Entity`

**Explanation:** Specifies that a class is an entity and is mapped to a database table.

```java
@Entity
public class User {
}
```

---

### `@Table`

**Explanation:** Specifies the primary table for the annotated entity.

```java
@Table(name = "users")
@Entity
public class User {
}
```

---

### `@Id`

**Explanation:** Specifies the primary key of an entity.

```java
@Id
private Long id;
```

---

### `@GeneratedValue`

**Explanation:** Specifies the generation strategy for the primary key values.

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

---

### `@Column`

**Explanation:** Specifies the mapped column for a persistent property or field.

```java
@Column(nullable = false)
private String name;
```

---

### `@OneToMany`, `@ManyToOne`, `@OneToOne`, `@ManyToMany`

**Explanation:** Defines various types of relationships between entities.

```java
@ManyToOne
private User user;
```

---

### `@JoinColumn`

**Explanation:** Specifies the foreign key column for a relationship.

```java
@JoinColumn(name = "user_id")
```

---

### `@JsonIgnoreProperties`

**Explanation:** Specifies properties to ignore during JSON serialization and deserialization.

```java
@JsonIgnoreProperties({"password"})
public class User {
}
```

---

### `@JsonProperty`

**Explanation:** Specifies the property name to be used during JSON serialization and deserialization.

```java
@JsonProperty("user_name")
private String username;
```

---

### `@SpringBootTest`

**Explanation:** Used to bootstrap the entire container and start the full Spring context for integration tests.

```java
@SpringBootTest
class AppTest {
}
```

---

### `@Test`

**Explanation:** Marks a method as a test method in a JUnit test class.

```java
@Test
void test() {
    assertTrue(true);
}
```
