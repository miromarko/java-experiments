package ro.springsoft.model.common;

import java.io.Serializable;

public interface IPersistent extends Serializable {
    String getInternalId(); //DB ID

    String getBusinessId(); //businessID
}
