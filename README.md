# Singular Web Toolkit

## What is SWT?

SWT is a Java-to-Javascript compiler.

Please see: rationale.md for the rationale behind SWT.

## What can it do?

Assuming you are using a Java Spring-Boot tech-stack, SWT will be able to generate fully functional UI from Java code.

## Run the demo

**To run the main demo:**

1. Clone this project (which you've already done)
2. Open Constants.java and make MainDemoPage the default page, like so:

```java
DEMO = MainDemoPage.class;
```

3. Run the project using the plugin in your IDE or terminal:

```
$ mvn spring-boot:run
```

5. Navigate to http://localhost:8000

**To run the forView demo:**

1. Clone this project (which you've already done)
2. Clone xds-dc-all-in-one and run it
3. Because ForView currently does not provide CORS HTTP headers, we need to open Chrome with web security disabled. On
   macOs this is done using the following command:

```
$ open -na Google\ Chrome --args --user-data-dir=/tmp/temporary-chrome-profile-dir --disable-web-security
```

_WARNING: This opens a new instance of Chrome that you should only use for this demo. Under no circumstance should you
use a Browser with web security disabled to browse the Internet. You can have another normal instance running at the
same time._

4. Open Constants.java and make LoginPage the default page, like so:

```java
DEMO = LoginPage.class;
```

5. Run the project using the plugin in your IDE or terminal:

```
$ mvn spring-boot:run
```

6. Navigate to http://localhost:8000

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
   public HomePage() {
      super(true);
   }

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

## Known issues

- When re-compiling the project while the Spring-Boot development server is running the web server will reload the
  project files automatically because spring-boot-devtools has been configured. However, this may occasionally result in
  an error. The error is always a missing page reference. In some cases the plugin automatically re-compiles again
  without errors. In most cases however, manually re-compiling is needed. The reason this happens is that the
  ToolkitController component does not receive all pages from Spring-Boot. Why this happens is not known.

## Author

Mo'in Creemers
https://www.linkedin.com/in/moincreemers/