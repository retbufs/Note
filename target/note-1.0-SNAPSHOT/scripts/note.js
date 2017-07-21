$(function(){
    onloadbook();
    //加载笔记内容
    $("#notes").on("click", "li", onloadContent);
    //保存笔记
    $("#save_note").click(save_note);
    //关闭弹窗事件
    $("#can").on("click",".cancle ,.close",closeAlert);
    //添加笔记本弹窗
    $("#add_notebook").click(alertAddBook);
    //确认添加笔记本
    $("#can").on("click",".sure-book", addBooks);
    //添加笔记弹窗
    $("#add_note").click(alertAddNote);
    //确认添加笔记
    $("#can").on("click",".sure-note", addNote);
    //加载笔记列表
    $("#book_ul").on("click","li", onloadnote);
    //弹出菜单
    $("#notes").on("click",".btn_slide_down",dowmMenu);
    //点击任意位置关闭菜单
    $("body").click(function () {
        $("#notes div").hide();
    });
    //弹出删除对话框
    // $("#notes").off("click");
    $("#notes").on("click",".btn_delete",alertdeleteNote);
    //确认删除
    $("#can").on("click",".sure-delete",deleteOk);
    //弹出移动对话框
    $("#notes").on("click",".btn_move",alertMoveNote);
    //移动笔记
    $("#can").on("click",".sure-move", moveNote);
    //分享笔记btn_sh
    $("#notes").on("click",".btn_share",ShareNote);
    //搜索笔记
    // $(".btn-collapse-search").click(search);
    //输入框回车事件
    $("#search_note").keydown(function(event){
        var code = event.keyCode; //按键
        var page =1; //默认为1
        var keys = $("#search_note").val();
        if(code == 13){ //回车按键
            //发送ajax请求
            keyword(keys,page)
        }
    });
    //回收站
    $("#rollback_button").click(recycle);
    //回收弹出确认
    $("#recycle").on("click",".btn_delete",function(){
        //加载alert
        $('#can').load('./../alert/alert_delete_rollback.html',function(){
            $(".opacity_bg").show();
        });
    });
    //加载回收站内容
    $("#recycle").on("click", "li", recycleContent);
    //确认清除
    $("#can").on("click",".sure-codele",deleRecycle);
    //恢复笔记
    $("#recycle").on("click",".btn_replay",function(){
        //加载alert
        $('#can').load('./../alert/alert_replay.html',function(){
            $(".opacity_bg").show();
            replaySelect();
        });
    });
    //确定恢复
    $("#can").on("click",".sure-recycle",recover);

});

//关闭弹窗
function closeAlert(){
    $("#can").empty();
    $('.opacity_bg').hide();
};
//笔记本列
function  onloadbook() {
    var userId = getCookie("uuid");
    if(userId == null){
        window.location.href="log_in.html"
    }else{
        $.ajax({
            url:base_path+"/book/loadbook.do",
            type:"post",
            data:{"userId":userId},
            dataType:"json",
            success:function (data) {
                $('#book_ul').html("");
                if(data.status == 0){
                    console.log("uuId异常");
                }
                if(data.status == 1){
                    var book= data.obj;
                    for(var i=0;i<book.length;i++){
                        var bookId=book[i].cn_notebook_id;
                        var bookName =book[i].cn_notebook_name;
                        createBook(bookName,bookId)
                    }
                }
            },error:function (error) {

            }
        });
    }
};
//创建bookLi
function createBook(bookName,bookId){
    var  sli = "";
    sli += '<li >';// class="checked"
    sli += '<a > <i class="fa fa-book" title="online" rel="tooltip-bottom">';
    sli += '</i> '+bookName;
    sli += '</a>';
    sli += '</li>';
    var $li = $(sli);
    $li.data("bookid",bookId);
    $("#book_ul").append($li);
};

