package com.luminar.GalleryManagement.Service;

import java.util.List;

import com.luminar.GalleryManagement.Entity.ArtWorkEntity;

public interface ArtworkService {
    List<ArtWorkEntity> getAllArtworks();
    ArtWorkEntity getArtworkById(Long id);
    void saveArtwork(ArtWorkEntity artwork);
	void deleteArtworkById(Long id);
	
    
}
