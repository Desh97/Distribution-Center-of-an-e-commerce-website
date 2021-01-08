export class orders
{
    orderNo : string;
    orderStatus : string;
    //Hashmap
    orderItems:object;
    

    

    constructor(orderNo, orderItems, orderStatus)

    {
        this.orderNo= orderNo;
       this. orderItems =  orderItems;
        this.orderStatus = orderStatus;
        
    }
}