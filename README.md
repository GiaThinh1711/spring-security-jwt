# Spring Security basic with jwt token auhentication

## References:

base tutorial: https://www.youtube.com/watch?v=VVn9OG9nfH0
read more about jwt: https://jwt.io/

## Quick overview
- Install dependecices: see `pom.xml`
- create Entities, Repository, Service
- you need a service which implements `UserDetailsService` and override `UserDetails loadUserByUsername(String username)` make sure that username is unique in your database
- create a class which extends from `WebSecurityConfigurerAdapter` to config spring security
- create a filter class to hanlde when user login (authentication user) extends from `UsernamePasswordAuthenticationFilter`
- create another filter to handle auhthorization of user which extends from `OncePerRequestFilter`
- Implements logic for each filter 
- Finally, create controller, path to run api.

