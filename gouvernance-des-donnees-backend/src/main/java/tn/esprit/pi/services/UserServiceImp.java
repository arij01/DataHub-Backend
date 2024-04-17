package tn.esprit.pi.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.repositories.UserRepository;

@Slf4j
@Service
public class UserServiceImp implements UserServiceI{

    @Autowired
    UserRepository ur;

}
