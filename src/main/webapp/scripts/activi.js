$(function () {
    $.ajax({
        url:base_path+'activity/onactivity.do',
        dataType:'json',
        success:function(data){
            var activity = data.obj;
            var status = data.status;
            $("#col_0").html("");
            $("#col_1").html("");
            $("#col_2").html("");
            if(status == 0){
                for(var i = 0;i<activity.length;i++){
                    var c = "";
                  c += '<div class="col-sm-4" id="col_0">';
                  c +=  '<a href="javascript:void(0)">'+activity[i].cn_activity_title+'</a>';
                  c += '</div>';
                  c +=  '<div class="col-sm-4" id="col_1">';
                  c += '<a href="javascript:void(0)">'+activity[i].cn_activity_body+'</a>';
                  c += '</div>';
                  c +='<div class="col-sm-4" id="col_3">';
                  c += '<a href="javascript:void(0)">'+activity[i].cn_activity_end_time+'</a>';
                  c += '</div>';
                 $("#activitys").append(c);
                }
            }
        },error:function(){

        }
    });
});
