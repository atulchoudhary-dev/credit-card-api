# Getting Started with Credit Card Application

* unzip provide credit-card-api.zip 
* Change directory : cd credit-card-api
* Run the application : mvn spring-boot:run

This runs the app in the development mode on port 9090.

# Rest Endpoints for credit card data
* Get: /cards to get all credit card details in the sytem
* Post: /cards to add a new card
* postman collection is attached with shared code packet


# Authentication and Authorization.

* Application is secured using spring security
* JSON Web Token is used for forming authorization for users
* During authentication, a JWT is returned.
* Whenever the user wants to access a protected resource, the browser must send JWTs in the Authorization header along with the request.

# User flow for authentication and authorization(Currently Disabled, Steps to enable it mentioned below)
* User needs to signup. This can be done using /signup endpoint which will create user along with password (encrypted) in DB.
* After successful sign, User need to login using /login endpoint. This will validate the user credentials and return authorization token in response header.
* User needs to sends this authorization bearer (JWT) token when accessing the protected resource. System validates the JWT.
* If JWT is valid, user is allowed to access the resource

# Steps to enable authentication and authorization for /cards api
* Go to com.example.cc.auth.config.WebSecurityConfiguration class.
* Remove "/cards" (line : 37) from AUTH_WHITELIST. Updated AUTH_WHITELIST should look like below. 
   private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/h2/**"
            
    };
* Build and run the application. Follow user flow mentioned above.

