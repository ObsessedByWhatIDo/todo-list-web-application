# Todo List Web Application

Учебный проект: веб-приложение «Планировщик дел» на Spring MVC. Создаётся по урокам курса Code Kitchen по Spring и развивается от урока к уроку.

## Стек

- Java
- Maven (web-архетип, упаковка WAR)
- Spring MVC
- Servlet API
- Apache Tomcat (запуск через плагин Smart Tomcat в IntelliJ IDEA)
- JSP (страницы-представления)

## Что уже реализовано

- Конфигурация на Java-коде, без `web.xml`:
  - `WebConfig` — `@Configuration`, `@ComponentScan`, `@EnableWebMvc`, реализует `WebMvcConfigurer`. Содержит бин `ViewResolver` (префикс `/WEB-INF/views/`, суффикс `.jsp`).
  - `ApplicationInitializer` — реализует `WebApplicationInitializer`: создаёт `ApplicationContext` и регистрирует `DispatcherServlet` на путь `/`.
- Контроллер на `@Controller` с методом на `@GetMapping`, который возвращает имя страницы.
- Главная страница `main-page.jsp`.

## Структура проекта

```
src/main/java/.../config       — WebConfig, ApplicationInitializer
src/main/java/.../controller   — контроллеры
src/main/webapp/WEB-INF/views  — JSP-страницы
```

## Как запустить

1. Склонировать репозиторий и открыть его в IntelliJ IDEA.
2. Дождаться загрузки Maven-зависимостей.
3. Создать конфигурацию запуска Smart Tomcat: указать папку установленного Tomcat и порт `8080`.
4. Запустить приложение и открыть в браузере `http://localhost:8080/home`.

## Как это работает

Запрос принимает Tomcat и передаёт его в `DispatcherServlet` (шаблон Front Controller). Тот находит контроллер, чей `@GetMapping` совпадает с адресом, метод возвращает имя страницы, `ViewResolver` собирает путь к JSP, и страница уходит в браузер.

## Урок 8
- Статические ресурсы: `addResourceHandler("/resources/**")` в WebConfig, без него 404 на CSS
- Слои Controller → Service → DAO. `@Service` и `@Repository` это `@Component` с меткой
- DAO отдаёт копию списка, но поверхностную: `setStatus` меняет оригинал
- `Model` → `${ключ}` в JSP подставляется на сервере при каждом запросе

## Урок 9
- JSTL: `<c:forEach items="${records}" var="record">`, `<c:if test>`, `<c:choose>` с `<c:when>` и `<c:otherwise>`. Нужны зависимость в pom.xml и `taglib` в начале JSP
- `@RequestParam` связывает query-параметр или поле формы с аргументом метода. Имена совпадают, иначе `name = "..."`
- Формы, меняющие состояние, отправляем POST. Какую запись менять, передаём через `<input type="hidden" name="id">`
- После POST делаем `redirect:/home`: адрес не меняется, логика отрисовки остаётся в одном методе
- Записи идентифицируем по уникальному `id`, а не по `title`: дубликаты ломали и «выполнить», и «удалить»