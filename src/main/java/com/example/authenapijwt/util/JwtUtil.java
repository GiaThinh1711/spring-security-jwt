package com.example.authenapijwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

//helper class for handling with jwt token
public class JwtUtil {
    private static Algorithm algorithm;
    /*this is jwt secret key use to encode jwt token only backend server hold this key
     * if an attacker know this key his can modify jwt token in the right way to grant access to api
     */
    private static final String JWT_SECRET_KEY = "secret";
    //some time units constant
    public static final int ONE_SECOND = 1000;
    public static final int ONE_MINUTE = ONE_SECOND * 60;
    public static final int ONE_HOUR = ONE_MINUTE * 60;
    public static final int ONE_DAY = ONE_HOUR * 24;
    public static final String ROLE_CLAIM_KEY = "role";

    public static Algorithm getAlgorithm() {
        if (algorithm == null) {
            algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
        }
        return algorithm;
    }

    public static DecodedJWT getDecodedJwt(String token){
        JWTVerifier verifier = JWT.require(getAlgorithm()).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

}
