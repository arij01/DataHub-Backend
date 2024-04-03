package com.example.demo.Repository;

import com.example.demo.entity.Glossaires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlossairesRepo extends JpaRepository<Glossaires,Long> {
}
