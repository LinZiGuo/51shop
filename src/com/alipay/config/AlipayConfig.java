package com.alipay.config;

public class AlipayConfig {
	// 商户appid
	public static String APPID = "2016091100485712";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCLoNT/M6+pTQa0JvfkHoMRDZLyAHx0TD45xe85PVcITJKDNLYhRHVd2CP1U0K2nIXP1jEQrvGO3zfTvEkBBKkeMZ5Ea7u0WbBz/E2QtyTlivZBIzCq/QaVuj99G95haY/OEsXCOn0a4tKNUAG86IU4Eql8vzVoppu0rqHMYpZaInU/aeudbAtg4I2PBEUmB0uozbv35QTXF7gJvVm9fB69BFBa/gcDWqAv7OvkM2qgaqaL/5e8VIwO94o8j5PCN6F76WyMOQlsHYVjZgIat6/re8t+jYR3Erwa3aDavSSrS8sEVrj1ACWMzsQuIf5l4sxgz0CnhDjzocp1ju5hNwiHAgMBAAECggEAGVWGMAO9xJcgN0G2yqIqn8Z5ugUSj7thsvPR8u3JQ8047Ct9PE4CiZN0+scvnJiLwukXH/LOC2E+7OEF0fR5huvzznBJr3ByjqjhnfdM74UmJy1uYCfmsPYJHPcYg0429Gn9jb81AdLT5yX0GsWJnmHsdKDw3NsV91OUMdRVeWbv8ML1vmyQAOv1OyT9CLI2abPUJJ6jKmbcsCg65UjsKTscFTCRUvROUEfAvGYSCJlOfwvqWXirX5pagbCeRpTYOsV2yUV35q3KjNP5am7qzC0QbJTnLl+bhYJjKpRRH40GGuBW1RlLk+w+aw2xkpuoxUkzlF/teEeRwYYsr0ea0QKBgQDXsSd2Tb3ivwqgEFBkg6hIL+QwWwyyEvvBybjvydudDd0CuyyvgSa06a75igIaPro/cmIinFbsjCZuNlNViUGyCDFLs/HGwJOvGp+lDYEAAlubAQozafvaO9H4aU7UC+ZP072wxDAKFhBESwFeSgBAN9x0rIzqJEDn+Obsjk54SQKBgQCluLz9Yxe/qx2NEJlkonk2PUAjS6oP9gi8FB8hTVd+FeezO/Pn+kyFUo4ZL+oAQhlPmryJZZfcAFYMSSp7gs1bg4cuXuScgcdtH7U+Jh2JVDEepoS4mWBMTr1HPapB/GBi4shm3YPQ3I7ezTrrlSx4NC/mD5nwvvN5YYKFVYSaTwKBgCIVSFsVZdASyCcK7Ok1NL5TcweBG7x363hv291oVF+49RedZC5HhkpIMvITsMlc6ClmdcNqWQIAE4KO6xQ1IEB1svR3dtklTTD6UAfQ3nWV6cvQmkANm1Kc6U0Ltv0i9D3hA7SPC9hMLD3ebFTt9KQxL5DwQ0QsfmUpm7P9uMChAoGAF1g+xzL+VXG1Z/m+of6BNLLMJiyyeC0gbd4nxHkwqgJL8WOwqC0KOn22prWpMQAesKgLhw9YpRUnCfQEmAGFDC8rC80yRxnBDg45adfUsVO616Z9a8OvXRB6eV6mbUUwDr4DnPn3Z6SqO5iNhEIvUX+If9ASz2cWxTKHQGEbnOECgYEApTfJborx05u/65PddWuTFeBk5g0nWR8R6tEgkGWTgvQR+pe0dPWHfo5tZiD2XRqVB3KhqJ6ZXja90pP+xrSgJbrjeEerE6I5k3sXLLiCtgWA2UuNyuYLBtthSyYG08AE0WXUWU4OubkfiwjGsO+XFnue7ctz7fyS+K2TVEalO+Y=";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAybydYbbcUuG31fejEp32R76YB+uSS0HlMUAr9f8axnQSeboJMdj1PzCLe9WIkyEGTsIOolloACQO7seb6Tdj/MxfRMr9qrKrM3l7Ko6kxwu1kPjq0wagypSUrMcMgJAFcbgd+6MJkL/mFsnLsI1Cu248fOE3DmtKrT+VHXGh3HV1abuytopfgUjI60FE9LojGHhSe9exTlqjPw4OZMM1ylBZjlHd+JHWqxBAldLro4/EOlmCEhjCML/Vq/5f/WKm6uFF1zMsq/DXVzHctleB2EoIhjus8gpcLaHKZa9MLDtKYUfOKFdvbYcqnEqkUG3KijU+f0zrTzuLfY9KX8haOwIDAQAB";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/shop/front/notify_url.jsp";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
  	public static String return_url = "http://localhost:8080/shop/front/return_url.jsp";
	// 请求网关地址
	public static String URL = "https://openapi.alipaydev.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 日志记录目录
	public static String log_path = "/log";
	// 签名方式RSA2
	public static String SIGNTYPE = "RSA2";
}
