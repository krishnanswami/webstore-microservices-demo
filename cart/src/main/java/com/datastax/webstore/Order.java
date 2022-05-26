package com.datastax.webstore;

public class Order {
    // {"orderid": "12321","total": "232.23", "creditcard": {"number": "4232454678667866","expiration": "04/22","nameOnCard": "Jane G Doe"}, "billingaddress": "123 Anystreet, Pueblo, CO 32213", "name": "Jane Doe"}
    
        private String orderid = null;
        private String total = "10";
        private String creditcard = "1234567890";
        private String billingaddress = null;
        private String name = null;
    
    
        public Order() {
        }
    
        public Order(String orderid, String total, String creditcard, String billingaddress, String name) {
            this.orderid = orderid;
            this.total = total;
            this.creditcard = creditcard;
            this.billingaddress = billingaddress;
            this.name = name;
        }
    
        public String getorderid() {
            return orderid;
        }
    
        public void setorderid(String orderid) {
            this.orderid = orderid;
        }
    
        public String getTotal() {
            return total;
        }
    
        public void setTotal(String total) {
            this.total = total;
        }
    
        public String getcreditcard() {
            return creditcard;
        }
    
        public void setcreditcard(String creditcard) {
            this.creditcard = creditcard;
        }
    
        public String getbillingaddress() {
            return billingaddress;
        }
    
        public void setbillingaddress(String billingaddress) {
            this.billingaddress = billingaddress;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    }
