package solid.humank.coffeeshop.order.persistence;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import solid.humank.coffeeshop.order.models.Order;
import solid.humank.coffeeshop.order.models.OrderItem;
import solid.humank.coffeeshop.order.models.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@DynamoDBTable(tableName="order")
public class OrderPO {

    public OrderPO(Order order) {
        this.id = order.getId().getSeqNo();
        order.getTableNo();
        this.status = order.getStatus();
        this.orderItems = order.getOrderItems();
        this.totalFee = order.totalFee();
        this.createdDate = order.getCreatedDate();
        this.modifiedDate = order.getModifiedDate();
    }

    @DynamoDBHashKey(attributeName = "SeqNo")
    private long id;

    @DynamoDBAttribute(attributeName="tableNo")
    private String tableNo;

    @DynamoDBAttribute(attributeName="status")
    private OrderStatus status;

    @DynamoDBAttribute(attributeName="orderItems")
    private List<OrderItem> orderItems;

    @DynamoDBAttribute(attributeName="totalFee")
    private BigDecimal totalFee;

    @DynamoDBAttribute(attributeName="createdDate")
    private OffsetDateTime createdDate;

    @DynamoDBAttribute(attributeName="modifiedDate")
    private OffsetDateTime modifiedDate;
}