//加载笔记列笔记
function onloadnote() {
     $("#pc_part_2").show();
     $("#pc_part_4").hide();
    $("#pc_part_6").hide();
    $("#pc_part_7").hide();
    $("#pc_part_8").hide();
    //设置选中效果
    $("#book_ul a ").removeClass("checked");
    $(this).find("a").addClass("checked");
    var bookId = $(this).data("bookid");
    $.ajax({
        url:base_path+"note/onloadnote.do",
        type:"post",
        data:{"bookId":bookId},
        dataType:"json",
        success:function(data){
            var note = data.obj;
            $("#notes").empty();
            for(var i = 0 ;i<note.length;i++){
                var title = title = note[i].cn_note_title;
                var  noteid = note[i].cn_note_id;
                createNoteLi(title,noteid)
            }
        }
    });
};

//创建NOTElI
function createNoteLi(noteName,noteID){
    var  lis = "";
    lis += '<li class="online">';
    lis +=  '<a >';//class="checked"
    lis +=     ' <i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' ;
    lis +=noteName;
    lis +=      '<button type="button" class="btn btn-default btn-xs btn_position  btn_slide_down">' ;
    lis +=      '<i class="fa fa-chevron-down"></i></button>';
    lis +=   '</a>';
    lis +=     '<div class="note_menu" tabindex="-1">';
    lis +=  '<dl>';
    lis += '<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
    lis +=  '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
    lis +=  ' <dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
    lis += ' </dl>';
    lis += ' </div>';
    lis += ' </li>';
    var $li =$(lis);
    $li.data("noteid",noteID);
    $("#notes").append($li);
};

//笔记内容

function onloadContent() {
    //    $("#notle input").attr("value").remove();
    $("#notes a ").removeClass("checked");
    $("#myEditor").empty();
    $(this).find("a").addClass("checked");
    var noteid = $(this).data("noteid");
    $.ajax({
        url: base_path + 'note/onloadnots.do',
        data: {'noteid': noteid},
        dataType: 'json',
        type: 'post',
        success: function (data) {
            var note = data.obj;
            // 设置标题
            var title = note.cn_note_title;
            // 编辑器内容
            var body = note.cn_note_body;
           content(title,body)
        },
        error: function () {
            alert('系统繁忙')
        }
    });
};
//笔记内容
function content(title,body){
    $("#input_note_title").val(title);
    um.setContent(body);
}
//保存笔记
function save_note() {
    var title = $("#input_note_title").val().trim();
    var body  =um.getContent();
    var noteid =$('#notes  a.checked').parent("li").data("noteid");
    $.ajax({
        url:base_path+"/note/save_note.do",
        data:{'noteid':noteid,'title':title,'body':body},
        dataType:"json",
        type:"post",
        success:function (data) {
            var status = data.status;
            if(status == 0){
                //ok
                alert(data.msg);
            }else if(status == 2){
                alert(data.msg);
            }
        }
    });
};
//弹出新增笔记本
function alertAddBook(){
    $('#can').load('./../alert/alert_notebook.html',function() {
        $(".opacity_bg").show()
        //弹出点击后获取
        // $(this).off("click");
    } );
};
//添加笔记ben操作
function addBooks() {
    //获取用户id
    var userid =getCookie("uuid");
    //获取输入的内容
    var bookname = $("#input_notebook").val().trim();
    var ok = true;  //判断是否符合
    if(bookname == ""){
        ok= false;
        alert("笔记本名称不能为空");
    }
    if(userid == null){
        ok = false;
        window.location="log_in.html";
    }
    //符合则调用发送ajax请求
    if(ok){
        $.ajax({
            url:base_path+"/book/addbook.do",
            type:"post",
            data:{"userid":userid,"bookname":bookname},
            dataType:"json",
            success:function (data) {
                var book = data.obj;
                var status = data.status;
                var bookid = book.cn_notebook_id;
                //关闭窗口
                closeAlert();
                if(status == 1){
                    //刷新列表/添加bookli
                    createBook(bookname,bookid);
                }else{
                    alert(data.msg);
                }
            }
        });
    }

}

