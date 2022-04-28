package com.example.BS9.Feign;
import com.example.BS9.infrastructure.dto.output.ProfesorOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="cliente", url="http://localhost:8080/")
public interface IFeignServer {
  @GetMapping("persona/feign/{id}")
  ResponseEntity<ProfesorOutputDTO> getProfesor(@PathVariable String id);


}
