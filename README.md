**Project Simple Web-Shop**

This is a simple webshop project with plane operations for Users, such as buying products, adding them to, and removing them from the cart, confirming the orders. Admin can add more products to the shop, manage orders but can’t buy any products. Not registered and not logged in users can only observe the products at the shop, but can’t buy anything until they will do the registration.

**Project structure**

DAO layers have been made for JDBC and Storage as ArrayLists.

Authorization and authentication covered at authorization service and filters.

WebApp working under controllers and JSP.

**Implementation details**

Used storage - MySql 8 DB and Tomcat 9.0.37 as web-server.
Webapp - servelts, controllers, filters, JSP.
Access to DB established by JDBC API.

**Configuration guide**

Tomcat:
Deployment — war_exploded, context address — "/"

Connection: com\internet\shop\util\ConnectionUtil.java

Set user login, password, and port at URL as it configured at your DB (MySQL)
Login as admin:
After the start inject the user with administrator privileges, and some minor products to DB from the Main page under the Inject button. After this, you will be able to log in as an administrator, use login “1” and password “1” for that on the login page. Users can be registered at any time.

Author
https://github.com/Nokafan

