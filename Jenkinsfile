#!/usr/bin/env groovy
import hudson.model.*

node {
    def rancherCli = 'v0.8.6'
    def version = ''

    stage('Checkout') {
        sh 'git fetch --tags'
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

    stage("Doc $version") {
        sh './gradlew apidoc -x test'
        sh 'git_stats generate -o docs/git'
        step([$class: 'JavadocArchiver', javadocDir: 'build/docs/javadoc/', keepAll: true])
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
        sh "wget https://github.com/rancher/rancher-compose/releases/download/$rancherCli/rancher-compose-linux-amd64-$rancherCli" + ".tar.gz"
        sh "tar -zxf rancher-compose-linux-amd64-$rancherCli" + ".tar.gz"
        sh "rm -f rancher-compose-linux-amd64-$rancherCli" + ".tar.gz"
        sh "cat > docker-compose.yml <<EOC\n" +
                "qaobee-hive:\n" +
                "  ports:\n" +
                "  - 8080:8080/tcp\n" +
                "  environment:\n" +
                "    ENV: REC\n" +
                "    OPENSHIFT_DATA_DIR: /opt/hive-data\n" +
                "    OPENSHIFT_MONGODB_DB_HOST: mongo\n" +
                "    OPENSHIFT_MONGODB_DB_PASSWORD: qaobee2016\n" +
                "    OPENSHIFT_MONGODB_DB_PORT: '27017'\n" +
                "    OPENSHIFT_MONGODB_DB_USERNAME: hive\n" +
                "  log_driver: ''\n" +
                "  labels:\n" +
                "    io.rancher.container.pull_image: always\n" +
                "    io.rancher.scheduler.affinity:host_label: tag=hive\n" +
                "    io.rancher.container.dns: 'true'\n" +
                "  image: registry.gitlab.com/qaobee/qaobee-hive:$version\n" +
                "  links:\n" +
                "  - qaobeehivedb:mongo\n" +
                "  volumes:\n" +
                "  - /opt/qaobee-hive:/opt/hive-data\n" +
                "  net: host\n" +
                "EOC"
        sh "./rancher-compose-$rancherCli/rancher-compose \\\n" +
                "--url http://vps234741.ovh.net:8080 \\\n" +
                "--access-key 854D77F36BD20C5D89FE \\\n" +
                "--secret-key p8ktQVdpEdGp4rwfJCfFoq5abCL2eYTXSHwee3ot  \\\n" +
                "--project-name Qaobee-Recette \\\n" +
                "up -d --force-upgrade qaobee-hive\n" +
                "./rancher-compose-$rancherCli/rancher-compose \\\n" +
                "--url http://vps234741.ovh.net:8080 \\\n" +
                "--access-key 854D77F36BD20C5D89FE \\\n" +
                "--secret-key p8ktQVdpEdGp4rwfJCfFoq5abCL2eYTXSHwee3ot  \\\n" +
                "--project-name Qaobee-Recette \\\n" +
                "up -d --upgrade --confirm-upgrade"
        sh "rm -f docker-compose.yml"
        sh "rm -fr rancher-compose-$rancherCli"
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