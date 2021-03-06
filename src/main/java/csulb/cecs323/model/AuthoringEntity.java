package csulb.cecs323.model;

import javax.persistence.*;

@Entity
@NamedNativeQuery(
        name = "AuthoringEntitiesPK",
        query = "SELECT AE.*  FROM AuthoringEntity AE",
        resultClass = AuthoringEntity.class
)
@NamedNativeQuery(
        name = "FindWritingGroupUsingEmail",
        query = "Select AE.* From AuthoringEntity AE WHERE AUTHORING_ENTITY_TYPE = 'Writing Group' and email = ? ",
        resultClass = WritingGroup.class
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="AUTHORING_ENTITY_TYPE", length=31, discriminatorType=DiscriminatorType.STRING)
//@Table(name="AUTHORING_ENTITIES")
public class AuthoringEntity {
    @Id
    @Column(name="EMAIL", length = 30, nullable = false)
    private String email;

    @Column(name="NAME", length = 80, nullable = false)
    private String name;

    public AuthoringEntity() {}

    public AuthoringEntity(String email, String name) {
        this.setEmail(email);
        this.setName(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName(){ return this.name;}

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public String getDiscrimatorValue(){
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }
}
