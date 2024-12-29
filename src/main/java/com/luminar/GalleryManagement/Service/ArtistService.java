package com.luminar.GalleryManagement.Service;

import java.util.List;

import com.luminar.GalleryManagement.Entity.ArtistEntity;

public interface ArtistService {

	ArtistEntity updatePassword(Long userId, String newPassword);

	/**
	 * Record login audit information (optional).
	 *
	 * @param email  User's email.
	 * @param status Login success or failure status.
	 */
	void recordLoginAttempt(String email, boolean status);

	void saveArtist(ArtistEntity artist);

	ArtistEntity findByEmailAndPassword(String emailId, String password);

	List<ArtistEntity> getAllArtists();

	ArtistEntity getArtistById(Long artistId);

}
