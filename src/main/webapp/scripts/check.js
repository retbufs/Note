$(function (){
    $("#login").click(checkLog);
    $("#regist_button").click(checkReg);

});
function checkLog() {
    var names = $("#count").val().trim();
    var pass = $("#password").val().trim();
    $("#nameMsg").html("");
    $("#passMsg").html("");
    var msg = true;
    if(names == ""){
        msg = false;
        $("#nameMsg").html("用户名不能为空!");
    }
    if(pass == ""){
        msg = false;
        $("#passMsg").html("密码不能为空");
    }
    msg = true;
    if(msg){
      $.ajax({
          url:'http://localhost:8080/user/checklog.do',
          data:{"name":names,"password":pass},
          dataType:'json',
          type:'post',
          success:function (data) {
              var status = data.status;

              var temp = data.data;
              var  user = JSON.parse(temp)
              console.log(user,data);
              if(status == 1){
                  //正确，并跳转页面.
                  addCookie("uuid",user.cn_user_id);
                  addCookie("uname",user.cn_user_name);
                  window.open("http://localhost:8080/user/edit.do","_self");
              }else if(status == 2){
                  //用户名错误
                 $("#nameMsg").html(data.msg).show();
              }else if (status == 3){
                  //密码错误
                  $("#passMsg").html(data.msg).show();
              }
          }
      });
    }
}
function checkReg(){
   var password = $("#regist_password").val().trim();
   var username = $("#regist_username").val().trim();
   var nickname = $("#nickname").val().trim();
   var fianlpass = $("#final_password").val().trim();
   var temp = true;
   $('#reg_name').html("");
    $('#reg_pass').html("");
    $('#reg_final').html("");

    if(username.length <4){
        temp = false;
        $('#reg_name').html("用户名不能低于4位").show();
    }
    if(username ==""){
       temp = false;
       $('#reg_name').html("用户名不能为空").show();
   }
    if(password ==""){
        temp = false;
        $('#reg_pass').html("密码不能为空").show();
    }
    if(password.length<6){
        temp = false;
        $('#reg_final').html("长度不符>6").show();
    }
      if(fianlpass != password){
        temp = false;
        $('#reg_final').html("密码不一致").show();
    }

   if(temp){
       $.ajax({
           // "http://localhost:8080/checkreg.do"
           url:"http://localhost:8080/checkreg.do",
           data:{'name':username,'nick':nickname,'password':password},
           type:"post",
           dataType:"json",
           success:function(data){
               var status  = data.status;
               if(status == 2){
                   $("#reg_name").html(data.msg).show()
               }
               if(status == 3){
                   $("reg_pass").html(data.msg).show()
               }
               if(status == 1){
                   alert(data.msg);
                   //跳转登入界面
                   window.open("login.do","_self")
               }
               if(status == 0){
                   alert(data.msg);
               }
           },
           error:function(data){
               alert(data.msg);
           }
       });
   }
}
