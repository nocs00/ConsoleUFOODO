package com.ufood.db.dao.mongodb;

/**
 * Created by pdudenkov on 03.07.2016.
 */
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;

public abstract class AbstractEntity {

    @Id
    @Property("id")
    private ObjectId id;

    @Version
    @Property("version")
    private Long version;

    public AbstractEntity() {
        super();
    }

    protected ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    protected Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
