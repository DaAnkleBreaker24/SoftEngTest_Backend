package com.test.part1.domain;


import java.io.Serializable;


import javax.persistence.*;


@Entity
@Table(name = "groups")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    
    
    /** 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    
    /** 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    
    /** 
     * @param id
     * @return Group
     */
    public Group id(Long id) {
        this.id = id;
        return this;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return this.name;
    }

    
    /** 
     * @param name
     * @return Group
     */
    public Group name(String name) {
        this.name = name;
        return this;
    }

    
    /** 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
   
   

  

    
    /** 
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Group)) {
            return false;
        }
        return id != null && id.equals(((Group) o).id);
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
       return getClass().hashCode();
    }

    
    /** 
     * @return String
     */
    // prettier-ignore
    @Override
    public String toString() {
        return "group{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
