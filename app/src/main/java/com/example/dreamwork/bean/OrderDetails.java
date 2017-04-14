package com.example.dreamwork.bean;

import java.util.List;


/**
 * Created by co-mall on 2016/3/14.
 */
public class OrderDetails {


    /**
     * addTime :
     * address : 世界上最遥远的距离
     * cancelTime :
     * cardPaymentValue : 0.00
     * countDown :
     * deliveryGoodsTime :
     * deliveryModeId : 100100008
     * deliveryModeName : 快递
     * deliveryOntime :
     * expressName :
     * expressNumber :
     * freightFee : 8.00
     * freightFeeDiscount : 0.00
     * freightServiceFee : 0.00
     * goodsDiscountAmount : 0.00
     * hasLimit : false
     * id : 100200072
     * invoiceCategory :
     * invoiceContent :
     * invoiceTitle :
     * invoiceType : 0
     * invoiceTypeName :
     * isCancelable : true
     * isCommentable : false
     * isDeleteable : false
     * isPayable : true
     * isRebuyable : false
     * isReceiveable : false
     * isServiceable : false
     * memberId : 100477669
     * mobile : 13108569636
     * needInvoice : false
     * orderAmount : 26.90
     * orderCreateTime : 2016-03-14 14:07:17
     * orderNumber : 8160314140717004272
     * orderStatus : 1
     * orderStatusList : ["2016-03-14 14:07:17"]
     * orderWeight : 0.235
     * payModeName :
     * payType : 2
     * payTypeName : 第三方在线支付
     * payableAmount : 26.90
     * payedTime :
     * pointPaymentValue : 0.00
     * processShowType : 1
     * productAmount : 18.90
     * productNum : 1
     * products : [{"goodsId":100100444,"isLimit":false,"itemType":1,"name":"三只松鼠 碧根果210g","pic":"http://pic10.cdn.3songshu.com:81//assets/upload/product/0ddba4d5be416ef69fe4b7ab420a9066_200x200.jpg","price":"18.90","productId":100100444,"quantity":1,"styleName":"其它"}]
     * receivedTime :
     * receiverName : 测试
     * regionName : 北京大兴
     * serviceStatus : 0
     * status : false
     */

    private DataEntity data;
    /**
     * data : {"addTime":"","address":"世界上最遥远的距离","cancelTime":"","cardPaymentValue":"0.00","countDown":"","deliveryGoodsTime":"","deliveryModeId":"100100008","deliveryModeName":"快递","deliveryOntime":"","expressName":"","expressNumber":"","freightFee":"8.00","freightFeeDiscount":"0.00","freightServiceFee":"0.00","goodsDiscountAmount":"0.00","hasLimit":false,"id":100200072,"invoiceCategory":"","invoiceContent":"","invoiceTitle":"","invoiceType":0,"invoiceTypeName":"","isCancelable":true,"isCommentable":false,"isDeleteable":false,"isPayable":true,"isRebuyable":false,"isReceiveable":false,"isServiceable":false,"memberId":100477669,"mobile":"13108569636","needInvoice":false,"orderAmount":"26.90","orderCreateTime":"2016-03-14 14:07:17","orderNumber":"8160314140717004272","orderStatus":1,"orderStatusList":["2016-03-14 14:07:17"],"orderWeight":"0.235","payModeName":"","payType":2,"payTypeName":"第三方在线支付","payableAmount":"26.90","payedTime":"","pointPaymentValue":"0.00","processShowType":1,"productAmount":"18.90","productNum":1,"products":[{"goodsId":100100444,"isLimit":false,"itemType":1,"name":"三只松鼠 碧根果210g","pic":"http://pic10.cdn.3songshu.com:81//assets/upload/product/0ddba4d5be416ef69fe4b7ab420a9066_200x200.jpg","price":"18.90","productId":100100444,"quantity":1,"styleName":"其它"}],"receivedTime":"","receiverName":"测试","regionName":"北京大兴","serviceStatus":0,"status":false}
     * stateCode : 0
     */

    private int stateCode;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public DataEntity getData() {
        return data;
    }

    public int getStateCode() {
        return stateCode;
    }

