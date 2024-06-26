module com.learning.learning {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.learning.learning.model to javafx.base, org.hibernate.orm.core;
    opens com.learning.learning to javafx.fxml;

    exports com.learning.learning;
}
