package pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pe.cibertec.gestion_docente.domain.seguridad.model.UsuarioModel;
import pe.cibertec.gestion_docente.domain.seguridad.service.TokenService;
import pe.cibertec.gestion_docente.infrastructure.configuration.seguridad.JwtProperties;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements TokenService {

    private final JwtProperties jwt;

    @Override
    public String generarTokenAcceso(UsuarioModel usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", usuario.getNombre() + " " + usuario.getApellido());

        // Guarda nombres “planos”: ADMINISTRATIVO, DOCENTE, etc.
        claims.put("roles", usuario.getRoles().stream()
                .map(r -> r.getNombre())  // <- SIN "ROLE_"
                .toList());

        claims.put("id_docente", usuario.getIdDocente());
        return generar(claims, usuario.getUsername(), jwt.getAccessTokenExpiration());
    }

    @Override
    public String generarTokenRefresco(UsuarioModel usuario) {
        return generar(Collections.emptyMap(), usuario.getUsername(), jwt.getRefreshTokenExpiration());
    }

    @Override
    public String extraerUsuario(String token) {
        return claim(token, Claims::getSubject);
    }

    @Override
    public boolean esTokenValido(String token) {
        try {
            Jwts.parser().verifyWith(key()).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public UserDetails crearUserDetailsDesdeToken(String token) {
        String username = extraerUsuario(token);
        Collection<GrantedAuthority> auths = extraerRoles(token).stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r)) // <-- aquí el prefijo
                .collect(Collectors.toSet());

        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password("")  // no se usa
                .authorities(auths)
                .build();
    }

    @Override
    public long segundosHastaExpirar(String token) {
        var exp = all(token).getExpiration();
        long now = System.currentTimeMillis();
        return Math.max(0, (exp.getTime() - now) / 1000);
    }

    private String generar(Map<String, Object> claims, String sub, long expMs) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .claims(claims).subject(sub)
                .issuedAt(new Date(now))
                .expiration(new Date(now + expMs))
                .signWith(key()).compact();
    }

    private SecretKey key() {
        byte[] bytes = Decoders.BASE64URL.decode(jwt.getSecret());
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims all(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload();
    }

    private <T> T claim(String token, Function<Claims, T> r) { return r.apply(all(token)); }

    @SuppressWarnings("unchecked")
    private List<String> extraerRoles(String token) { return claim(token, c -> (List<String>) c.get("roles")); }
}
