package codegym.model;


import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Entity
@Table
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min = 5, max = 45)
    private String firstName;

    @NotEmpty
    @Size(min = 5,max = 45)
    private String lastName;


    @Min(value = 18,message = "You must older than 18")
    private int age;

    @NotEmpty
    private String phoneNumber;

    private String email;

    public User() {
    }
    public User(String firstName,String lastName,int age,String phoneNumber,String email) {
        this.firstName = firstName;
        this.lastName=lastName;
        this.age=age;
        this.phoneNumber=phoneNumber;
        this.email=email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String number = user.getPhoneNumber();
        String email = user.getEmail();
        if(number.length()>11||number.length()<10){
            errors.rejectValue("phoneNumber","","Phonenumber have 10-11 numbers only");
        }
        if(!number.startsWith("0")){
            errors.rejectValue("phoneNumber","","Should begin with 0");
        }
        if(!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("phoneNumber","","Include number only");
        }
        if(!email.matches("^[A-Za-z0-9]+[\\_A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")){
            errors.rejectValue("email","","Invalid email");
        }
    }
}
