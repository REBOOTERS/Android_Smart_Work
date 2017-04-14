package com.example.dreamwork.bean;

import java.util.List;


/**
 * Created by co-mall on 2016/3/8.
 */
public class CheckoutUpdate {

    /**
     * coupons : [{"beginTime":"2015-11-23 11:25:00","endTime":"2016-05-12 11:07:00","id":100856759,"pic":"http://pic10.cdn.3songshu.com:81//assets/upload/coupon/68c0b82f7c0bb2766da20009951752b7.jpg","remainTime":64,"ruleId":100414119,"selected":false,"title":"主人，5元无门槛优惠券","type":"5","value":"5.0"},{"beginTime":"2015-11-23 11:25:00","endTime":"2016-05-12 11:07:00","id":100864889,"pic":"http://pic10.cdn.3songshu.com:81//assets/upload/coupon/68c0b82f7c0bb2766da20009951752b7.jpg","remainTime":64,"ruleId":100414120,"selected":false,"title":"主人，5元无门槛优惠券","type":"5","value":"5.0"},{"beginTime":"2016-02-19 14:20:00","endTime":"2016-03-31 23:59:00","id":101412846,"pic":"http://pic10.cdn.3songshu.com:81//assets/upload/coupon/68c0b82f7c0bb2766da20009951752b7.jpg","remainTime":23,"ruleId":100901063,"selected":false,"title":"主人，五元无门槛优惠券","type":"5","value":"5.0"},{"beginTime":"2016-02-19 14:20:00","endTime":"2016-03-31 23:59:00","id":101412847,"pic":"http://pic10.cdn.3songshu.com:81//assets/upload/coupon/68c0b82f7c0bb2766da20009951752b7.jpg","remainTime":23,"ruleId":100901064,"selected":false,"title":"主人，五元无门槛优惠券","type":"5","value":"5.0"}]
     * deliveryModes : [{"id":100100008,"name":"快递","selected":true}]
     * pointPaymentLimit : 1390
     * info : {"checkoutDiscountAmount":0,"checkoutDiscountFreight":0,"couponAmounts":null,"couponDiscountAmount":0,"couponDiscountFreight":0,"discountAmount":0,"discountFreight":0,"freight":8,"freightServiceFee":0,"payableAmount":27.89,"payableFreight":8,"pointDiscount":0.01,"totalAmount":19.9,"totalDiscountAmount":0.01,"totalDiscountFreight":0,"totalFreight":8}
     */

    private DataEntity data;
    /**
     * data : {"coupons":[{"beginTime":"2015-11-23 11:25:00","endTime":"2016-05-12 11:07:00","id":100856759,"pic":"http://pic10.cdn.3songshu.com:81//assets/upload/coupon/68c0b82f7c0bb2766da20009951752b7.jpg","remainTime":64,"ruleId":100414119,"selected":false,"title":"主人，5元无门槛优惠券","type":"5","value":"5.0"},{"beginTime":"2015-11-23 11:25:00","endTime":"2016-05-12 11:07:00","id":100864889,"pic":"http://pic10.cdn.3songshu.com:81//assets/upload/coupon/68c0b82f7c0bb2766da20009951752b7.jpg","remainTime":64,"ruleId":100414120,"selected":false,"title":"主人，5元无门槛优惠券","type":"5","value":"5.0"},{"beginTime":"2016-02-19 14:20:00","endTime":"2016-03-31 23:59:00","id":101412846,"pic":"http://pic10.cdn.3songshu.com:81//assets/upload/coupon/68c0b82f7c0bb2766da20009951752b7.jpg","remainTime":23,"ruleId":100901063,"selected":false,"title":"主人，五元无门槛优惠券","type":"5","value":"5.0"},{"beginTime":"2016-02-19 14:20:00","endTime":"2016-03-31 23:59:00","id":101412847,"pic":"http://pic10.cdn.3songshu.com:81//assets/upload/coupon/68c0b82f7c0bb2766da20009951752b7.jpg","remainTime":23,"ruleId":100901064,"selected":false,"title":"主人，五元无门槛优惠券","type":"5","value":"5.0"}],"deliveryModes":[{"id":100100008,"name":"快递","selected":true}],"pointPaymentLimit":1390,"info":{"checkoutDiscountAmount":0,"checkoutDiscountFreight":0,"couponAmounts":null,"couponDiscountAmount":0,"couponDiscountFreight":0,"discountAmount":0,"discountFreight":0,"freight":8,"freightServiceFee":0,"payableAmount":27.89,"payableFreight":8,"pointDiscount":0.01,"totalAmount":19.9,"totalDiscountAmount":0.01,"totalDiscountFreight":0,"totalFreight":8}}
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
        private int pointPaymentLimit;
        /**
         * checkoutDiscountAmount : 0
         * checkoutDiscountFreight : 0
         * couponAmounts : null
         * couponDiscountAmount : 0
         * couponDiscountFreight : 0
         * discountAmount : 0
         * discountFreight : 0
         * freight : 8.0
         * freightServiceFee : 0
         * payableAmount : 27.89
         * payableFreight : 8.0
         * pointDiscount : 0.01
         * totalAmount : 19.9
         * totalDiscountAmount : 0.01
         * totalDiscountFreight : 0
         * totalFreight : 8.0
         */

        private InfoEntity info;
        /**
         * beginTime : 2015-11-23 11:25:00
         * endTime : 2016-05-12 11:07:00
         * id : 100856759
         * pic : http://pic10.cdn.3songshu.com:81//assets/upload/coupon/68c0b82f7c0bb2766da20009951752b7.jpg
         * remainTime : 64
         * ruleId : 100414119
         * selected : false
         * title : 主人，5元无门槛优惠券
         * type : 5
         * value : 5.0
         */

