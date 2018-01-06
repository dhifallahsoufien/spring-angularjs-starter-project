# Rapid prototyping with MySQL in Spring Boot via Spring Data JPA and Hibernate and AngularJS 1.6
This example demonstrates how **Spring Boot**, **Spring Data JPA**,  and in the front-end **AngularJS** can be used together to write web applications easily.
In this code example I'm demonstrating this by providing a full CRUD-based web application with authentication based on spring security

## Frameworks

### Front-end

#### Bootstrap
For rapidly creating prototypes of a web application, a UI toolkit or library will become really handy. There are many choices available, and for this example I chose Bootstrap.

#### AngularJS 1.6
AngularJS is a MVC based framework for web applications, written in JavaScript. It makes it possible to use the Model-View-Controller pattern on the front-end.

### Back-end

<ul>
<li>Spring Boot</li>
<li>Spring Security</li>
<li>Spring Data</li>
<li>Spring MVC architecture</li>
<li>RestController</li>
<li> Maven 3+ </li>
<li> JAVA 8 </li>
<li>Mysql Database 5</li>
</ul>

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations:

- Change the name schema of the database.
- Change the port number if port 8185 is reserved.
- Change the email configuration to end you can send emails.

#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.

#### Screenshots of Demo ####

Authentication page

![authentification](https://user-images.githubusercontent.com/19384321/34639049-9cb82756-f2d8-11e7-9924-62140fff3e14.PNG)

Reset password

![reset password](https://user-images.githubusercontent.com/19384321/34638389-ae7adb4a-f2ca-11e7-9e2b-e0e5090e5061.PNG)

Detail user page

![userpage](https://user-images.githubusercontent.com/19384321/34638464-2e92ecea-f2cc-11e7-9fb1-c50297d14550.PNG)

Add user

![aaduser](https://user-images.githubusercontent.com/19384321/34639060-ba25604c-f2d8-11e7-8172-06fa42200f90.PNG)

