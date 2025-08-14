package pe.cibertec.gestion_docente.infrastructure.seguridad.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.gestion_docente.domain.seguridad.service.TokenService;
import pe.cibertec.gestion_docente.infrastructure.configuration.seguridad.JwtProperties;
import pe.cibertec.gestion_docente.infrastructure.seguridad.entity.UsuarioEntity;

import java.security.Key;
import java.util.Date;

@Service @RequiredArgsConstructor
public class JwtTokenServiceImpl implements TokenService {
    private final JwtProperties props;
    private Key key(){ return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(props.getSecret())); }

    public String generarTokenAcceso(UsuarioEntity u){
        return Jwts.builder()
                .setSubject(u.getUsername())
                .claim("rol", u.getRol().getNombreRol())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+props.getAccessTokenExpiration()))
                .signWith(key(), SignatureAlgorithm.HS256).compact();
    }
    public String generarTokenRefresco(UsuarioEntity u){
        return Jwts.builder()
                .setSubject(u.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+props.getRefreshTokenExpiration()))
                .signWith(key(), SignatureAlgorithm.HS256).compact();
    }
    public boolean esTokenValido(String t){
        try { Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(t); return true; }
        catch(Exception e){ return false; }
    }
    public String extraerUsuario(String t){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(t).getBody().getSubject();
    }
}
