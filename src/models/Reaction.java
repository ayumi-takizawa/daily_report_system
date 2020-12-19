package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "reactions")
@NamedQueries({
    @NamedQuery(
            name = "getGoodCount",
            query = "SELECT COUNT(g)  FROM Reaction AS g WHERE g.good_flag = 1 AND g.report=:report"
            ),
    @NamedQuery(
            name = "getMyAllGood",
            query = "SELECT g FROM Reaction AS g WHERE g.report= :report AND g.good_flag = 1"
            ),
    @NamedQuery(
            name="checkGood_flag",
            query="SELECT g FROM Reaction AS g WHERE g.good_flag=1 AND g.employee = :employee AND g.report=:report"
            )
})
@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer  id;

    @JoinColumn(name="good_flag",nullable=false)
    private Integer good_flag;
    //goodしたかどうか（してない：０/した：１）

    @Lob     //このアノテーションは改行もデータベースに保存されるようになる
    @Column(name = "coment", nullable = true)
    private String coment;

    @ManyToOne     //1対多
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne     //1対多
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGood_flag() {
        return good_flag;
    }

    public void setGood_flag(Integer good_flag) {
        this.good_flag = good_flag;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

}
