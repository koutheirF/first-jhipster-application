package com.kouthair.jhipster;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.kouthair.jhipster");

        noClasses()
            .that()
            .resideInAnyPackage("com.kouthair.jhipster.service..")
            .or()
            .resideInAnyPackage("com.kouthair.jhipster.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.kouthair.jhipster.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
