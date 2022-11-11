package br.com.global.takeYourCar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.global.takeYourCar.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