//弹出添加笔记
function alertAddNote(){
    $('#can').load('./../alert/alert_note.html',function(){
        // $(this).off("click");
        $(".opacity_bg").show();
    })
}
//添加笔记操作
function   addNote() {
    var bookid=$('#book_ul  a.checked').parent("li").data("bookid");
    var note= $("#input_note").val();
    var flag = true;
    if(bookid == null){
        flag = false;
        alert("请选择笔记本后在创建");
    }
    if(note == null){
        flag == false;
        alert("名称不能为空")
    }
    if(flag){
        $.ajax({
            url: base_path + "note/addnote.do",
            type: "post",
            data: {"notename": note, "bookid": bookid},
            dataType: "json",
            success: function (data) {
                var status = data.status;
                closeAlert();
                //关闭窗口
                //获取笔记
                var noteid = data.obj;
                if(data.status == 1){
                    createNoteLi(note,noteid);
                } else {
                    //NO
                    alert(data.msg+"异常");
                }
            }
        });
    }
};

//笔记编辑
/**
 * 移动 删除  分享
 */
//显示按钮
function dowmMenu() {

        var $menu =  $(this).parent().next();
        $("#notes div").hide();
        $menu.slideDown(1000);
        $("#notes a").removeClass("checked");
        $(this).parent("a").addClass("checked");
        //阻止事件li body冒泡
        // $("#notes").on("click",".btn_delete",alertDeleteNote());
        return false;
    };

