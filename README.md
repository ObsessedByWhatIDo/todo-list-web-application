# Todo List Web Application

Учебный проект: веб-приложение «Планировщик дел» на Spring MVC. Создаётся по урокам курса Code Kitchen по Spring и развивается от урока к уроку.

## Стек

- Java
- Maven (web-архетип, упаковка WAR)
- Spring MVC
- Servlet API
- Apache Tomcat (запуск через плагин Smart Tomcat в IntelliJ IDEA)
- JSP (страницы-представления)


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
- counterSequence++ и ArrayList не потокобезопасны. Singleton-бины в Spring общие для всех потоков Tomcat, лечится AtomicInteger и потокобезопасными коллекциями