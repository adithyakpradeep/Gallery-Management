package com.luminar.GalleryManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luminar.GalleryManagement.Entity.ArtistEntity;
import com.luminar.GalleryManagement.Repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	private ArtistRepository artistRepository;

	// private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //
	// Initialize the password encoder

//	@Override
//	public ArtistEntity authenticateUser(String email, String password) {
//		ArtistEntity artist = artistRepository.findByEmailId(email);
//
//		if (artist == null) {
//			throw new RuntimeException("User not found with email: " + email);
//		}
//
//		// Compare hashed passwords
//		if (!passwordEncoder.matches(password, artist.getPassword())) {
//			throw new RuntimeException("Invalid password for email: " + email);
//		}
//
//		return artist; // Successful authentication
//	}

	@Override
	public ArtistEntity updatePassword(Long userId, String newPassword) {
		ArtistEntity artist = artistRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
		artist.setPassword(newPassword);
		return artistRepository.save(artist);
	}

	@Override
	public void recordLoginAttempt(String email, boolean status) {
		// Optional: Record login attempts in a separate table or log system
		System.out.println("Login attempt for email: " + email + ", Status: " + status);
	}

	@Override
	public void saveArtist(ArtistEntity artist) {
		artistRepository.save(artist);
	}

	@Override
	public ArtistEntity findByEmailAndPassword(String emailId, String password) {
		return artistRepository.findByEmailIdAndPassword(emailId, password);
	}

	@Override
	public List<ArtistEntity> getAllArtists() {
		return artistRepository.findAll();
}


	@Override
	public ArtistEntity getArtistById(Long artistId) {
		Optional<ArtistEntity> artist = artistRepository.findById(artistId);
		if (artist.isPresent()) {
			return artist.get();
		} else {
			throw new RuntimeException("Artist not found with ID: " + artistId);
		}
	}
}


