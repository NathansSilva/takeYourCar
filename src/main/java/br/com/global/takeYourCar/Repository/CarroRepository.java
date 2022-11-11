package br.com.global.takeYourCar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.global.takeYourCar.Model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

}
