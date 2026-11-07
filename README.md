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
