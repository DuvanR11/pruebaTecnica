package org.example.pruebatecnica.dominio.client;


import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.dominio.product.ProductType;

import java.util.Date;
import java.util.List;


public class Client {
    private Long id;
    private ClientTypeIdentification typeIdentification;
    private String identificationNumber;
    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private List<Product> products;
    private Date createDate;
    private Date updateDate;

    public Client(Long id, ClientTypeIdentification typeIdentification, String identificationNumber, String name, String lastName, String email, Integer age) {
        this.id = id;
        this.typeIdentification = typeIdentification;
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        //this.products = products;
    }

    public Client() {}

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientTypeIdentification getTypeIdentification() {
        return typeIdentification;
    }

    public void setTypeIdentification(ClientTypeIdentification typeIdentification) {
        this.typeIdentification = typeIdentification;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Formato de correo electrónico no válido");
        }
    }

    public void validateAge(Integer age) {
        if (age == null) {
            // Puedes decidir cómo manejar el caso en que la edad es nula
            throw new IllegalArgumentException("La edad no puede ser nula");
        }

        if (age < 18) {
            throw new IllegalArgumentException("El cliente debe ser mayor de edad");
        }
    }

    public void validateFirstNameAndLastName(String name, String lastName) {
        if (name.length() < 2 || lastName.length() < 2) {
            throw new IllegalArgumentException("La longitud del nombre y apellido debe ser al menos de 2 caracteres");
        }
    }

    public boolean canAddProduct(ProductType newProductType) {
        System.out.println("los products -------- " + products);
        long cuentaCorrienteCount = products.stream()
                .filter(p -> p.getType() == ProductType.CUENTA_CORRIENTE)
                .count();

        long cuentaDeAhorrosCount = products.stream()
                .filter(p -> p.getType() == ProductType.CUENTA_DE_AHORROS)
                .count();

        if (newProductType == ProductType.CUENTA_CORRIENTE && cuentaCorrienteCount >= 1) {
            return false;
        }

        if (newProductType == ProductType.CUENTA_DE_AHORROS && cuentaDeAhorrosCount >= 1) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", typeIdentification=" + typeIdentification +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", products=" + products +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
