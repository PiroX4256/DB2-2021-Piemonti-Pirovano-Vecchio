package it.polimi.db2.db2project.model;

import java.util.List;

public class QuestionnaireDTO {

    private final ProductDTO productDTO;
    private final List<String> marketingQuestions;

    public QuestionnaireDTO(ProductDTO productDTO, List<String> marketingQuestions) {
        this.productDTO = productDTO;
        this.marketingQuestions = marketingQuestions;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public List<String> getMarketingQuestions() {
        return marketingQuestions;
    }
}
