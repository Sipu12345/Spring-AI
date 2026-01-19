package com.dibyatech.analyzer;

import com.dibyatech.model.RiskLevel;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JavaCodeAnalyzer {
    public List<String> findDeprecatedApis(String code) {
        Set<String> suggestions = new HashSet<>();

        CompilationUnit cu;
        try {
            cu = StaticJavaParser.parse(code);
        } catch (Exception e) {
            return new ArrayList<>(suggestions); // parsing failed, return empty
        }

        // Detect deprecated APIs too
        cu.findAll(com.github.javaparser.ast.type.ClassOrInterfaceType.class)
                .forEach(type -> {
                    if ("Date".equals(type.getNameAsString())) {
                        suggestions.add("Replace java.util.Date with java.time.LocalDate");
                    }
                });

        // Detect traditional for-loops
        cu.findAll(ForStmt.class).forEach(f ->
                suggestions.add("Consider converting for-loop to Stream API"));

        // Detect for-each loops
        cu.findAll(ForEachStmt.class).forEach(f ->
                suggestions.add("Consider converting for-each loop to Stream API"));

        return new ArrayList<>(suggestions);
    }

    public List<String> modernizationOpportunities(String code) {
        Set<String> suggestions = new HashSet<>();

        CompilationUnit cu;
        try {
            cu = StaticJavaParser.parse(code);
        } catch (Exception e) {
            return new ArrayList<>(suggestions); // return empty if parsing fails
        }

        // Suggest records for final classes with only fields
        cu.findAll(com.github.javaparser.ast.body.ClassOrInterfaceDeclaration.class)
                .forEach(c -> {
                    if (c.isFinal() && c.getMethods().isEmpty()) {
                        suggestions.add("Consider using Java records");
                    }
                });
        // Suggest pattern matching for instanceof
        if (code.contains("instanceof")) {
            suggestions.add("Use pattern matching for instanceof");
        }

        // Detect for-loops → Streams
        cu.findAll(ForStmt.class).forEach(f ->
                suggestions.add("Consider converting for-loop to Stream API"));
        cu.findAll(ForEachStmt.class).forEach(f ->
                suggestions.add("Consider converting for-each loop to Stream API"));

        // Deprecated API detection (optional)
        cu.findAll(ClassOrInterfaceType.class).forEach(type -> {
            if ("Date".equals(type.getNameAsString())) {
                suggestions.add("Replace java.util.Date with java.time.LocalDate");
            }
        });

        return new ArrayList<>(suggestions);
    }

    public int calculateComplexity(String code) {
        try {
            CompilationUnit cu = StaticJavaParser.parse(code);
            int complexity = 1; // start with 1

            complexity += cu.findAll(IfStmt.class).size();
            complexity += cu.findAll(ForStmt.class).size();
            complexity += cu.findAll(ForEachStmt.class).size();
            complexity += cu.findAll(WhileStmt.class).size();
            complexity += cu.findAll(SwitchEntry.class).size();

            return Math.min(complexity, 10); // cap at 10
        } catch (Exception e) {
            return 1; // fallback
        }
    }


    /** Risk assessment based on known risky APIs / constructs */
    public RiskLevel assessRisk(String code) {
        try {
            CompilationUnit cu = StaticJavaParser.parse(code);

            // HIGH risk: Thread.stop()
            if (code.contains("Thread.stop")) {
                return RiskLevel.HIGH;
            }

            // MEDIUM risk: synchronized block or synchronized method
            boolean hasSynchronizedMethod = cu.findAll(MethodDeclaration.class)
                    .stream()
                    .anyMatch(MethodDeclaration::isSynchronized);

            boolean hasSynchronizedBlock = !cu.findAll(SynchronizedStmt.class).isEmpty();

            if (hasSynchronizedMethod || hasSynchronizedBlock) {
                return RiskLevel.MEDIUM;
            }

        } catch (Exception e) {
            // ignore parsing errors
        }

        return RiskLevel.LOW;
    }
}
