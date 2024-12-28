package com.apptester.app12;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface userrep extends JpaRepository<user, Integer> {
    public List<user> findByEmail(String email);
}
