package com.crm.market.stock.controllers.auth;

import com.crm.market.stock.dto.auth.AuthenticationRequest;
import com.crm.market.stock.dto.auth.AuthenticationResponse;
import com.crm.market.stock.model.auth.ExtendedUser;
import com.crm.market.stock.services.auth.StockMarketJwtUtilService;
import com.crm.market.stock.services.auth.StockMarketUserDetailsService;
import com.crm.market.stock.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.API_AUTH)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StockMarketJwtUtilService stockMarketJwtUtilService;

    @Autowired
    private StockMarketUserDetailsService stockMarketUserDetailsService;

    @PostMapping(value = Constants.API_LOGIN)
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        ));
        final UserDetails userdDetails = stockMarketUserDetailsService.loadUserByUsername(request.getEmail());
        final String jwtToken = stockMarketJwtUtilService.generateToken((ExtendedUser) userdDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().jwtToken(jwtToken).build());
    }
}
