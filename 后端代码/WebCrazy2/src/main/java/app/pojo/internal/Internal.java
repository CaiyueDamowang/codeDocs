package app.pojo.internal;

import javax.persistence.*;
import java.util.Date;

/**
 * @author myk
 * @description: TODO
 * @date 2020/12/20 15:31
 */
@Entity
@Table(name = "internal_tb")
public class Internal {
    @Id
    @Column(name = "internal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "internal_people")
    private String name;

    @Column(name = "internal_msg")
    private String Msg;

    @Column(name = "internal_contact")
    private String contact;

    @Column (name = "internal_startTime")
    private Date date;

    @Column (name = "internal_link")
    private String link;

    @Column (name = "internal_company")
    private String company;

    @Column (name = "internal_companyMsg")
    private String companyMsg;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyMsg() {
        return companyMsg;
    }

    public void setCompanyMsg(String companyMsg) {
        this.companyMsg = companyMsg;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
