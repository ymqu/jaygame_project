package JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

@ConfigurationProperties("jwt.config")
public class JwtUtil {

    private  String key;  //secret
    private long ttl;      //expiration time

    public JwtUtil() {
    }

    public JwtUtil(String key, long ttl) {
        this.key = key;
        this.ttl = ttl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    /**
     *
     * @param id: user id
     * @param subject: user name
     * @param roles: user role
     * @return
     */
    public String createJWT(String id, String subject, String roles){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key).claim("roles", roles);
        if( ttl > 0){
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();  //return jwt string
    }

    public Claims parseJWT(String jwtStr){
        return Jwts.parser()
                .setSigningKey(key)     //set key
                .parseClaimsJws(jwtStr)     //parse jwtStr
                .getBody();                 //get jwt string information
    }
}
