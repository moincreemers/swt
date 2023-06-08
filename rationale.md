# Singular UI Toolkit - Rationale

## Summary

Singular is a Web UI framework created by and designed for Java Spring-Boot developers.

The goal of Singular is to provide software engineers that use a Java Spring-Boot stack with a tool to quickly and
easily create JS clients for their applications. While there are many Web UI frameworks to choose from. Singular has a
much smaller impact on aspects such as: competence management, dependency management, development cost, maintenance, 
information security and others.

Many organizations need to build and maintain applications specific to their internal business processes or to provide
to their customers. The services for those applications are often created using Java. However, creating great UI
requires different skills such as HTML, Javascript and CSS. The reality is that this work is far from cheap. It often
accounts for at least 50% of the development budget if not more.

Obviously, Java developers could do this work as well but many organizations will find they need to hire people with
these specific skills or outsource the work. In response to this, some 'low-code' application frameworks and platforms
were created such as Mendix, Thinkwise, Creatio and many others. And these are gaining popularity. They also introduce a
completely new tech-stack, resulting in lock-in.
Of course, using Singular also means the application cannot be simply moved to another technology but Singular is
specifically meant for teams that already use Java Spring-Boot which is not a short-term decision. All low-code
platforms move away from Java software development entirely.

Singular is meant to minimize the skills needed to create Web applications without resorting to a completely different
tech-stack and removes the need to outsource UI specific development work entirely.

In contrast to the leading Web UI frameworks, Singular is designed to avoid or resolve specific issues that have
significant impact on the cost of creation and maintenance of Web applications.

## Audience

Singular is a web UI framework primarily designed for organizations. While it can be used to run Internet facing
applications, its intended purpose is to drive creation of internal business applications and keep those running while
keeping maintenance cost at a minimum and extending the life of the applications.

Singular is designed for use by Java developers.

## Business requirements

Businesses are not like consumers and the real-world requirements for the creation of business applications are
very different. We believe that the leading Web UI frameworks or libraries do not address these requirements or do not
address them in a way that makes sense.

### Cost

The cost of initial development and maintenance over the lifetime of the application is a critical factor. The total
cost of ownership of any business application is usually 20-80 meaning that 80% of the cost goes into maintenance.

We believe that the traditional Web UI frameworks fail to provide a strategy for managing cost.

Given that Java development capacity is already available but the organization would like to drastically improve
the cost of development and maintenance leads to the logical conclusion that parts of the application development work
must be automated.

Singular requires no HTML, Javascript or CSS coding.

### Target audience

Business applications are used internally by the organization that creates them or they are provided to customers.
Applications are not typically Internet facing and all users are authenticated using a single-sign-on mechanism.

While typical tech-stacks are probably great for consumer facing web applications, the requirements on the competencies
needed by the development teams is considerable. Java developers are not typically involved with UI development and when
they are, the UI is not always all that well made. Furthermore, testers need to use more advanced tools to verify the
work and finally, the use of (for example) Angular and Bootstrap often results in the use of other tools such as a
Javascript repository.

We believe that Angular and Bootstrap-like technologies are actually unsuitable for the creation of business
applications.

Singular brings the ability to create great Web applications back to Java development teams without the
need to learn a Web UI framework and the various tools and dependencies that come with that while still resulting in
good looking applications that just work.

### Functionality

First and foremost, applications need to be functional.

The strength of traditional Web UI frameworks is the ability to create any UI which makes sense when creating a tool for
everyone. However, this is not necessarily what organizations need, most business applications are relatively simple.

Singular reduces the number of features to a very specific set (See below) of building blocks.

### Look and feel

Creating applications that look nice is obviously a good thing but the reality is that this is rarely a requirement that
businesses are willing to pay for. In fact, many business applications look 'terrible' compared to most applications
made for consumers.

User experience is important but only becomes an issue when the application suffers a reduction in functionality that
the business cares about, not the users of the application.

Arguably, tools such as Bootstrap do improve the way Web applications look and raise the bar substantially. However,
these are intended for publicly facing, consumer Web application, not business applications and mostly impact look and
feel. At the same time, most businesses do not actually care about pixel perfect accuracy.

Singular offers a look and feel that is basic yet functional. There is no fancy 'Accordeon' widget. However, for the use
cases that Singular was intended for, these type of widgets offer very little value.

### Change in response to business requirements

Changing the application must be easy, fast and without degradation of other aspects of the application such as
information security, privacy or stability.

This is a top priority. Businesses change how they work all the time, not because they want to but because of changing
market conditions or changes in legislation for example.

The tools that support their business processes must follow those changes and they must follow rapidly. Arguably, this
is where the traditional tech-stacks fail.

A typical scenario is that an application is created and then runs for a while. Then the original team of developers
moves on. When it is time to add new functionality or modify existing ones, new developers need to acquaint themselves
with the code. Documentation is typically sparse or non-existent.

When the business uses an agency to do the development work, the problems may be outsourced as well but usually requires
a support contract with the agency. While the 'feature request' may be a relatively small task, this means it soon turns
into a fully-fledged project.