        private List<CouponsEntity> coupons;
        /**
         * id : 100100008
         * name : 快递
         * selected : true
         */

        private List<DeliveryModesEntity> deliveryModes;

        public void setPointPaymentLimit(int pointPaymentLimit) {
            this.pointPaymentLimit = pointPaymentLimit;
        }

        public void setInfo(InfoEntity info) {
            this.info = info;
        }

        public void setCoupons(List<CouponsEntity> coupons) {
            this.coupons = coupons;
        }

        public void setDeliveryModes(List<DeliveryModesEntity> deliveryModes) {
            this.deliveryModes = deliveryModes;
        }

        public int getPointPaymentLimit() {
            return pointPaymentLimit;
        }

        public InfoEntity getInfo() {
            return info;
        }

        public List<CouponsEntity> getCoupons() {
            return coupons;
        }

        public List<DeliveryModesEntity> getDeliveryModes() {
            return deliveryModes;
        }

        public static class InfoEntity {
            private int checkoutDiscountAmount;
            private int checkoutDiscountFreight;
            private Object couponAmounts;
            private int couponDiscountAmount;
            private int couponDiscountFreight;
            private int discountAmount;
            private int discountFreight;
            private double freight;
            private int freightServiceFee;
            private double payableAmount;
            private double payableFreight;
            private double pointDiscount;
            private double totalAmount;
            private double totalDiscountAmount;
            private int totalDiscountFreight;
            private double totalFreight;

            public void setCheckoutDiscountAmount(int checkoutDiscountAmount) {
                this.checkoutDiscountAmount = checkoutDiscountAmount;
            }

            public void setCheckoutDiscountFreight(int checkoutDiscountFreight) {
                this.checkoutDiscountFreight = checkoutDiscountFreight;
            }

            public void setCouponAmounts(Object couponAmounts) {
                this.couponAmounts = couponAmounts;
            }

            public void setCouponDiscountAmount(int couponDiscountAmount) {
                this.couponDiscountAmount = couponDiscountAmount;
            }

            public void setCouponDiscountFreight(int couponDiscountFreight) {
                this.couponDiscountFreight = couponDiscountFreight;
            }

            public void setDiscountAmount(int discountAmount) {
                this.discountAmount = discountAmount;
            }

            public void setDiscountFreight(int discountFreight) {
                this.discountFreight = discountFreight;
            }

            public void setFreight(double freight) {
                this.freight = freight;
            }

            public void setFreightServiceFee(int freightServiceFee) {
                this.freightServiceFee = freightServiceFee;
            }

            public void setPayableAmount(double payableAmount) {
                this.payableAmount = payableAmount;
            }

            public void setPayableFreight(double payableFreight) {
                this.payableFreight = payableFreight;
            }

            public void setPointDiscount(double pointDiscount) {
                this.pointDiscount = pointDiscount;
            }

            public void setTotalAmount(double totalAmount) {
                this.totalAmount = totalAmount;
            }

            public void setTotalDiscountAmount(double totalDiscountAmount) {
                this.totalDiscountAmount = totalDiscountAmount;
            }

            public void setTotalDiscountFreight(int totalDiscountFreight) {
                this.totalDiscountFreight = totalDiscountFreight;
            }

            public void setTotalFreight(double totalFreight) {
                this.totalFreight = totalFreight;
            }

            public int getCheckoutDiscountAmount() {
                return checkoutDiscountAmount;
            }

            public int getCheckoutDiscountFreight() {
                return checkoutDiscountFreight;
            }

            public Object getCouponAmounts() {
                return couponAmounts;
            }

            public int getCouponDiscountAmount() {
                return couponDiscountAmount;
            }

            public int getCouponDiscountFreight() {
                return couponDiscountFreight;
            }

            public int getDiscountAmount() {
                return discountAmount;
            }

            public int getDiscountFreight() {
                return discountFreight;
            }

            public double getFreight() {
                return freight;
            }

            public int getFreightServiceFee() {
                return freightServiceFee;
            }

            public double getPayableAmount() {
                return payableAmount;
            }

            public double getPayableFreight() {
                return payableFreight;
            }

            public double getPointDiscount() {
                return pointDiscount;
            }

            public double getTotalAmount() {
                return totalAmount;
            }

            public double getTotalDiscountAmount() {
                return totalDiscountAmount;
            }

            public int getTotalDiscountFreight() {
                return totalDiscountFreight;
            }

            public double getTotalFreight() {
                return totalFreight;
            }
        }

        public static class CouponsEntity {
            private String beginTime;
            private String endTime;
            private int id;
            private String pic;
            private int remainTime;
            private int ruleId;
            private boolean selected;
            private String title;
            private String type;
            private String value;

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public void setRemainTime(int remainTime) {
                this.remainTime = remainTime;
            }

            public void setRuleId(int ruleId) {
                this.ruleId = ruleId;
            }

            public void setSelected(boolean selected) {
                this.selected = selected;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getBeginTime() {
                return beginTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public int getId() {
                return id;
            }

            public String getPic() {
                return pic;
            }

            public int getRemainTime() {
                return remainTime;
            }

            public int getRuleId() {
                return ruleId;
            }

            public boolean isSelected() {
                return selected;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }

            public String getValue() {
                return value;
            }
        }

        public static class DeliveryModesEntity {
            private int id;
            private String name;
            private boolean selected;

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setSelected(boolean selected) {
                this.selected = selected;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public boolean isSelected() {
                return selected;
            }
        }
    }
}
