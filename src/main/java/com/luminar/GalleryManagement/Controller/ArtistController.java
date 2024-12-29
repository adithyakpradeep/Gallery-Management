package com.luminar.GalleryManagement.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.luminar.GalleryManagement.Entity.ArtWorkEntity;
import com.luminar.GalleryManagement.Entity.ArtistEntity;
import com.luminar.GalleryManagement.Service.ArtistService;
import com.luminar.GalleryManagement.Service.ArtworkService;

import jakarta.servlet.http.HttpSession;

@Configuration
class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:uploads/");
    }
}

@Controller
public class ArtistController {

    @Value("${upload.directory:uploads}") // Use "uploads" as the default if the property is not set
    private String uploadDir;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ArtworkService artworkService;

    // Define the upload directory for files
    private static final String UPLOAD_DIR = "uploads";

    // Root URL - Show Sign-Up Page
    @GetMapping("/")
    public String showIndexPage(Model model) {
        model.addAttribute("artist", new ArtistEntity());
        List<ArtWorkEntity> artworks = artworkService.getAllArtworks();
        model.addAttribute("artworks", artworks);
        return "index";
    }

    // Show Sign-Up Page
    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("artist", new ArtistEntity());
        return "signup";
    }

    // Register a new artist
    @PostMapping("/register")
    public String registerArtist(@ModelAttribute("artist") ArtistEntity artist) {
        artistService.saveArtist(artist);
        return "redirect:/signin";
    }

    // Show Sign-In Page
    @GetMapping("/signin")
    public String showSigninForm() {
        return "signin";
    }

    // Login an artist
    @PostMapping("/login")
    public String loginArtist(@RequestParam("email") String emailId, @RequestParam("password") String password,
                              Model model) {
        ArtistEntity artist = artistService.findByEmailAndPassword(emailId, password);
        if (artist != null) {
            model.addAttribute("artwork", new ArtWorkEntity());
            List<ArtWorkEntity> artworks = artworkService.getAllArtworks(); // Fetch all artworks
            model.addAttribute("artworks", artworks); // Add artworks to the model
            return "add";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "signin";
        }
    }

    // Logout the artist
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        // Redirect to the sign-in page
        return "redirect:/";
    }

    // Show artwork details
    @GetMapping("/art/details/{id}")
    public String getArtworkDetails(@PathVariable("id") Long id, Model model) {
        ArtWorkEntity artwork = artworkService.getArtworkById(id);
        model.addAttribute("art", artwork);
        return "viewArt";
    }

    // Show add artwork form
    @GetMapping("/add")
    public String showAddArtworkForm(Model model) {
        model.addAttribute("artwork", new ArtWorkEntity());
        List<ArtWorkEntity> artworks = artworkService.getAllArtworks(); // Fetch all artworks
        model.addAttribute("artworks", artworks); // Add artworks to the model
        return "add";
    }

    // Save artwork with file upload functionality
    @PostMapping("/save")
    public String saveArtwork(@ModelAttribute("artwork") ArtWorkEntity artwork,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              @RequestParam("tempFile") MultipartFile tempFile) throws IOException {

        // Validate if the files are provided
        if (imageFile == null || imageFile.isEmpty() || tempFile == null || tempFile.isEmpty()) {
            throw new IllegalArgumentException("Files are required for upload");
        }

        // Ensure the upload directory exists
        File uploadDirFile = new File(UPLOAD_DIR);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        // Save files and set paths in the artwork entity
        String imageFileName = saveFile(imageFile);
        String tempFileName = saveFile(tempFile);
        artwork.setImageUrl(imageFileName);
        artwork.setTempUrl(tempFileName);

        // Save artwork in the database
        artworkService.saveArtwork(artwork);
        return "redirect:/add";
    }

    // Utility method to save a file to the server
    private String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        // Generate a unique file name
        String uniqueFileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // Construct the full file path
        Path path = Paths.get(UPLOAD_DIR + File.separator + uniqueFileName);

        // Save the file
        Files.write(path, file.getBytes());
        return uniqueFileName;
    }

    // Show addform Page
    @GetMapping("/addform")
    public String showAddForm() {
        return "addform";
    }
    
    
    
//    @PostMapping("/deleteform/{id}")
//	public String deleteArtwork(@PathVariable("id") Long id) {
//		artworkService.deleteArtworkById(id);
//		return "redirect:add";
//	}
   

}
