package com.dibyatech.analyzer;

import com.dibyatech.model.RiskLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JavaCodeAnalyzerTest {

    private JavaCodeAnalyzer analyzer;

    @BeforeEach
    void setup() {
        analyzer = new JavaCodeAnalyzer();
    }

    // ---------------------
    // Deprecated APIs tests
    // ---------------------
    @Test
    void testFindDeprecatedApis_Date() {
        String code = """
            import java.util.Date;
            public class Test {
                private Date date = new Date();
            }
        """;

        List<String> deprecated = analyzer.findDeprecatedApis(code);

        assertEquals(1, deprecated.size());
        assertTrue(deprecated.contains("Replace java.util.Date with java.time.LocalDate"));
    }

    @Test
    void testFindDeprecatedApis_NoDeprecated() {
        String code = "public class Test {}";
        List<String> deprecated = analyzer.findDeprecatedApis(code);
        assertTrue(deprecated.isEmpty());
    }

    // -----------------------------
    // Modernization opportunities
    // -----------------------------
    @Test
    void testModernizationOpportunities_ForEachLoop() {
        String code = """
            import java.util.List;
            public class Test {
                public void sum(List<Integer> nums) {
                    int total = 0;
                    for(int n : nums) { total += n; }
                }
            }
        """;

        List<String> suggestions = analyzer.modernizationOpportunities(code);

        assertTrue(suggestions.stream().anyMatch(s -> s.toLowerCase().contains("stream")));
    }

    @Test
    void testModernizationOpportunities_RecordSuggestion() {
        String code = """
            public final class DataHolder {
                private int x;
                private int y;
            }
        """;

        List<String> suggestions = analyzer.modernizationOpportunities(code);

        assertTrue(suggestions.stream().anyMatch(s -> s.toLowerCase().contains("record")));
    }

    @Test
    void testModernizationOpportunities_Instanceof() {
        String code = """
            public class Test {
                public void check(Object obj) {
                    if(obj instanceof String) {}
                }
            }
        """;

        List<String> suggestions = analyzer.modernizationOpportunities(code);

        assertTrue(suggestions.stream().anyMatch(s -> s.toLowerCase().contains("instanceof")));
    }

    // -----------------------------
    // Complexity tests
    // -----------------------------
    @Test
    void testCalculateComplexity_Simple() {
        String code = "public class Test {}";
        int complexity = analyzer.calculateComplexity(code);
        assertEquals(1, complexity); // minimum complexity
    }

    @Test
    void testCalculateComplexity_Large() {
        String codeBuilder = "public class Test {\n" +
                "    public void testMethod() {\n" +
                "        " + "if(true){}\n".repeat(50) +
                "    }\n" +
                "}";

        int complexity = analyzer.calculateComplexity(codeBuilder);

        assertEquals(10, complexity); // capped at 10
    }

    // -----------------------------
    // Risk assessment tests
    // -----------------------------
    @Test
    void testAssessRisk_High() {
        String code = "public class Test { void run(){ Thread.stop(); } }";
        assertEquals(RiskLevel.HIGH, analyzer.assessRisk(code));
    }

    @Test
    void testAssessRisk_Medium() {
        String code = "public class Test { synchronized void run(){} }";
        assertEquals(RiskLevel.MEDIUM, analyzer.assessRisk(code));
    }

    @Test
    void testAssessRisk_Low() {
        String code = "public class Test { void run(){} }";
        assertEquals(RiskLevel.LOW, analyzer.assessRisk(code));
    }
}
