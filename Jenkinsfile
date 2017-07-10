#!/usr/bin/env groovy
import hudson.model.*

node {
    def version = ''

    stage('Checkout') {
        git credentialsId: 'b74a476d-7464-429c-ab8e-7ebbe03bcd1f', url: 'git@gitlab.com:qaobee/qaobee-hive.git'
        sh 'git fetch --tags'
    //    sh 'git pull'
        version = this.version()
        echo("Building $version")
    }

    stage("Build $version") {
        sh 'rm -fr build'
        sh './gradlew clean build -x test'
    }

    stage("Test $version") {
        try {
            sh './gradlew test'
        } finally {
            junit keepLongStdio: true, testResults: 'build/test-results/test/*.xml'
            step([$class: 'JacocoPublisher', classPattern: 'build/jacoco/classpathdumps/', exclusionPattern: '**/test/**/*.class', execPattern: 'build/jacoco/**.exec', inclusionPattern: 'com/qaobee/hive/**/*.class', sourcePattern: 'src/main/java'])
        }
    }

    stage("Quality $version") {
        sh "./gradlew sonarqube -x test -Dsonar.projectVersion=$version -Dsonar.login=marin.xavier -Dsonar.password=zaza66629!"
    }

    stage("Docker $version") {
        timeout(time: 30, unit: 'DAYS') {
            input 'Build docker ?'
        }
        sh "docker build -t registry.gitlab.com/qaobee/qaobee-hive:$version ."
        sh "docker tag  registry.gitlab.com/qaobee/qaobee-hive:$version registry.gitlab.com/qaobee/qaobee-hive"
        sh "docker push registry.gitlab.com/qaobee/qaobee-hive:$version"
        sh "docker push registry.gitlab.com/qaobee/qaobee-hive"
        sh "docker rmi registry.gitlab.com/qaobee/qaobee-hive:$version"
        sh "git tag -a $version -m \"$version\""
        sh "git push origin --tags"
    }

    stage("Deploy $version in REC") {
       sh "./gradlew updateRancherImage -PdockerVersion=$version"
    }

    stage("Doc $version") {
        sh 'npm install'
        sh 'node_modules/apidoc/bin/apidoc -i src/main/java/ -o build/docs/api-doc'
        sh 'git_stats generate -o build/docs/git'
        sh './gradlew gitChangelogTask javadoc'
        step([$class: 'JavadocArchiver', javadocDir: 'build/docs/javadoc/', keepAll: true])
        publishHTML (target: [
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'build/docs/api-doc',
                reportFiles: 'index.html',
                reportName: "APIDoc"
        ])
        publishHTML (target: [
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'build/docs/git',
                reportFiles: 'index.html',
                reportName: "GitStats"
        ])
        publishHTML (target: [
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'build/docs/changelog',
                reportFiles: 'index.html',
                reportName: "Changelog"
        ])
    }
}


def version() {
    def v = sh(returnStdout: true, script: 'git describe --abbrev=0 --tags').trim().substring(1).tokenize('.').toArray()
    def gitVersion = [
            major: v[0].toInteger(),
            minor: v[1].toInteger(),
            patch: v[2].toInteger() + 1
    ]
    return 'v' + gitVersion.values().join('.')
}