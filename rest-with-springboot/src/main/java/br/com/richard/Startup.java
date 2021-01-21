package br.com.richard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import br.com.richard.config.FileStorageConfig;
// import br.com.richard.security.AES;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageConfig.class
})
@EnableAutoConfiguration
@ComponentScan
public class Startup {
	
	public static void main(String[] args) {
    SpringApplication.run(Startup.class, args);
    
    // final String secretKey = "segredoJava";

    // String originalString = "admin123";
    // String encryptedString = AES.encrypt(originalString, secretKey) ;
    // String decryptedString = AES.decrypt(encryptedString, secretKey) ;
     
    // System.out.println(originalString);
    // System.out.println(encryptedString);
    // System.out.println(decryptedString);


	}
}
