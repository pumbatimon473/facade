package com.assignment.question;

import java.util.List;

import com.assignment.question.models.AnalysisAlgorithmConfig;
import com.assignment.question.models.AnalysisResult;
import com.assignment.question.models.DataCollectionParams;
import com.assignment.question.models.DataCollectionResult;
import com.assignment.question.models.PreprocessedData;
import com.assignment.question.models.PreprocessingOptions;
import com.assignment.question.services.AnalysisAlgorithmService;
import com.assignment.question.services.DataCollectionService;
import com.assignment.question.services.DataPreprocessingService;
import com.assignment.question.services.VisualizationService;

// Part 1: Define Facade - provides simplified interface to communicate with the complex subsystem
public class DataAnalysisFacade {
    // subsystem object references
    private DataCollectionService dataCollectionService;
    private DataPreprocessingService dataPreprocessingService;
    private AnalysisAlgorithmService analysisAlgorithmService;
    private VisualizationService visualizationService;

    // CTOR - DI (Dependency Injection)
    public DataAnalysisFacade(
        DataCollectionService dataCollectionService,
        DataPreprocessingService dataPreprocessingService,
        AnalysisAlgorithmService analysisAlgorithmService,
        VisualizationService visualizationService) {
            this.dataCollectionService = dataCollectionService;
            this.dataPreprocessingService = dataPreprocessingService;
            this.analysisAlgorithmService = analysisAlgorithmService;
            this.visualizationService = visualizationService;
        }
    
    // simplified interfaces
    public DataCollectionResult collectData(DataCollectionParams dataCollectionParams) {
        return this.dataCollectionService.collectData(dataCollectionParams);
    }

    public PreprocessedData preprocessData(DataCollectionResult collectedData, PreprocessingOptions preprocessingOptions) {
        return this.dataPreprocessingService.preprocessData(collectedData.getData(), preprocessingOptions);
    }

    public AnalysisResult analyzeData(PreprocessedData data, AnalysisAlgorithmConfig analysisConfig) {
        return this.analysisAlgorithmService.applyAnalysisAlgorithms(data, analysisConfig);
    }

    public void visualizeResult(AnalysisResult analysisResult) {
        this.visualizationService.visualizeResults(analysisResult);
    }
}
