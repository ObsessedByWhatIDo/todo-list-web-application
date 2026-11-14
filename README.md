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


git
## Урок 8. Статические ресурсы, три уровня, @Repository, @Service, Model
- Статические ресурсы лежат в `webapp/resources`, подключаются в `WebConfig` через `addResourceHandler("/resources/**")`. Без этого `DispatcherServlet` отвечает 404 на запрос CSS
- Три уровня: Controller (представление), Service (бизнес-логика), DAO/Repository (доступ к данным). Сервис не знает, где и как хранятся данные
- `@Repository` и `@Service` это `@Component` со смысловой пометкой. У `@Repository` ещё перевод исключений БД
- DAO возвращает копию списка, а не оригинал. Копия поверхностная: `setStatus` у элемента меняет и оригинал
- `Model` это Map атрибутов. Контроллер кладёт значения через `addAttribute`, JSP подставляет их через `${ключ}` на сервере при каждом запросе

### HTML и CSS в этом проекте
- У радиокнопок общий `name` создаёт группу и становится ключом в запросе (`?filter=done`), `value` значением
- Форма без `action` и `method` отправляет GET на тот же адрес
- Имена классов в HTML и CSS должны совпадать до символа (БЭМ: `блок__элемент_модификатор`)
- Flexbox: `justify-content` по главной оси, `align-items` по поперечной
- Побеждает более конкретный селектор, порядок в файле решает при равенстве: id > класс и псевдокласс > тег
- Отладка стилей: F12 → Elements → Styles