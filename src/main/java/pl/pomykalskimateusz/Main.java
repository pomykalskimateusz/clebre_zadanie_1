package pl.pomykalskimateusz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@EnableCassandraRepositories
@SpringBootApplication
public class Main
{
    public static void main(String[] args)
    {
        SpringApplication.run(Main.class, args);
    }
}