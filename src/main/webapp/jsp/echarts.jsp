<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
    <script type="text/javascript">
        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-2cee62d1b57b4c5c9d23032665b66aef", //替换为您的应用appkey
        });
        goEasy.subscribe({
            channel: "cmfz", //替换为您自己的channel
            onMessage: function (message) {
                var data = JSON.parse(message.content);
                myChart.setOption({
                    series: [{
                        name: '男',
                        // line 折线形式(反应趋势) bar 柱状图(统计数量)  pie 饼状图(反应比例)
                        type: 'bar',
                        data: data.man
                    },{
                        name: '女',
                        type: 'bar',
                        data: data.women
                    }]
                });
            }
        });
    </script>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 1000px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            // 标题名称
            text: '持明法洲用户注册趋势图',
            //subtext: '今天明天后天'
        },
        //工具提示
        tooltip: {},
        //图例
        legend: {
            data:['男','女']
        },
        //X轴
        xAxis: {
            data: ["今天注册的用户数量","一周内注册的用户数量","一个月内注册的用户数量","一年内注册的用户数量"]
        },
        // Y轴展示的内容 自适应的Y轴数据
        yAxis: {}
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    $.ajax({
        url:"${pageContext.request.contextPath}/user/queryBySexAndDay",
        type:"get",
        datatype:"json",
        success:function (data) {
            myChart.setOption({
                series: [{
                    name: '男',
                    // line 折线形式(反应趋势) bar 柱状图(统计数量)  pie 饼状图(反应比例)
                    type: 'bar',
                    data: data.man
                },{
                    name: '女',
                    type: 'bar',
                    data: data.women
                }]
            });
        }
    })
</script>

</body>
