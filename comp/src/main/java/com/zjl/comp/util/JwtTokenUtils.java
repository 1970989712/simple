package com.zjl.comp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author zhoujl
 * @date 2020/3/23
 */
public class JwtTokenUtils {

    private static final String SECRET = "OPQRSTUVWXYZ0123456789abcdefghijklmn";
    private static final String ISS = "zjl_run_boot";

    public static String createToken(String userid) {
        return Jwts.builder()
                .setIssuer(ISS)
                .setSubject(userid)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String getUserid(String token){
        return getTokenBody(token).getSubject();
    }


    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
