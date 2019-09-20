package solid.humank.ddd.commons.baseclasses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public abstract class EntityId extends ValueObject<EntityId> {

    @Getter(AccessLevel.PUBLIC)
    String abbr;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PRIVATE)
    long seqNo;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PRIVATE)
    OffsetDateTime occurredDate;

    protected EntityId() {
        this.seqNo = 0;
        this.occurredDate = OffsetDateTime.now();
    }

    protected EntityId(long seqNo, OffsetDateTime occurredDate) {

        if (seqNo < 0) {
            throw new IllegalArgumentException("SeqNo can not be negative digital");
        }

        this.seqNo = seqNo;
        this.occurredDate = occurredDate;
    }

    @Override
    public String toString() {
        String date = this.occurredDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return String.format("%s-%s-%s", this.abbr, date, this.seqNo);
    }

    //TODO: protected override IEnumerable<object> GetEqualityComponents(), 還沒想好java 這邊的實踐
}
