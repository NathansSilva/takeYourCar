package br.com.global.takeYourCar.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.global.takeYourCar.Model.Carro;
import br.com.global.takeYourCar.Service.CarroService;

@RestController
@RequestMapping("/api/carro")
public class CarroController {

    @Autowired
    private CarroService service;

    @GetMapping
    public Page<Carro> index(@PageableDefault(size = 10) Pageable pageable) {
        return service.listAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Carro> create(@RequestBody Carro carro) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(carro));
    }

    @GetMapping("{id}")
    public ResponseEntity<Carro> show(@PathVariable Long id) {
        return ResponseEntity.of(service.get(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        Optional<Carro> optional = service.get(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        service.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Carro> update(@PathVariable Long id, @RequestBody @Valid Carro newCarro) {
        Optional<Carro> optional = service.get(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        var carro = optional.get();
        BeanUtils.copyProperties(newCarro, carro);
        carro.setId(id);
        service.save(carro);

        return ResponseEntity.ok().build();
    }
}
