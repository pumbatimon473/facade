package com.assignment.question;

import com.assignment.question.models.Image;
import com.assignment.question.services.*;

// Part 2: Updating the client to make use of Facade
public class ImageEditingManager {

    // private ImageLoader imageLoader;
    // private FilterService filterService;
    // private ImageModifier imageModifier;
    // private ImageWriter imageWriter;
    // private AnalyticsService analyticsService;

    // public ImageEditingManager(ImageLoader imageLoader, FilterService filterService, ImageModifier imageModifier, ImageWriter imageWriter, AnalyticsService analyticsService) {
    //     this.imageLoader = imageLoader;
    //     this.filterService = filterService;
    //     this.imageModifier = imageModifier;
    //     this.imageWriter = imageWriter;
    //     this.analyticsService = analyticsService;
    // }

    
    private ImageEditingFacade imageEditor;

    public ImageEditingManager(ImageLoader imageLoader, FilterService filterService, ImageModifier imageModifier, ImageWriter imageWriter, AnalyticsService analyticsService) {
        this.imageEditor = new ImageEditingFacade(imageLoader, filterService, imageModifier, imageWriter, analyticsService);
    }


    // public void editImage(String imagePath, String filterType, int brightness) {

    //     Image image = imageLoader.loadImage(imagePath);

    //     filterService.applyFilter(image, filterType);
    //     imageModifier.adjustBrightness(image, brightness);

    //     imageWriter.saveImage(image);
    //     analyticsService.store(image);
    // }

    public void editImage(String imagePath, String filterType, int brightness) {
        Image image = this.imageEditor.loadImage(imagePath);
        this.imageEditor.applyFilter(image, filterType);
        this.imageEditor.adjustBrightness(image, brightness);
        this.imageEditor.saveChangesToImage(image);
        this.imageEditor.storeImage(image);
    }

}