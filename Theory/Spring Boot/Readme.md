# Spring Annotations

### 📦 **Spring Core / Dependency Injection**

| Annotation | Purpose |
| --- | --- |
| `@Component` | Marks a class as a Spring bean (generic component). |
| `@Service` | Specialization of `@Component`, used for business logic/service layer. |
| `@Repository` | Specialization of `@Component`, used for DAO layer. Enables exception translation. |
| `@Autowired` | Injects a dependency (field, constructor, or setter). |
| `@Qualifier` | Resolves ambiguity when multiple beans of the same type exist. |
| `@Primary` | Marks a bean as the default one when multiple candidates exist. |
| `@Value("${key}")` | Injects a value from application properties or environment variables. |

---

### 🌐 **Spring MVC (Web Layer)**

| Annotation | Purpose |
| --- | --- |
| `@RestController` | Combines `@Controller` and `@ResponseBody` — used for REST APIs. |
| `@RequestMapping` | Maps HTTP requests to handler methods (all HTTP methods). |
| `@GetMapping`, `@PostMapping`, etc. | Shortcut for `@RequestMapping(method = ...)`. |
| `@RequestParam` | Binds query parameters (`?key=value`) to method arguments. |
| `@PathVariable` | Binds URI template variables (e.g., `/user/{id}`) to method arguments. |
| `@RequestBody` | Binds incoming JSON to a Java object. |
| `@ResponseBody` | Sends return value as JSON (used with `@Controller`). |

---

### 🔐 **Validation (JSR-380 + Hibernate Validator)**

| Annotation | Purpose |
| --- | --- |
| `@Valid` | Triggers validation on request body or nested objects. |
| `@NotNull` | Field must not be `null`. |
| `@NotBlank` | For Strings: must not be `null` or whitespace. |
| `@NotEmpty` | Must not be empty (works for collections, strings). |
| `@Size(min, max)` | Enforces size/length constraints. |
| `@Pattern(regexp = "...")` | Validates format using regex. |
| `@Min`, `@Max` | Validates numeric min/max values. |

---

### 💥 **Exception Handling**

| Annotation | Purpose |
| --- | --- |
| `@ControllerAdvice` | Global exception handling for controllers. |
| `@ExceptionHandler(SomeException.class)` | Handles a specific exception in a controller or globally. |
| `@ResponseStatus(HttpStatus.NOT_FOUND)` | Custom status on exception classes. |

---

### ⚙️ **Spring Boot & Config**

| Annotation | Purpose |
| --- | --- |
| `@SpringBootApplication` | Meta-annotation combining `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`. |
| `@Configuration` | Marks a class as a source of bean definitions. |
| `@Bean` | Defines a bean method inside a `@Configuration` class. |
| `@EnableConfigurationProperties` | Binds configuration properties to POJOs. |
| `@PropertySource` | Loads properties file manually. |

---

### 🛡️ **Resilience / Fault Tolerance (via Resilience4j)**

| Annotation | Purpose |
| --- | --- |
| `@Retryable` | Automatically retries a failed method. |
| `@CircuitBreaker` | Opens the circuit if failure rate is high. |
| `@RateLimiter` | Limits request rate per method/user. |
| `@Bulkhead` | Limits concurrent calls to a method (thread pool or semaphore-based). |
| `@TimeLimiter` | Enforces a time limit on method execution. |
| `@Recover` | Fallback method when all retries fail. |

---

### 🧪 **Testing Annotations**

| Annotation | Purpose |
| --- | --- |
| `@SpringBootTest` | Loads full application context for integration tests. |
| `@WebMvcTest` | Loads only web layer beans for controller tests. |
| `@MockBean` | Injects a mock into Spring’s application context. |
| `@DataJpaTest` | Loads only JPA repositories and config for DB testing. |
