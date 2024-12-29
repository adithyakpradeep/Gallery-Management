package com.luminar.GalleryManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.luminar.GalleryManagement.Entity.ArtWorkEntity;

public interface ArtworkRepository extends JpaRepository<ArtWorkEntity, Long> {

}
