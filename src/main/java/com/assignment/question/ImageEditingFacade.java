package com.assignment.question;

import com.assignment.question.models.Image;
import com.assignment.question.services.AnalyticsService;
import com.assignment.question.services.FilterService;
import com.assignment.question.services.ImageLoader;
import com.assignment.question.services.ImageModifier;
import com.assignment.question.services.ImageWriter;

// Part 1: Defining the Facade - Provides a unified interface to communicate with the complex subsystem
public class ImageEditingFacade {
    // subsystem object references
    private ImageLoader imageLoader;
    private FilterService filterService;
    private ImageModifier imageModifier;
    private ImageWriter imageWriter;
    private AnalyticsService analyticsService;

    // CTOR - Dependency Injection
    public ImageEditingFacade(ImageLoader imageLoader, FilterService filterService, ImageModifier imageModifier, ImageWriter imageWriter, AnalyticsService analyticsService) {
        this.imageLoader = imageLoader;
        this.filterService = filterService;
        this.imageModifier = imageModifier;
        this.imageWriter = imageWriter;
        this.analyticsService = analyticsService;
    }

    // user friendly interfaces
    public Image loadImage(String imagePath) {
        return this.imageLoader.loadImage(imagePath);
    }

    public void adjustBrightness(Image image, int brightnessLevel) {
        this.imageModifier.adjustBrightness(image, brightnessLevel);
    }

    public void applyFilter(Image image, String filterType) {
        this.filterService.applyFilter(image, filterType);
    }

    public void saveChangesToImage(Image image) {
        this.imageWriter.saveImage(image);
    }

    public void storeImage(Image image) {
        this.analyticsService.store(image);
    }
}
