package com.ironhack.Bank.model.users;

import com.ironhack.Bank.helpers.Address;
import com.ironhack.Bank.model.Checking;
import com.ironhack.Bank.model.CreditCard;
import com.ironhack.Bank.model.Savings;
import com.ironhack.Bank.model.StudentChecking;
import com.ironhack.Bank.validators.EmailValidator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;

@Entity
public class AccountHolder implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_holder_id", nullable = false, updatable = false)
    private Integer id;

    private String userName;
    private String password;

    private String name;
    private LocalDate dateBirth;
    private Address primaryAddress;
    @Column(name = "mail",nullable = true,unique=true)
    private String mailingAddress;

    private boolean enabled = true;
    private EmailValidator emailValidator = new EmailValidator();


    @OneToOne
    private Checking checking;

    @OneToOne
    private Savings savings;

    @OneToOne
    private StudentChecking studentChecking;

    @OneToOne
    private CreditCard creditCard;


    //getters & setters

    public Integer getId() {
        return id;
    }


    @Override
    public String getUsername() {
        return null;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth=dateBirth;

    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) throws Exception {
        if(!emailValidator.validateEmail(mailingAddress)){
            throw new Exception("Email format isn't correct");
        }
        this.mailingAddress=mailingAddress;
    }

    public Checking getChecking() {
        return checking;
    }

    public void setChecking(Checking checking) {
        this.checking = checking;
    }

    public Savings getSavings() {
        return savings;
    }

    public void setSavings(Savings savings) {
        this.savings = savings;
    }

    public StudentChecking getStudentChecking() {
        return studentChecking;
    }

    public void setStudentChecking(StudentChecking studentChecking) {
        this.studentChecking = studentChecking;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public boolean isStudent(){
        LocalDate currentDate = LocalDate.now();
        return Period.between(this.dateBirth,currentDate).getYears()<24 ? true : false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "AccountHolder{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", dateBirth=" + dateBirth +
                ", primaryAddress=" + primaryAddress +
                ", mailingAddress='" + mailingAddress + '\'' +
                ", enabled=" + enabled +
                ", emailValidator=" + emailValidator +
                ", checking=" + checking +
                ", savings=" + savings +
                ", studentChecking=" + studentChecking +
                ", creditCard=" + creditCard +
                '}';
    }
}
