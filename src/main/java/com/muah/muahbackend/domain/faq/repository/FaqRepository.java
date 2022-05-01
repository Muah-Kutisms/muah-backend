package com.muah.muahbackend.domain.faq.repository;

import com.muah.muahbackend.domain.faq.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long> {
}
