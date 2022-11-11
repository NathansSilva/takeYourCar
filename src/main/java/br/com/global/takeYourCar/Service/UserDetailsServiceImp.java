package br.com.global.takeYourCar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.global.takeYourCar.Model.User;
import br.com.global.takeYourCar.Repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByEmail(username);
    return user;
  }

}
