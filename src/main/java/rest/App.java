package rest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import rest.configuration.MyConfig;
import rest.entity.Communication;

public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean(Communication.class);
        communication.getAllUsers();

        ResponseEntity<String> saveUser = communication.saveUser();
        ResponseEntity<String> updateUser = communication.updateUser();
        ResponseEntity<String> deleteUser = communication.deleteUser();

        String result = saveUser.toString().substring(12, 17) +
                updateUser.toString().substring(12, 17) +
                deleteUser.toString().substring(12, 17);

        System.out.println(result);

    }
}
