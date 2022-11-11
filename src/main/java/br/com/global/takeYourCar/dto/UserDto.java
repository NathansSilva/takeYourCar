package br.com.global.takeYourCar.dto;

import br.com.global.takeYourCar.Model.User;

public record UserDto(Long id, String nome, String email) {

    public User toUser() {
        var user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setNome(nome);
        return user;
    }

    public boolean isPresent() {
        return false;
    }

}
