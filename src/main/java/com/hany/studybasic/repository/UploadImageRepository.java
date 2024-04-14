package com.hany.studybasic.repository;

import com.hany.studybasic.domain.entity.UploadImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadImageRepository extends JpaRepository<UploadImage, Long> {
}