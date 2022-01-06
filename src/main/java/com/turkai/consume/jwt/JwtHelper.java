package com.turkai.consume.jwt;



import com.turkai.consume.configuration.SecurityConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtHelper.class);
    protected final SecurityConfiguration securityConfiguration;
    /**
     * Modülün çalışmakta olduğu Kubernetes node adını döner.
     */
    private final String nodeName;

    /**
     * Yapıcı.
     *
     * @param securityConfiguration
     */
    public JwtHelper(SecurityConfiguration securityConfiguration) throws UnknownHostException {
        this.securityConfiguration = securityConfiguration;
        this.nodeName = InetAddress.getLocalHost().getHostName();
    }

    /**
     * Yeni bir JWT token üretir.
     *
     * @return JWT token.
     */
    public String createToken() {

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");

        String token = Jwts.builder()
                .setHeader(header)

                .setId(RandomStringUtils.randomAlphanumeric(16))
                .setNotBefore(Date.from(Instant.ofEpochMilli(System.currentTimeMillis() - (1 * 60 * 1000)))) // -1m
                .setIssuedAt(Date.from(Instant.ofEpochMilli(System.currentTimeMillis())))
                .setExpiration(Date.from(Instant.ofEpochMilli(System.currentTimeMillis() + (5 * 60 * 1000)))) // +5m
                .signWith(
                        SignatureAlgorithm.HS256, securityConfiguration.getJwtSecret().getBytes(StandardCharsets.UTF_8)
                )
                .compact();

        LOGGER.debug("Created token {}", token);
        return token;
    }

    /**
     * Varolan bir JWT token'ı doğrular.
     *
     * @param token JWT token
     *
     * @return Geçerli bir JWT token ise true, değilse false
     */
    public boolean validateToken(String token) {
        try {
            LOGGER.debug(token);

            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(
                            securityConfiguration.getJwtSecret().getBytes(StandardCharsets.UTF_8)
                    )
                    .parseClaimsJws(token);
            LOGGER.debug(claimsJws.toString());
        } catch (JwtException e) {
            LOGGER.warn(e.toString(), e);
            return false;
        }
        return true;
    }

    /**
     * Authorization başlığında yer alan bir JWT token'ı doğrular.
     *
     * @param header Authorization başlığı
     *
     * @return Geçerli bir JWT token ise true, değilse false
     */
    public boolean validateTokenFromHeader(String header) {
        return validateToken(getJWTFromAuthorizationHeader(header));
    }

    /**
     * Authorization başlığından sadece JWT token'ı alır.
     *
     * @param header Authorization başlığı.
     *
     * @return JWT token.
     */
    private String getJWTFromAuthorizationHeader(String header) {
        return header.replace("Bearer ", "");
    }

}
