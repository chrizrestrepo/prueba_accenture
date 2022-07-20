package com.example.exercise.model;

import com.example.exercise.DTO.ClientDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client implements Serializable {

    private static final Long serialVersionUUID = 1L;

    public Client(String identification, String firstName, String lastName, String email) {
        this.identification = identification;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Client(Long clientId, String identification, String firstName, String lastName, String email) {
        this.clientId = clientId;
        this.identification = identification;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "identification")
    private String identification;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Email(message = "the emial doesn't permitted")
    private String email;

    /**
     * la opcion orphanRemoval permite decirle a JPA si se desea eliminar los registros huerfanos que no tengan relacion con
     * la tabla
     */
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Bill.class)
    private List<Bill> bills;

    public Client() {
       bills = new ArrayList<>();
    }

    public void addBill(Bill bill){
        bills.add(bill);
    }


}
