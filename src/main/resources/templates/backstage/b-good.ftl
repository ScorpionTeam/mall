<table class="easyui-datagrid" style="width:100%;height:100%"
       data-options="rownumbers:true,singleSelect:true,url:'datagrid_data1.json',method:'get',toolbar:'#toolbar'">
    <thead>
    <tr>
        <th data-options="field:'itemid',width:80,align:'center'">名称</th>
        <th data-options="field:'productid',width:100,align:'center'">价格</th>
        <th data-options="field:'listprice',width:80,align:'center'">促销价</th>
        <th data-options="field:'unitcost',width:80,align:'center'">销量</th>
        <th data-options="field:'attr1',width:240,align:'center'">库存</th>
        <th data-options="field:'itemid',width:80,align:'center'">热销</th>
        <th data-options="field:'itemid',width:80,align:'center'">新品</th>
        <th data-options="field:'itemid',width:80,align:'center'">折扣</th>
        <th data-options="field:'status',width:60,align:'center'">上/下(架)</th>
    </tr>
    </thead>
</table>
<div id="toolbar" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a id="add" class="easyui-linkbutton" iconCls="icon-add" plain="true"
           onclick="$('#addModal').window('open')"></a>
        <a id="edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
    </div>
    <div>
        商品名称:
        <input class="easyui-textbox" name="name" style="width:200px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
    </div>
</div>

<div id="addModal" class="easyui-dialog" title="新增商品" style="width: 600px;height: 500px;"
     data-options="iconCls:'icon-save',closed:true,modal:true,top:40,buttons:[{
				text:'保存',
				handler:function(){
				    var good={};


				}
			},{
				text:'取消',
				handler:function(){}
			}]">
    <div style="padding:30px 60px">
        <form id="goodForm" class="easyui-form" method="post" data-options="novalidate:true">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="goodName" style="width:100%" data-options="label:'名称:',required:true">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="price" style="width:100%"
                       data-options="label:'价格:',required:true">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="promotion" style="width:100%"
                       data-options="label:'促销价:',required:true">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="saleVolume" style="width:100%"
                       data-options="label:'销量:',required:true">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="stock" style="width:100%"
                       data-options="label:'库存:',required:true">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="isHot" style="width:100%"
                       data-options="label:'热销:',required:true">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="isNew" style="width:100%"
                       data-options="label:'新品:',required:true">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="discount" style="width:100%;height:60px"
                       data-options="label:'折扣:',multiline:true">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="isOnSale" style="width:100%;height:60px"
                       data-options="label:'上/下(架):',multiline:true">
            </div>
        </form>
    </div>
</div>

<script>
    $(function () {
        $('#add').bind('click', function () {
            console.log("触发点击事件...");
        })

        function addGood(){

        }
    })
</script>