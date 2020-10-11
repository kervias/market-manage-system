window.bool_edit = false;
//一般直接写在一个js文件中

window.transfer_data = function(data){
    window.render_data = data;
    window.render_page();
};


layui.use(['layer', 'form', 'jquery'], function(){
    var layer = layui.layer
        ,form = layui.form;
    var $ = layui.jquery;

    window.render_page = function(){

        window.init_render_data = function (){
            data = window.render_data;
            $('#category_id').val(data.id);
            $('#category_name').val(data.name);
            //document.getElementById("category_name").value = data.name;
        };

        init_render_data();

        form.on('submit(formUpdateCategory)', function(data){
            $.ajax({
                url: '/admin/category',
                data: JSON.stringify(data.field),
                method: 'PUT',
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success: function (rdata) {
                    if(rdata['code'] === 0){
                        layer.msg("商品类别信息修改成功！",{
                            icon: 1,
                            time: 1000 //2秒关闭（如果不配置，默认是3秒）
                        }, function(){
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭
                        });
                        window.bool_edit = true;
                    }else{
                        layer.msg("商品类别信息修改失败！"+rdata['msg']);
                    }
                },
                error: function (msg) {
                    layer.msg("服务器无响应 msg:"+msg);
                }

            });

            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
    }
});