package com.example.BackEmpresa.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    String ADMIN="ADMIN";
    String USER ="USER";

    String ALLRESERVA ="/api/v0-empresa/reserva/**";
    String ALLUSER ="/api/v0-empresa/user/**";
    String ALLTICKET="/api/v0-empresa/ticket/**";
    String ALLTOKENS="/api/v0-empresa/token/**";
    String ALLCORREO ="api/v0-empresa/correo/**";


    @Override
    public void configure(WebSecurity web) throws Exception {
                web.ignoring()
                .antMatchers("/h2-console/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAfter(new JWTAuthorization(), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers(GET,ALLTOKENS).permitAll();
        http.authorizeRequests().antMatchers(POST, ALLTOKENS).permitAll();


        http.authorizeRequests().antMatchers(GET,"/api/v0-empresa/reserva/detalle").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/v0-empresa/reserva/detalledisponible").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/v0-empresa/reserva/detalledisponible/{\\d}").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/v0-empresa/reserva/detalle/{\\d}").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/v0-empresa/reserva/{\\d}").permitAll();
        http.authorizeRequests().antMatchers(GET, ALLRESERVA).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(POST, ALLRESERVA).permitAll();
        http.authorizeRequests().antMatchers(DELETE, ALLRESERVA).hasAnyAuthority(ADMIN);

        http.authorizeRequests().antMatchers(GET,"/api/v0-empresa/correo/{\\d}").permitAll();
        http.authorizeRequests().antMatchers(GET,ALLCORREO).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(POST, ALLCORREO).permitAll();
        http.authorizeRequests().antMatchers(PUT, ALLCORREO).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(DELETE, ALLCORREO).hasAnyAuthority(ADMIN);

        http.authorizeRequests().antMatchers(GET,"/api/v0-empresa/user/{\\d}").hasAnyAuthority(USER,ADMIN);
        http.authorizeRequests().antMatchers(GET, ALLUSER).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(POST, ALLUSER).permitAll();
        http.authorizeRequests().antMatchers(PUT, ALLUSER).hasAnyAuthority(ADMIN,USER);
        http.authorizeRequests().antMatchers(DELETE, ALLUSER).hasAnyAuthority(ADMIN);

        http.authorizeRequests().antMatchers(GET,"/api/v0-empresa/ticket/{\\d}").hasAnyAuthority(USER);
        http.authorizeRequests().antMatchers(GET, ALLTICKET).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(POST, ALLTICKET).hasAnyAuthority(USER,ADMIN);
        http.authorizeRequests().antMatchers(DELETE, ALLTICKET).hasAnyAuthority(USER,ADMIN);


        http.authorizeRequests().anyRequest().authenticated();

    }
}