Singular addresses this by keeping the initial development work as simple and concise as technically possible. Producing
a single 'view' (i.e., a web page) is done in a single Java class. Nothing more, nothing less. Modifying this code later
is equally simple.

### Quality and consistency

A high quality standard is expected as well as a reasonable level of uniformity across the application landscape.

All applications created with Singular look and feel similar because there are not that many choices available.
And because the application is generated, the quality is excellent and stays that way.

### Information security

All software needs to be highly secure. This is especially true for regulated businesses. The inflow of security related
vulnerabilities is a business risk that is unpredictable and can cause serious issues.

Singular requires a very select number of dependencies that are already available when using Spring-Boot and requires
no third party Javascript dependencies substantially reducing the attack-surface. While Java developers are often
careful when reusing third party libraries, many front-end developers reuse Javascript libraries indiscriminately,
typically resulting in hundreds to thousands (!) of dependencies that are simply impossible to manage manually.

We assume that applications will be penetration-tested on a regular basis and the production standards of the
organization are audited. Security vulnerabilities can cause serious operational delays and other issues, not to mention
the additional administrative work. For this reason, limiting the potential for security vulnerabilities can reduce cost
and improve trust.

Singular aids with these processes since the code for the Web applications are not coded by developers but they are
generated and the building blocks used are always the same.
For example: a file upload feature in one application is no different than a file upload feature in another because
Singular provides only one file upload 'widget'. While Singular does offer some options, these are all pre-scripted,
well tested and result in a much smaller opportunity for developers to introduce vulnerabilities.

(Note that it is always possible for developers to work around the guardrails that Singular puts up).

Singular enforces a (basic) level of information security that is typically at a higher standard than most software
developers will be reasonable able to deliver.

### Privacy

TODO

### Licensing

Needs to be well managed. Certain licenses are simply not accepted.

### Performance

TODO

Singular results in an SPA that responds very quickly because it runs in the browser, alleviating
backend services. This is especially noticeable in case of high number of users. In these cases, operational cost of
the application is reduced considerably compared to applications that mostly run on servers.

### Tech-stack changes

Must remain stable long term (10+ years).

While it is true that some UI frameworks have been around for a long time, the reality is that
they come and go as popularity rises and falls. What was made using Angular last year is already considered old
technology the next year. But depending on old technology for too long is a serious risk to organizations.
The point is that UI Web framework technologies are not very stable.

Singular avoids this problem by not being a Web UI framework at all. It is a Java framework.

### Technical knowledge of the applications

The people that maintain the application change regularly.

Once an application is built, it needs to be maintained. It is rarely the case,
that the same people do this work for years. This is a serious risk to organizations. Because Singular is really a Java
framework, the risk of not being able to understand the way the application works is reduced considerably. In fact, we
have made it a technical requirement that a view (i.e., web page) is created using a single Java class.

### Outsourcing

Businesses would rather not do this because of cost but keeping the work in-house is often more expensive.

Because Singular is a Java framework, no outsourcing to web design agencies is needed.

## Negatives

1. Will Singular still be able to do everything I want?

Probably not everything, but that is also the point. Singular forces people to look at what is actually needed, not
what we want. What we want is often more than what we are able to justify in terms of cost.

For edge cases, extension is still very much possible and relatively easy to do.

2. It can still be difficult to use because the SWT API is quite extensive

Yes, this is definitely a tradeoff. We did not want to end up with a domain specific language but the complexity of
dealing with for example data adapters can feel overwhelming. This is similar to having to learn a new programming
language.

## Singular use cases

Data entry:

- Human resources
- Service management
- Inventory control
- Reservations
- Accounting
  Communication:
- Text-rich pages
- People directory
- Reporting
  Other:
- Planning and scheduling
- Data analysis and forecasting
- Dashboards

## Technical Requirements

The initial technical requirements for the development of Singular are:

- Made for Spring-Boot
- Compatibility with common Spring-Boot features
- Just like most Spring-Boot features, Singular works immediately after adding the dependency
- Specifically meant for building single page applications (SPA)
- Very short lead time for technology adoption
- Keep the framework as light as possible
- Zero HTML, Javascript and CSS coding
- Zero configuration
- Reduce choice in order to speed up development
- Avoid elaborate rules or conventions
- No template engines or other frameworks needed
- No other Javascript dependencies
- Basic and functional but still sophisticated styling (CSS) out of the box
- No-nonsense widgets
- Registers itself at the root mapping ("/") automatically
- Supports multiple views that can be developed in isolation
- All coding of a 'view' should happen in a single class, no resource files required
- Simple view controller
- Supports querying from HTTP services
- Supports updating HTTP services and supports various request encoding methods
- Supports adding static data to the client for use in listContainer boxes etc.
- Views are validated when starting the application so configuration and composition errors are immediately reported
- Full support for common widgets such as text box, checkbox, radiobutton, list box, link, button, table etc.
- Offers a data binding strategy that is easy to use but is advanced enough to offer flexibility
- Easy to extend by writing own DataAdapters or widgets