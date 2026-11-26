# Todo List Web Application

Учебный проект: веб-приложение «Планировщик дел» на Spring MVC. Создаётся по урокам курса Code Kitchen по Spring и развивается от урока к уроку.

## Стек

- Java
- Maven (web-архетип, упаковка WAR)
- Spring MVC
- Servlet API
- Apache Tomcat (запуск через плагин Smart Tomcat в IntelliJ IDEA)
- JSP + JSTL (страницы-представления)

## Что уже реализовано

- Конфигурация на Java-коде, без `web.xml`: `WebConfig` (`@Configuration`, `@ComponentScan`, `@EnableWebMvc`, `ViewResolver`, обработчик статики `/resources/**`) и `ApplicationInitializer` (регистрирует `DispatcherServlet` на `/`).
- Слои Controller → Service → DAO. Данные хранятся в памяти (список в `RecordDao`) и сбрасываются при перезапуске.
- Главная страница на JSP + JSTL: список задач, статистика (сколько активных и выполненных), сообщения для пустого списка.
- Добавление, выполнение и удаление задач через POST-формы с `redirect:/home`.
- Фильтр All / Active / Done через параметр `?filter=` (регистр не важен, при неверном значении показываются все задачи). При выполнении и удалении задачи фильтр сохраняется.

## Структура проекта

```
src/main/java/kz/mortcode/config      — WebConfig, ApplicationInitializer
src/main/java/kz/mortcode/controller  — CommonController
src/main/java/kz/mortcode/service     — RecordService
src/main/java/kz/mortcode/dao         — RecordDao
src/main/java/kz/mortcode/entity      — Record, RecordStatus
src/main/java/kz/mortcode/dto         — RecordsContainerDto
src/main/webapp/WEB-INF/views         — main-page.jsp
src/main/webapp/resources/css         — main-page.css
```

## Как запустить

1. Склонировать репозиторий и открыть его в IntelliJ IDEA.
2. Дождаться загрузки Maven-зависимостей.
3. Создать конфигурацию запуска Smart Tomcat: указать папку установленного Tomcat и порт `8080`.
4. Запустить приложение и открыть в браузере `http://localhost:8080/home`.

## Как это работает

Запрос принимает Tomcat и передаёт его в `DispatcherServlet` (Front Controller). Тот находит метод контроллера по `@RequestMapping`, контроллер вызывает сервис, а сервис — DAO. `GET /home` кладёт в `Model` записи и статистику и возвращает `main-page`. `ViewResolver` собирает путь к JSP, и JSTL отрисовывает страницу на сервере. POST-запросы (`/add-record`, `/make-record-done`, `/delete-record`) меняют данные и отвечают `redirect:/home`.

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
- Post/Redirect/Get: после POST делаем redirect, чтобы F5 не повторял действие (иначе дубликаты)
- `counterSequence++` и `ArrayList` не потокобезопасны. Singleton-бины в Spring общие для всех потоков Tomcat, лечится `AtomicInteger` и потокобезопасными коллекциями

## Урок 10
- DTO хранит только данные для страницы (`RecordsContainerDto`: записи + статистика), entity повторяет структуру хранилища. Лежат в разных пакетах: `dto` и `entity`
- Бизнес-логика (фильтрация, подсчёт статистики) живёт в сервисе, не в контроллере. Статистика считается по всем записям до фильтра
- `@RequestParam(required = false)` делает параметр необязательным, иначе его отсутствие даёт 400
- `${...}` в JSP не экранирует HTML, это путь к XSS. Пользовательский текст выводим через `<c:out>` или `fn:escapeXml`