node{

   def tomcatWeb = 'D:\\apache-tomcat-8.5.76\\webapps'
   def tomcatBin = 'D:\\apache-tomcat-8.5.76\\bin'
   def tomcatStatus = ''
   stage('SCM Checkout'){
     git 'https://github.com/mathewaugustine30/spring-country-services.git'
   }
    stage('Clean-Compile'){
      // Get maven home path
      def mvnHome =  tool name: 'maven-3', type: 'maven'
      bat "${mvnHome}/bin/mvn clean compile"
      }
   stage('Package'){
      // Get maven home path
      def mvnHome =  tool name: 'maven-3', type: 'maven'
      bat "${mvnHome}/bin/mvn package"
      }
   stage('Test'){
      // Get maven home path
      def mvnHome =  tool name: 'maven-3', type: 'maven'
      bat "${mvnHome}/bin/mvn test"
      }
   stage('Deploy to Tomcat'){
     bat "copy target\\country-services-rest-api.war \"${tomcatWeb}\\country-services-rest-api.war\""
   }
      stage ('Start Tomcat Server') {
         sleep(time:5,unit:"SECONDS")
         bat "${tomcatBin}\\startup.bat"
         sleep(time:100,unit:"SECONDS")
   }
}