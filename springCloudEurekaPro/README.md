### 基于spring cloud Eureka注册服务实例
* http://127.0.0.1:8281/api/order?var1=224455   接口地址
* ORDER-SERVICE   向Eureka注册的服务名
* http://ORDER-SERVICE/api/order?var1="+orderId   通过Eureka调用地址
