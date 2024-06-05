package com.bytesops.demo.orm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PanFileDao extends JpaRepository<PanFile, Long> {
}
