package cz.uhk.fim.DameFilm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()

                //User
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/register").permitAll()
                .antMatchers("/auth/profile/*").permitAll()
                .antMatchers("/auth/profile/update/*").permitAll()//.hasRole("ADMIN")
                .antMatchers("/auth/profile/delete/*").permitAll()//.hasRole("ADMIN")

                //Movie
                .antMatchers("/movie/all").permitAll()
                .antMatchers("/movie/detail/*").permitAll()
                .antMatchers("/movie/create").authenticated()
                .antMatchers("/movie/update/*").hasRole("ADMIN")
                .antMatchers("/movie/delete/*").hasRole("ADMIN");


        http
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