    public static class DataEntity {
        private String addTime;
        private String address;
        private String cancelTime;
        private String cardPaymentValue;
        private String countDown;
        private String deliveryGoodsTime;
        private String deliveryModeId;
        private String deliveryModeName;
        private String deliveryOntime;
        private String expressName;
        private String expressNumber;
        private String freightFee;
        private String freightFeeDiscount;
        private String freightServiceFee;
        private String goodsDiscountAmount;
        private boolean hasLimit;
        private int id;
        private String invoiceCategory;
        private String invoiceContent;
        private String invoiceTitle;
        private int invoiceType;
        private String invoiceTypeName;
        private boolean isCancelable;
        private boolean isCommentable;
        private boolean isDeleteable;
        private boolean isPayable;
        private boolean isRebuyable;
        private boolean isReceiveable;
        private boolean isServiceable;
        private int memberId;
        private String mobile;
        private boolean needInvoice;
        private String orderAmount;
        private String orderCreateTime;
        private String orderNumber;
        private int orderStatus;
        private String orderWeight;
        private String payModeName;
        private int payType;
        private String payTypeName;
        private String payableAmount;
        private String payedTime;
        private String pointPaymentValue;
        private int processShowType;
        private String productAmount;
        private int productNum;
        private String receivedTime;
        private String receiverName;
        private String regionName;
        private int serviceStatus;
        private boolean status;
        private List<String> orderStatusList;
        /**
         * goodsId : 100100444
         * isLimit : false
         * itemType : 1
         * name : 三只松鼠 碧根果210g
         * pic : http://pic10.cdn.3songshu.com:81//assets/upload/product/0ddba4d5be416ef69fe4b7ab420a9066_200x200.jpg
         * price : 18.90
         * productId : 100100444
         * quantity : 1
         * styleName : 其它
         */

        private List<ProductsEntity> products;

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setCancelTime(String cancelTime) {
            this.cancelTime = cancelTime;
        }

        public void setCardPaymentValue(String cardPaymentValue) {
            this.cardPaymentValue = cardPaymentValue;
        }

        public void setCountDown(String countDown) {
            this.countDown = countDown;
        }

        public void setDeliveryGoodsTime(String deliveryGoodsTime) {
            this.deliveryGoodsTime = deliveryGoodsTime;
        }

        public void setDeliveryModeId(String deliveryModeId) {
            this.deliveryModeId = deliveryModeId;
        }

        public void setDeliveryModeName(String deliveryModeName) {
            this.deliveryModeName = deliveryModeName;
        }

        public void setDeliveryOntime(String deliveryOntime) {
            this.deliveryOntime = deliveryOntime;
        }

        public void setExpressName(String expressName) {
            this.expressName = expressName;
        }

        public void setExpressNumber(String expressNumber) {
            this.expressNumber = expressNumber;
        }

        public void setFreightFee(String freightFee) {
            this.freightFee = freightFee;
        }

        public void setFreightFeeDiscount(String freightFeeDiscount) {
            this.freightFeeDiscount = freightFeeDiscount;
        }

        public void setFreightServiceFee(String freightServiceFee) {
            this.freightServiceFee = freightServiceFee;
        }

        public void setGoodsDiscountAmount(String goodsDiscountAmount) {
            this.goodsDiscountAmount = goodsDiscountAmount;
        }

        public void setHasLimit(boolean hasLimit) {
            this.hasLimit = hasLimit;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setInvoiceCategory(String invoiceCategory) {
            this.invoiceCategory = invoiceCategory;
        }

        public void setInvoiceContent(String invoiceContent) {
            this.invoiceContent = invoiceContent;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }

        public void setInvoiceType(int invoiceType) {
            this.invoiceType = invoiceType;
        }

        public void setInvoiceTypeName(String invoiceTypeName) {
            this.invoiceTypeName = invoiceTypeName;
        }

        public void setIsCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
        }

        public void setIsCommentable(boolean isCommentable) {
            this.isCommentable = isCommentable;
        }

        public void setIsDeleteable(boolean isDeleteable) {
            this.isDeleteable = isDeleteable;
        }

        public void setIsPayable(boolean isPayable) {
            this.isPayable = isPayable;
        }

        public void setIsRebuyable(boolean isRebuyable) {
            this.isRebuyable = isRebuyable;
        }

        public void setIsReceiveable(boolean isReceiveable) {
            this.isReceiveable = isReceiveable;
        }

