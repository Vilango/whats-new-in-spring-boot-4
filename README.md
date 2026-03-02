# FancyStore — What's New in Spring Boot 4

A demo project for exploring the new features in Spring Boot 4. It consists of two microservices:

- **`product`** — exposes a pricing endpoint (`GET /api/products/{id}?quantity=N`), runs on port 8081
- **`order`** — accepts order placement requests (`POST /api/{version}/orders`), calls the product service to calculate the total price

The services are intentionally simple so the focus stays on the Spring Boot 4 features being introduced at each step.

---

## Code-Along Steps

Check out each tag to follow along. Every step builds on the previous one.

| # | Tag | What's introduced                                                                                                                                                 |
|---|-----|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1 | `baseline` | Starting point — two plain Spring Boot apps with a hardcoded product price. `order` does not call `product` yet.                                                  |
| 2 | `introduce_restclient` | Introduce modern `RestClient`.                                                                                                   |
| 3 | `introduce_httpservice_programmatic` | Introduce the HTTP Interface (`ProductHttpService`) and register it programmatically using a `RestClient`-backed proxy.                                           |
| 4 | `simplify_httpservice` | Simplify the HTTP Interface setup using Spring Boot 4's `@ImportHttpServices` and auto-configuration via `spring.http.serviceclient.<group>.base-url`.            |
| 5 | `api_versioning_and_deprecation` | Add native API versioning to `OrderController` using `@PostMapping(version = "...")` and configure deprecation/sunset dates via `WebMvcConfigurer`.               |
| 6 | `introduce_observability` | Add OpenTelemetry support (traces, metrics, logs) using the new `spring-boot-starter-opentelemetry`. Wire Logback into OTel via `InstallOpenTelemetryLogAppender`. |
| 7 | `seed_products_jackson3` | Load product data from a JSON file at startup using Jackson 3 (bundled with Spring Boot 4 — note the new `tools.jackson` package name).                           |
| 8 | `null_safety` | Enable compile-time null safety with NullAway + ErrorProne. Annotate all packages with `@NullMarked` (JSpecify) and enforce the convention with an ArchUnit test. |
| 9 | `rfc9457_problem_details` | Return structured error responses using RFC 9457 Problem Details (`ProblemDetail`) via `@RestControllerAdvice` and `server.mvc.problem-details.enabled`.          |
| 10 | `performance_virtual_threads` | Enable Java virtual threads with `spring.threads.virtual.enabled: true` to handle concurrent requests without blocking platform threads.                          |
| 11 | `resilience` | Add `@EnableResilientMethods` and annotate `calculateTotalPrice` with `@Retryable` (exponential backoff) and optionally `@ConcurrencyLimit`.                      |
