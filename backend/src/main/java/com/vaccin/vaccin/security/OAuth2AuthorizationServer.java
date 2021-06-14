package com.vaccin.vaccin.security;

import com.vaccin.vaccin.exception.ExceptionHandlerAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailService;
    private ExceptionHandlerAdvice exceptionTranslator;

    @Autowired
    public OAuth2AuthorizationServer(AuthenticationManager authenticationManager, UserDetailsService userDetailService, ExceptionHandlerAdvice exceptionTranslator) {
        this.authenticationManager=authenticationManager;
        this.userDetailService=userDetailService;
        this.exceptionTranslator = exceptionTranslator;
    }

//    http://localhost:8080/oauth/token

    @Override
    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
        String CLIENT_ID = "backend";
        String CLIENT_SECRET = "{noop}";
        String SCOPE = "web";
        String PASSWORD = "password";
        String REFRESH_TOKEN = "refresh_token";
        client.inMemory()
                .withClient(CLIENT_ID)
                .secret(CLIENT_SECRET)
                .accessTokenValiditySeconds(86_400)
                .refreshTokenValiditySeconds(86_400)
                .authorizedGrantTypes(PASSWORD, REFRESH_TOKEN)
                .scopes(SCOPE)
                .autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoint) {
        TokenEnhancerChain tokenEnhancerChain = tokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer()));

        endpoint
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .accessTokenConverter(jwtTokenEnhancer())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailService)
                .tokenServices(defaultTokenService(tokenEnhancerChain))
                .reuseRefreshTokens(false)
                .exceptionTranslator(exceptionTranslator);
    }

    @Bean
    public DefaultTokenServices defaultTokenService(TokenEnhancerChain tokenEnhancerChain) {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setReuseRefreshToken(false);

        return defaultTokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
        InMemoryTokenStore tokenStore = new InMemoryTokenStore();
        return tokenStore;
    }

    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource("jwt.jks"),
                "udzVTE49YCFnrgjVnpvhSttqS9mYtjUWn5ecMZwd4hus434GEpWRhvEdKDdZLdZkGnYC5RGUztVykUduPFH9m9u9wXVtG5Vk3HAZVT8p8pAaYufYeKnMVStPFAthNscp".toCharArray()
        );
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
        return converter;
    }

    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        return new TokenEnhancerChain();
    }
}
