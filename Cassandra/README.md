# Apache Cassandra

Краткое описание:
Apache Cassandra — это распределенная NoSQL база данных, оптимизированная для высокой доступности, горизонтального масштабирования и работы с большими объемами данных без единой точки отказа. В этом проекте Cassandra интегрирована через Spring Boot Data Cassandra для хранения и чтения данных пользователей.

## Интеграция Cassandra в проекте

### 1) Зависимость Maven
В `pom.xml` подключен Spring Data Cassandra:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-cassandra</artifactId>
</dependency>
```

### 2) Конфигурация подключения
В `src/main/resources/application.yml` настроены параметры подключения:

```yaml
spring:
  cassandra:
    contact-points: localhost
    keyspace-name: test_keyspace
    schema-action: create_if_not_exists
    local-datacenter: datacenter1
    port: 9042
```

### 3) Модель (таблица)
Сущность `User` связана с таблицей `users`:

```java
@Table("users")
public class User {
    @PrimaryKey
    private String id;
    private String name;
    private Integer age;
}
```

Файл: `src/main/java/org/khasanof/domain/User.java`

### 4) Репозиторий для CRUD
Репозиторий использует `CassandraRepository`:

```java
@Repository
public interface UserRepository extends CassandraRepository<User, String> {
}
```

Файл: `src/main/java/org/khasanof/repository/UserRepository.java`

### 5) Локальный запуск Cassandra через Docker
Для локальной разработки есть docker-compose файл:

`src/main/docker/cassandra.yml`

```yaml
services:
  cassandra:
    image: cassandra:4.1
    ports:
      - "9042:9042"
```

## Мини-пример использования репозитория в сервисе

```java
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(String id, String name, Integer age) {
        return userRepository.save(new User(id, name, age));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
```