package com.crm.market.stock.filter;

import com.crm.market.stock.services.auth.StockMarketJwtUtilService;
import com.crm.market.stock.services.auth.StockMarketUserDetailsService;
import com.crm.market.stock.utils.Constants;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class StockMarketFilter extends OncePerRequestFilter {

    @Autowired
    private StockMarketJwtUtilService stockMarketJwtUtilService;

    @Autowired
    private StockMarketUserDetailsService stockMarketUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(Constants.STOCK_AUTHORIZATION);

        String token = null;
        String userEmail = null;
        String idEntreprise = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(Constants.STOCK_HEADER)) {
            token = authorizationHeader.substring(7);
            userEmail = stockMarketJwtUtilService.extractUsername(token);
            idEntreprise = stockMarketJwtUtilService.extractIdEntreprise(token);
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = stockMarketUserDetailsService.loadUserByUsername(userEmail);

            if (stockMarketJwtUtilService.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        MDC.put("idEntreprise", idEntreprise);
        filterChain.doFilter(request, response);
    }
}