//删除操作
//弹出删除框
// function deleteNote(){
//     $("#notes").on("click",'.btn_delete',alertdeleteNote());
// }
function alertdeleteNote(){
    $('#can').load('./../alert/alert_delete_note.html',function(){
        $(".opacity_bg").show();
    });
};
//确认删除操作
function deleteOk(){
    //获取笔记ID
    var noteId =$("#notes a.checked").parent("li").data("noteid");
    //获取用户uid
    var uid = getCookie("uuid");
    var ok = true;
    if(uid == null){
        ok = false;
        window.location ="log_in.html";
    }
    if(noteId == null){
        ok = false;
        alert("请选择笔记在操作");
    }
    if(ok){
     $.ajax({
         url:base_path+"note/delenete.do",
         data:{"noteid":noteId},
         type:"post",
         dataType:"JSON",
         success:function(data){
         closeAlert();
          var status = data.status;
          var msg = data.msg;
          console.log("跑路了");
          if(status == 1){
              // 0 OK    1:ERROR
              alert("删除成功");
              //移除列表
              $('#notes  a.checked').parent("li").remove();
          } else{
              alert(msg);
          }
         }
     });
    }
}
//弹出移动对话框
function alertMoveNote(){
    $('#can').load('./../alert/alert_move.html',function(){
        $(".opacity_bg").show();
        selectMove();
    });
}
//确认点击笔记按钮
function moveNote(){
    //获取所有的book
   //获取笔记id
    var $li = $("#notes a.checked").parent("li");
    var notesid = $li.data("noteid");
    //获取bookId
    var bookid = $("#moveSelect").val();
    //发送请求参数
    $.ajax({
        url:base_path+"note/move.do",
        type:"post",
        dataType:"json",
        data:{"bookid":bookid,"noteid":notesid},
        success:function (data) {
            var status = data.status;
            var msg = data.msg;
                alert(msg)
         $('#notes  a.checked').parent("li").remove();
        },error:function () {
            alert("转移异常")
        }
    });
}
//获取所有的笔记本列
function selectMove(){
    //获取所有的li
    var books = $("#book_ul li");
    //遍历books
    for(var i = 0;i<books.length;i++){
        var $li = $(books[i]);

        var bookid = $li.data("bookid");//获取笔记本id
        var bookname = $li.text().trim();//获取笔记本名
        var op = ""
        //创建option对象
             op +='<option value="' + bookid +
                 '">' ;
             op +=bookname;
             op += '</option>';
       $("#moveSelect").append(op);
      $("#replaySelect").append(op);
    }

}
//分享笔记
function  ShareNote() {
    //获取分享笔记的id
    var noteid = $("#notes a.checked").parent("li").data("noteid");
    if(noteid == ""){
        alert("请选择要分享的笔记");
    }
    if(noteid !=""){
        $.ajax({
            url:base_path+"note/share.do",
            type:"get",
            data:{"noteid":noteid},
            success:function (data) {
                var share = data.obj;
                var shareUrl = base_path+"note/duang.do?share="+share;
                alert("分享成功:"+shareUrl);
            },error:function () {
                alert("分享失败");
            }
        });
    }
}
//回车
function keyword(key,page){
    $("#pc_part_2").hide();
    $("#pc_part_4").hide();
    $("#pc_part_6").show();
    $("#pc_part_7").hide();
    $("#pc_part_8").hide();
        //发送Ajax请求
        $.ajax({
            url:base_path+"note/search.do",
            type:"post",
            dataType:"json ",
            data:{"share":key,"page":page},
            success:function(data){
                var share = data.obj;
                if(share == null){
                    alert("未查询到");
                }
                for(var i = 0 ; i<share.length;i++){
                    var title = share[i].cn_share_title;
                    var body = share[i].cn_share_body;
                    var noteid = share[i].cn_note_id;
                    //添加到里Li列表
                    //清空notes
                    $("#notes").empty();
                    createNoteLi(title,noteid);


                }
            },
            error:function(){

            }
        });
}
//回收站
function recycle(){
    //pc_part_2 全部
    //搜索笔记：pc_part_6
    //收藏  pc_part_7
    //活动笔记
    $("#pc_part_2").hide(1000);
    $("#pc_part_4").show();
    $("#recycle").empty();
    $.ajax({
        url:base_path+"note/recycle.do",
        type:"post",
        dataType:"json",
        success:function (data) {
            var note = data.obj;
        for(var i = 0;i<note.length;i++){
            var noteid = note[i].cn_note_id;
            var title = note[i].cn_note_title;
            //加载回收站中的li
            creaRecycle(noteid,title)
        }
        }
    });
}
//加载回收li
//recycle
function creaRecycle(noteid,title){
    var  x = "";
        x += '<li class="disable"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
        x +=  title;
        x +=  '<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>';
        x +=   '<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay">';
        x +=  '  <i class="fa fa-reply"></i></button></a></li>';
        $x = $(x);
        $x.data("noteid",noteid);
        $("#recycle").append($x);
}
//确认删除
function deleRecycle(){
      //获取noteid
    var $li = $("#recycle a.checked").parent("li");
    var noteid = $li.data("noteid");

       //发送删除请求
    $.ajax({
        url:base_path+"note/rcedele.do",
        type:"post",
        dataType:"json",
        data:{"noteid":noteid},
        success:function(data){
            closeAlert();
            var status = data.status;
            var msg = data.msg;
            if(status == 1){
                alert("删除成功");
                $('#recycle  a.checked').parent("li").remove();
            }else
                {
                alert(msg);
            }
    }
    });
}
//加载需要回收站笔记内容
function recycleContent(){
    $("#recycle a").removeClass("checked");
    $(this).find("a").addClass("checked");
    var $li = $("#recycle a.checked").parent("li");
    var noteid = $li.data("noteid");

    $.ajax({
        url:base_path+"note/rcecontent.do",
        type:"post",
        dataType:"json",
        data:{"noteid":noteid},
        success:function (data) {
            var note = data.obj;

                var title = note.cn_note_title;
                var body = note.cn_note_body;
                content(title,body)
            }
    });
}
//恢复
function  recover(){
    //获取要恢复笔记id
    var $li = $("#recycle a.checked").parent("li");
    var noteid = $li.data("noteid");
    var bookid= $("#replaySelect").val();
    console.log(bookid);
    $.ajax({
        url:base_path+"note/recover.do",
        type:"post",
        dataType:"json",
        data:{"noteid":noteid,"bookid":bookid},
        success:function (data) {
            closeAlert();
                $('#recycle  a.checked').parent("li").remove();
        }
    });
}

//获取所有的笔记本列
function replaySelect(){
    //获取所有的li
    var books = $("#book_ul li");
    //遍历books
    for(var i = 0;i<books.length;i++){
        var $li = $(books[i]);
        var bookid = $li.data("bookid");//获取笔记本id
        var bookname = $li.text().trim();//获取笔记本名
        var op = ""
        //创建option对象
        op +='<option value="' + bookid +
            '">' ;
        op +=bookname;
        op += '</option>';
        $("#replaySelect").append(op);
    }
}
//函数事件

