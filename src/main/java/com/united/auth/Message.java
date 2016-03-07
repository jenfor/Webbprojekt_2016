/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.united.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author jenny
 */

@Entity
@Table(name="MESSAGES")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(nullable = false)  // unique is implied
    protected String id;
    @Column(nullable = false)
    protected String text;
    //@ElementCollection(fetch = FetchType.LAZY)
   /*@CollectionTable(name = "MESSAGES_GROUPS", 
            joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)*/
    //protected List<Groups> groups = new ArrayList<>();

    public Message() {
    }

    public Message(String id, String text/*, Groups group*/) {
        this.id = id;
        this.text = text;
        //groups.add(group);
    }
/*
    public void addGroup(Groups group) {
        groups.add(group);
    }

    public void removeGroup(Groups group) {
        groups.remove(group);
    }

    public List<Groups> getGroups() {
        return groups;
    }
*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }

}