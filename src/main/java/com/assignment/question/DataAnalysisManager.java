package com.assignment.question;

import com.assignment.question.models.*;
import com.assignment.question.services.AnalysisAlgorithmService;
import com.assignment.question.services.DataCollectionService;
import com.assignment.question.services.DataPreprocessingService;
import com.assignment.question.services.VisualizationService;

// Part 2: Refactor existing code - Use Facade
public class DataAnalysisManager {

    // private DataCollectionService dataCollectionService;
    // private DataPreprocessingService dataPreprocessingService;
    // private AnalysisAlgorithmService analysisAlgorithmService;
    // private VisualizationService visualizationService;

    private DataAnalysisFacade dataAnalyzer;  // using single unified interface

    public DataAnalysisManager(DataCollectionService dataCollectionService,
                               DataPreprocessingService dataPreprocessingService,
                               AnalysisAlgorithmService analysisAlgorithmService,
                               VisualizationService visualizationService) {
        // this.dataCollectionService = dataCollectionService;
        // this.dataPreprocessingService = dataPreprocessingService;
        // this.analysisAlgorithmService = analysisAlgorithmService;
        // this.visualizationService = visualizationService;

        this.dataAnalyzer = new DataAnalysisFacade(dataCollectionService, dataPreprocessingService, analysisAlgorithmService, visualizationService);
    }

    /*
     * OBSERVE: Instead of directly interacting with multiple subsystems
     * (DataCollectionService, DataPreprocessingService, AnalysisAlgorithmService, VisualizationService),
     * we are using a single interface (DataAnalysisFacade)
     *   
     */
    public AnalysisResult performFullAnalysis(DataCollectionParams collectionParams,
                                              PreprocessingOptions preprocessingOptions,
                                              AnalysisAlgorithmConfig algorithmConfig) {
        // Step 1: Collect data
        // DataCollectionResult collectionResult = dataCollectionService.collectData(collectionParams);
        DataCollectionResult collectionResult = this.dataAnalyzer.collectData(collectionParams);

        // Step 2: Preprocess data
        // PreprocessedData preprocessedData = dataPreprocessingService.preprocessData(collectionResult.getData(), preprocessingOptions);
        PreprocessedData preprocessedData = this.dataAnalyzer.preprocessData(collectionResult, preprocessingOptions);

        // Step 3: Apply analysis algorithms
        // AnalysisResult analysisResult = analysisAlgorithmService.applyAnalysisAlgorithms(preprocessedData, algorithmConfig);
        AnalysisResult analysisResult = this.dataAnalyzer.analyzeData(preprocessedData, algorithmConfig);

        // Step 4: Visualize results
        // visualizationService.visualizeResults(analysisResult);
        this.dataAnalyzer.visualizeResult(analysisResult);

        return analysisResult;
    }

}
