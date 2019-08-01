package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * Created by heyong
 * 2019/7/30
 */

@ConfigurationProperties("jwt.config")
public class JwtUtil {

    private String key;//签名（盐）

    private long time;//超时时间

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


    /**
     * 创建token
     * @param id  用户id
     * @param subject 用户姓名
     * @param roles 用户角色
     * @return
     */
    public String createJwt(String id,String subject,String roles){
        Date  date=new Date();
        JwtBuilder jwtBuilder=Jwts.builder()
                //设置用户id
                .setId(id)
                //设置用户姓名
                .setSubject(subject)
                //设置用户登录时间，取当前系统时间
                .setIssuedAt(date)

                //设置token头部
                .signWith(SignatureAlgorithm.HS256,"coolhy")
                //设置用户角色
                .claim("roles",roles);
        if(time>0){
            //设置失效时间
            jwtBuilder.setExpiration(new Date(date.getTime()+this.time));
        }
            return jwtBuilder.compact();
    }


    /**
     * 解析token
     * @param jwtStr
     * @return
     */
    public Claims parseJwt(String jwtStr){
        return Jwts.parser()
                //申明盐
                .setSigningKey("coolhy")
                //传入token
                .parseClaimsJws(jwtStr)
                .getBody();
    }


}
