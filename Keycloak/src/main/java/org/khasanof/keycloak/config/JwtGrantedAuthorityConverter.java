package org.khasanof.keycloak.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Nurislom
 * @see org.khasanof.keycloak.config
 * @since 30.07.2023 6:50
 */
@Component
public class JwtGrantedAuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        return SecurityUtils.extractAuthorityFromClaims(source.getClaims());
    }
}
