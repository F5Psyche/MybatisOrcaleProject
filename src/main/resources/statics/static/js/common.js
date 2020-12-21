function request(url,type,data,callback) {
    $.ajax({
        url: 'http://121.41.8.228:8080/'+url,
        // url: 'http://192.168.1.73:8080/'+url,
        type: type,
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: "application/json",
        success: function (res) {
            if(res.success){
                callback(res)
            }else {
                alert(res.resultDes)
            }
        },
        error:function () {
            alert("请求失败")
        }
    })
}

function requestPam(url,type,data,callback) {
    $.ajax({
        url: 'http://121.41.8.228:8080/'+url,
        // url: 'http://192.168.1.73:8080/'+url,
        type: type,
        data: data,
        dataType: 'json',
        success: function (res) {
            if(res.success){
                callback(res)
            }else {
                alert(res.resultDes)
            }
        },
        error:function () {
            alert("请求失败")
        }
    })
}

function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    console.log(theRequest)
    return theRequest;
}
