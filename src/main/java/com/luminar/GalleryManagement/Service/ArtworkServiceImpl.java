package com.luminar.GalleryManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luminar.GalleryManagement.Entity.ArtWorkEntity;
import com.luminar.GalleryManagement.Repository.ArtworkRepository;

@Service
public class ArtworkServiceImpl implements ArtworkService {

	@Autowired
	private ArtworkRepository artworkRepository;

	@Override
	public List<ArtWorkEntity> getAllArtworks() {
		return artworkRepository.findAll();
	}

	@Override
	public ArtWorkEntity getArtworkById(Long id) {
		return artworkRepository.findById(id).orElse(null);
	}

	@Override
	public void saveArtwork(ArtWorkEntity artwork) {
		artworkRepository.save(artwork);
	}

	@Override
	public void deleteArtworkById(Long id) {
		artworkRepository.deleteById(id);
	}

	

}
