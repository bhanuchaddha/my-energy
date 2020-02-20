package com.bhanuchaddha.myenergy;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class RecommendationTest {

    @Test
    public void testCorrectRecommendation(){
        new GenerateTestData().generate();
        HelperMethods.getRecommendation("smart-meter-0")
                .then()
                .statusCode(200)
                .assertThat()
                .body("[0].price-plan-2", equalTo(10.3f));
    }
}
