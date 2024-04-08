package com.redvelvet.redvelvet.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageProcessingUtil {
    
    @Value("${app.pathToImages}")
    private String pathToImages;

    @Value("${app.urlToImages}")
    private String imagesUrl;

    public String createRecipeMainImageDirectory(String recipeName, Long recipeId){

        String directoryPath = pathToImages +"/" + recipeId.toString() + "/main";

        File directory = new File(directoryPath);

        // Create the directory
        boolean created = directory.mkdirs();

        //check if created

        return directoryPath;

    }

    public String createRecipeImagesDirectory(String recipeName, Long recipeId){

        String directoryPath = pathToImages +"/" + recipeId.toString() + "/images";

        File directory = new File(directoryPath);

        // Create the directory
        boolean created = directory.mkdirs();

        //check if created

        return directoryPath;

    }

    public List<String> addImageToDirectory(Long recipeId, List<MultipartFile> files) throws IOException{

        String directoryPath = pathToImages + "/" + recipeId.toString() + "/images";

        List<String> filePaths = new ArrayList<>();
        
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (MultipartFile file : files) {

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            Path filePath = Paths.get(directoryPath, fileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            filePaths.add(filePath.toString());


        }

        return filePaths;
        
    }

    public void addMainImage(Long recipeId, String originalFilepath) throws IOException{

        String directoryPath = pathToImages + "/" + recipeId.toString() + "/main/mainPic";

        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        generateThumbnail(originalFilepath, directoryPath);

    }

    private String generateThumbnail(String originalFilePath, String thumbnailFilePath) throws IOException {
        int thumbnailWidth = 100;
        int thumbnailHeight = 100;

        BufferedImage originalImage = ImageIO.read(new File(originalFilePath));
        BufferedImage thumbnail = new BufferedImage(thumbnailWidth, thumbnailHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = thumbnail.createGraphics();
        g.drawImage(originalImage, 0, 0, thumbnailWidth, thumbnailHeight, null);
        g.dispose();

        ImageIO.write(thumbnail, "jpg", new File(thumbnailFilePath));

        return thumbnailFilePath;
    }

    public void delete(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    delete(f);
                }
            }
        }
        file.delete();
    }

    public List<String> getFileNameList(String filepath){
        
        List<String> filenames = new ArrayList<>();
        
        File directory = new File(filepath);

        String folderName = directory.getName();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        filenames.add(imagesUrl + "/" + folderName + "/" + file.getName());
                    }
                }
            }
        } else {
            return filenames;
        }

        return filenames;

    }

    public List<String> getMainFileName(String filepath){
        
        List<String> filenames = new ArrayList<>();
        
        File directory = new File(filepath);

        String folderName = directory.getName();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        filenames.add(imagesUrl + "/" + folderName + "/" + file.getName());
                    }
                }
            }
        } else {
            return filenames;
        }

        return filenames;

    }





}
