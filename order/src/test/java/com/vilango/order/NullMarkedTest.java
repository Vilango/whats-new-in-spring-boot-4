package com.vilango.order;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaPackage;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.AbstractClassesTransformer;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ClassesTransformer;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import java.util.Set;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.all;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static java.util.function.Predicate.not;

@AnalyzeClasses(packages = "com.vilango")
public class NullMarkedTest {

	@ArchTest
	static final ArchRule packageInfoIsAnnotatedWithNullMarked = classes().that()
		.haveSimpleName("package-info")
		.should()
		.beAnnotatedWith(org.jspecify.annotations.NullMarked.class);

	@ArchTest
	static final ArchRule packageInfoIsPresentInAllNonEmptyPackages = all(packagesLocatedUnder("com.vilango"))
		.should(containAPackageInfo());

	@SuppressWarnings("SameParameterValue")
	private static ClassesTransformer<JavaPackage> packagesLocatedUnder(String startingPoint, String... exclude) {
		return new AbstractClassesTransformer<>("packages") {
			final Set<String> excludedPackages = Set.of(exclude);

			@Override
			public Iterable<JavaPackage> doTransform(JavaClasses classes) {
				return classes.getPackage(startingPoint)
					.getSubpackagesInTree()
					.stream()
					.filter(not(this::excludedPackage))
					.filter(not(this::emptyPackage))
					.toList();
			}

			private boolean excludedPackage(JavaPackage javaPackage) {
				return excludedPackages.contains(javaPackage.getName());
			}

			private boolean emptyPackage(JavaPackage javaPackage) {
				return javaPackage.getClasses().isEmpty();
			}
		};
	}

	private static ArchCondition<JavaPackage> containAPackageInfo() {
		return new ArchCondition<>("contain a package-info") {
			@Override
			public void check(JavaPackage javaPackage, ConditionEvents events) {
				if (!javaPackage.containsClassWithSimpleName("package-info")) {
					String message = "Package '%s' does not contain a package-info".formatted(javaPackage.getName());
					events.add(SimpleConditionEvent.violated(javaPackage, message));
				}
			}
		};
	}

}