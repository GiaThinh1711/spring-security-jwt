# Spring Security basic with jwt token authentication

## References:

- Base tutorial: https://www.youtube.com/watch?v=VVn9OG9nfH0

- Read more about jwt: https://jwt.io/

## Quick overview
- Install dependencices: see `pom.xml`
- Create Entities, Repository, Service
- You need a service which implements `UserDetailsService` and override `UserDetails loadUserByUsername(String username)` make sure that username is unique in your database
- Create a class which extends from `WebSecurityConfigurerAdapter` to config spring security
- Create a filter class to hanlde when user login (authentication user) extends from `UsernamePasswordAuthenticationFilter`
- Create another filter to handle auhthorization of user which extends from `OncePerRequestFilter`
- Implements logic for each filter 
- Finally, create controller, path to run api.

## Update: Refresh token follow:
- When access token is exprired, client sent refresh token to request a new access token
- Don't need to encode user's role along with refresh token cause we only need username to check and create new access token
- Ưhen sending a refreshToken to get a new accessToken, it is good practice to create a new refreshToken as well. small thing but it helps, that the refreshToken always has longer expire date-time then the accessToken

