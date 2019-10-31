package solid.humank.coffeeshop.cofee.sls.orders.datacontracts;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class OrderId {

    String abbr;
    long seqNo;
    OffsetDateTime createdDate;

    public OrderId() {
        this.setAbbr("ord");
    }

    public OrderId(long seqNo, OffsetDateTime createdDate) {
        this.setSeqNo(seqNo);
        this.setCreatedDate(createdDate);
        this.setAbbr("ord");
    }
}
