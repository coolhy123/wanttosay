package com.hydu.custom.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Created by heyong
 * 2019/7/30
 */



public class JWTTest {
    public static void main(String[] args) {
//        创建token
//         JwtBuilder jwtBuilder=Jwts.builder().setId("9527")
//                    .setSubject("何永")
//                    .setIssuedAt(new Date())
//                 //设置头部和签名（盐），用hs256算法
//                    .signWith(SignatureAlgorithm.HS256,"coolhy")
//                 //设置过期时间
//                    .setExpiration(new Date(new Date().getTime()+60000))
//                    //设置自定义键值对
//                    .claim("role","admin")
//                 ;
//        System.out.println(jwtBuilder.compact());


        transJwt();
    }



    //解析token
public static void transJwt(){
    Claims claims=Jwts.parser().setSigningKey("coolhy")
            .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5NTI3Iiwic3ViIjoi5L2V5rC4IiwiaWF0IjoxNTY0NDUwMTMxLCJleHAiOjE1NjQ0NTAxOTEsInJvbGUiOiJhZG1pbiJ9.VJj108RVgSIntyTQx84Fs2FDW-5NTnUFe3LPkMfdlHs")
            .getBody();

    System.out.println("用户id："+claims.getId());
    System.out.println("用户签名："+claims.getSubject());
    System.out.println("登录时间："+claims.getIssuedAt());
    System.out.println("过期时间："+claims.getExpiration());
    System.out.println("用户角色："+claims.get("role"));

}
}

