package org.example.getwayitem.Fliters;

import com.example.common.HttpStateCode;
import com.example.common.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author wzh
 */
@Component
public class GlobalTokenFilter implements GlobalFilter, Ordered {
    @Resource
    private ObjectMapper mapper;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpRequest request = exchange.getRequest();
            String token = request.getHeaders().get("token").toString();
            Map<String, Object> map = JWTUtils.checkLog(token);
            if ("true".equals(map.get("state"))) {
                return chain.filter(exchange);
            }
        }catch (Exception e){
            return denyAccess(exchange, HttpStateCode.INTERNAL_SERVER_ERROR);
        }

        return denyAccess(exchange, HttpStateCode.BAD_REQUEST);
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @SneakyThrows
    private Mono<Void> denyAccess(ServerWebExchange exchange, HttpStateCode resultCode) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        //这里在返回头添加编码，否则中文会乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        Map<String, Object> result = Map.of("code", resultCode.getCode(), "msg", resultCode.getDescription());
        byte[] bytes = mapper.writeValueAsBytes(result);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(buffer));
    }
}
