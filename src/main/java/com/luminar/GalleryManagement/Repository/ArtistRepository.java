package com.luminar.GalleryManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luminar.GalleryManagement.Entity.ArtistEntity;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

    

	ArtistEntity findByEmailIdAndPassword(String emailId, String password);
    
	

}
