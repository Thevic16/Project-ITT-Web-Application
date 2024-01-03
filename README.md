# Project-ITT-Web-Application

[GitHub - Thevic16/Project-ITT-Web-Application](https://github.com/Thevic16/Project-ITT-Web-Application)

**Description:**
The Project-ITT-Web-Application project is an innovative web application that facilitates seamless communication between users and intelligent wheelchairs, offering a user-friendly interface for receiving notifications and updates related to the wheelchair's status and activities. Developed using the Javalin framework, this project combines various technologies to create a comprehensive solution for enhanced user experience and accessibility.

**Key Components and Features:**

1. **Login Page:**
The application begins with a secure login page, ensuring that only authenticated users have access to wheelchair-related notifications. Users can create accounts, log in securely, and personalize their notification preferences.
2. **API REST:**
Javalin's lightweight and straightforward API design is leveraged to create RESTful endpoints, allowing seamless communication between the web application and the intelligent wheelchair. The API enables the retrieval of real-time data, status updates, and configuration changes.
3. **HTML with Thymeleaf:**
Thymeleaf, a modern server-side Java template engine, is utilized to dynamically generate HTML content. This ensures a responsive and interactive user interface, with the ability to present real-time data from the intelligent wheelchair in an intuitive manner.
4. **CSS with Bootstrap:**
Bootstrap is employed to enhance the visual aesthetics and responsiveness of the web application. The use of CSS styles, coupled with Bootstrap's components, ensures a consistent and visually appealing user experience across different devices and screen sizes.
5. **Locations with Google Maps:**
Users can track the wheelchair's location in real-time through integration with Google Maps. This feature provides an interactive map interface, allowing users to visualize the wheelchair's movements and receive location-based notifications.
6. **Email Messages:**
The application incorporates email notifications to keep users informed about critical updates or events related to the intelligent wheelchair. Email messages are sent securely using industry-standard protocols, enhancing the accessibility of information.
7. **Push Notifications:**
Users can opt to receive push notifications on their devices for immediate updates. Leveraging modern web technologies, push notifications enhance the responsiveness of the application, ensuring that users stay informed in real-time.
8. **Database Support with JPA Hibernate:**
JPA Hibernate is used to manage and persist data related to user accounts, notification preferences, and wheelchair activities. The integration with a relational database ensures data consistency and provides a reliable storage mechanism for long-term data retention.

**How to Use the Application:**

1. Create an account on the secure login page.
2. Log in to access the personalized dashboard and notification preferences.
3. Monitor the intelligent wheelchair's location and receive real-time updates.
4. Configure notification preferences, including email and push notifications.
5. Stay informed about the wheelchair's status and activities through the user-friendly interface.

**Implementation:**

Database: In this case the application was implemented using the H2 database in the
Java programming language, this is incorporated directly into the application in client-
server mode, this makes its handling easier. Through this database you can save, access
and edit the following information:
− User information (regardless of their role).
− Information about reminders.
− Information about fall events.

Figure #1 - Web Application Class Diagram: This UML diagram has the classes and attributes required for web application deployment.

![Untitled](Project-ITT-Web-Application%20206627b40d8947f99e82b63a958833ef/Untitled.png)

Authentication module: This is the module in charge of authenticating the identity of
the users who try to access the web application, for this it is responsible for comparing
the user and password data entered in the web browser with the users and keys stored
in the database of the web application. In addition, it has functionalities of remember
me, forget username / password and registration.

Figure #2

![Untitled](Project-ITT-Web-Application%20206627b40d8947f99e82b63a958833ef/Untitled%201.png)

User Management Module: This module is only accessed by the user administrator of
the web application, this is the one that allows you to create the users of the application,
both in your role as a wheelchair user and as a follow-up. It also allows you to list,
delete and edit each of the users that have been established.

(Note: At the time of the images were taken, this stage was still in development).

Figure #3

![Untitled](Project-ITT-Web-Application%20206627b40d8947f99e82b63a958833ef/Untitled%202.png)

Figure #4

![Untitled](Project-ITT-Web-Application%20206627b40d8947f99e82b63a958833ef/Untitled%203.png)

Figure #5

![Untitled](Project-ITT-Web-Application%20206627b40d8947f99e82b63a958833ef/Untitled%204.png)

Reminders Module: This module is responsible for managing the reminders created by
the users of the application with the role of "Wheelchair User", in this you can specify
a description, weekly repetition, time, end date among other features. In the same way
as the previous module, in this one you can list, edit and delete the reminders created.

Figure #6

![Untitled](Project-ITT-Web-Application%20206627b40d8947f99e82b63a958833ef/Untitled%205.png)

Figure #7

![Untitled](Project-ITT-Web-Application%20206627b40d8947f99e82b63a958833ef/Untitled%206.png)

Tracking Module: This is the module in charge of displaying the list of fall events that
have happened so far. In this you can see information such as the user's name, first
name, surname, date the event occurred, time of the event, see image that was taken
and see exact location on the map.

Figure #8

![Untitled](Project-ITT-Web-Application%20206627b40d8947f99e82b63a958833ef/Untitled%207.png)

Communications module: This is the module in charge of generating the corresponding
information of the reminders and events of fall through emails and what is known as Push-Notification.

Figure #9 - Push-Notification.

![Untitled](Project-ITT-Web-Application%20206627b40d8947f99e82b63a958833ef/Untitled%208.png)

Figure #10 - Push-Notification.

![Untitled](Project-ITT-Web-Application%20206627b40d8947f99e82b63a958833ef/Untitled%209.png)

Figure #11 - Email.

![Untitled](Project-ITT-Web-Application%20206627b40d8947f99e82b63a958833ef/Untitled%2010.png)

Figure #12 - Email.

![Untitled](Project-ITT-Web-Application%20206627b40d8947f99e82b63a958833ef/Untitled%2011.png)

**Conclusion:**

The **Project-ITT-Web-Application** project showcases the capabilities of the Javalin framework in building modern and feature-rich web applications. By combining advanced technologies such as Google Maps integration, email messaging, and push notifications, this application delivers a user-friendly experience for individuals managing and interacting with intelligent wheelchairs. Whether used in healthcare or accessibility-focused applications, **Project-ITT-Web-Application** provides a solid foundation for creating intelligent and responsive systems.