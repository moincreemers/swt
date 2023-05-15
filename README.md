# Singular Web Toolkit

## What is SWT?

SWT is a Java-to-Javascript compiler (more or less).

Please see: rationale.md for the rationale behind SWT.

## What can it do?

Assuming you are using a Java Spring-Boot tech-stack, SWT will be able to generate fully functional UI from Java code. 

## Run the demo

1. Clone this project (which you've already done)
2. Run the project:
```
$ mvn spring-boot:run
```
3. Navigate to http://localhost:8000
4. "ExamplePage" should open 

## Getting started

1. Start a Spring-Boot project
2. Add a dependency to SWT.
3. Create a Spring Controller like so:
```java
@Controller
public class MyApplicationController {
    final Toolkit toolkit;

    public MyApplicationController(Toolkit toolkit) {
        this.toolkit = toolkit;
    }

    @GetMapping("/")
    public void serveHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {
        toolkit.render(request, response);
    }
}
```
3. Create a first page like so:
```java
@Component
public class HomePage extends Page {
    @Override
    protected void build() throws Exception {        
        add(new HtmlHeading("The Home Page"));
    }
}
```
4. Run the application. With Maven, simply run: 
```
$ mvn spring-boot:run
```
5. Navigate to http://localhost:8000
6. Congratulations, you've created your first SWT application