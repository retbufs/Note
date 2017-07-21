$(function(){
    $("#back").click(function(){
        window.location="/user/edit.do";
    });
    $("#last_password").change(function(){
        var lastPass = $("#last_password").val();
        var flag = true;
        if(lastPass == ""){
            flag = false;
            $("#last-pass").html("请输入原始密码").show();
        }
    });
    $("#changePassword").click(function(){
        $("#last-pass").html("");
        $("#new-pass").html("");
        $("#final-pass").html("");
        var lastPass = $("#last_password").val();
        var newPass = $("#new_password").val();
        var finalPass = $("#final_password").val();
        var uuid = getCookie("uuid");
        var flag = true;
        if(lastPass == newPass){
            $("#last-pass").html("").show();
        }
        if(newPass.length >8&&20){
            flag = false;
            $("#new-pass").html("密码长度需要最少8位>20").show();
        }
        if(newPass == finalPass){
            flag = false;
            $("#new-pass").html("密码输入不一致").show();
            $("#final-pass").html("密码输入不一致").show();
        }
        if(flag){
            $.ajax({
                url:base_path+"user/changpass.do",
                data:{"uid":uuid,"newPass":newPass,"lastPass":lastPass},
                type:"post",
                dataType:"json",
                success:function(data){
                    alert("ok");

                },
                error:function () {

                }
            });
        }
    });
});
