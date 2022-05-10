package cz.uhk.fim.DameFilm.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    @Autowired
    private UserSecurityService userService;

    private static Date calculateExpirationDate() {
        Date now = new Date();
        return new Date(now.getTime() + 8* 60 * 60 * 1000);
    }

    public String createToken(String email) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setId(UUID.randomUUID().toString())
                .setSubject(email)
                .setAudience("guiUrl")
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(calculateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, encodeSecretKey())
                .compact();
    }

    private String encodeSecretKey() {
        return Base64.getEncoder().encodeToString(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String obtainToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer") && token.length() > 7) {
            return token.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(encodeSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(body);
            return !body.getExpiration().before(new Date());
        } catch (ExpiredJwtException | IllegalArgumentException | SignatureException | MalformedJwtException | UnsupportedJwtException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Authentication getAuthentication(String token) {
        String email = getEmailFromToken(token);
        UserDetails userDetails = userService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    private String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(encodeSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

