package com.alpha.kooing.config.jwt
import com.alpha.kooing.user.Role
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.Date
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import kotlin.math.exp

// accessToken, refreshToken 생성 책임
@Component
@PropertySource("classpath:application.yml")

class JwtTokenProvider(
    @Value("\${spring.jwt.secretKey}") val secret: String
){
    private final var secretKey:SecretKey
    init{
        secretKey = SecretKeySpec(secret.toByteArray(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().algorithm)
    }
    fun getJwtEmail(token:String):String{
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload["email"].toString()
    }
    fun getJwtRole(token: String):String{
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload["role"].toString()
    }
    fun isExpired(token: String):Boolean{
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload.expiration.before(Date(System.currentTimeMillis()))
    }
    fun createJwt(email:String, role:String, expiration:Long): String {
        return Jwts.builder()
            .claim("email", email)
            .claim("role", role)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(secretKey)
            .compact()
    }


}