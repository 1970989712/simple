var token = sessionStorage.getItem('token');
var user = sessionStorage.getItem('user');
$.ajaxSetup({
    dataType: "json",
    cache: false,
    xhrFields: {
        withCredentials: true
    },
    //请求发送前encodeURIComponent处理一下url的参数
    beforeSend: function (xhr, setting) {
        if (user) {
            var oldUrl = setting.url;
            if (oldUrl) {
                var newUrl = "/zjl" + oldUrl;
                setting.url = newUrl;
            }
            xhr.setRequestHeader("Authorization", token);  // 请求发起前在头部附加token  
        } else {
            window.location = "login.html";   //没有用户信息跳转登录页面
        }
    },
    complete: function (xhr) {
        //token过期，则跳转到登录页面
        if (xhr.responseText == -815) {
            sessionStorage.removeItem("user");    //token失效清除页面缓存token,用户信息
            sessionStorage.removeItem("token");
            layer.msg('你的账户已在别处登录', {
                time: 20000, //20s后自动关闭
                btn: ['确定', '取消'] //按钮
            }, function () {
                window.location = "login.html";
            }, function () {
                window.location = "login.html";
            });
        }
    }
});