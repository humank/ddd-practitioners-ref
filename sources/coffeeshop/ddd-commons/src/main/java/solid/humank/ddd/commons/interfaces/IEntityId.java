package solid.humank.ddd.commons.interfaces;

import java.time.OffsetDateTime;

public interface IEntityId {

    String abbr = null;
    long seqNo = 0;
    OffsetDateTime occuredDate = null;

}