        public void setIsServiceable(boolean isServiceable) {
            this.isServiceable = isServiceable;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setNeedInvoice(boolean needInvoice) {
            this.needInvoice = needInvoice;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public void setOrderCreateTime(String orderCreateTime) {
            this.orderCreateTime = orderCreateTime;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public void setOrderWeight(String orderWeight) {
            this.orderWeight = orderWeight;
        }

        public void setPayModeName(String payModeName) {
            this.payModeName = payModeName;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public void setPayTypeName(String payTypeName) {
            this.payTypeName = payTypeName;
        }

        public void setPayableAmount(String payableAmount) {
            this.payableAmount = payableAmount;
        }

        public void setPayedTime(String payedTime) {
            this.payedTime = payedTime;
        }

        public void setPointPaymentValue(String pointPaymentValue) {
            this.pointPaymentValue = pointPaymentValue;
        }

        public void setProcessShowType(int processShowType) {
            this.processShowType = processShowType;
        }

        public void setProductAmount(String productAmount) {
            this.productAmount = productAmount;
        }

        public void setProductNum(int productNum) {
            this.productNum = productNum;
        }

        public void setReceivedTime(String receivedTime) {
            this.receivedTime = receivedTime;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public void setServiceStatus(int serviceStatus) {
            this.serviceStatus = serviceStatus;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public void setOrderStatusList(List<String> orderStatusList) {
            this.orderStatusList = orderStatusList;
        }

        public void setProducts(List<ProductsEntity> products) {
            this.products = products;
        }

        public String getAddTime() {
            return addTime;
        }

        public String getAddress() {
            return address;
        }

        public String getCancelTime() {
            return cancelTime;
        }

        public String getCardPaymentValue() {
            return cardPaymentValue;
        }

        public String getCountDown() {
            return countDown;
        }

        public String getDeliveryGoodsTime() {
            return deliveryGoodsTime;
        }

        public String getDeliveryModeId() {
            return deliveryModeId;
        }

        public String getDeliveryModeName() {
            return deliveryModeName;
        }

        public String getDeliveryOntime() {
            return deliveryOntime;
        }

        public String getExpressName() {
            return expressName;
        }

        public String getExpressNumber() {
            return expressNumber;
        }

        public String getFreightFee() {
            return freightFee;
        }

        public String getFreightFeeDiscount() {
            return freightFeeDiscount;
        }

        public String getFreightServiceFee() {
            return freightServiceFee;
        }

        public String getGoodsDiscountAmount() {
            return goodsDiscountAmount;
        }

        public boolean isHasLimit() {
            return hasLimit;
        }

        public int getId() {
            return id;
        }

        public String getInvoiceCategory() {
            return invoiceCategory;
        }

        public String getInvoiceContent() {
            return invoiceContent;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public int getInvoiceType() {
            return invoiceType;
        }

        public String getInvoiceTypeName() {
            return invoiceTypeName;
        }

        public boolean isIsCancelable() {
            return isCancelable;
        }

        public boolean isIsCommentable() {
            return isCommentable;
        }

        public boolean isIsDeleteable() {
            return isDeleteable;
        }

        public boolean isIsPayable() {
            return isPayable;
        }

        public boolean isIsRebuyable() {
            return isRebuyable;
        }

        public boolean isIsReceiveable() {
            return isReceiveable;
        }

        public boolean isIsServiceable() {
            return isServiceable;
        }

        public int getMemberId() {
            return memberId;
        }

        public String getMobile() {
            return mobile;
        }

        public boolean isNeedInvoice() {
            return needInvoice;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public String getOrderCreateTime() {
            return orderCreateTime;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public String getOrderWeight() {
            return orderWeight;
        }

        public String getPayModeName() {
            return payModeName;
        }

        public int getPayType() {
            return payType;
        }

        public String getPayTypeName() {
            return payTypeName;
        }

        public String getPayableAmount() {
            return payableAmount;
        }

        public String getPayedTime() {
            return payedTime;
        }

        public String getPointPaymentValue() {
            return pointPaymentValue;
        }

        public int getProcessShowType() {
            return processShowType;
        }

        public String getProductAmount() {
            return productAmount;
        }

        public int getProductNum() {
            return productNum;
        }

        public String getReceivedTime() {
            return receivedTime;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public String getRegionName() {
            return regionName;
        }

        public int getServiceStatus() {
            return serviceStatus;
        }

        public boolean isStatus() {
            return status;
        }

        public List<String> getOrderStatusList() {
            return orderStatusList;
        }

        public List<ProductsEntity> getProducts() {
            return products;
        }

        public static class ProductsEntity {
            private int goodsId;
            private boolean isLimit;
            private int itemType;
            private String name;
            private String pic;
            private String price;
            private int productId;
            private int quantity;
            private String styleName;

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public void setIsLimit(boolean isLimit) {
                this.isLimit = isLimit;
            }

            public void setItemType(int itemType) {
                this.itemType = itemType;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public void setStyleName(String styleName) {
                this.styleName = styleName;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public boolean isIsLimit() {
                return isLimit;
            }

            public int getItemType() {
                return itemType;
            }

            public String getName() {
                return name;
            }

            public String getPic() {
                return pic;
            }

            public String getPrice() {
                return price;
            }

            public int getProductId() {
                return productId;
            }

            public int getQuantity() {
                return quantity;
            }

            public String getStyleName() {
                return styleName;
            }
        }
    }
}
