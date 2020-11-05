**Project Web-Shop**

Project webshop with usual operations for Users, such as buying products, adding them to, and removing them from the cart, confirming the orders. Admin can add more products to the shop, manage orders but can’t buy any products, also admin can remove any user as well as any product. Not registered and not logged in users can only observe the products at the shop, but can’t buy anything until they will do the registration.

**Used technologies:**
-	Java Core, Java 8
-	JDBС
-	Maven
-	Servlets
-	MySql
-	JSP
-	JSTL
-	Servlets

**Implementation details**
-	OOP
-	SOLID
-	RBAC

**Configuration guide (for local machine)**
- Tomcat: 
Deployment — war_exploded, context address — "/"
- Connection: com\internet\shop\util\ConnectionUtil.java
Set user login, password, and port at URL as it configured at your DB (MySQL)
Login as admin:
After the start inject the user with administrator privileges, and some minor products to DB from the Main page under the Inject button. After this, you will be able to log in as an administrator, use login “1” and password “1” for that on the login page. Users can be registered at any time.

**Author**
https://github.com/Nokafan
