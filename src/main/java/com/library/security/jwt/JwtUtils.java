package com.library.security.jwt;


import com.library.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class JwtUtils {

    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    private String jwtSecret = "citadel6@";


    private long jwtExpirationMs = 86400000;

    public String generateToken(Authentication authentication){
      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

      return Jwts.builder()
              .setSubject(""+(userDetails.getId()))
              .setIssuedAt(new Date())
              .setExpiration(new Date(new Date().getTime()+jwtExpirationMs))
              .signWith(SignatureAlgorithm.HS512, jwtSecret)
              .compact();
    }

    public Long getIdFromJwtToken(String token){
      String strId = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return Long.parseLong(strId);
    }

    public boolean validateJwtToken(String token) {

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("JWT Token is expired {}",e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT Token is unsupported {}",e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("JWT Token is malformed {}",e.getMessage());
        } catch (SignatureException e) {
            logger.error(" Invalid JWT signature {}",e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT Token illegal args {}",e.getMessage());
        }
        return false;
    }


}
