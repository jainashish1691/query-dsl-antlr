ependencies {
    implementation 'com.querydsl:querydsl-jpa:4.4.0'
    annotationProcessor 'com.querydsl:querydsl-apt:4.4.0:jpa'
    annotationProcessor 'javax.annotation:javax.annotation-api:1.3.2'
    implementation 'javax.annotation:javax.annotation-api:1.3.2'
}

sourceSets {
    main {
        java {
            srcDirs = ['src/main/java', 'build/generated/source/apt/main']
        }
    }
}

configurations {
    querydsl.extendsFrom compileClasspath
}

compileJava {
    options.annotationProcessorPath = configurations.querydsl
}

task generateQueryDSL(type: JavaCompile, group: 'build', description: 'Generates the QueryDSL Q-types') {
    source = sourceSets.main.java
    classpath = configurations.compileClasspath
    destinationDir = file("build/generated/source/apt/main")
    options.compilerArgs = [
        "-proc:only",
        "-processor", "com.querydsl.apt.jpa.JPAAnnotationProcessor"
    ]
}

compileJava.dependsOn generateQueryDSL
