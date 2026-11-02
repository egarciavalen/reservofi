package com.uoc.reservofi.base.busines.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;


@Service
public class JwtService {

	// Servicio enfocado en proporcionar aquellos métodos que requiere la configuración
	// por token JWT

	@Value("${token.security.secret.key}")
	private String secretKey;

	@Value("${token.security.expiration}")
	private long jwtExpiration;

	public String extractUsername(String token) {
		// Identificación del subject dentro del claim
		return extractClaim(token, Claims::getSubject);
	}

	public String generateToken(UserDetails userDetails) {
		// Generación del token con las credenciales y la clave secreta
		return Jwts
				.builder()
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + getExpirationTime()))
				.signWith(getSecretKey())
				.compact();
	}


	public long getExpirationTime() {
		return jwtExpiration;
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		// Define si el token es válido en función de si el usuario coincide y no ha expirado
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		// Define si el token ha expirado
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		// Identificación del expiration dentro del claim
		return extractClaim(token, Claims::getExpiration);
	}


	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		// Permite devolver el claim que se pide en el parámetro
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		// Obtiene el contenido de las claims del token (es decir, el interior de su payload)
		return Jwts
				.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	private SecretKey getSecretKey() {
		// Construcción de la clave criptográfica a partir del secreto
		// Primero se decodifica la hash del secreto del archivo de propiedades (por seguridad no se pone la clave en plano)
		// y posteriormente se crea un objeto SecretKey
		return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
	}
}