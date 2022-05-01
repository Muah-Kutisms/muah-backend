package com.muah.muahbackend.domain.notice.repository;

import com.muah.muahbackend.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
