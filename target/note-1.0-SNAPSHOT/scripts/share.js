 function getUrlParam(name) { //获取url参数
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null)
    return unescape(r[2]);
    return null; //返回参数值
}
$(function(){
       var share = getUrlParam("share");
       $.ajax({
           type:"post",
           dataType:"json",
           url:base_path+"note/shareurl.do",
           data:{"share":share},
           success:function (data) {
               var note = data.obj;
               var body = note.cn_share_body;
               var title = note.cn_share_title;
               var ti = "";
               ti += '<h3 style="text-align: center">' +title+
                   '</h3>'
              $(".duangs").append(ti+body)
           }
       });

});
