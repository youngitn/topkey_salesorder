package com.topkey.salesorder.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.observation.ServerRequestObservationContext;

import io.micrometer.observation.ObservationPredicate;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ZipkinConfig {


     @Bean
      ObservationPredicate noopServerRequestObservationPredicate() {

        ObservationPredicate  predicate = (name, context) -> {
          if(context instanceof ServerRequestObservationContext c) {
            HttpServletRequest servletRequest = c.getCarrier();
            String requestURI = servletRequest.getRequestURI();
            if(StringUtils.containsAny(requestURI, "actuator")) {
              return false;
            }
            //System.out.println("requestURI: " + requestURI);
          }
          if(StringUtils.equalsAny(name,"spring.security.filterchains","spring.security.authorizations","spring.security.http.secured.requests")) {
            return false;
          }
          return true;
        };

        return predicate;
      }
}
