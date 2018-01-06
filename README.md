# Rapid prototyping with MySQL in Spring Boot via Spring Data JPA and Hibernate and AngularJS 1.6
This example demonstrates how **Spring Boot**, **Spring Data JPA**,  and in the front-end **AngularJS** can be used together to write web applications easily.
In this code example I'm demonstrating this by providing a full CRUD-based web application with authentication based on spring security

## Frameworks

### Front-end

#### Bootstrap
For rapidly creating prototypes of a web application, a UI toolkit or library will become really handy. There are many choices available, and for this example I chose Bootstrap.

#### AngularJS 1.6
AngularJS is a MVC based framework for web applications, written in JavaScript. It makes it possible to use the Model-View-Controller pattern on the front-end. It also comes with several additional modules. In this example I'm also using **angular-resource**, which is a simple factory-pattern based module for creating REST clients.

### Back-end

#### Spring Boot
One of the hassles while creating web applications using the Spring Framework is that it involves a lot of configuration. Spring Boot makes it possible to write configuration-less web application because it does a lot for you out of the box.
For example, if you add HSQLDB as a dependency to your application, it will automatically provide a datasource to it.
If you add the spring-boot-starter-web dependency, then you can start writing controllers for creating a web application.

#### Spring Data JPA
Spring Data JPA allows you to create repositories for your data without even having to write a lot of code. The only code you need is a simple interface that extends from another interface and then you're done.
With Spring Boot you can even leave the configuration behind for configuring Spring Data JPA, so now it's even easier.


### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations.

#### Prerequisites

- Java 8
- Maven 3

#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.

#### Screenshots of Demo ####

Authentication page

![authentification](https://user-images.githubusercontent.com/19384321/34638312-58ba461a-f2c9-11e7-9c6a-30b8ba362d7c.PNG)

Reset password

![reset password](https://user-images.githubusercontent.com/19384321/34638389-ae7adb4a-f2ca-11e7-9e2b-e0e5090e5061.PNG)

Detail user page

![userpage](https://user-images.githubusercontent.com/19384321/34638464-2e92ecea-f2cc-11e7-9fb1-c50297d14550.PNG)

Add user

![aaduser](https://user-images.githubusercontent.com/19384321/34638489-b48ff1e4-f2cc-11e7-8219-3efa4028ef55.PNG)

